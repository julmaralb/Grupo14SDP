package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Group;
import domain.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {

	@Query("select s from Subject s where s.lecturer.id = ?1")
	Collection<Subject> findByLecturerId(int lecturerId);

	@Query("select s.groups from Subject s")
	Collection<Group> findAllGroup();

}
