package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.EnderecoDTO;
import com.gerenciamento.imoveis.entity.Endereco;
import com.gerenciamento.imoveis.entity.Rua;
import com.gerenciamento.imoveis.service.EnderecoService;
import com.gerenciamento.imoveis.service.RuaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;
    private final RuaService ruaService;

    @GetMapping
    public List<EnderecoDTO> listar() {
        return service.findAll().stream()
                .map(EnderecoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public EnderecoDTO buscarPorId(@PathVariable String id) {
        Endereco endereco = service.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        return new EnderecoDTO(endereco);
    }

    @PostMapping
    public EnderecoDTO criar(@RequestBody EnderecoDTO dto) {
        Rua rua = ruaService.findById(dto.getRuaId())
                .orElseThrow(() -> new RuntimeException("Rua não encontrada"));
        Endereco endereco = new Endereco(rua, dto.getNumero(), dto.getComplemento());
        Endereco saved = service.save(endereco);
        return new EnderecoDTO(saved);
    }

    @PutMapping("/{id}")
    public EnderecoDTO atualizar(@PathVariable String id, @RequestBody EnderecoDTO dto) {
        Endereco endereco = service.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        
        if (dto.getRuaId() != null) {
            Rua rua = ruaService.findById(dto.getRuaId())
                    .orElseThrow(() -> new RuntimeException("Rua não encontrada"));
            endereco.setRua(rua);
        }
        
        Endereco saved = service.save(endereco);
        return new EnderecoDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}
