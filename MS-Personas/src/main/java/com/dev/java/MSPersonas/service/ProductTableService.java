package com.dev.java.MSPersonas.service;

import com.dev.java.MSPersonas.dto.RenaperDataDTO;
import com.dev.java.MSPersonas.dto.VerazDataDTO;
import com.dev.java.MSPersonas.dto.WorldsysDataDTO;
import com.dev.java.MSPersonas.model.Producto;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductTableService {

    private static final String CUENTA_PESOS = "cuenta_pesos";
    private static final String CUENTA_PESOS_DOLAR = "cuenta_pesos_dolar";
    private static final String TARJETA_BASIC = "tarjeta_basic";
    private static final String TARJETA_GOLD = "tarjeta_gold";
    private static final String TARJETA_BLACK = "tarjeta_black";

    public Producto getProduct(BigDecimal sueldoBruto, String worldsysData, String verazData, String renaperData) {
        Gson gson = new Gson();
        WorldsysDataDTO worldsysDataDTO = gson.fromJson(worldsysData, WorldsysDataDTO.class);
        VerazDataDTO verazDataDTO = gson.fromJson(verazData, VerazDataDTO.class);
        RenaperDataDTO renaperDataDTO = gson.fromJson(renaperData, RenaperDataDTO.class);

        if (!renaperDataDTO.isAuthorize()) {
            return buildProduct(CUENTA_PESOS, Optional.empty());
        }

        if (!worldsysDataDTO.isTerrorist()) {
            return determineProduct(sueldoBruto, verazDataDTO);
        }

        return null;
    }

    private Producto determineProduct(BigDecimal sueldoBruto, VerazDataDTO verazDataDTO) {
        double score = verazDataDTO.score();

        if (score <= 0.1) {
            return evaluateProduct(sueldoBruto, Optional.of(TARJETA_BLACK), Optional.of(TARJETA_GOLD), Optional.of(TARJETA_BASIC));
        } else if (score <= 0.5) {
            return evaluateProduct(sueldoBruto, Optional.of(TARJETA_GOLD), Optional.of(TARJETA_BASIC), Optional.empty());
        } else if (score <= 1.0) {
            return evaluateProduct(sueldoBruto, Optional.of(TARJETA_BASIC), Optional.empty());
        } else if (score <= 1.5) {
            return evaluateProduct(sueldoBruto, Optional.empty());
        }

        return null;
    }

    private Producto evaluateProduct(BigDecimal sueldoBruto, Optional<String>... tarjetas) {
        if (sueldoBruto.compareTo(BigDecimal.valueOf(999000)) > 0 && tarjetas.length > 0 && tarjetas[0].isPresent()) {
            return buildProduct(CUENTA_PESOS_DOLAR, tarjetas[0]);
        }
        if (sueldoBruto.compareTo(BigDecimal.valueOf(827000)) > 0 && tarjetas.length > 1 && tarjetas[1].isPresent()) {
            return buildProduct(CUENTA_PESOS_DOLAR, tarjetas[1]);
        }
        if (sueldoBruto.compareTo(BigDecimal.valueOf(446000)) > 0 && tarjetas.length > 2 && tarjetas[2].isPresent()) {
            return buildProduct(CUENTA_PESOS_DOLAR, tarjetas[2]);
        }
        if (sueldoBruto.compareTo(BigDecimal.valueOf(200000)) > 0) {
            return buildProduct(CUENTA_PESOS_DOLAR, Optional.empty());
        }

        return buildProduct(CUENTA_PESOS, Optional.empty());
    }

    private Producto buildProduct(String cuenta, Optional<String> tarjeta) {
        return Producto.builder()
                .cuenta(cuenta)
                .tarjeta(tarjeta)
                .build();
    }
}
