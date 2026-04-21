package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.RuaDTO;
import com.gerenciamento.imoveis.entity.Rua;
import com.gerenciamento.imoveis.entity.Cidade;
import com.gerenciamento.imoveis.service.RuaService;
import com.gerenciamento.imoveis.service.CidadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/ruas")
@RequiredArgsConstructor
public class RuaController {

    private final RuaService service;
    private final CidadeService cidadeService;

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

    @GetMapping("/cidade/{cidadeId}")
    public List<RuaDTO> buscarPorCidade(@PathVariable String cidadeId) {
        Cidade cidade = cidadeService.findById(cidadeId).orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        return service.findByCidade(cidade).stream()
                .map(RuaDTO::new)
                .collect(Collectors.toList());
    }

    @PostMapping
    public RuaDTO criar(@RequestBody RuaDTO dto) {
        Cidade cidade = cidadeService.findById(dto.getCidadeId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        Rua rua = new Rua(dto.getNome(), dto.getCep(), cidade);
        Rua saved = service.save(rua);
        return new RuaDTO(saved);
    }

    @PutMapping("/{id}")
    public RuaDTO atualizar(@PathVariable String id, @RequestBody RuaDTO dto) {
        Rua rua = service.findById(id).orElseThrow(() -> new RuntimeException("Rua não encontrada"));
        rua.setNome(dto.getNome());
        rua.setCep(dto.getCep());
        
        if (dto.getCidadeId() != null) {
            Cidade cidade = cidadeService.findById(dto.getCidadeId())
                    .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
            rua.setCidade(cidade);
        }
        
        Rua saved = service.save(rua);
        return new RuaDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}
