package com.dev.java.MSTarjetas.controller;

import java.util.concurrent.CompletableFuture;

import com.dev.java.MSTarjetas.dto.TarjetaDTO;
import com.dev.java.MSTarjetas.service.TarjetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tarjetas")
@RequiredArgsConstructor
public class TarjetaController {


    private final TarjetaService tarjetaService;

    @PostMapping("/crearTarjeta")
    public CompletableFuture<String> createTarjeta(@RequestBody TarjetaDTO tarjetaDTO) {
        return tarjetaService.createTarjeta(tarjetaDTO);
    }
}
