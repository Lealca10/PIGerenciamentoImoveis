package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.EstadoDTO;
import com.gerenciamento.imoveis.entity.Estado;
import com.gerenciamento.imoveis.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoService service;

    @GetMapping
    public List<EstadoDTO> listar() {
        return service.findAll().stream()
                .map(EstadoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EstadoDTO buscarPorId(@PathVariable String id) {
        Estado estado = service.findById(id).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        return new EstadoDTO(estado);
    }

    @GetMapping("/sigla/{sigla}")
    public EstadoDTO buscarPorSigla(@PathVariable String sigla) {
        Estado estado = service.findBySigla(sigla).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        return new EstadoDTO(estado);
    }

    @PostMapping
    public EstadoDTO criar(@RequestBody EstadoDTO dto) {
        Estado estado = new Estado(dto.getSigla(), dto.getNome());
        Estado saved = service.save(estado);
        return new EstadoDTO(saved);
    }

    @PutMapping("/{id}")
    public EstadoDTO atualizar(@PathVariable String id, @RequestBody EstadoDTO dto) {
        Estado estado = service.findById(id).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        estado.setSigla(dto.getSigla());
        estado.setNome(dto.getNome());
        Estado saved = service.save(estado);
        return new EstadoDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}
