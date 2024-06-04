package com.dev.java.MSCuentas.service;

import com.dev.java.MSCuentas.dto.CrearCuentaDTO;
import com.dev.java.MSCuentas.model.CodigoMoneda;
import com.dev.java.MSCuentas.model.Cuenta;
import com.dev.java.MSCuentas.model.EstadoCuenta;
import com.dev.java.MSCuentas.repository.CodigoMonedaRepository;
import com.dev.java.MSCuentas.repository.CuentaRepository;
import com.dev.java.MSCuentas.repository.EstadoCuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CodigoMonedaRepository codigoMonedaRepository;
    private final EstadoCuentaRepository estadoCuentaRepository;


    public CompletableFuture<String> crearCuenta(CrearCuentaDTO dto) {

            return CompletableFuture.supplyAsync(() -> {

                try {
                    CodigoMoneda moneda = codigoMonedaRepository.findById(dto.divisa())
                            .orElseThrow(() -> new IllegalArgumentException("Codigo de moneda no encontrada"));
                    EstadoCuenta estado = estadoCuentaRepository.findById(dto.estado())
                            .orElseThrow(() -> new IllegalArgumentException("Estado de cuenta no encontrado"));

                    Cuenta cuenta = new Cuenta();
                    cuenta.setNumcue(dto.numcue());
                    cuenta.setPersnum(dto.persnum());
                    cuenta.setDivisa(moneda);
                    cuenta.setEstado(estado);
                    cuenta.setSaldo(dto.saldo());

                    cuentaRepository.save(cuenta);
                    return "cuenta creada correctamente";
                } catch (Exception e) {
                    throw new RuntimeException("Error en el proceso de creación de cuenta", e);
                }

            }).handle((result, ex) -> {
                if (ex != null) {
                    return "Error creando cuenta: " + ex.getCause().getMessage();
                }
                return result;

        });
    }
}
