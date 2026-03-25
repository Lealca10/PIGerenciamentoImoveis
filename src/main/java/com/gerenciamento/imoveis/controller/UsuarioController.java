package com.gerenciamento.imoveis.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.imoveis.dto.AtualizarUsuarioDTO;
import com.gerenciamento.imoveis.dto.UsuarioDTO;
import com.gerenciamento.imoveis.service.UsuarioService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService service;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public UsuarioDTO buscarPorId(@PathVariable UUID id) {
        return service.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public UsuarioDTO atualizar(
            @PathVariable UUID id,
            @RequestBody AtualizarUsuarioDTO dto
    ) {
        return service.atualizar(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable UUID id) {
        service.deletar(id);
    }
}