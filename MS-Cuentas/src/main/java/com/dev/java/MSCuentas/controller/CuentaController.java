package com.dev.java.MSCuentas.controller;

import com.dev.java.MSCuentas.dto.CrearCuentaDTO;
import com.dev.java.MSCuentas.service.CuentaService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/cuentas")
@RequiredArgsConstructor
public class CuentaController {

    private final CuentaService cuentaService;


    @PostMapping("/crearCuenta")
    public CompletableFuture<String> crearCuenta(@RequestBody CrearCuentaDTO crearCuentaDTO) {
        return cuentaService.crearCuenta(crearCuentaDTO);

    }
}
