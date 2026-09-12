package com.parameta.mapper;

import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeDetailResponse;
import com.parameta.dto.response.EmployeeSummaryResponse;
import com.parameta.entity.Employee;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeMapperTest {

    private Employee buildEmployee() {
        return Employee.builder()
                .id(1L)
                .name("Carlos")
                .lastName("Sánchez")
                .documentType("CC")
                .documentNumber("123456789")
                .dateOfBirth(LocalDate.of(1998, 5, 10))
                .linkingDate(LocalDate.of(2023, 10, 31))
                .jobTitle("Backend Developer")
                .salary(3500000.0)
                .build();
    }

    private EmployeeRequest buildRequest() {
        EmployeeRequest request = new EmployeeRequest();
        request.setName("Carlos");
        request.setLastName("Sánchez");
        request.setDocumentType("CC");
        request.setDocumentNumber("123456789");
        request.setDateOfBirth(LocalDate.of(1998, 5, 10));
        request.setLinkingDate(LocalDate.of(2023, 10, 31));
        request.setJobTitle("Backend Developer");
        request.setSalary(3500000.0);
        return request;
    }

    @Test
    void toEntity_deberiaMapearTodosLosCampos() {
        EmployeeRequest request = buildRequest();

        Employee employee = EmployeeMapper.toEntity(request);

        assertThat(employee.getName()).isEqualTo(request.getName());
        assertThat(employee.getLastName()).isEqualTo(request.getLastName());
        assertThat(employee.getDocumentType()).isEqualTo(request.getDocumentType());
        assertThat(employee.getDocumentNumber()).isEqualTo(request.getDocumentNumber());
        assertThat(employee.getDateOfBirth()).isEqualTo(request.getDateOfBirth());
        assertThat(employee.getLinkingDate()).isEqualTo(request.getLinkingDate());
        assertThat(employee.getJobTitle()).isEqualTo(request.getJobTitle());
        assertThat(employee.getSalary()).isEqualTo(request.getSalary());
    }

    @Test
    void toSummaryResponse_deberiaMapearSoloCamposDeResumen() {
        Employee employee = buildEmployee();

        EmployeeSummaryResponse response = EmployeeMapper.toSummaryResponse(employee);

        assertThat(response.getId()).isEqualTo(employee.getId());
        assertThat(response.getName()).isEqualTo(employee.getName());
        assertThat(response.getLastName()).isEqualTo(employee.getLastName());
        assertThat(response.getJobTitle()).isEqualTo(employee.getJobTitle());
    }

    @Test
    void toDetailResponse_deberiaMapearTodosLosCampos() {
        Employee employee = buildEmployee();

        EmployeeDetailResponse response = EmployeeMapper.toDetailResponse(employee);

        assertThat(response.getId()).isEqualTo(employee.getId());
        assertThat(response.getName()).isEqualTo(employee.getName());
        assertThat(response.getLastName()).isEqualTo(employee.getLastName());
        assertThat(response.getDocumentType()).isEqualTo(employee.getDocumentType());
        assertThat(response.getDocumentNumber()).isEqualTo(employee.getDocumentNumber());
        assertThat(response.getDateOfBirth()).isEqualTo(employee.getDateOfBirth());
        assertThat(response.getLinkingDate()).isEqualTo(employee.getLinkingDate());
        assertThat(response.getJobTitle()).isEqualTo(employee.getJobTitle());
        assertThat(response.getSalary()).isEqualTo(employee.getSalary());
    }

    @Test
    void updateEntity_deberiaActualizarTodosLosCamposDelEmpleadoExistente() {
        Employee employeeExistente = buildEmployee();
        EmployeeRequest nuevaData = buildRequest();
        nuevaData.setName("Andrés");
        nuevaData.setSalary(4000000.0);

        EmployeeMapper.updateEntity(employeeExistente, nuevaData);

        assertThat(employeeExistente.getName()).isEqualTo("Andrés");
        assertThat(employeeExistente.getSalary()).isEqualTo(4000000.0);
        assertThat(employeeExistente.getLastName()).isEqualTo(nuevaData.getLastName());
    }
}