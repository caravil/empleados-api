package com.parameta.validator;

import com.parameta.dto.request.EmpleadoRequest;
import com.parameta.exception.BusinessException;
import com.parameta.util.EmpleadoCalculator;

import java.time.LocalDate;

public class EmpleadoValidator {

    private EmpleadoValidator() {
    }

    public static void validar(EmpleadoRequest request) {

        if (request.getFechaNacimiento()
                .isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }

        if (!EmpleadoCalculator
                .esMayorDeEdad(request.getFechaNacimiento())) {
            throw new BusinessException(
                    "El empleado debe ser mayor de edad.");
        }

        if (request.getFechaVinculacion()
                .isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha de vinculación no puede ser posterior a la fecha actual.");
        }

        if (request.getFechaVinculacion()
                .isBefore(request.getFechaNacimiento())) {
            throw new BusinessException(
                    "La fecha de vinculación no puede ser anterior a la fecha de nacimiento.");
        }
    }
}