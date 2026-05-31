package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.LeilaoDTO;
import com.gerenciamento.imoveis.entity.Leilao;
import com.gerenciamento.imoveis.entity.Imovel;
import com.gerenciamento.imoveis.service.ImovelService;
import com.gerenciamento.imoveis.dto.LeilaoRequest;
import com.gerenciamento.imoveis.service.LeilaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/leiloes")
@RequiredArgsConstructor
public class LeilaoController {

    private final LeilaoService service;
    private final ImovelService imovelService;

    @GetMapping
    public List<LeilaoDTO> listar() {
        return service.findAll().stream()
                .map(LeilaoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/imovel/{imovelId}")
    public List<LeilaoDTO> listarPorImovel(@PathVariable String imovelId) {
        return service.findByImovelId(imovelId).stream()
                .map(LeilaoDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public LeilaoDTO buscarPorId(@PathVariable String id) {
        Leilao leilao = service.findById(id).orElseThrow(() -> new RuntimeException("Leilão não encontrado"));
        return new LeilaoDTO(leilao);
    }

    @PostMapping
    public LeilaoDTO criar(@RequestBody LeilaoRequest request) {
        Leilao leilao = new Leilao();
        if (request.getImovelId() != null) {
            Imovel imovel = imovelService.findById(request.getImovelId())
                    .orElseThrow(() -> new RuntimeException("Imóvel não encontrado: " + request.getImovelId()));
            leilao.setImovel(imovel);
        }
        leilao.setNumero(request.getNumero());
        leilao.setValorMinimo(request.getValorMinimo());
        leilao.setData(request.getData());
        leilao.setStatus(request.getStatus());
        Leilao saved = service.save(leilao);
        return new LeilaoDTO(saved);
    }

    @PutMapping("/{id}")
    public LeilaoDTO atualizar(@PathVariable String id, @RequestBody LeilaoRequest request) {
        Leilao leilao = new Leilao();
        leilao.setId(id);
        if (request.getImovelId() != null) {
            Imovel imovel = imovelService.findById(request.getImovelId())
                    .orElseThrow(() -> new RuntimeException("Imóvel não encontrado: " + request.getImovelId()));
            leilao.setImovel(imovel);
        }
        leilao.setNumero(request.getNumero());
        leilao.setValorMinimo(request.getValorMinimo());
        leilao.setData(request.getData());
        leilao.setStatus(request.getStatus());
        Leilao saved = service.save(leilao);
        return new LeilaoDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}