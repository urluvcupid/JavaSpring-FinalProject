package com.example.necromancer.dto;

import java.time.LocalDate;

public record CreateReanimationTaskRequest(
        Long employeeId,
        LocalDate scheduledDate,
        String notes
) {
}