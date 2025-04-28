package com.example.tapplatform.service;

import org.apache.dubbo.config.annotation.DubboService;

@DubboService(group = "lowFreq", weight = 20)
public class GeneralActionService implements ActionService {
    @Override
    public String executeAction(String triggerTitle, String actionTitle) {
        return "GeneralActionService executed: " + actionTitle + " for " + triggerTitle;
    }
}