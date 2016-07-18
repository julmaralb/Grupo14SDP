package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {

	@Query("select g from Group g where g.subject.lecturer.id =?1")
	Collection<Group> findByLecturerId(int lecturerId);
	
	@Query("select s.groups from Student s where s.id = ?1")
	Collection<Group> findByStudentId(int studentId);

}
