package com.parameta.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class EmployeeRequest {

    @NotBlank(message = "El nombre es obligatorio")
    @Schema(description = "Employee first name", example = "Carlos Andrés")
    private String name;

    @NotBlank(message = "El apellido es obligatorio")
    @Schema(description = "Employee last name", example = "Sánchez Avilés")
    private String lastName;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String documentType;

    @NotBlank(message = "El número de documento es obligatorio")
    private String documentNumber;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Schema(description = "Date of birth", example = "1998-05-10")
    private LocalDate dateOfBirth;

    @NotNull(message = "La fecha de vinculación es obligatoria")
    @Schema(description = "Employment start date", example = "2023-10-31")
    private LocalDate linkingDate;

    @NotBlank(message = "El cargo es obligatorio")
    @Schema(description = "Employee jobTitle", example = "Backend Developer")
    private String jobTitle;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser mayor que cero")
    @Schema(description = "Monthly salary", example = "3500000")
    private Double salary;
}