package ${packagePath}.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class ${entityName} {
@Id
private Long id;
private String title;
private String triggerTitle;
private String actionTitle;
private String actionService;
private String actionMethod;
private String actionParam;
private int priority;
}