package com.parameta.controller;

import java.util.List;
import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeCreatedResponse;
import com.parameta.dto.response.EmployeeSummaryResponse;
import com.parameta.dto.response.EmployeeDetailResponse;
import com.parameta.dto.response.EmployeeUpdatedResponse;
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

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService empleadoService) {
        this.employeeService = empleadoService;
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

        return employeeService.createEmployee(request);
    }

    @Operation(summary = "Obtiene todos los empleados", description = "Devuelve una lista de los empleados registrados.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Datos recuperados con exito"),
            @ApiResponse(responseCode = "400", description = "Error al recuperar los datos")
    })

    @GetMapping
    public List<EmployeeSummaryResponse> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @Operation(summary = "Obtiene un empleado por ID", description = "Devuelve la información detallada de un empleado.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Empleado encontrado"),
            @ApiResponse(responseCode = "404", description = "Empleado no encontrado")
    })
    @GetMapping("/{id}")
    public EmployeeDetailResponse getEmployeeById(@PathVariable Long id) {

        return employeeService.getEmployeeById(id);
    }

    @Operation(summary = "Actualizar empleado", description = "Updates an existing employee.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Employee updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid employee data"),
            @ApiResponse(responseCode = "404", description = "Employee not found")
    })
    @PutMapping("/{id}")
    public EmployeeUpdatedResponse updateEmployee(
            @PathVariable Long id,
            @Valid @RequestBody EmployeeRequest request) {

        return employeeService.updateEmployee(id, request);
    }

}