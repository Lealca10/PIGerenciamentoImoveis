package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.NegociacaoDTO;
import com.gerenciamento.imoveis.service.NegociacaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/negociacoes")
@RequiredArgsConstructor
public class NegociacaoController {

    private final NegociacaoService service;

    @GetMapping
    public ResponseEntity<List<NegociacaoDTO>> listar() {
        List<NegociacaoDTO> negociacoes = service.listar();
        return ResponseEntity.ok(negociacoes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<NegociacaoDTO> obterPorId(@PathVariable String id) {
        NegociacaoDTO negociacao = service.obterPorId(id);
        return ResponseEntity.ok(negociacao);
    }

    @GetMapping("/imovel/{imovelId}")
    public ResponseEntity<List<NegociacaoDTO>> obterPorImovel(@PathVariable String imovelId) {
        List<NegociacaoDTO> negociacoes = service.obterPorImovel(imovelId);
        return ResponseEntity.ok(negociacoes);
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<NegociacaoDTO>> obterPorCliente(@PathVariable String clienteId) {
        List<NegociacaoDTO> negociacoes = service.obterPorCliente(clienteId);
        return ResponseEntity.ok(negociacoes);
    }

    @PostMapping
    public ResponseEntity<NegociacaoDTO> criar(@RequestBody NegociacaoDTO dto) {
        NegociacaoDTO negociacao = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(negociacao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<NegociacaoDTO> atualizar(@PathVariable String id, @RequestBody NegociacaoDTO dto) {
        NegociacaoDTO negociacao = service.atualizar(id, dto);
        return ResponseEntity.ok(negociacao);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
