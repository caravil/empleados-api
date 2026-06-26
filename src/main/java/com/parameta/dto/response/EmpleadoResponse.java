package com.parameta.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class EmpleadoResponse {

    private Long id;

    private String nombres;

    private String apellidos;

    private String tipoDocumento;

    private String numeroDocumento;

    private LocalDate fechaNacimiento;

    private LocalDate fechaVinculacion;

    private String cargo;

    private Double salario;

    private String edadActual;

    private String tiempoVinculacion;
}