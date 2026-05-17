package com.example.necromancer.repository;

import com.example.necromancer.entity.ReanimationStatus;
import com.example.necromancer.entity.ReanimationTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReanimationTaskRepository extends JpaRepository<ReanimationTask, Long> {
    List<ReanimationTask> findByStatus(ReanimationStatus status);
}