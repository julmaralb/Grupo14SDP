package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Day;

@Repository
public interface DayRepository extends JpaRepository<Day, Integer> {

	@Query("select d from Day d where d.day = ?1 AND d.court in (select c from Court c where c.centre.id= ?2)")
	Collection<Day> findByDateAndCentreId(Date day, int centreId);

	@Query("select DISTINCT d from Day d group by d.day")
	Collection<Day> findAllDistinct();

}