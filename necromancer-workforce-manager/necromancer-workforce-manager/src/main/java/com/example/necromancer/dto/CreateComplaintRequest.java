package com.example.necromancer.dto;

import com.example.necromancer.entity.SeverityLevel;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreateComplaintRequest(
        @NotBlank
        String complainantName,

        @NotBlank
        @Size(max = 1000)
        String description,

        @NotNull
        SeverityLevel severity,

        @NotNull
        Long employeeId
) {
}