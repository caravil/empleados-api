package com.parameta.mapper;

import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeResponse;
import com.parameta.entity.Employee;

public class EmployeeMapper {

    private EmployeeMapper() {
    }

    public static Employee toEntity(EmployeeRequest request) {

        return Employee.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .documentType(request.getDocumentType())
                .documentNumber(request.getDocumentNumber())
                .dateOfBirth(request.getDateOfBirth())
                .linkingDate(request.getLinkingDate())
                .jobTitle(request.getJobTitle())
                .salary(request.getSalary())
                .build();
    }

    public static EmployeeResponse toResponse(
            Employee employee,
            String currentAge,
            String employmentDuration) {

        return EmployeeResponse.builder()
                .id(employee.getId())
                .name(employee.getName())
                .lastName(employee.getLastName())
                .documentType(employee.getDocumentType())
                .documentNumber(employee.getDocumentNumber())
                .dateOfBirth(employee.getDateOfBirth())
                .linkingDate(employee.getLinkingDate())
                .jobTitle(employee.getJobTitle())
                .salary(employee.getSalary())
                .currentAge(currentAge)
                .employmentDuration(employmentDuration)
                .build();
    }
}