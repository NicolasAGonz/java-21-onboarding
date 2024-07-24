package com.dev.java.MSCuentas.service;

import com.dev.java.MSCuentas.dto.CrearCuentaDTO;
import com.dev.java.MSCuentas.model.CodigoMoneda;
import com.dev.java.MSCuentas.model.Cuenta;
import com.dev.java.MSCuentas.model.EstadoCuenta;
import com.dev.java.MSCuentas.repository.CodigoMonedaRepository;
import com.dev.java.MSCuentas.repository.CuentaRepository;
import com.dev.java.MSCuentas.repository.EstadoCuentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CodigoMonedaRepository codigoMonedaRepository;
    private final EstadoCuentaRepository estadoCuentaRepository;

    @KafkaListener(topics = "healthCheckTopic", groupId = "new-user-group")
    public void consume(String message) {
        System.out.println("Consumed message: " + message);
    }

    @KafkaListener(topics = "newUserCreatedTopic", groupId = "new-user-group")
    public void crearCuenta(CrearCuentaDTO dto) {

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            Callable accountCreationTask = accountCreationTask(dto, cuentaRepository, codigoMonedaRepository, estadoCuentaRepository);
            Future newAccountFuture = executorService.submit(accountCreationTask); //end of submit
            newAccountFuture.get();

        } //END OF TRY
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Callable accountCreationTask (CrearCuentaDTO dto, CuentaRepository cuentaRepository, CodigoMonedaRepository codigoMonedaRepository, EstadoCuentaRepository estadoCuentaRepository){

        return () -> {
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

                return cuentaRepository.save(cuenta);
            } catch (Exception e) {
                throw new RuntimeException("Error en el proceso de creación de cuenta", e);
            }
        };
    }
}


