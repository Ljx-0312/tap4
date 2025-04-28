package com.example.tapplatform.entity;

import jakarta.persistence.*;

@Entity
public class InstructionStats {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String triggerTitle; // 修复：从 Long 改为 String
    private Long callCount;
    private String frequencyTier;

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTriggerTitle() { return triggerTitle; }
    public void setTriggerTitle(String triggerTitle) { this.triggerTitle = triggerTitle; }

    public Long getCallCount() { return callCount; }
    public void setCallCount(Long callCount) { this.callCount = callCount; }

    public String getFrequencyTier() { return frequencyTier; }
    public void setFrequencyTier(String frequencyTier) { this.frequencyTier = frequencyTier; }
}