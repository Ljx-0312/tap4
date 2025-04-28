package com.example.tapplatform.service;

import com.example.tapplatform.entity.InstructionStats;
import com.example.tapplatform.repository.InstructionStatsRepository;
import com.example.tapplatform.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsService {

    @Autowired
    private RuleRepository ruleRepository;

    @Autowired
    private InstructionStatsRepository instructionStatsRepository;

    public void analyzeDataset() {
        instructionStatsRepository.deleteAll();

        // 获取所有 trigger_title 及其数量
        List<Object[]> results = ruleRepository.countByTriggerTitle();
        for (Object[] result : results) {
            String triggerTitle = (String) result[0];
            Long count = (Long) result[1];

            InstructionStats stats = new InstructionStats();
            stats.setTriggerTitle(triggerTitle); // 修复后：triggerTitle 是 String 类型，匹配正确
            stats.setCallCount(count);
            instructionStatsRepository.save(stats);
        }

        // 按 call_count 降序排序，划分档位
        List<InstructionStats> allStats = instructionStatsRepository.findAll(Sort.by(Sort.Direction.DESC, "callCount"));
        for (int i = 0; i < allStats.size(); i++) {
            InstructionStats stats = allStats.get(i);
            Long count = stats.getCallCount();

            if (count > 600) {
                stats.setFrequencyTier("highest");
            } else if (count >= 400) {
                stats.setFrequencyTier("high");
            } else if (count >= 200) {
                stats.setFrequencyTier("medium");
            } else {
                stats.setFrequencyTier("low");
            }
            instructionStatsRepository.save(stats);
        }
    }
}