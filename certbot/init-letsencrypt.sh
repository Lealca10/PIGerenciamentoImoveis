#!/bin/bash
# Bootstrap do Let's Encrypt para o Nginx deste docker-compose.
#
# Pré-requisitos antes de rodar:
#   1. DNS do domínio (variável DOMAIN no .env) já apontando (registro A) para o IP público desta instância.
#   2. .env preenchido com DOMAIN e EMAIL reais (não os valores de exemplo).
#   3. Portas 80 e 443 liberadas no security group da instância.
#
# Uso: ./certbot/init-letsencrypt.sh

set -euo pipefail
cd "$(dirname "$0")/.."

if [ ! -f .env ]; then
  echo "Arquivo .env não encontrado. Copie .env.example para .env e preencha os valores antes de continuar."
  exit 1
fi

set -a
source .env
set +a

if [ -z "${DOMAIN:-}" ] || [ "$DOMAIN" = "example.com" ]; then
  echo "Defina DOMAIN no .env com o domínio real (não use o valor de exemplo 'example.com')."
  exit 1
fi

if [ -z "${EMAIL:-}" ] || [ "$EMAIL" = "admin@example.com" ]; then
  echo "Defina EMAIL no .env com um e-mail real para o Let's Encrypt."
  exit 1
fi

RSA_KEY_SIZE=4096
STAGING=${STAGING:-0} # mude para 1 para testar sem consumir o rate limit real do Let's Encrypt

echo "### Criando certificado dummy para $DOMAIN ..."
docker compose run --rm --entrypoint "\
  mkdir -p /etc/letsencrypt/live/$DOMAIN && \
  openssl req -x509 -nodes -newkey rsa:$RSA_KEY_SIZE -days 1 \
    -keyout '/etc/letsencrypt/live/$DOMAIN/privkey.pem' \
    -out '/etc/letsencrypt/live/$DOMAIN/fullchain.pem' \
    -subj '/CN=localhost'" certbot

echo "### Subindo o Nginx com o certificado dummy ..."
docker compose up -d nginx

echo "### Removendo certificado dummy ..."
docker compose run --rm --entrypoint "\
  rm -rf /etc/letsencrypt/live/$DOMAIN && \
  rm -rf /etc/letsencrypt/archive/$DOMAIN && \
  rm -rf /etc/letsencrypt/renewal/$DOMAIN.conf" certbot

echo "### Solicitando certificado real do Let's Encrypt para $DOMAIN ..."
STAGING_ARG=""
if [ "$STAGING" != "0" ]; then
  STAGING_ARG="--staging"
fi

docker compose run --rm --entrypoint "\
  certbot certonly --webroot -w /var/www/certbot \
    $STAGING_ARG \
    --email $EMAIL \
    -d $DOMAIN \
    --rsa-key-size $RSA_KEY_SIZE \
    --agree-tos \
    --no-eff-email \
    --force-renewal" certbot

echo "### Recarregando o Nginx com o certificado real ..."
docker compose exec nginx nginx -s reload

echo "Concluído. Certificado emitido para $DOMAIN."
