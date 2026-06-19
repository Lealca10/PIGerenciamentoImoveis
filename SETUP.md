# Setup do Deploy Automático (GitHub Actions)

## Pré-requisitos

1. Repositório: `github.com/Lealca10/PIGerenciamentoImoveis`
2. Acesso AWS com permissão para criar IAM roles
3. Cluster EKS `bluvagas-production` já rodando

---

## Passo 1: Terraform — Provisionar recursos

No repositório TCS, aplicar o Terraform para criar:
- ECR repository `bluvagas-production/imoveis`
- GitHub OIDC provider + IAM role para GitHub Actions
- Access Entry no EKS para o role

A trust policy da role OIDC deve permitir exatamente:

```text
repo:Lealca10/PIGerenciamentoImoveis:*
```

```bash
cd infra/environments/production
terraform init
terraform plan -out=production.tfplan
terraform apply production.tfplan
```

Anote o output `github_actions_role_arn` (ex: `arn:aws:iam::533266955243:role/bluvagas-production-github-actions`).

---

## Passo 2: Criar o secret `imoveis-env` no cluster

```bash
kubectl create ns imoveis

kubectl create secret generic imoveis-env -n imoveis \
  --from-literal=POSTGRES_PASSWORD='senha_forte_aqui' \
  --from-literal=JWT_SECRET='$(openssl rand -base64 32)' \
  --from-literal=CLOUDFLARE_R2_ACCESS_KEY='sua_chave_cloudflare_r2' \
  --from-literal=CLOUDFLARE_R2_SECRET_KEY='sua_secret_cloudflare_r2' \
  --from-literal=CLOUDFLARE_R2_BUCKET='documents-images' \
  --from-literal=CLOUDFLARE_R2_ENDPOINT='https://seu_account.r2.cloudflarestorage.com' \
  --from-literal=CLOUDFLARE_R2_PUBLIC_URL='https://seu_domain.r2.dev'
```

O storage da aplicação usa a API compatível com S3 do Cloudflare R2. Não use
credenciais AWS nessas variáveis.

---

## Passo 3: Configurar GitHub Secret

No repositório `Lealca10/PIGerenciamentoImoveis`, ir em:

**Settings → Secrets and variables → Actions → New repository secret**

| Nome | Valor |
|---|---|
| `AWS_ROLE_ARN` | `arn:aws:iam::533266955243:role/bluvagas-production-github-actions` |

---

## Passo 4: Fazer o primeiro deploy

Duas opções:

### a) Manual (primeira vez)
```bash
kubectl apply -f k8s/namespace.yaml      # se não existir
kubectl apply -f k8s/serviceaccounts.yaml # se não existir
kubectl apply -f k8s/postgres.yaml        # somente bootstrap do banco
kubectl apply -f k8s/backend.yaml
kubectl apply -f k8s/ingress.yaml
```

O workflow não reaplica `k8s/postgres.yaml`. Mudanças no banco e no PVC devem
ser executadas manualmente, com backup e revisão prévia.

### b) Automático (GitHub Actions)
Após configurar o secret `AWS_ROLE_ARN`:
1. Dar push na branch `main` do repositório
2. Ou ir em **Actions → Deploy to EKS → Run workflow**

---

## Passo 5: DNS

Criar um registro CNAME na Cloudflare:

| Nome | Tipo | Valor |
|---|---|---|
| `imoveis.kpaweb.com.br` | CNAME | `<DNS name do ALB do bluvagas>` |

O DNS name do ALB pode ser encontrado no console do EC2 → Load Balancers, ou via:
```bash
kubectl get ingress -n bluvagas -o jsonpath='{.items[0].status.loadBalancer.ingress[0].hostname}'
```

---

## Fluxo do GitHub Action

```
Push na main (ou workflow_dispatch)
  ↓
Checkout do código
  ↓
Assume role IAM via OIDC
  ↓
Login no ECR
  ↓
Build & Push Docker image (tag: sha do commit + "latest")
  ↓
Update kubeconfig (EKS)
  ↓
kubectl apply do backend e ingress (com a nova imagem)
  ↓
Validação do rollout e do endpoint de health
```

---

## Arquivos do CI/CD

| Arquivo | Descrição |
|---|---|
| `.github/workflows/deploy.yml` | Workflow principal |
| `k8s/postgres.yaml` | Deployment + PVC + Service do PostgreSQL |
| `k8s/backend.yaml` | Deployment + Service do Spring Boot |
| `k8s/ingress.yaml` | Ingress ALB (mesmo grupo do TCS) |
