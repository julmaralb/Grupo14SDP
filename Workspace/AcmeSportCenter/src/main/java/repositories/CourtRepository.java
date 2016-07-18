package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Court;

@Repository
public interface CourtRepository extends JpaRepository<Court, Integer> {

	@Query("select courts from Centre c where c.id=?1")
	Collection<Court> findByCentreId(int centreId);

}
