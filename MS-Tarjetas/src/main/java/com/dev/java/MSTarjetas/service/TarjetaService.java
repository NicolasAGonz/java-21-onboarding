package com.dev.java.MSTarjetas.service;

import com.dev.java.MSTarjetas.dto.TarjetaDTO;
import com.dev.java.MSTarjetas.model.EstadoTarjeta;
import com.dev.java.MSTarjetas.model.Tarjeta;
import com.dev.java.MSTarjetas.repository.EstadoTarjetaRepository;
import com.dev.java.MSTarjetas.repository.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;

    private final EstadoTarjetaRepository estadoTarjetaRepository;


    public CompletableFuture<String> createTarjeta(TarjetaDTO tarjetaDTO) {

        return CompletableFuture.supplyAsync(() -> {

            try {
                EstadoTarjeta estadoTarjeta = estadoTarjetaRepository.findById(tarjetaDTO.estado())
                        .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));

                Tarjeta tarjeta = new Tarjeta();
                tarjeta.setNumtarj(tarjetaDTO.numtarj());
                tarjeta.setNumcue(tarjetaDTO.numcue());
                tarjeta.setF_vencimiento(tarjetaDTO.f_vencimiento());
                tarjeta.setPin(tarjetaDTO.pin());
                tarjeta.setEstado(estadoTarjeta);
                tarjeta.setF_emision(String.valueOf(tarjetaDTO.f_emision()));
                tarjeta.setTipo(tarjetaDTO.tipo());

                tarjetaRepository.save(tarjeta);
                return "Tarjeta creada correctamente";
            } catch (Exception e) {
                throw new RuntimeException("Error en el proceso de creación de tarjeta", e);
            }

        }).handle((result, ex) -> {
            if (ex != null) {
                return "Error creando tarjeta: " + ex.getCause().getMessage();
            }
            return result;

        });
    }
}
