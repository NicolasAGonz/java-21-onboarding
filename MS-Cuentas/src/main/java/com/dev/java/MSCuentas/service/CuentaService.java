package com.dev.java.MSCuentas.service;

import com.dev.java.MSCuentas.dto.NewUserWithProductDTO;
import com.dev.java.MSCuentas.kafka.KafkaConsumer;
import com.dev.java.MSCuentas.kafka.KafkaProducer;
import com.dev.java.MSCuentas.model.CodigoMoneda;
import com.dev.java.MSCuentas.model.Cuenta;
import com.dev.java.MSCuentas.model.EstadoCuenta;
import com.dev.java.MSCuentas.repository.CodigoMonedaRepository;
import com.dev.java.MSCuentas.repository.CuentaRepository;
import com.dev.java.MSCuentas.repository.EstadoCuentaRepository;
import com.dev.java.MSCuentas.utils.RandomNumberGenerator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

@Service
@RequiredArgsConstructor
public class CuentaService {

    private final CuentaRepository cuentaRepository;
    private final CodigoMonedaRepository codigoMonedaRepository;
    private final KafkaProducer kafkaProducer;
    private static final String CUENTA_PESOS = "cuenta_pesos";
    private static final String CUENTA_PESOS_DOLAR = "cuenta_pesos_dolar";
    private static final Logger logger = (Logger) LoggerFactory.getLogger(CuentaService.class);
    private static RandomNumberGenerator randomNumberGenerator;


    public void crearCuenta(NewUserWithProductDTO dto) {

        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            Callable<List<Cuenta>> accountCreationTask = accountCreationTask(dto, cuentaRepository, codigoMonedaRepository, randomNumberGenerator);
            Future<List<Cuenta>> newAccountFuture = executorService.submit(accountCreationTask); //end of submit
            List<Cuenta> newAccountsCreated = newAccountFuture.get();

            logger.info("ENVIANDO MENSAJE MEDIANTE KAFKA: NUEVAS CUENTAS CREADAS: ");
            newAccountsCreated.forEach(account -> {
                logger.info("Cuenta creada: " + account.toString());

                int persnum = dto.getPersnum();
                int codMoneda = account.getCodigoMoneda().getCod_moneda();

                int numcue = cuentaRepository.findNumcueByPersnumAndCodigoMoneda(persnum, codMoneda);
                kafkaProducer.sendNewAccountCreatedMessage(String.valueOf(numcue));
                }
            );
        } //END OF TRY
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Callable<List<Cuenta>> accountCreationTask (NewUserWithProductDTO dto, CuentaRepository cuentaRepository, CodigoMonedaRepository codigoMonedaRepository, RandomNumberGenerator randomNumberGenerator){

        return () -> {

            String cuenta = dto.getProducto().getCuenta();
            List<Integer> cuentasACrear = new ArrayList<>();
            List<Cuenta> createdAccounts = new ArrayList<>();

            Integer idMonedaARS = codigoMonedaRepository.findCodMonedaBySimbolo("ARS");

            if (cuenta.equals(CUENTA_PESOS)){
                cuentasACrear.add(idMonedaARS); //Agrego una cuenta en pesos
            }

            if (cuenta.equals(CUENTA_PESOS_DOLAR)){
                Integer idMonedaUSD = codigoMonedaRepository.findCodMonedaBySimbolo("USD");
                cuentasACrear.add(idMonedaARS); //Agrego una cuenta en pesos
                cuentasACrear.add(idMonedaUSD); //Agrego una cuenta en dolares
            }

            logger.info("SE PRODECERA A LA CREACION DE LAS SIGUIENTES CUENTAS");
            cuentasACrear.forEach(c -> logger.info("CUENTA A CREAR: " + c));

            Cuenta newAccount = null;

            for (Integer c : cuentasACrear) {

                if (c != null){
                    try {
                        CodigoMoneda moneda = codigoMonedaRepository.findById(c)
                                .orElseThrow(() -> new IllegalArgumentException("Codigo de moneda no encontrada"));

                        newAccount = Cuenta.builder()
                                .numcue(randomNumberGenerator.generateRandomNumber())
                                .codigoMoneda(new CodigoMoneda(moneda.getCod_moneda(), moneda.getPais(), moneda.getSimbolo()))
                                .estadoCuenta(new EstadoCuenta(1, "Activa"))
                                .persnum(dto.getPersnum())
                                .saldo(0)
                                .build();

                        logger.info("GUARDANDO NUEVA CUENTA...");
                        cuentaRepository.save(newAccount);
                        createdAccounts.add(newAccount);

                        logger.info("SE HA GUARDADO LA SIGUIENTE CUENTA: ");
                        logger.info(newAccount.toString());

                    } catch (Exception e) {
                        throw new RuntimeException("Error en el proceso de creación de cuenta", e);
                    }

                } //End of IF
            } //End of For

            if (newAccount == null) {
                throw new IllegalStateException("No se pudo crear ninguna cuenta");
            }

            return createdAccounts;

        }; //End of Callable Return
    }

}


