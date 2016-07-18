package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ActivityComment;

@Repository
public interface ActivityCommentRepository extends JpaRepository<ActivityComment, Integer> {

}
