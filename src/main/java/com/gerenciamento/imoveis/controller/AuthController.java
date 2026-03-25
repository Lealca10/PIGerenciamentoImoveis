package com.gerenciamento.imoveis.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gerenciamento.imoveis.dto.AuthResponseDTO;
import com.gerenciamento.imoveis.dto.LoginRequestDTO;
import com.gerenciamento.imoveis.dto.RegisterRequestDTO;
import com.gerenciamento.imoveis.service.AuthService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public AuthResponseDTO register(@RequestBody RegisterRequestDTO request) {
        return service.register(request);
    }

    @PostMapping("/login")
    public AuthResponseDTO login(@RequestBody LoginRequestDTO request) {
        return service.login(request);
    }
}
