package com.example.necromancer.controller;

import com.example.necromancer.dto.CreateReanimationTaskRequest;
import com.example.necromancer.dto.ReanimationCompletionResponse;
import com.example.necromancer.dto.ReanimationTaskResponse;
import com.example.necromancer.service.ReanimationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/reanimations")
public class ReanimationController {

    private final ReanimationService reanimationService;

    public ReanimationController(ReanimationService reanimationService) {
        this.reanimationService = reanimationService;
    }

    @GetMapping
    public ResponseEntity<List<ReanimationTaskResponse>> getAllReanimations(
            @RequestParam(required = false) String status) {
        try {
            return ResponseEntity.ok(reanimationService.getAllTasks(status));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReanimationTaskResponse> getReanimationById(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reanimationService.getTaskById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<ReanimationTaskResponse> createReanimationTask(
            @RequestBody CreateReanimationTaskRequest request) {
        try {
            return ResponseEntity.ok(reanimationService.createTask(request));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{id}/complete")
    public ResponseEntity<ReanimationCompletionResponse> completeReanimation(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(reanimationService.completeTask(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        } catch (IllegalStateException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}