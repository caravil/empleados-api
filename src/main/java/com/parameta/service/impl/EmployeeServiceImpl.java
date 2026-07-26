package com.parameta.service.impl;

import java.util.List;
import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeCreatedResponse;
import com.parameta.dto.response.EmployeeSummaryResponse;
import com.parameta.dto.response.EmployeeDetailResponse;
import com.parameta.entity.Employee;
import com.parameta.exception.BusinessException;
import com.parameta.exception.ResourceNotFoundException;
import com.parameta.mapper.EmployeeMapper;
import com.parameta.repository.EmployeeRepository;
import com.parameta.service.EmployeeService;
import com.parameta.util.EmployeeCalculator;
import com.parameta.validator.EmployeeValidator;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public EmployeeCreatedResponse createEmployee(EmployeeRequest request) {

        EmployeeValidator.validate(request);

        if (employeeRepository
                .findByDocumentNumber(request.getDocumentNumber())
                .isPresent()) {

            throw new BusinessException(
                    "Ya existe un empleado con ese número de documento.");
        }

        Employee employee = EmployeeMapper.toEntity(request);

        Employee savedEmployee = employeeRepository.save(employee);

        return EmployeeCreatedResponse.builder()
                .id(savedEmployee.getId())
                .message("Employee created successfully")
                .build();
    }

    @Override
    public List<EmployeeSummaryResponse> getAllEmployees() {
        {

            return employeeRepository.findAll()
                    .stream()
                    .map(EmployeeMapper::toSummaryResponse)
                    .toList();
        }
    }
    @Override
    public EmployeeDetailResponse getEmployeeById(Long id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Employee not found with id: " + id));

        return EmployeeMapper.toDetailResponse(employee);
    }
}