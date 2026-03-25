package com.gerenciamento.imoveis.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gerenciamento.imoveis.config.JwtService;
import com.gerenciamento.imoveis.dto.AuthResponseDTO;
import com.gerenciamento.imoveis.dto.LoginRequestDTO;
import com.gerenciamento.imoveis.dto.RegisterRequestDTO;
import com.gerenciamento.imoveis.entity.Usuario;
import com.gerenciamento.imoveis.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

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
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        if (!passwordEncoder.matches(request.senha(), usuario.getSenha())) {
            throw new RuntimeException("Senha inválida");
        }

        String token = jwtService.gerarToken(usuario.getEmail());

        return new AuthResponseDTO(token);
    }
}
