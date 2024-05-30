package com.dev.java.MSPersonas.controller;

import com.dev.java.MSPersonas.dto.UsuarioDTO;
import com.dev.java.MSPersonas.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping("/validarUsuario")
    public CompletableFuture<String> validarUsuario(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.validarUsuario(usuarioDTO);

    }
}
