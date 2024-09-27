package com.dev.java.MSTarjetas.helpers;

import java.util.Random;

public class PINGenerator {

    // Método para generar un número entero de 4 dígitos aleatorios
    public static Integer cardPinGenerator() {
        Random random = new Random();
        // Generar un número entre 1000 y 9999 (ambos inclusive)
        return 1000 + random.nextInt(9000);
    }
}
