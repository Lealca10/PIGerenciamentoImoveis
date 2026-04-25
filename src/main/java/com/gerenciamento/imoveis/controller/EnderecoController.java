package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.EnderecoDTO;
import com.gerenciamento.imoveis.entity.Bairro;
import com.gerenciamento.imoveis.entity.Cidade;
import com.gerenciamento.imoveis.entity.Endereco;
import com.gerenciamento.imoveis.entity.Estado;
import com.gerenciamento.imoveis.entity.Rua;
import com.gerenciamento.imoveis.service.BairroService;
import com.gerenciamento.imoveis.service.CidadeService;
import com.gerenciamento.imoveis.service.EnderecoService;
import com.gerenciamento.imoveis.service.EstadoService;
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
    private final BairroService bairroService;
    private final CidadeService cidadeService;
    private final EstadoService estadoService;

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

        Bairro bairro = null;
        if (dto.getBairroId() != null) {
            bairro = bairroService.findById(dto.getBairroId())
                    .orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
        } else if (dto.getBairroDescricao() != null) {
            bairro = bairroService.findByDescricao(dto.getBairroDescricao())
                    .orElseGet(() -> bairroService.save(new Bairro(dto.getBairroDescricao())));
        } else {
            throw new RuntimeException("Bairro não informado");
        }

        Cidade cidade = cidadeService.findById(dto.getCidadeId())
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
        Estado estado = estadoService.findById(dto.getEstadoId())
                .orElseThrow(() -> new RuntimeException("Estado não encontrado"));

        Endereco endereco = new Endereco(dto.getCep(), rua, bairro, cidade, estado, dto.getNumero(), dto.getComplemento());
        Endereco saved = service.save(endereco);
        return new EnderecoDTO(saved);
    }

    @PutMapping("/{id}")
    public EnderecoDTO atualizar(@PathVariable String id, @RequestBody EnderecoDTO dto) {
        Endereco endereco = service.findById(id).orElseThrow(() -> new RuntimeException("Endereço não encontrado"));
        endereco.setNumero(dto.getNumero());
        endereco.setComplemento(dto.getComplemento());
        endereco.setCep(dto.getCep());

        if (dto.getRuaId() != null) {
            Rua rua = ruaService.findById(dto.getRuaId())
                    .orElseThrow(() -> new RuntimeException("Rua não encontrada"));
            endereco.setRua(rua);
        }

        if (dto.getBairroId() != null) {
            Bairro bairro = bairroService.findById(dto.getBairroId())
                    .orElseThrow(() -> new RuntimeException("Bairro não encontrado"));
            endereco.setBairro(bairro);
        } else if (dto.getBairroDescricao() != null) {
            Bairro bairro = bairroService.findByDescricao(dto.getBairroDescricao())
                    .orElseGet(() -> bairroService.save(new Bairro(dto.getBairroDescricao())));
            endereco.setBairro(bairro);
        }

        if (dto.getCidadeId() != null) {
            Cidade cidade = cidadeService.findById(dto.getCidadeId())
                    .orElseThrow(() -> new RuntimeException("Cidade não encontrada"));
            endereco.setCidade(cidade);
        }

        if (dto.getEstadoId() != null) {
            Estado estado = estadoService.findById(dto.getEstadoId())
                    .orElseThrow(() -> new RuntimeException("Estado não encontrado"));
            endereco.setEstado(estado);
        }

        Endereco saved = service.save(endereco);
        return new EnderecoDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}
