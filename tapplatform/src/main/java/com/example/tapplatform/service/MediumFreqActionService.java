package com.example.tapplatform.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "mediumFreq", weight = 40)
public class MediumFreqActionService implements ActionService {
    @Override
    public String executeAction(String triggerTitle, String actionTitle) {
        return "MediumFreqActionService executed: " + actionTitle + " for " + triggerTitle;
    }
}