package com.example.tapplatform.dto;

import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
public class RuleDTO {
    @JsonProperty("id")
    private Long id;

    @JsonProperty("triggerTitle")
    private String triggerTitle;

    @JsonProperty("actionTitle")
    private String actionTitle;
}