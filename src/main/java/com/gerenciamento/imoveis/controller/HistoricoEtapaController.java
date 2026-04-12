package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.HistoricoEtapaDTO;
import com.gerenciamento.imoveis.entity.HistoricoEtapa;
import com.gerenciamento.imoveis.service.HistoricoEtapaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/historico-etapas")
@RequiredArgsConstructor
public class HistoricoEtapaController {

    private final HistoricoEtapaService service;

    @GetMapping
    public List<HistoricoEtapaDTO> listar() {
        return service.findAll().stream()
                .map(HistoricoEtapaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public HistoricoEtapaDTO buscarPorId(@PathVariable String id) {
        HistoricoEtapa historicoEtapa = service.findById(id).orElseThrow(() -> new RuntimeException("Histórico não encontrado"));
        return new HistoricoEtapaDTO(historicoEtapa);
    }

    @PostMapping
    public HistoricoEtapaDTO criar(@RequestBody HistoricoEtapa historicoEtapa) {
        HistoricoEtapa saved = service.save(historicoEtapa);
        return new HistoricoEtapaDTO(saved);
    }

    @PutMapping("/{id}")
    public HistoricoEtapaDTO atualizar(@PathVariable String id, @RequestBody HistoricoEtapa historicoEtapa) {
        historicoEtapa.setId(id);
        HistoricoEtapa saved = service.save(historicoEtapa);
        return new HistoricoEtapaDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}