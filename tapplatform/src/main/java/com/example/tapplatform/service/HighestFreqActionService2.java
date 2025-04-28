package com.example.tapplatform.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "highestFreq", weight = 80)
public class HighestFreqActionService2 implements ActionService {
    @Override
    public String executeAction(String triggerTitle, String actionTitle) {
        return "HighestFreqActionService2 executed: " + actionTitle + " for " + triggerTitle;
    }
}