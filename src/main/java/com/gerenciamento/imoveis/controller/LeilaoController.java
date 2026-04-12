package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.LeilaoDTO;
import com.gerenciamento.imoveis.entity.Leilao;
import com.gerenciamento.imoveis.service.LeilaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leiloes")
@RequiredArgsConstructor
public class LeilaoController {

    private final LeilaoService service;

    @GetMapping
    public List<LeilaoDTO> listar() {
        return service.findAll().stream()
                .map(LeilaoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LeilaoDTO buscarPorId(@PathVariable String id) {
        Leilao leilao = service.findById(id).orElseThrow(() -> new RuntimeException("Leilão não encontrado"));
        return new LeilaoDTO(leilao);
    }

    @PostMapping
    public LeilaoDTO criar(@RequestBody Leilao leilao) {
        Leilao saved = service.save(leilao);
        return new LeilaoDTO(saved);
    }

    @PutMapping("/{id}")
    public LeilaoDTO atualizar(@PathVariable String id, @RequestBody Leilao leilao) {
        leilao.setId(id);
        Leilao saved = service.save(leilao);
        return new LeilaoDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}