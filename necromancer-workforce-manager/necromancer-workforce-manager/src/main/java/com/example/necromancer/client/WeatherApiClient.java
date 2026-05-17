package com.example.necromancer.client;

import com.example.necromancer.dto.WeatherApiAstronomyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "weather-api-client", url = "${weather.api.base-url}")
public interface WeatherApiClient {

    @GetMapping("/astronomy.json")
    WeatherApiAstronomyResponse getAstronomy(
            @RequestParam("key") String apiKey,
            @RequestParam("q") String location,
            @RequestParam("dt") String date
    );
}