package com.example.necromancer.service;

import com.example.necromancer.dto.DecayStatusResponse;
import com.example.necromancer.entity.UndeadEmployee;
import org.springframework.stereotype.Service;

@Service
public class DecayStatusService {

    public DecayStatusResponse calculateStatus(UndeadEmployee employee) {
        int decayRate = employee.getDecayRate();

        String status;
        String recommendation;

        if (decayRate <= 30) {
            status = "STABLE";
            recommendation = "No reanimation needed";
        } else if (decayRate <= 70) {
            status = "WARNING";
            recommendation = "Monitor closely and prepare a ritual";
        } else {
            status = "URGENT_REANIMATION_REQUIRED";
            recommendation = "Schedule reanimation immediately";
        }

        return new DecayStatusResponse(
                employee.getId(),
                employee.getName(),
                decayRate,
                status,
                recommendation
        );
    }
}