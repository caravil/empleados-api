package com.parameta.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeCreatedResponse {

    private Long id;

    private String message;
}
