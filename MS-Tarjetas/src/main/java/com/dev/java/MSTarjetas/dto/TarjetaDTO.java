package com.dev.java.MSTarjetas.dto;

import java.util.Date;

public record TarjetaDTO(
        String numtarj,
        int numcue,
        String f_vencimiento,
        int pin,
        int estado,
        Date f_emision,
        String tipo
) {}

