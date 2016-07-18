package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Assignment;

@Repository
public interface AssignmentRepository extends
		JpaRepository<Assignment, Integer> {

	@Query("select g.assignments from Student s join s.groups g where s.id = ?1")
	Collection<Assignment> findAllByStudentId(int studentId);

	@Query("select g.assignments from Group g where g.id = ?1 AND (select s from Student s where s.id = ?2) member of g.students")
	Collection<Assignment> findByGroupIdAndStudentId(int groupId, int studentId);

	@Query("select g.assignments from Group g where g.id = ?1 AND g.subject.lecturer.id = ?2")
	Collection<Assignment> findByGroupIdAndLecturerId(int groupId,
			int lecturerId);

	@Query("select g.assignments from Group g where g.subject.lecturer.id = ?1")
	Collection<Assignment> findAllByLecturerId(int lecturerId);

}