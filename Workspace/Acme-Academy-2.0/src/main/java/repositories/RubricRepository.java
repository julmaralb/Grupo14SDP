package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Rubric;

@Repository
public interface RubricRepository extends JpaRepository<Rubric, Integer> {

	@Query("select r from Rubric r where r.assignment.id = ?1 AND r.explanation= 'default'")
	Rubric findDefaultByAssignmentId(int assignmentId);

	@Query("select r from Rubric r where r.assignment.id = ?1")
	Collection<Rubric> findByAssignmentId(int assignmentId);

	@Query("select r from Rubric r where r.percentage !=0 AND r.assignment.id = ?1")
	Collection<Rubric> findByAssignmentIdWithoutPercentage0(int assignmentId);

	@Query("select r from Rubric r where r.assignment.id=?1 AND r.instantiated = false")
	Collection<Rubric> findNotInstantiatedByAssignmentId(int assignmentId);

	@Query("select r from Rubric r where r.assignment.id=?1 AND r.number = ?2")
	Rubric findByAssignmentIdAndNumber(int assignmentId, int number);

}
