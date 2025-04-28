package ${packagePath}.service;

import ${packagePath}.entity.${entityName};
import ${packagePath}.repository.${entityName}Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${entityName}Service {
@Autowired
private ${entityName}Repository ${entityName?uncap_first}Repository;

@Cacheable(value = "rules", key = "'allRules'")
public List<${entityName}> getAllRules() {
return ${entityName?uncap_first}Repository.findAllByOrderByPriorityAsc();
}

public ${entityName} saveRule(${entityName} rule) {
return ${entityName?uncap_first}Repository.save(rule);
}

public List<${entityName}> saveRules(List<${entityName}> rules) {
return ${entityName?uncap_first}Repository.saveAll(rules);
}
}