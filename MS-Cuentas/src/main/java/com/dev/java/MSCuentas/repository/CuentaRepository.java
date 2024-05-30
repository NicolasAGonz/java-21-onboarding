package com.dev.java.MSCuentas.repository;

import com.dev.java.MSCuentas.model.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CuentaRepository extends JpaRepository<Cuenta, String> {
}

