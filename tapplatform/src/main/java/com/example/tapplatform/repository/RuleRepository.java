package com.example.tapplatform.repository;

import com.example.tapplatform.entity.Rule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleRepository extends JpaRepository<Rule, Long> {
List<Rule> findAllByOrderByPriorityAsc();

    List<Object[]> countByTriggerTitle();
}