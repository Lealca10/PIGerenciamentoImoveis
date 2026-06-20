package com.gerenciamento.imoveis.service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gerenciamento.imoveis.config.JwtService;
import com.gerenciamento.imoveis.exception.BusinessException;
import com.gerenciamento.imoveis.dto.AuthResponseDTO;
import com.gerenciamento.imoveis.dto.LoginRequestDTO;
import com.gerenciamento.imoveis.dto.RegisterRequestDTO;
import com.gerenciamento.imoveis.entity.PasswordResetCode;
import com.gerenciamento.imoveis.entity.Usuario;
import com.gerenciamento.imoveis.repository.PasswordResetCodeRepository;
import com.gerenciamento.imoveis.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private static final int CODIGO_EXPIRACAO_MINUTOS = 15;
    private static final SecureRandom RANDOM = new SecureRandom();

    private final UsuarioRepository repository;
    private final PasswordResetCodeRepository resetCodeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final EmailService emailService;

    public AuthResponseDTO register(RegisterRequestDTO request) {

        Usuario usuario = Usuario.builder()
                .id(UUID.randomUUID())
                .nome(request.nome())
                .email(request.email())
                .senha(passwordEncoder.encode(request.senha()))
                .tipoUsuario(request.tipoUsuario())
                .build();

        repository.save(usuario);

        String token = jwtService.gerarToken(usuario.getEmail());

        return new AuthResponseDTO(token);
    }

    public AuthResponseDTO login(LoginRequestDTO request) {

        Usuario usuario = repository.findByEmail(request.email())
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new BusinessException("Senha inválida");
        }

        String token = jwtService.gerarToken(usuario.getEmail());

        return new AuthResponseDTO(token);
    }

    public void forgotPassword(String email) {
        repository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        String codigo = gerarCodigo();
        LocalDateTime agora = LocalDateTime.now();

        PasswordResetCode resetCode = PasswordResetCode.builder()
                .id(UUID.randomUUID())
                .email(email)
                .codigo(codigo)
                .criadoEm(agora)
                .expiraEm(agora.plusMinutes(CODIGO_EXPIRACAO_MINUTOS))
                .usado(false)
                .build();

        resetCodeRepository.save(resetCode);
        emailService.enviarCodigoRecuperacao(email, codigo);
    }

    public void verifyResetCode(String email, String codigo) {
        buscarCodigoValido(email, codigo);
    }

    public void resetPassword(String email, String codigo, String novaSenha) {
        PasswordResetCode resetCode = buscarCodigoValido(email, codigo);

        Usuario usuario = repository.findByEmail(email)
                .orElseThrow(() -> new BusinessException("Usuário não encontrado"));

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        repository.save(usuario);

        resetCode.setUsado(true);
        resetCodeRepository.save(resetCode);
    }

    private PasswordResetCode buscarCodigoValido(String email, String codigo) {
        PasswordResetCode resetCode = resetCodeRepository
                .findFirstByEmailAndCodigoAndUsadoFalseOrderByCriadoEmDesc(email, codigo)
                .orElseThrow(() -> new BusinessException("Código inválido."));

        if (resetCode.getExpiraEm().isBefore(LocalDateTime.now())) {
            throw new BusinessException("Código expirado. Solicite um novo.");
        }

        return resetCode;
    }

    private String gerarCodigo() {
        int numero = RANDOM.nextInt(1_000_000);
        return String.format("%06d", numero);
    }
}
