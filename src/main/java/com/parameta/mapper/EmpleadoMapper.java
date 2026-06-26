package com.parameta.mapper;

import com.parameta.dto.request.EmpleadoRequest;
import com.parameta.dto.response.EmpleadoResponse;
import com.parameta.entity.Empleado;

public class EmpleadoMapper {

    private EmpleadoMapper() {
    }

    public static Empleado toEntity(
            EmpleadoRequest request) {

        return Empleado.builder()
                .nombres(request.getNombres())
                .apellidos(request.getApellidos())
                .tipoDocumento(request.getTipoDocumento())
                .numeroDocumento(request.getNumeroDocumento())
                .fechaNacimiento(request.getFechaNacimiento())
                .fechaVinculacion(request.getFechaVinculacion())
                .cargo(request.getCargo())
                .salario(request.getSalario())
                .build();
    }

    public static EmpleadoResponse toResponse(
            Empleado empleado,
            String edadActual,
            String tiempoVinculacion) {

        return EmpleadoResponse.builder()
                .id(empleado.getId())
                .nombres(empleado.getNombres())
                .apellidos(empleado.getApellidos())
                .tipoDocumento(empleado.getTipoDocumento())
                .numeroDocumento(empleado.getNumeroDocumento())
                .fechaNacimiento(empleado.getFechaNacimiento())
                .fechaVinculacion(empleado.getFechaVinculacion())
                .cargo(empleado.getCargo())
                .salario(empleado.getSalario())
                .edadActual(edadActual)
                .tiempoVinculacion(tiempoVinculacion)
                .build();
    }
}
