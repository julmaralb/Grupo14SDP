package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

	@Query("select s from DailyPlan dp join dp.slots s where dp.id = ?1")
	Collection<Slot> findAllByDailyPlanId(int dailyPlanId);

}
