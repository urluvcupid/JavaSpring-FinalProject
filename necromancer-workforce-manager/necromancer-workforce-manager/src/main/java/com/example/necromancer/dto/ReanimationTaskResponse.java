package com.example.necromancer.dto;

import java.time.LocalDate;

public record ReanimationTaskResponse(
        Long id,
        LocalDate scheduledDate,
        String status,
        String notes,
        Long employeeId,
        String employeeName
) {
}