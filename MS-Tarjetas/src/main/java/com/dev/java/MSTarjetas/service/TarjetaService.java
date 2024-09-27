package com.dev.java.MSTarjetas.service;

import com.dev.java.MSTarjetas.dto.TarjetaDTO;
import com.dev.java.MSTarjetas.model.EstadoTarjeta;
import com.dev.java.MSTarjetas.model.Tarjeta;
import com.dev.java.MSTarjetas.repository.EstadoTarjetaRepository;
import com.dev.java.MSTarjetas.repository.TarjetaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.concurrent.*;

import static com.dev.java.MSTarjetas.helpers.CardNumberGenerator.generateCardNumber;
import static com.dev.java.MSTarjetas.helpers.DateGenerators.cardDatesGenerator;
import static com.dev.java.MSTarjetas.helpers.PINGenerator.cardPinGenerator;

@Service
@RequiredArgsConstructor
public class TarjetaService {

    private final TarjetaRepository tarjetaRepository;
    private final EstadoTarjetaRepository estadoTarjetaRepository;
    private static final Logger logger = (Logger) LoggerFactory.getLogger(TarjetaService.class);

    public void crearTarjeta(String newAccountNumCue){
        try (ExecutorService executorService = Executors.newVirtualThreadPerTaskExecutor()) {

            Callable cardCreationTask = cardCreationTask(newAccountNumCue, tarjetaRepository, estadoTarjetaRepository);
            Future newAcoountFuture = executorService.submit(cardCreationTask); //end of submit
            newAcoountFuture.get();

        } //END OF TRY
        catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static Callable cardCreationTask(String newAccountNumCue, TarjetaRepository tarjetaRepository, EstadoTarjetaRepository estadoTarjetaRepository ){
        return () -> {
            try {
                EstadoTarjeta estadoTarjeta = estadoTarjetaRepository.findById(1)
                        .orElseThrow(() -> new IllegalArgumentException("Estado no encontrado"));

                /*EstadoTarjeta estadoTarjeta = EstadoTarjeta.builder()
                        .id(1)
                        .detalle("Activa")
                        .build();*/

                Integer newAccountNumCueInt = Integer.parseInt(sanitizeInput(newAccountNumCue));
                String newCardNumber = generateCardNumber();
                String[] newCardDates = cardDatesGenerator();
                Integer newCardPIN = cardPinGenerator();

                Tarjeta newTarjeta = Tarjeta.builder()
                        .numtarj(newCardNumber)
                        .numcue(newAccountNumCueInt)
                        .f_vencimiento(newCardDates[1])
                        .pin(newCardPIN)
                        .estado(estadoTarjeta)
                        .f_emision(newCardDates[0])
                        .tipo("C")
                        .build();

                logger.info("GUARDANDO NUEVA TARJETA...");
                tarjetaRepository.save(newTarjeta);
                logger.info("SE HA GUARDADO EXITOSAMENTE LA SIGUIENTE TARJETA:");
                logger.info(newTarjeta.toString());

                return "Tarjeta creada correctamente";
            } catch (Exception e) {
                throw new RuntimeException("Error en el proceso de creación de tarjeta", e);
            }
        };
    }

    private static String sanitizeInput(String input) {
        return input.replace("\"", "");
    }

}

