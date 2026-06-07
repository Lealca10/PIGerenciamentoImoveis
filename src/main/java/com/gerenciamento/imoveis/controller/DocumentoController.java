package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.DocumentoDTO;
import com.gerenciamento.imoveis.entity.Documento;
import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.repository.DocumentoRepository;
import com.gerenciamento.imoveis.repository.ImovelRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class DocumentoController {

    private final ImovelRepository imovelRepository;
    private final DocumentoRepository documentoRepository;

    public DocumentoController(ImovelRepository imovelRepository, DocumentoRepository documentoRepository) {
        this.imovelRepository = imovelRepository;
        this.documentoRepository = documentoRepository;
    }

    @GetMapping("/imoveis/{imovelId}/documentos")
    public ResponseEntity<List<Documento>> list(@PathVariable String imovelId) {
        return imovelRepository.findById(imovelId)
                .map(i -> ResponseEntity.ok(documentoRepository.findByImovelId(imovelId)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/imoveis/{imovelId}/documentos")
    public ResponseEntity<Documento> create(@PathVariable String imovelId, @RequestBody DocumentoDTO dto) {
        Imovel imovel = imovelRepository.findById(imovelId).orElse(null);
        if (imovel == null) {
            return ResponseEntity.notFound().build();
        }

        Documento doc = new Documento();
        doc.setTipo(dto.getTipo());
        doc.setNomeArquivo(dto.getNomeArquivo());
        doc.setUrl(dto.getUrl());
        doc.setUploadedAt(LocalDateTime.now());
        doc.setImovel(imovel);

        Documento saved = documentoRepository.save(doc);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @DeleteMapping("/imoveis/{imovelId}/documentos/{documentoId}")
    public ResponseEntity<Void> delete(@PathVariable String imovelId, @PathVariable String documentoId) {
        var opt = documentoRepository.findById(documentoId);
        if (opt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Documento d = opt.get();
        Imovel imovel = d.getImovel();
        if (imovel == null || !imovel.getId().equals(imovelId)) {
            return ResponseEntity.notFound().build();
        }

        documentoRepository.delete(d);
        return ResponseEntity.noContent().build();
    }
}
