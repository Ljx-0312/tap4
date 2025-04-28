package ${packagePath}.repository;

import ${packagePath}.entity.${entityName};
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ${entityName}Repository extends JpaRepository<${entityName}, Long> {
List<${entityName}> findAllByOrderByPriorityAsc();
}