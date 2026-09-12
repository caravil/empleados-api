package com.parameta.service.impl;

import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeCreatedResponse;
import com.parameta.dto.response.EmployeeDetailResponse;
import com.parameta.dto.response.EmployeeSummaryResponse;
import com.parameta.dto.response.EmployeeUpdatedResponse;
import com.parameta.entity.Employee;
import com.parameta.exception.BusinessException;
import com.parameta.exception.ResourceNotFoundException;
import com.parameta.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private EmployeeRequest buildValidRequest() {
        EmployeeRequest request = new EmployeeRequest();
        request.setName("Carlos");
        request.setLastName("Sánchez");
        request.setDocumentType("CC");
        request.setDocumentNumber("123456789");
        request.setDateOfBirth(LocalDate.now().minusYears(25));
        request.setLinkingDate(LocalDate.now().minusYears(1));
        request.setJobTitle("Developer");
        request.setSalary(3500000.0);
        return request;
    }

    private Employee buildEmployee(Long id, String documentNumber) {
        return Employee.builder()
                .id(id)
                .name("Carlos")
                .lastName("Sánchez")
                .documentType("CC")
                .documentNumber(documentNumber)
                .dateOfBirth(LocalDate.now().minusYears(25))
                .linkingDate(LocalDate.now().minusYears(1))
                .jobTitle("Developer")
                .salary(3500000.0)
                .build();
    }

    // ---------- createEmployee ----------

    @Test
    void createEmployee_deberiaCrearEmpleado_cuandoDatosSonValidos() {
        EmployeeRequest request = buildValidRequest();
        Employee employeeGuardado = buildEmployee(1L, request.getDocumentNumber());

        when(employeeRepository.findByDocumentNumber(request.getDocumentNumber()))
                .thenReturn(Optional.empty());
        when(employeeRepository.save(any(Employee.class)))
                .thenReturn(employeeGuardado);

        EmployeeCreatedResponse response = employeeService.createEmployee(request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getMessage()).isEqualTo("Employee created successfully");
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void createEmployee_deberiaLanzarExcepcion_cuandoDocumentoYaExiste() {
        EmployeeRequest request = buildValidRequest();
        Employee existente = buildEmployee(1L, request.getDocumentNumber());

        when(employeeRepository.findByDocumentNumber(request.getDocumentNumber()))
                .thenReturn(Optional.of(existente));

        assertThatThrownBy(() -> employeeService.createEmployee(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Ya existe un empleado");

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void createEmployee_deberiaLanzarExcepcion_cuandoValidacionDeNegocioFalla() {
        EmployeeRequest request = buildValidRequest();
        request.setDateOfBirth(LocalDate.now().minusYears(10));

        assertThatThrownBy(() -> employeeService.createEmployee(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("mayor de edad");

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    // ---------- getAllEmployees ----------

    @Test
    void getAllEmployees_deberiaRetornarListaDeResumenes() {
        Employee empleado1 = buildEmployee(1L, "111");
        Employee empleado2 = buildEmployee(2L, "222");

        when(employeeRepository.findAll()).thenReturn(List.of(empleado1, empleado2));

        List<EmployeeSummaryResponse> resultado = employeeService.getAllEmployees();

        assertThat(resultado).hasSize(2);
        assertThat(resultado.get(0).getId()).isEqualTo(1L);
        assertThat(resultado.get(1).getId()).isEqualTo(2L);
    }

    // ---------- getEmployeeById ----------

    @Test
    void getEmployeeById_deberiaRetornarDetalle_cuandoExiste() {
        Employee empleado = buildEmployee(1L, "123456789");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(empleado));

        EmployeeDetailResponse response = employeeService.getEmployeeById(1L);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getDocumentNumber()).isEqualTo("123456789");
    }

    @Test
    void getEmployeeById_deberiaLanzarExcepcion_cuandoNoExiste() {
        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeById(99L))
                .isInstanceOf(ResourceNotFoundException.class)
                .hasMessageContaining("99");
    }

    // ---------- updateEmployee ----------

    @Test
    void updateEmployee_deberiaActualizar_cuandoDatosSonValidos() {
        Employee empleadoExistente = buildEmployee(1L, "123456789");
        EmployeeRequest request = buildValidRequest();

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(empleadoExistente));
        when(employeeRepository.findByDocumentNumber(request.getDocumentNumber()))
                .thenReturn(Optional.of(empleadoExistente));
        when(employeeRepository.save(any(Employee.class))).thenReturn(empleadoExistente);

        EmployeeUpdatedResponse response = employeeService.updateEmployee(1L, request);

        assertThat(response.getId()).isEqualTo(1L);
        assertThat(response.getMessage()).isEqualTo("Employee updated successfully");
    }

    @Test
    void updateEmployee_deberiaLanzarExcepcion_cuandoEmpleadoNoExiste() {
        EmployeeRequest request = buildValidRequest();

        when(employeeRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(99L, request))
                .isInstanceOf(ResourceNotFoundException.class);

        verify(employeeRepository, never()).save(any(Employee.class));
    }

    @Test
    void updateEmployee_deberiaLanzarExcepcion_cuandoDocumentoPerteneceAOtroEmpleado() {
        Employee empleadoAActualizar = buildEmployee(1L, "111");
        Employee otroEmpleado = buildEmployee(2L, "999");
        EmployeeRequest request = buildValidRequest();
        request.setDocumentNumber("999");

        when(employeeRepository.findById(1L)).thenReturn(Optional.of(empleadoAActualizar));
        when(employeeRepository.findByDocumentNumber("999")).thenReturn(Optional.of(otroEmpleado));

        assertThatThrownBy(() -> employeeService.updateEmployee(1L, request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("Ya existe otro empleado");

        verify(employeeRepository, never()).save(any(Employee.class));
    }
}