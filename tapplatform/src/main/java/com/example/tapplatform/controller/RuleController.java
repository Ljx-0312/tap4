package com.example.tapplatform.controller;

import com.example.tapplatform.entity.Rule;
import com.example.tapplatform.service.RuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class RuleController {
@Autowired
private RuleService ruleService;

@PostMapping
public Rule createRule(@RequestBody Rule rule) {
return ruleService.saveRule(rule);
}

@PostMapping("/batch")
public List<Rule> batchCreateRules(@RequestBody List<Rule> rules) {
return ruleService.saveRules(rules);
}

@GetMapping
public List<Rule> getAllRules() {
return ruleService.getAllRules();
}
}