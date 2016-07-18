package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.TripComment;

@Repository
public interface TripCommentRepository extends
		JpaRepository<TripComment, Integer> {

}
