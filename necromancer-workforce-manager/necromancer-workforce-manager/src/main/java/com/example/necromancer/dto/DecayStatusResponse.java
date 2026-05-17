package com.example.necromancer.dto;

public record DecayStatusResponse(
        Long employeeId,
        String employeeName,
        Integer decayRate,
        String status,
        String recommendation
) {
}