package com.example.necromancer.controller;

import com.example.necromancer.dto.CreateEmployeeRequest;
import com.example.necromancer.dto.DecayStatusResponse;
import com.example.necromancer.dto.EmployeeResponse;
import com.example.necromancer.entity.Department;
import com.example.necromancer.entity.UndeadEmployee;
import com.example.necromancer.repository.DepartmentRepository;
import com.example.necromancer.repository.UndeadEmployeeRepository;
import com.example.necromancer.service.DecayStatusService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final UndeadEmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final DecayStatusService decayStatusService;

    public EmployeeController(UndeadEmployeeRepository employeeRepository,
                              DepartmentRepository departmentRepository,
                              DecayStatusService decayStatusService) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.decayStatusService = decayStatusService;
    }

    @GetMapping
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponse> getEmployeeById(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(this::toResponse)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/decay-status")
    public ResponseEntity<DecayStatusResponse> getDecayStatus(@PathVariable Long id) {
        return employeeRepository.findById(id)
                .map(decayStatusService::calculateStatus)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<EmployeeResponse> createEmployee(@Valid @RequestBody CreateEmployeeRequest request) {
        try {
            Department department = departmentRepository.findById(request.departmentId())
                    .orElseThrow(() -> new NoSuchElementException("Department not found"));

            UndeadEmployee employee = new UndeadEmployee();
            employee.setName(request.name());
            employee.setType(request.type());
            employee.setPosition(request.position());
            employee.setDecayRate(request.decayRate());
            employee.setLastReanimationDate(request.lastReanimationDate());
            employee.setDepartment(department);

            UndeadEmployee savedEmployee = employeeRepository.save(employee);

            return ResponseEntity.ok(toResponse(savedEmployee));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    private EmployeeResponse toResponse(UndeadEmployee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getName(),
                employee.getType().name(),
                employee.getPosition(),
                employee.getDecayRate(),
                employee.getLastReanimationDate(),
                employee.getDepartment() != null ? employee.getDepartment().getId() : null,
                employee.getDepartment() != null ? employee.getDepartment().getName() : null
        );
    }
}