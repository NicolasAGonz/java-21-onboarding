package com.dev.java.MSPersonas.service;

import com.dev.java.MSPersonas.dto.RenaperDataDTO;
import com.dev.java.MSPersonas.dto.VerazDataDTO;
import com.dev.java.MSPersonas.dto.WorldsysDataDTO;
import com.dev.java.MSPersonas.model.Producto;
import com.google.gson.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
    private static final String TARJETA_NULL = "no_aplica_tarjeta";

    private static final Logger logger = (Logger) LoggerFactory.getLogger(ProductTableService.class);

    public Producto getProduct(BigDecimal sueldoBruto, String worldsysData, String verazData, String renaperData) {
        Gson gson = new Gson();

        //Obtengo los response de cada uno de los datos provistos
        String worldsysDataResponse = getResponse(worldsysData);
        String verazDataResponse = getResponse(verazData);
        String renaperDataResponse = getResponse(renaperData);


        WorldsysDataDTO worldsysDataDTO = gson.fromJson(worldsysDataResponse, WorldsysDataDTO.class);
        logger.warn("ARME EL SIGUIENTE DTO: " + worldsysDataDTO.toString() );

        VerazDataDTO verazDataDTO = gson.fromJson(verazDataResponse, VerazDataDTO.class);
        logger.warn("ARME EL SIGUIENTE DTO: " + verazDataDTO.toString() );

        RenaperDataDTO renaperDataDTO = gson.fromJson(renaperDataResponse, RenaperDataDTO.class);
        logger.warn("ARME EL SIGUIENTE DTO: " + renaperDataDTO.toString() );

        if (!renaperDataDTO.getIsAuthorize()) {
            logger.warn("EL CLIENTE NO ESTA AUTORIZADO POR RENAPER, SE LE ASIGNARAN LOS PRODUCTOS MINIMOS");
            return buildProduct(CUENTA_PESOS, TARJETA_NULL);
        }

        if (!worldsysDataDTO.getIsTerrorist()) {
            logger.warn("CONSULTANDO LOS PRODUCTOS QUE LE CORRESPONDEN AL CLIENTE...");
            return determineProduct(sueldoBruto, verazDataDTO);
        }

        return null;
    }

    private String getResponse (String data) {
        Gson gson = new Gson();

        // Parsear el JSON de la respuesta en un JsonObject
        JsonObject rootObject = gson.fromJson(data, JsonObject.class);

        // Obtener el array "response"
        JsonArray responseArray = rootObject.getAsJsonArray("response");

        // Obtener el primer objeto del array
        JsonElement firstResponseElement = responseArray.get(0);

        // Convertir ese objeto a un string JSON
        return gson.toJson(firstResponseElement);
    }

    private Producto determineProduct(BigDecimal sueldoBruto, VerazDataDTO verazDataDTO) {
        double score = verazDataDTO.getScore();
        logger.info("CONSULTANDO PRODUCTO APLICABLE PARA UN USUARIO CON SCORE: {}", score);

        if (score <= 0.1) {
            return evaluateProduct(sueldoBruto, TARJETA_NULL, TARJETA_BASIC, TARJETA_GOLD, TARJETA_BLACK);
        } else if (score <= 0.5) {
            return evaluateProduct(sueldoBruto, TARJETA_NULL, TARJETA_BASIC, TARJETA_GOLD);
        } else if (score <= 1.0) {
            return evaluateProduct(sueldoBruto, TARJETA_NULL, TARJETA_BASIC);
        } else if (score <= 1.5) {
            return evaluateProduct(sueldoBruto, TARJETA_NULL);
        }

        return null;
    }

    private Producto evaluateProduct(BigDecimal sueldoBruto, String... tarjetas) {
        logger.info("OBTENIENDO PRODUCTO APLICABLE SEGUN SUELDO BRUTO: {}", sueldoBruto);

        logger.info("Tarjetas recibidas:");
        for (String tarjeta : tarjetas) {
            logger.info("Tarjeta: {}", tarjeta);
        }

        if (sueldoBruto.compareTo(BigDecimal.valueOf(999000)) > 0 ) {
            return buildProduct(CUENTA_PESOS_DOLAR, tarjetas[3]);
        }
        if (sueldoBruto.compareTo(BigDecimal.valueOf(827000)) > 0 ) {
            return buildProduct(CUENTA_PESOS_DOLAR, tarjetas[2]);
        }
        if (sueldoBruto.compareTo(BigDecimal.valueOf(446000)) > 0 ) {
            return buildProduct(CUENTA_PESOS_DOLAR, tarjetas[1]);
        }
        if (sueldoBruto.compareTo(BigDecimal.valueOf(200000)) > 0) {
            return buildProduct(CUENTA_PESOS_DOLAR, tarjetas[0]);
        }

        return buildProduct(CUENTA_PESOS, TARJETA_NULL);
    }

    private Producto buildProduct(String cuenta, String tarjeta) {
        return Producto.builder()
                .cuenta(cuenta)
                .tarjeta(tarjeta)
                .build();
    }
}
