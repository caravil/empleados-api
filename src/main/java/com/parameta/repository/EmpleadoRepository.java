package com.parameta.repository;

import com.parameta.entity.Empleado;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmpleadoRepository extends JpaRepository<Empleado, Long> {

    Optional<Empleado> findByNumeroDocumento(String numeroDocumento);
}