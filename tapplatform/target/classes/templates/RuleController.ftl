package ${packagePath}.controller;

import ${packagePath}.entity.${entityName};
import ${packagePath}.service.${entityName}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rules")
public class ${entityName}Controller {
@Autowired
private ${entityName}Service ${entityName?uncap_first}Service;

@PostMapping
public ${entityName} createRule(@RequestBody ${entityName} rule) {
return ${entityName?uncap_first}Service.saveRule(rule);
}

@PostMapping("/batch")
public List<${entityName}> batchCreateRules(@RequestBody List<${entityName}> rules) {
return ${entityName?uncap_first}Service.saveRules(rules);
}

@GetMapping
public List<${entityName}> getAllRules() {
return ${entityName?uncap_first}Service.getAllRules();
}
}