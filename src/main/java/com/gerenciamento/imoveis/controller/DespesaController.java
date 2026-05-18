package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.DespesaCriarDTO;
import com.gerenciamento.imoveis.dto.DespesaDTO;
import com.gerenciamento.imoveis.entity.Despesa;
import com.gerenciamento.imoveis.service.DespesaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping("/imovel/{imovelId}")
    public List<DespesaDTO> listarPorImovel(@PathVariable String imovelId) {
        return service.findByImovelId(imovelId).stream()
                .map(DespesaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public DespesaDTO buscarPorId(@PathVariable String id) {
        Despesa despesa = service.findById(id).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));
        return new DespesaDTO(despesa);
    }

    @PostMapping
    public ResponseEntity<DespesaDTO> criar(@RequestBody DespesaCriarDTO dto) {
        DespesaDTO despesa = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(despesa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DespesaDTO> atualizar(@PathVariable String id, @RequestBody DespesaCriarDTO dto) {
        DespesaDTO despesa = service.atualizar(id, dto);
        return ResponseEntity.ok(despesa);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}