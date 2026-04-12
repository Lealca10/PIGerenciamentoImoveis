package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.DespesaDTO;
import com.gerenciamento.imoveis.entity.Despesa;
import com.gerenciamento.imoveis.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/despesas")
@RequiredArgsConstructor
public class DespesaController {

    private final DespesaService service;

    @GetMapping
    public List<DespesaDTO> listar() {
        return service.findAll().stream()
                .map(DespesaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DespesaDTO buscarPorId(@PathVariable String id) {
        Despesa despesa = service.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
        return new DespesaDTO(despesa);
    }

    @PostMapping
    public DespesaDTO criar(@RequestBody Despesa despesa) {
        Despesa saved = service.save(despesa);
        return new DespesaDTO(saved);
    }

    @PutMapping("/{id}")
    public DespesaDTO atualizar(@PathVariable String id, @RequestBody Despesa despesa) {
        despesa.setId(id);
        Despesa saved = service.save(despesa);
        return new DespesaDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}