package ${packagePath}.repository;

import ${packagePath}.entity.RuleExecutionLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RuleExecutionLogRepository extends JpaRepository<RuleExecutionLog, Long> {
List<RuleExecutionLog> findAll();
    }