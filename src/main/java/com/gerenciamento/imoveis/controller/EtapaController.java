package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.EtapaDTO;
import com.gerenciamento.imoveis.entity.Etapa;
import com.gerenciamento.imoveis.service.EtapaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/etapas")
@RequiredArgsConstructor
public class EtapaController {

    private final EtapaService service;

    @GetMapping
    public List<EtapaDTO> listar() {
        return service.findAll().stream()
                .map(EtapaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EtapaDTO buscarPorId(@PathVariable String id) {
        Etapa etapa = service.findById(id).orElseThrow(() -> new RuntimeException("Etapa não encontrada"));
        return new EtapaDTO(etapa);
    }

    @PostMapping
    public EtapaDTO criar(@RequestBody Etapa etapa) {
        Etapa saved = service.save(etapa);
        return new EtapaDTO(saved);
    }

    @PutMapping("/{id}")
    public EtapaDTO atualizar(@PathVariable String id, @RequestBody Etapa etapa) {
        etapa.setId(id);
        Etapa saved = service.save(etapa);
        return new EtapaDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}