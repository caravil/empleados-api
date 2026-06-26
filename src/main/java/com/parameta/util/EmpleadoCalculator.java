package com.parameta.util;

import java.time.LocalDate;
import java.time.Period;

public class EmpleadoCalculator {

    public static boolean esMayorDeEdad(LocalDate fechaNacimiento) {
        return Period.between(fechaNacimiento, LocalDate.now()).getYears() >= 18;
    }

    public static String calcularEdad(LocalDate fechaNacimiento) {

        Period periodo = Period.between(
                fechaNacimiento,
                LocalDate.now());

        String anios = periodo.getYears() == 1
                ? "1 año"
                : periodo.getYears() + " años";

        String meses = periodo.getMonths() == 1
                ? "1 mes"
                : periodo.getMonths() + " meses";

        String dias = periodo.getDays() == 1
                ? "1 día"
                : periodo.getDays() + " días";

        return anios + ", " + meses + " y " + dias;
    }

    public static String calcularTiempoVinculacion(
            LocalDate fechaVinculacion) {

        Period periodo = Period.between(
                fechaVinculacion,
                LocalDate.now());

        String anios = periodo.getYears() == 1
                ? "1 año"
                : periodo.getYears() + " años";

        String meses = periodo.getMonths() == 1
                ? "1 mes"
                : periodo.getMonths() + " meses";

        return anios + " y " + meses;
    }
}
