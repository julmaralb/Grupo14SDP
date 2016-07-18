package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Deliverable;

@Repository
public interface DeliverableRepository extends JpaRepository<Deliverable, Integer> {

	@Query("select d from Deliverable d where d.assignment.id= ?1")
	Collection<Deliverable> findByAssignmentId(int assignmentId);

}
