package com.example.necromancer.dto;

import java.time.LocalDate;

public record EmployeeResponse(
        Long id,
        String name,
        String type,
        String position,
        Integer decayRate,
        LocalDate lastReanimationDate,
        Long departmentId,
        String departmentName
) {
}