package com.parameta.service;

import com.parameta.dto.request.EmployeeRequest;
import com.parameta.dto.response.EmployeeResponse;

public interface EmployeeService {

    EmployeeResponse createEmployee(EmployeeRequest request);

}