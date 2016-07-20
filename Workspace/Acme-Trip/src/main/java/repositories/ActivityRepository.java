package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Integer> {

	@Query("select a from Activity a where a.user.id = ?1")
	Collection<Activity> findAllByUserId(int userId);
	
	@Query("select s.activity from Slot s where s.id = ?1 AND s.activity.inappropriate = false")
	Activity findBySlotId(int slotId);

}
