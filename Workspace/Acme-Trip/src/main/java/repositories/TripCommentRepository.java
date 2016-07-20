package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.TripComment;

@Repository
public interface TripCommentRepository extends
		JpaRepository<TripComment, Integer> {

	@Query("select tc from TripComment tc where tc.trip.id = ?1 AND tc.inappropriate = false")
	Collection<TripComment> findAllByTripId(int tripId);

}
