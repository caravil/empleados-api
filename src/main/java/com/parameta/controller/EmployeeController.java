package com.parameta.controller;
import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeResponse;
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
    public EmployeeResponse createEmployee(
            @Valid @RequestBody EmployeeRequest request) {
        return EmployeeService.createEmployee(request);
    }
}