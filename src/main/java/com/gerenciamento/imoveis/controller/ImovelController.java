package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.ImovelDTO;
import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.service.ImovelService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/imoveis")
@RequiredArgsConstructor
public class ImovelController {

    private final ImovelService service;

    @GetMapping
    public List<ImovelDTO> listar() {
        return service.findAll().stream()
                .map(ImovelDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ImovelDTO buscarPorId(@PathVariable String id) {
        Imovel imovel = service.findById(id).orElseThrow(() -> new RuntimeException("Imóvel não encontrado"));
        return new ImovelDTO(imovel);
    }

    @PostMapping
    public ImovelDTO criar(@RequestBody ImovelDTO imovelDTO) {
        Imovel imovel = service.dtoToEntity(imovelDTO);
        Imovel saved = service.save(imovel);
        return new ImovelDTO(saved);
    }

    @PutMapping("/{id}")
    public ImovelDTO atualizar(@PathVariable String id, @RequestBody ImovelDTO imovelDTO) {
        imovelDTO.setId(id);
        Imovel imovel = service.dtoToEntity(imovelDTO);
        Imovel saved = service.save(imovel);
        return new ImovelDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}