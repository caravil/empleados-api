package com.parameta.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import java.time.LocalDate;

import io.swagger.v3.oas.annotations.media.Schema;

@Data
public class EmpleadoRequest {

    @NotBlank(message = "Los nombres son obligatorios")
    @Schema(description = "Nombres del empleado", example = "Carlos Andrés")
    private String nombres;

    @NotBlank(message = "Los apellidos son obligatorios")
    @Schema(description = "Apellidos del empleado", example = "Sánchez Avilés")
    private String apellidos;

    @NotBlank(message = "El tipo de documento es obligatorio")
    private String tipoDocumento;

    @NotBlank(message = "El número de documento es obligatorio")
    private String numeroDocumento;

    @NotNull(message = "La fecha de nacimiento es obligatoria")
    @Schema(description = "Fecha de nacimiento", example = "1998-05-10")
    private LocalDate fechaNacimiento;

    @NotNull(message = "La fecha de vinculación es obligatoria")
    @Schema(description = "Fecha de vinculación", example = "1999-10-31")
    private LocalDate fechaVinculacion;

    @NotBlank(message = "El cargo es obligatorio")
    private String cargo;

    @NotNull(message = "El salario es obligatorio")
    @Positive(message = "El salario debe ser mayor que cero")
    @Schema(description = "Salario mensual", example = "3500000")
    private Double salario;
}