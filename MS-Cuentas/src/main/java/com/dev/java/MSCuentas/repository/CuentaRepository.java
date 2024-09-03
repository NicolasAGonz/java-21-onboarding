package com.dev.java.MSCuentas.repository;

import com.dev.java.MSCuentas.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {

    @Query("SELECT CAST(c.numcue AS integer) FROM Cuenta c WHERE c.persnum = :persnum AND c.codigoMoneda = :divisa")
    Integer findNumcueByPersnumAndCodigoMoneda(@Param("persnum") Integer persnum, @Param("divisa") Integer divisa);
}

