package com.dev.java.MSCuentas.repository;

import com.dev.java.MSCuentas.model.CodigoMoneda;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CodigoMonedaRepository extends JpaRepository<CodigoMoneda, Integer> {
    @Query("SELECT c.cod_moneda FROM CodigoMoneda c WHERE c.simbolo = :simbolo")
    Integer findCodMonedaBySimbolo(@Param("simbolo") String simbolo);
}

