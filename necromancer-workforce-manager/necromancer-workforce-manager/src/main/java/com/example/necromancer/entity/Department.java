package com.example.necromancer.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "departments")
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "department")
    @JsonIgnore
    private List<UndeadEmployee> employees = new ArrayList<>();

    public Department(){
    }

    public Department(String name){
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName(){
        return name;
    }

    public List<UndeadEmployee> getEmployees() {
        return employees;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmployees(List<UndeadEmployee> employees) {
        this.employees = employees;
    }
}
