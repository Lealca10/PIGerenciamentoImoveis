package com.gerenciamento.imoveis.service;

import java.util.List;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.gerenciamento.imoveis.dto.AtualizarUsuarioDTO;
import com.gerenciamento.imoveis.dto.UsuarioDTO;
import com.gerenciamento.imoveis.entity.Usuario;
import com.gerenciamento.imoveis.repository.UsuarioRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repository;
    private final PasswordEncoder passwordEncoder;

    public List<UsuarioDTO> listarTodos() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public UsuarioDTO buscarPorId(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        return toDTO(usuario);
    }

    public UsuarioDTO atualizar(UUID id, AtualizarUsuarioDTO dto) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        usuario.setNome(dto.nome());

        if (dto.senha() != null && !dto.senha().isEmpty()) {
            usuario.setSenha(passwordEncoder.encode(dto.senha()));
        }

        usuario.setTipoUsuario(dto.tipoUsuario());

        repository.save(usuario);

        return toDTO(usuario);
    }

    public void deletar(UUID id) {
        Usuario usuario = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        repository.delete(usuario);
    }

    private UsuarioDTO toDTO(Usuario usuario) {
        return new UsuarioDTO(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getTipoUsuario()
        );
    }
}