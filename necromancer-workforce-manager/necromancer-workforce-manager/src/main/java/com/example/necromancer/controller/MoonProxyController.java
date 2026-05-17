package com.example.necromancer.controller;

import com.example.necromancer.dto.MoonPhaseResponse;
import com.example.necromancer.service.MoonPhaseService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/moon")
public class MoonProxyController {

    private final MoonPhaseService moonPhaseService;

    public MoonProxyController(MoonPhaseService moonPhaseService) {
        this.moonPhaseService = moonPhaseService;
    }

    @GetMapping("/today")
    public MoonPhaseResponse getMoonPhase() {
        return moonPhaseService.getTodayMoonPhase();
    }
}