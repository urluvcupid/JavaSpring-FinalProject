package com.example.necromancer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "undead_employees")
public class UndeadEmployee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private EmployeeType type;

    @Column(nullable = false)
    private String position;

    @Column(nullable = false)
    private Integer decayRate;

    private LocalDate lastReanimationDate;

    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<Complaint> complaints = new ArrayList<>();

    @OneToMany(mappedBy = "employee")
    @JsonIgnore
    private List<ReanimationTask> reanimationTasks = new ArrayList<>();

    public UndeadEmployee() {
    }

    public UndeadEmployee(String name, EmployeeType type, String position, Integer decayRate,
                          LocalDate lastReanimationDate, Department department) {
        this.name = name;
        this.type = type;
        this.position = position;
        this.decayRate = decayRate;
        this.lastReanimationDate = lastReanimationDate;
        this.department = department;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public EmployeeType getType() {
        return type;
    }

    public String getPosition() {
        return position;
    }

    public Integer getDecayRate() {
        return decayRate;
    }

    public LocalDate getLastReanimationDate() {
        return lastReanimationDate;
    }

    public Department getDepartment() {
        return department;
    }

    public List<Complaint> getComplaints() {
        return complaints;
    }

    public List<ReanimationTask> getReanimationTasks() {
        return reanimationTasks;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(EmployeeType type) {
        this.type = type;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public void setDecayRate(Integer decayRate) {
        this.decayRate = decayRate;
    }

    public void setLastReanimationDate(LocalDate lastReanimationDate) {
        this.lastReanimationDate = lastReanimationDate;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setComplaints(List<Complaint> complaints) {
        this.complaints = complaints;
    }

    public void setReanimationTasks(List<ReanimationTask> reanimationTasks) {
        this.reanimationTasks = reanimationTasks;
    }
}