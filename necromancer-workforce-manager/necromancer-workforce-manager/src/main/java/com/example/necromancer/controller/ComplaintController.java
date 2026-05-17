package com.example.necromancer.controller;

import com.example.necromancer.dto.ComplaintResponse;
import com.example.necromancer.dto.CreateComplaintRequest;
import com.example.necromancer.service.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/complaints")
public class ComplaintController {

    private final ComplaintService complaintService;

    public ComplaintController(ComplaintService complaintService) {
        this.complaintService = complaintService;
    }

    @GetMapping
    public List<ComplaintResponse> getAllComplaints() {
        return complaintService.getAllComplaints();
    }

    @PostMapping
    public ResponseEntity<ComplaintResponse> createComplaint(@Valid @RequestBody CreateComplaintRequest request) {
        try {
            return ResponseEntity.ok(complaintService.createComplaint(request));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}