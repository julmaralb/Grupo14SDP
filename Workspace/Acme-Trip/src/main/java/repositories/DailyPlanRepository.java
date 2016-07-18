package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.DailyPlan;


@Repository
public interface DailyPlanRepository extends JpaRepository<DailyPlan, Integer> {

}
