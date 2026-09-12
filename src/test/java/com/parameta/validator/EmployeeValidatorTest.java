package com.parameta.validator;

import com.parameta.dto.request.EmployeeRequest;
import com.parameta.exception.BusinessException;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EmployeeValidatorTest {

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
    void validate_noDeberiaLanzarExcepcion_cuandoDatosSonValidos() {
        EmployeeRequest request = buildValidRequest();

        assertThatCode(() -> EmployeeValidator.validate(request))
                .doesNotThrowAnyException();
    }

    @Test
    void validate_deberiaLanzarExcepcion_cuandoFechaNacimientoEsFutura() {
        EmployeeRequest request = buildValidRequest();
        request.setDateOfBirth(LocalDate.now().plusDays(1));

        assertThatThrownBy(() -> EmployeeValidator.validate(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("fecha de nacimiento no puede ser posterior");
    }

    @Test
    void validate_deberiaLanzarExcepcion_cuandoEsMenorDeEdad() {
        EmployeeRequest request = buildValidRequest();
        request.setDateOfBirth(LocalDate.now().minusYears(15));

        assertThatThrownBy(() -> EmployeeValidator.validate(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("mayor de edad");
    }

    @Test
    void validate_deberiaLanzarExcepcion_cuandoFechaVinculacionEsFutura() {
        EmployeeRequest request = buildValidRequest();
        request.setLinkingDate(LocalDate.now().plusDays(1));

        assertThatThrownBy(() -> EmployeeValidator.validate(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("vinculación no puede ser posterior");
    }

    @Test
    void validate_deberiaLanzarExcepcion_cuandoVinculacionEsAntesDeNacimiento() {
        EmployeeRequest request = buildValidRequest();
        request.setDateOfBirth(LocalDate.now().minusYears(20));
        request.setLinkingDate(LocalDate.now().minusYears(25));

        assertThatThrownBy(() -> EmployeeValidator.validate(request))
                .isInstanceOf(BusinessException.class)
                .hasMessageContaining("vinculación no puede ser anterior");
    }

    @Test
    void validate_deberiaPermitir_cuandoVinculacionEsElMismoDiaDeNacimiento() {
        LocalDate fecha = LocalDate.now().minusYears(20);
        EmployeeRequest request = buildValidRequest();
        request.setDateOfBirth(fecha);
        request.setLinkingDate(fecha);

        assertThatCode(() -> EmployeeValidator.validate(request))
                .doesNotThrowAnyException();
    }
}