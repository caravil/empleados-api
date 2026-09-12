package com.parameta.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeCreatedResponse;
import com.parameta.dto.response.EmployeeDetailResponse;
import com.parameta.dto.response.EmployeeSummaryResponse;
import com.parameta.exception.ResourceNotFoundException;
import com.parameta.service.EmployeeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EmployeeController.class)
class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private EmployeeService employeeService;

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

    @Test
    void createEmployee_deberiaRetornar201_cuandoDatosSonValidos() throws Exception {
        EmployeeRequest request = buildValidRequest();
        EmployeeCreatedResponse response = EmployeeCreatedResponse.builder()
                .id(1L)
                .message("Employee created successfully")
                .build();

        when(employeeService.createEmployee(any(EmployeeRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.message").value("Employee created successfully"));
    }

    @Test
    void createEmployee_deberiaRetornar400_cuandoFaltaCampoObligatorio() throws Exception {
        EmployeeRequest request = buildValidRequest();
        request.setName(null);

        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.name").exists());
    }

    @Test
    void createEmployee_deberiaRetornar400_cuandoSalarioEsNegativo() throws Exception {
        EmployeeRequest request = buildValidRequest();
        request.setSalary(-100.0);

        mockMvc.perform(post("/api/empleados")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errores.salary").exists());
    }

    @Test
    void getAllEmployees_deberiaRetornar200ConLista() throws Exception {
        EmployeeSummaryResponse resumen = EmployeeSummaryResponse.builder()
                .id(1L)
                .name("Carlos")
                .lastName("Sánchez")
                .jobTitle("Developer")
                .build();

        when(employeeService.getAllEmployees()).thenReturn(List.of(resumen));

        mockMvc.perform(get("/api/empleados"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].lastName").value("Sánchez"));
    }

    @Test
    void getEmployeeById_deberiaRetornar200_cuandoExiste() throws Exception {
        EmployeeDetailResponse detalle = EmployeeDetailResponse.builder()
                .id(1L)
                .name("Carlos")
                .documentNumber("123456789")
                .build();

        when(employeeService.getEmployeeById(1L)).thenReturn(detalle);

        mockMvc.perform(get("/api/empleados/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
    }

    @Test
    void getEmployeeById_deberiaRetornar404_cuandoNoExiste() throws Exception {
        when(employeeService.getEmployeeById(eq(99L)))
                .thenThrow(new ResourceNotFoundException("Employee not found with id: 99"));

        mockMvc.perform(get("/api/empleados/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.mensaje").value("Employee not found with id: 99"));
    }
}