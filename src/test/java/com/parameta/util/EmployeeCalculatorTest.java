package com.parameta.util;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

class EmployeeCalculatorTest {

    @Test
    void isAdult_deberiaRetornarTrue_cuandoTieneMasDe18Anios() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(20);

        boolean resultado = EmployeeCalculator.isAdult(fechaNacimiento);

        assertThat(resultado).isTrue();
    }

    @Test
    void isAdult_deberiaRetornarFalse_cuandoTieneMenosDe18Anios() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(15);

        boolean resultado = EmployeeCalculator.isAdult(fechaNacimiento);

        assertThat(resultado).isFalse();
    }

    @Test
    void isAdult_deberiaRetornarTrue_cuandoTieneExactamente18Anios() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(18);

        boolean resultado = EmployeeCalculator.isAdult(fechaNacimiento);

        assertThat(resultado).isTrue();
    }

    @Test
    void calculateAge_deberiaUsarSingularAnio_cuandoTieneExactamenteUnAnio() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(1);

        String resultado = EmployeeCalculator.calculateAge(fechaNacimiento);

        assertThat(resultado).startsWith("1 año,");
    }

    @Test
    void calculateAge_deberiaUsarPlural_cuandoTieneMasDeUnAnio() {
        LocalDate fechaNacimiento = LocalDate.now().minusYears(5);

        String resultado = EmployeeCalculator.calculateAge(fechaNacimiento);

        assertThat(resultado).startsWith("5 años,");
    }

    @Test
    void calculateEmploymentDuration_deberiaCalcularCorrectamente() {
        LocalDate fechaVinculacion = LocalDate.now().minusYears(2).minusMonths(3);

        String resultado = EmployeeCalculator.calculateEmploymentDuration(fechaVinculacion);

        assertThat(resultado).isEqualTo("2 años y 3 meses");
    }

    @Test
    void calculateEmploymentDuration_deberiaUsarSingular_cuandoEsUnAnioYUnMes() {
        LocalDate fechaVinculacion = LocalDate.now().minusYears(1).minusMonths(1);

        String resultado = EmployeeCalculator.calculateEmploymentDuration(fechaVinculacion);

        assertThat(resultado).isEqualTo("1 año y 1 mes");
    }
}