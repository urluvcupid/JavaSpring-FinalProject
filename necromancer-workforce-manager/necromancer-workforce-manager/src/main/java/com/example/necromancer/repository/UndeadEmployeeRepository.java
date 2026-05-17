package com.example.necromancer.repository;

import com.example.necromancer.entity.UndeadEmployee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UndeadEmployeeRepository extends JpaRepository<UndeadEmployee, Long> {
}