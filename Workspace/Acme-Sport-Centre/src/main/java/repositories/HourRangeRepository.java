package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.HourRange;

@Repository
public interface HourRangeRepository extends JpaRepository<HourRange, Integer> {

	@Query("select hr from Day d join d.hourRanges hr where d = (select d from Court c join c.days d where d.day = ?1 AND c.id=?2)")
	Collection<HourRange> findByCourtAndDayId(int courtId, String day);

}
