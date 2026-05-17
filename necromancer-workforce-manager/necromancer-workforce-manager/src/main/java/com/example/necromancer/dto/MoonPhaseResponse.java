package com.example.necromancer.dto;

public record MoonPhaseResponse(
        String requestedDate,
        String requestedLocation,
        String matchedLocationName,
        String matchedRegion,
        String matchedCountry,
        Double lat,
        Double lon,
        String tzId,
        String localTime,
        String phase,
        Integer illumination,
        Integer phaseBase,
        Integer illuminationBonus,
        Integer ritualBoost
) {
}