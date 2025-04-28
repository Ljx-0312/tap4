package ${packagePath}.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class RuleExecutionLog {
@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;
private Long ruleId;
private String triggerTitle;
private String actionTitle;
private LocalDateTime executionTime;
private boolean success;
private long duration;
private String errorMessage;
private String providerAddress;
}