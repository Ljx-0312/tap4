package com.example.tapplatform.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "highestFreq", weight = 80)
public class HighestFreqActionService3 implements ActionService {
    @Override
    public String executeAction(String triggerTitle, String actionTitle) {
        return "HighestFreqActionService3 executed: " + actionTitle + " for " + triggerTitle;
    }
}