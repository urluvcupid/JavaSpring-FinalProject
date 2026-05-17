package com.example.necromancer.service;

import com.example.necromancer.dto.ComplaintResponse;
import com.example.necromancer.dto.CreateComplaintRequest;
import com.example.necromancer.entity.Complaint;
import com.example.necromancer.entity.UndeadEmployee;
import com.example.necromancer.repository.ComplaintRepository;
import com.example.necromancer.repository.UndeadEmployeeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class ComplaintService {

    private final ComplaintRepository complaintRepository;
    private final UndeadEmployeeRepository employeeRepository;

    public ComplaintService(ComplaintRepository complaintRepository,
                            UndeadEmployeeRepository employeeRepository) {
        this.complaintRepository = complaintRepository;
        this.employeeRepository = employeeRepository;
    }

    @Transactional(readOnly = true)
    public List<ComplaintResponse> getAllComplaints() {
        return complaintRepository.findAll().stream().map(this::toResponse).toList();
    }

    @Transactional
    public ComplaintResponse createComplaint(CreateComplaintRequest request) {
        UndeadEmployee employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));

        Complaint complaint = new Complaint();
        complaint.setComplainantName(request.complainantName());
        complaint.setDescription(request.description());
        complaint.setSeverity(request.severity());
        complaint.setEmployee(employee);

        Complaint savedComplaint = complaintRepository.save(complaint);

        return toResponse(savedComplaint);
    }

    private ComplaintResponse toResponse(Complaint complaint) {
        return new ComplaintResponse(
                complaint.getId(),
                complaint.getComplainantName(),
                complaint.getDescription(),
                complaint.getSeverity().name(),
                complaint.getEmployee().getId(),
                complaint.getEmployee().getName()
        );
    }
}