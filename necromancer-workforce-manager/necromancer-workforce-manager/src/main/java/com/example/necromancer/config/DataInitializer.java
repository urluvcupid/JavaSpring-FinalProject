package com.example.necromancer.config;

import com.example.necromancer.entity.*;
import com.example.necromancer.repository.ComplaintRepository;
import com.example.necromancer.repository.DepartmentRepository;
import com.example.necromancer.repository.ReanimationTaskRepository;
import com.example.necromancer.repository.UndeadEmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(
            DepartmentRepository departmentRepository,
            UndeadEmployeeRepository employeeRepository,
            ComplaintRepository complaintRepository,
            ReanimationTaskRepository reanimationTaskRepository
    ) {
        return args -> {
            if (employeeRepository.count() > 0) {
                return;
            }

            Department ritualOps = departmentRepository.save(new Department("Ritual Operations"));
            Department graveyardLogistics = departmentRepository.save(new Department("Graveyard Logistics"));

            UndeadEmployee morvin = employeeRepository.save(
                    new UndeadEmployee(
                            "Morvin the Zombie",
                            EmployeeType.ZOMBIE,
                            "Grave Digger",
                            25,
                            LocalDate.now().minusDays(10),
                            graveyardLogistics
                    )
            );

            UndeadEmployee jack = employeeRepository.save(
                    new UndeadEmployee(
                            "Skeleton Jack",
                            EmployeeType.SKELETON,
                            "Night Accountant",
                            82,
                            LocalDate.now().minusDays(40),
                            ritualOps
                    )
            );

            UndeadEmployee whisper = employeeRepository.save(
                    new UndeadEmployee(
                            "Whisper Shade",
                            EmployeeType.GHOST,
                            "Archive Keeper",
                            61,
                            LocalDate.now().minusDays(20),
                            ritualOps
                    )
            );

            complaintRepository.save(
                    new Complaint(
                            "Alice Mortis",
                            "Drops loose bones in the main hallway.",
                            SeverityLevel.MEDIUM,
                            jack
                    )
            );

            complaintRepository.save(
                    new Complaint(
                            "Intern Pavel",
                            "Scares living coworkers during the night shift.",
                            SeverityLevel.HIGH,
                            whisper
                    )
            );

            reanimationTaskRepository.save(
                    new ReanimationTask(
                            LocalDate.now().plusDays(1),
                            ReanimationStatus.PLANNED,
                            "Urgent midnight reanimation ritual required.",
                            jack
                    )
            );
        };
    }
}