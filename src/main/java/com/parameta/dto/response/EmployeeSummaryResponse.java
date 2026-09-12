package com.parameta.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EmployeeSummaryResponse {

    private Long id;

    private String name;

    private String lastName;

    private String jobTitle;
}