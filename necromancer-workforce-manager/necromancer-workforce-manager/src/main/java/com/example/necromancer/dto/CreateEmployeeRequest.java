package com.example.necromancer.dto;

import com.example.necromancer.entity.EmployeeType;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record CreateEmployeeRequest(
        @NotBlank
        String name,

        @NotNull
        EmployeeType type,

        @NotBlank
        String position,

        @NotNull
        @Min(0)
        @Max(100)
        Integer decayRate,

        LocalDate lastReanimationDate,

        @NotNull
        Long departmentId
) {
}