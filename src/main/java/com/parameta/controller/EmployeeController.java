package com.parameta.controller;

import java.util.List;
import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeCreatedResponse;
import com.parameta.dto.response.EmployeeSummaryResponse;
import com.parameta.dto.response.EmployeeDetailResponse;
import com.parameta.service.EmployeeService;
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
public class EmployeeController {

    private final EmployeeService EmployeeService;

    public EmployeeController(EmployeeService empleadoService) {
        this.EmployeeService = empleadoService;
    }

    @Operation(summary = "Crear empleado", description = "Registra un nuevo empleado y retorna la información calculada del mismo.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Empleado creado correctamente"),
            @ApiResponse(responseCode = "400", description = "La solicitud contiene datos inválidos")
    })

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeCreatedResponse createEmployee(
            @Valid @RequestBody EmployeeRequest request) {

        return EmployeeService.createEmployee(request);
    }

    @Operation(summary = "Obtiene todos los empleados", description = "Devuelve una lista de los empleados registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos recuperados con exito"),
            @ApiResponse(responseCode = "400", description = "Error al recuperar los datos")
    })

    @GetMapping
    public List<EmployeeSummaryResponse> getAllEmployees() {
        return EmployeeService.getAllEmployees();
    }

    @Operation(summary = "Obtiene un empleado por ID", description = "Devuelve la información detallada de un empleado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/{id}")
    public EmployeeDetailResponse getEmployeeById(@PathVariable Long id) {

        return EmployeeService.getEmployeeById(id);
    }
}