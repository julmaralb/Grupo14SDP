package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Trip;

@Repository
public interface TripRepository extends JpaRepository<Trip, Integer> {

	@Query("select distinct t from Trip t join t.dailyPlans dp join dp.slots s where t.title like %?1% or t.description like %?1% or dp.title like %?1% or dp.description like %?1% or s.title like %?1% or s.description like %?1% or s.activity.title like %?1% or s.activity.description like %?1%")
	Collection<Trip> findByKeyword(String keyword);

	@Query("select distinct t from ActivityType at join at.activities a join a.slot.dailyPlan.trip t where at.id=?1")
	Collection<Trip> findByActivityTypeId(int activityTypeId);

	@Query("select t from Trip t where t.owner.id=?1")
	Collection<Trip> findByUserId(int userId);
}
