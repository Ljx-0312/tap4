package com.example.tapplatform.repository;

import com.example.tapplatform.entity.InstructionStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface InstructionStatsRepository extends JpaRepository<InstructionStats, Long> {
    InstructionStats findByTriggerTitle(String triggerTitle);
    List<InstructionStats> findAll(Sort sort);
}