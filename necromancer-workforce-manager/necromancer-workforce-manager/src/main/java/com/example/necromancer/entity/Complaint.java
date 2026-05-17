package com.example.necromancer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "complaints")
public class Complaint {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String complainantName;

    @Column(nullable = false, length = 1000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeverityLevel severity;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore
    private UndeadEmployee employee;

    public Complaint() {
    }

    public Complaint(String complainantName, String description, SeverityLevel severity, UndeadEmployee employee) {
        this.complainantName = complainantName;
        this.description = description;
        this.severity = severity;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public String getComplainantName() {
        return complainantName;
    }

    public String getDescription() {
        return description;
    }

    public SeverityLevel getSeverity() {
        return severity;
    }

    public UndeadEmployee getEmployee() {
        return employee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setComplainantName(String complainantName) {
        this.complainantName = complainantName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSeverity(SeverityLevel severity) {
        this.severity = severity;
    }

    public void setEmployee(UndeadEmployee employee) {
        this.employee = employee;
    }
}