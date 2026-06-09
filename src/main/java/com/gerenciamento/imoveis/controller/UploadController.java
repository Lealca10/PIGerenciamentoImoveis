package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.entity.Arquivo;
import com.gerenciamento.imoveis.repository.ArquivoRepository;
import com.gerenciamento.imoveis.service.R2Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class UploadController {

    @Autowired
    private R2Service r2Service;

    @Autowired
    private ArquivoRepository arquivoRepository;

    @PostMapping("/upload")
    public ResponseEntity<Map<String, String>> upload(@RequestParam("file") MultipartFile file) {
        try {
            String url = r2Service.uploadFile(file);

            Arquivo arquivo = new Arquivo();
            arquivo.setUrl(url);
            arquivo.setNome(file.getOriginalFilename());
            arquivo.setUploadedAt(LocalDateTime.now());
            arquivoRepository.save(arquivo);

            return ResponseEntity.ok(Map.of("url", url));
        } catch (Exception e) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Falha no upload: " + e.getMessage()));
        }
    }
}
