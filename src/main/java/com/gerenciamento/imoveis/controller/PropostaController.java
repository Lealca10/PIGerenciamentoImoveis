package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.PropostaDTO;
import com.gerenciamento.imoveis.entity.Proposta;
import com.gerenciamento.imoveis.service.PropostaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/propostas")
@RequiredArgsConstructor
public class PropostaController {

    private final PropostaService service;

    @GetMapping
    public List<PropostaDTO> listar() {
        return service.findAll().stream()
                .map(PropostaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PropostaDTO buscarPorId(@PathVariable String id) {
        Proposta proposta = service.findById(id).orElseThrow(() -> new RuntimeException("Proposta não encontrada"));
        return new PropostaDTO(proposta);
    }

    @PostMapping
    public PropostaDTO criar(@RequestBody Proposta proposta) {
        Proposta saved = service.save(proposta);
        return new PropostaDTO(saved);
    }

    @PutMapping("/{id}")
    public PropostaDTO atualizar(@PathVariable String id, @RequestBody Proposta proposta) {
        proposta.setId(id);
        Proposta saved = service.save(proposta);
        return new PropostaDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}