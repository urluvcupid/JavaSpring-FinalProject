package com.example.necromancer.dto;

public record ReanimationCompletionResponse(
        Long taskId,
        Long employeeId,
        String employeeName,
        Integer oldDecayRate,
        Integer newDecayRate,
        String moonPhase,
        Integer illumination,
        Integer phaseBase,
        Integer illuminationBonus,
        Integer ritualBoost,
        Integer baseReduction,
        Integer totalReduction,
        String taskStatus,
        String completionDate
) {
}