package com.example.necromancer.service;

import com.example.necromancer.client.WeatherApiClient;
import com.example.necromancer.dto.MoonPhaseResponse;
import com.example.necromancer.dto.WeatherApiAstronomyResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class MoonPhaseService {

    private final WeatherApiClient weatherApiClient;

    @Value("${weather.api.key}")
    private String apiKey;

    @Value("${weather.api.location}")
    private String location;

    public MoonPhaseService(WeatherApiClient weatherApiClient) {
        this.weatherApiClient = weatherApiClient;
    }

    public MoonPhaseResponse getTodayMoonPhase() {
        String requestedDate = LocalDate.now(ZoneId.of("Asia/Almaty")).toString();

        WeatherApiAstronomyResponse response =
                weatherApiClient.getAstronomy(apiKey, location, requestedDate);

        String phase = response.astronomy().astro().moon_phase();
        Integer illumination = response.astronomy().astro().moon_illumination();

        int phaseBase = calculatePhaseBase(phase);
        int illuminationBonus = calculateIlluminationBonus(illumination);
        int ritualBoost = phaseBase + illuminationBonus;

        return new MoonPhaseResponse(
                requestedDate,
                location,
                response.location().name(),
                response.location().region(),
                response.location().country(),
                response.location().lat(),
                response.location().lon(),
                response.location().tz_id(),
                response.location().localtime(),
                phase,
                illumination,
                phaseBase,
                illuminationBonus,
                ritualBoost
        );
    }

    private int calculatePhaseBase(String phase) {
        return switch (phase) {
            case "Full Moon" -> 20;
            case "Waxing Gibbous", "Waning Gibbous" -> 15;
            case "First Quarter", "Last Quarter" -> 10;
            case "Waxing Crescent", "Waning Crescent" -> 5;
            case "New Moon" -> 0;
            default -> 5;
        };
    }

    private int calculateIlluminationBonus(int illumination) {
        if (illumination >= 90) {
            return 10;
        } else if (illumination >= 75) {
            return 7;
        } else if (illumination >= 50) {
            return 4;
        } else if (illumination >= 25) {
            return 2;
        } else {
            return 0;
        }
    }
}