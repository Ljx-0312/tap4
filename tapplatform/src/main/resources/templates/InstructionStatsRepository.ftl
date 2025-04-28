package ${packagePath}.repository;

import ${packagePath}.entity.InstructionStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InstructionStatsRepository extends JpaRepository<InstructionStats, Long> {
InstructionStats findByActionTitle(String actionTitle);
List<InstructionStats> findAllByOrderByCallCountDesc();
    }