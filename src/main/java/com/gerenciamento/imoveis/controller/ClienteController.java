package com.gerenciamento.imoveis.controller;

import com.gerenciamento.imoveis.dto.ClienteDTO;
import com.gerenciamento.imoveis.entity.Cliente;
import com.gerenciamento.imoveis.service.ClienteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/clientes")
@RequiredArgsConstructor
public class ClienteController {

    private final ClienteService service;

    @GetMapping
    public List<ClienteDTO> listar() {
        return service.findAll().stream()
                .map(ClienteDTO::new)
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClienteDTO buscarPorId(@PathVariable String id) {
        Cliente cliente = service.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        return new ClienteDTO(cliente);
    }

    @PostMapping
    public ClienteDTO criar(@RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNome(clienteDTO.getNome());
        cliente.setConta(clienteDTO.getConta());
        cliente.setAgencia(clienteDTO.getAgencia());
        Cliente saved = service.save(cliente);
        return new ClienteDTO(saved);
    }

    @PutMapping("/{id}")
    public ClienteDTO atualizar(@PathVariable String id, @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = service.findById(id).orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        cliente.setNome(clienteDTO.getNome());
        cliente.setConta(clienteDTO.getConta());
        cliente.setAgencia(clienteDTO.getAgencia());
        Cliente saved = service.save(cliente);
        return new ClienteDTO(saved);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable String id) {
        service.deleteById(id);
    }
}
