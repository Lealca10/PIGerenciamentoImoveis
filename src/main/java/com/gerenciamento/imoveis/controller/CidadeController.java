package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.CidadeDTO;
import com.gerenciamento.imoveis.entity.Cidade;
import com.gerenciamento.imoveis.entity.Estado;
import com.gerenciamento.imoveis.service.CidadeService;
import com.gerenciamento.imoveis.service.EstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cidades")
@RequiredArgsConstructor
public class CidadeController {

    private final CidadeService service;
    private final EstadoService estadoService;

    @GetMapping
    public List<CidadeDTO> listar() {
        return service.findAll().stream()
                .map(CidadeDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CidadeDTO buscarPorId(@PathVariable String id) {
        Cidade cidade = service.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        return new CidadeDTO(cidade);
    }

    @GetMapping("/estado/{estadoId}")
    public List<CidadeDTO> buscarPorEstado(@PathVariable String estadoId) {
        Estado estado = estadoService.findById(estadoId).orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        return service.findByEstado(estado).stream()
                .map(CidadeDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public CidadeDTO criar(@RequestBody CidadeDTO dto) {
        Estado estado = estadoService.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
        Cidade cidade = new Cidade(dto.getNome(), estado);
        Cidade saved = service.save(cidade);
        return new CidadeDTO(saved);
    }

    @PutMapping("/{id}")
    public CidadeDTO atualizar(@PathVariable String id, @RequestBody CidadeDTO dto) {
        Cidade cidade = service.findById(id).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        cidade.setNome(dto.getNome());
        
        if (dto.getEstadoId() != null) {
            Estado estado = estadoService.findById(dto.getEstadoId())
                    .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
            cidade.setEstado(estado);
        }
        
        Cidade saved = service.save(cidade);
        return new CidadeDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}
