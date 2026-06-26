package com.parameta.controller;

import com.parameta.dto.request.EmpleadoRequest;
import com.parameta.dto.response.EmpleadoResponse;
import com.parameta.service.EmpleadoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/empleados")
@Tag(name = "Empleados", description = "Operaciones relacionadas con empleados")
public class EmpleadoController {

    private final EmpleadoService empleadoService;

    public EmpleadoController(EmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    @Operation(summary = "Crear empleado", description = "Registra un nuevo empleado y retorna la información calculada del mismo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado creado correctamente"),
            @ApiResponse(responseCode = "400", description = "La solicitud contiene datos inválidos")
    })
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmpleadoResponse crearEmpleado(
            @Valid @RequestBody EmpleadoRequest request) {

        return empleadoService.crearEmpleado(request);
    }
}