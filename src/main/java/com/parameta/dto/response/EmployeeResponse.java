package com.parameta.dto.response;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class EmployeeResponse {

    private Long id;

    private String name;

    private String lastName;

    private String documentType;

    private String documentNumber;

    private LocalDate dateOfBirth;

    private LocalDate linkingDate;

    private String jobTitle;

    private Double salary;

    private String currentAge;

    private String employmentDuration;
}
