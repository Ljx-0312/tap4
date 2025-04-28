package com.example.tapplatform.controller;

import com.example.tapplatform.entity.InstructionStats;
import com.example.tapplatform.entity.Rule;
import com.example.tapplatform.entity.RuleExecutionLog;
import com.example.tapplatform.repository.InstructionStatsRepository;
import com.example.tapplatform.repository.RuleExecutionLogRepository;
import com.example.tapplatform.service.ActionService;
import com.example.tapplatform.service.RuleService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/trigger")
public class TriggerController {

    @Autowired
    private RuleService ruleService;

    @Autowired
    private InstructionStatsRepository instructionStatsRepository;

    @Autowired
    private RuleExecutionLogRepository ruleExecutionLogRepository;

    @DubboReference(group = "highestFreq", check = false)
    private ActionService highestFreqActionService;

    @DubboReference(group = "highFreq", check = false)
    private ActionService highFreqActionService;

    @DubboReference(group = "mediumFreq", check = false)
    private ActionService mediumFreqActionService;

    @DubboReference(group = "lowFreq", check = false)
    private ActionService generalActionService;

    @PostMapping
    public String trigger(@RequestBody TriggerEvent event) {
        List<Rule> rules = ruleService.getAllRules();
        int triggeredCount = 0;

        for (Rule rule : rules) {
            if (rule.getTriggerTitle().equals(event.getTriggerTitle())) {
                long startTime = System.currentTimeMillis();
                boolean success = true;
                String errorMessage = null;
                String providerAddress = "10.208.124.88:20880";
                try {
                    ActionService selectedService = selectService(rule.getTriggerTitle());
                    String actionResult = selectedService.executeAction(rule.getTriggerTitle(), rule.getActionTitle());
                    providerAddress = selectedService.getClass().getSimpleName();
                    System.out.println("Executing action: " + rule.getActionTitle() + ", Result: " + actionResult);
                } catch (Exception e) {
                    success = false;
                    errorMessage = e.getMessage();
                }
                long duration = System.currentTimeMillis() - startTime;

                updateStats(rule.getTriggerTitle()); // 修改：传入 triggerTitle 而不是 actionTitle
                logExecution(rule, success, duration, errorMessage, providerAddress);
                triggeredCount++;
            }
        }

        return "Triggered " + triggeredCount + " rules for trigger: " + event.getTriggerTitle();
    }

    private ActionService selectService(String triggerTitle) {
        InstructionStats stats = instructionStatsRepository.findByTriggerTitle(triggerTitle);
        if (stats == null) {
            return generalActionService; // 默认使用低频 Provider
        }

        String tier = stats.getFrequencyTier();
        switch (tier) {
            case "highest":
                return highestFreqActionService;
            case "high":
                return highFreqActionService;
            case "medium":
                return mediumFreqActionService;
            default:
                return generalActionService;
        }
    }

    private void updateStats(String triggerTitle) {
        InstructionStats stats = instructionStatsRepository.findByTriggerTitle(triggerTitle); // 修改：按 triggerTitle 查询
        if (stats == null) {
            stats = new InstructionStats();
            stats.setTriggerTitle(triggerTitle); // 设置 triggerTitle
            stats.setCallCount(1L);
        } else {
            stats.setCallCount(stats.getCallCount() + 1L);
        }
        instructionStatsRepository.save(stats);
    }

    private void logExecution(Rule rule, boolean success, long duration, String errorMessage, String providerAddress) {
        RuleExecutionLog log = new RuleExecutionLog();
        log.setRuleId(rule.getId());
        log.setTriggerTitle(rule.getTriggerTitle());
        log.setActionTitle(rule.getActionTitle());
        log.setExecutionTime(LocalDateTime.now());
        log.setSuccess(success);
        log.setDuration(duration);
        log.setErrorMessage(errorMessage);
        log.setProviderAddress(providerAddress);
        ruleExecutionLogRepository.save(log);
    }

    static class TriggerEvent {
        private String triggerTitle;

        public String getTriggerTitle() {
            return triggerTitle;
        }

        public void setTriggerTitle(String triggerTitle) {
            this.triggerTitle = triggerTitle;
        }
    }
}