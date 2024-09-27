package com.dev.java.MSTarjetas.helpers;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateGenerators {

    // Método para obtener la fecha actual y la fecha dentro de 5 años en formato "mes/año"
    public static String[] cardDatesGenerator() {
        // Obtener la fecha actual
        LocalDateTime today = LocalDateTime.now();

        // Obtener la fecha dentro de 5 años
        LocalDateTime futureDate = today.plusYears(5);

        // Formateador para extraer "mes/año" con año recortado
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/yy");

        String currentDate = today.format(formatter);
        String futureDateStr = futureDate.format(formatter);

        return new String[]{currentDate, futureDateStr};
    }

}
