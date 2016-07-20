package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ActivityComment;

@Repository
public interface ActivityCommentRepository extends
		JpaRepository<ActivityComment, Integer> {

	@Query("select ac from ActivityComment ac where ac.activity.id = ?1 AND ac.inappropriate = false")
	Collection<ActivityComment> findAllByActivityId(int activityId);

}
