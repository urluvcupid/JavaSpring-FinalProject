package com.example.necromancer.dto;

public record ComplaintResponse(
        Long id,
        String complainantName,
        String description,
        String severity,
        Long employeeId,
        String employeeName
) {
}