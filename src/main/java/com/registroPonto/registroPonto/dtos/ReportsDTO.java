package com.registroPonto.registroPonto.dtos;

import java.util.List;

public record ReportsDTO(
        Double totalHours,
       List<EmployeeDTO> employeeDTO
) {
}
