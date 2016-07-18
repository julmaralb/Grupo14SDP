package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.DailyPlan;


@Repository
public interface DailyPlanRepository extends JpaRepository<DailyPlan, Integer> {
	
	@Query("select dp from Trip t join t.dailyPlans dp where t.id = ?1")
	Collection<DailyPlan> findAllByTripId(int tripId);

}
