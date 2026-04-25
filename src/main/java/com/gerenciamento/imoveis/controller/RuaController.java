package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.RuaDTO;
import com.gerenciamento.imoveis.entity.Rua;
import com.gerenciamento.imoveis.service.RuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruas")
@RequiredArgsConstructor
public class RuaController {

    private final RuaService service;

    @GetMapping
    public List<RuaDTO> listar() {
        return service.findAll().stream()
                .map(RuaDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public RuaDTO buscarPorId(@PathVariable String id) {
        Rua rua = service.findById(id).orElseThrow(() -> new RuntimeException("Rua não encontrada"));
        return new RuaDTO(rua);
    }

    @PostMapping
    public RuaDTO criar(@RequestBody RuaDTO dto) {
        Rua rua = new Rua(dto.getNome(), dto.getCep());
        Rua saved = service.save(rua);
        return new RuaDTO(saved);
    }

    @PutMapping("/{id}")
    public RuaDTO atualizar(@PathVariable String id, @RequestBody RuaDTO dto) {
        Rua rua = service.findById(id).orElseThrow(() -> new RuntimeException("Rua não encontrada"));
        rua.setNome(dto.getNome());
        rua.setCep(dto.getCep());
        Rua saved = service.save(rua);
        return new RuaDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}
