package com.example.necromancer.service;

import com.example.necromancer.dto.CreateReanimationTaskRequest;
import com.example.necromancer.dto.MoonPhaseResponse;
import com.example.necromancer.dto.ReanimationCompletionResponse;
import com.example.necromancer.dto.ReanimationTaskResponse;
import com.example.necromancer.entity.ReanimationStatus;
import com.example.necromancer.entity.ReanimationTask;
import com.example.necromancer.entity.UndeadEmployee;
import com.example.necromancer.repository.ReanimationTaskRepository;
import com.example.necromancer.repository.UndeadEmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ReanimationService {

    private final ReanimationTaskRepository reanimationTaskRepository;
    private final UndeadEmployeeRepository employeeRepository;
    private final MoonPhaseService moonPhaseService;

    public ReanimationService(ReanimationTaskRepository reanimationTaskRepository,
                              UndeadEmployeeRepository employeeRepository,
                              MoonPhaseService moonPhaseService) {
        this.reanimationTaskRepository = reanimationTaskRepository;
        this.employeeRepository = employeeRepository;
        this.moonPhaseService = moonPhaseService;
    }

    public List<ReanimationTaskResponse> getAllTasks(String status) {
        List<ReanimationTask> tasks;

        if (status == null || status.isBlank()) {
            tasks = reanimationTaskRepository.findAll();
        } else {
            ReanimationStatus reanimationStatus = ReanimationStatus.valueOf(status.toUpperCase());
            tasks = reanimationTaskRepository.findByStatus(reanimationStatus);
        }

        return tasks.stream().map(this::toResponse).toList();
    }

    @Transactional
    public ReanimationTaskResponse createTask(CreateReanimationTaskRequest request) {
        UndeadEmployee employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));

        ReanimationTask task = new ReanimationTask();
        task.setEmployee(employee);
        task.setScheduledDate(request.scheduledDate());
        task.setNotes(request.notes());
        task.setStatus(ReanimationStatus.PLANNED);

        ReanimationTask savedTask = reanimationTaskRepository.save(task);

        return toResponse(savedTask);
    }

    @Transactional
    public ReanimationCompletionResponse completeTask(Long taskId) {
        ReanimationTask task = reanimationTaskRepository.findById(taskId) //1 shag
                .orElseThrow(() -> new NoSuchElementException("Reanimation task not found"));

        if (task.getStatus() == ReanimationStatus.COMPLETED) { //checkaet it is not completed
            throw new IllegalStateException("This reanimation task is already completed");
        }

        UndeadEmployee employee = task.getEmployee();

        int oldDecayRate = employee.getDecayRate();

        MoonPhaseResponse moonPhase = moonPhaseService.getTodayMoonPhase(); //eto for getting phase illum etc.

        int baseReduction = 5;
        int totalReduction = baseReduction + moonPhase.ritualBoost();

        int newDecayRate = Math.max(0, oldDecayRate - totalReduction); //уменьшает но не ниже 0

        employee.setDecayRate(newDecayRate);
        employee.setLastReanimationDate(LocalDate.now(ZoneId.of("Asia/Almaty")));

        task.setStatus(ReanimationStatus.COMPLETED);

        String oldNotes = task.getNotes() == null ? "" : task.getNotes();
        String completionNote = " | Completed under " + moonPhase.phase()
                + " with ritual boost " + moonPhase.ritualBoost();
        task.setNotes(oldNotes + completionNote);

        employeeRepository.save(employee);
        reanimationTaskRepository.save(task);

        return new ReanimationCompletionResponse(
                task.getId(),
                employee.getId(),
                employee.getName(),
                oldDecayRate,
                newDecayRate,
                moonPhase.phase(),
                moonPhase.illumination(),
                moonPhase.phaseBase(),
                moonPhase.illuminationBonus(),
                moonPhase.ritualBoost(),
                baseReduction,
                totalReduction,
                task.getStatus().name(),
                employee.getLastReanimationDate().toString()
        );
    }

    private ReanimationTaskResponse toResponse(ReanimationTask task) {
        return new ReanimationTaskResponse(
                task.getId(),
                task.getScheduledDate(),
                task.getStatus().name(),
                task.getNotes(),
                task.getEmployee().getId(),
                task.getEmployee().getName()
        );
    }


    public ReanimationTaskResponse getTaskById(Long id) {
        ReanimationTask task = reanimationTaskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Reanimation task not found"));

        return toResponse(task);
    }
}