package com.example.tapplatform.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "highestFreq", weight = 80)
public class HighestFreqActionService1 implements ActionService {
    @Override
    public String executeAction(String triggerTitle, String actionTitle) {
        return "HighestFreqActionService1 executed: " + actionTitle + " for " + triggerTitle;
    }
}