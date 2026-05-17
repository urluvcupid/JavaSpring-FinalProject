package com.example.necromancer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "reanimation_tasks")
public class ReanimationTask {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate scheduledDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReanimationStatus status;

    @Column(length = 1000)
    private String notes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", nullable = false)
    @JsonIgnore
    private UndeadEmployee employee;

    public ReanimationTask() {
    }

    public ReanimationTask(LocalDate scheduledDate, ReanimationStatus status, String notes, UndeadEmployee employee) {
        this.scheduledDate = scheduledDate;
        this.status = status;
        this.notes = notes;
        this.employee = employee;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getScheduledDate() {
        return scheduledDate;
    }

    public ReanimationStatus getStatus() {
        return status;
    }

    public String getNotes() {
        return notes;
    }

    public UndeadEmployee getEmployee() {
        return employee;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setScheduledDate(LocalDate scheduledDate) {
        this.scheduledDate = scheduledDate;
    }

    public void setStatus(ReanimationStatus status) {
        this.status = status;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setEmployee(UndeadEmployee employee) {
        this.employee = employee;
    }
}