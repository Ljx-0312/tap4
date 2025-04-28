package com.example.tapplatform.service;

import com.example.tapplatform.entity.Rule;
import com.example.tapplatform.repository.RuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RuleService {
@Autowired
private RuleRepository ruleRepository;

@Cacheable(value = "rules", key = "'allRules'")
public List<Rule> getAllRules() {
return ruleRepository.findAllByOrderByPriorityAsc();
}

public Rule saveRule(Rule rule) {
return ruleRepository.save(rule);
}

public List<Rule> saveRules(List<Rule> rules) {
return ruleRepository.saveAll(rules);
}
}