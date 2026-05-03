package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.OcorrenciaDTO;
import com.gerenciamento.imoveis.service.OcorrenciaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/ocorrencias")
@RequiredArgsConstructor
public class OcorrenciaController {

    private final OcorrenciaService service;

    @GetMapping
    public ResponseEntity<List<OcorrenciaDTO>> listar() {
        List<OcorrenciaDTO> ocorrencias = service.listar();
        return ResponseEntity.ok(ocorrencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> obterPorId(@PathVariable String id) {
        OcorrenciaDTO ocorrencia = service.obterPorId(id);
        return ResponseEntity.ok(ocorrencia);
    }

    @GetMapping("/imovel/{imovelId}")
    public ResponseEntity<List<OcorrenciaDTO>> obterPorImovel(@PathVariable String imovelId) {
        List<OcorrenciaDTO> ocorrencias = service.obterPorImovel(imovelId);
        return ResponseEntity.ok(ocorrencias);
    }

    @PostMapping
    public ResponseEntity<OcorrenciaDTO> criar(@RequestBody OcorrenciaDTO dto) {
        OcorrenciaDTO ocorrencia = service.criar(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(ocorrencia);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OcorrenciaDTO> atualizar(@PathVariable String id, @RequestBody OcorrenciaDTO dto) {
        OcorrenciaDTO ocorrencia = service.atualizar(id, dto);
        return ResponseEntity.ok(ocorrencia);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable String id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
