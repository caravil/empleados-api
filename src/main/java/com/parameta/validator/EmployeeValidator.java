package com.parameta.validator;

import com.parameta.dto.request.EmployeeRequest;
import com.parameta.exception.BusinessException;
import com.parameta.util.EmployeeCalculator;

import java.time.LocalDate;

public class EmployeeValidator {

    private EmployeeValidator() {
    }

    public static void validate(EmployeeRequest request) {

        if (request.getDateOfBirth().isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha de nacimiento no puede ser posterior a la fecha actual.");
        }

        if (!EmployeeCalculator.isAdult(request.getDateOfBirth())) {
            throw new BusinessException(
                    "El empleado debe ser mayor de edad.");
        }

        if (request.getLinkingDate().isAfter(LocalDate.now())) {
            throw new BusinessException(
                    "La fecha de vinculación no puede ser posterior a la fecha actual.");
        }

        if (request.getLinkingDate().isBefore(request.getDateOfBirth())) {
            throw new BusinessException(
                    "La fecha de vinculación no puede ser anterior a la fecha de nacimiento.");
        }
    }
}