package com.example.necromancer.dto;

public record WeatherApiAstronomyResponse(
        Location location,
        Astronomy astronomy
) {
    public record Location(
            String name,
            String region,
            String country,
            Double lat,
            Double lon,
            String tz_id,
            String localtime
    ) {}

    public record Astronomy(
            Astro astro
    ) {}

    public record Astro(
            String moon_phase,
            Integer moon_illumination
    ) {}
}