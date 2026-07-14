package com.parameta.util;

import java.time.LocalDate;
import java.time.Period;

public class EmployeeCalculator {

    private EmployeeCalculator() {
    }

    public static boolean isAdult(LocalDate dateOfBirth) {
        return Period.between(dateOfBirth, LocalDate.now()).getYears() >= 18;
    }

    public static String calculateAge(LocalDate dateOfBirth) {

        Period period = Period.between(
                dateOfBirth,
                LocalDate.now());

        String years = period.getYears() == 1
                ? "1 año"
                : period.getYears() + " años";

        String months = period.getMonths() == 1
                ? "1 mes"
                : period.getMonths() + " meses";

        String days = period.getDays() == 1
                ? "1 día"
                : period.getDays() + " días";

        return years + ", " + months + " y " + days;
    }

    public static String calculateEmploymentDuration(LocalDate linkingDate) {

        Period period = Period.between(
                linkingDate,
                LocalDate.now());

        String years = period.getYears() == 1
                ? "1 año"
                : period.getYears() + " años";

        String months = period.getMonths() == 1
                ? "1 mes"
                : period.getMonths() + " meses";

        return years + " y " + months;
    }
}