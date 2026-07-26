package com.parameta.service;

import java.util.List;
import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeCreatedResponse;
import com.parameta.dto.response.EmployeeSummaryResponse;
import com.parameta.dto.response.EmployeeDetailResponse;
import com.parameta.dto.response.EmployeeUpdatedResponse;

public interface EmployeeService {

    EmployeeCreatedResponse createEmployee(EmployeeRequest request);

    List<EmployeeSummaryResponse> getAllEmployees();

    EmployeeDetailResponse getEmployeeById(Long id);

    EmployeeUpdatedResponse updateEmployee(
            Long id,
            EmployeeRequest request);
}