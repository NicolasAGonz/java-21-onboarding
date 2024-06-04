package com.dev.java.MSTarjetas.repository;

import com.dev.java.MSTarjetas.model.Tarjeta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TarjetaRepository extends JpaRepository<Tarjeta, String> {
}
