package com.example.tapplatform.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "highFreq", weight = 60)
public class HighFreqActionService1 implements ActionService {
    @Override
    public String executeAction(String triggerTitle, String actionTitle) {
        return "HighFreqActionService1 executed: " + actionTitle + " for " + triggerTitle;
    }
}