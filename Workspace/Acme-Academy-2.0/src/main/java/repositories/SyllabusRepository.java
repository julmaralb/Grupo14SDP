package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Syllabus;

@Repository
public interface SyllabusRepository extends JpaRepository<Syllabus, Integer> {

	@Query("select s from Syllabus s where s.subject.lecturer.id = ?1")
	Collection<Syllabus> findByLecturerId(int lecturerId);

	@Query("select s from Syllabus s where s.subject.id = ?1 AND s.subject.lecturer.id= ?2")
	Collection<Syllabus> findBySubjectAndLecturerId(int subjectId,
			int lecturerId);
	
	@Query("select s from Syllabus s where s.subject.id = ?1")
	Collection<Syllabus> findBySubjectId(int subjectId);

	@Query("select s from Syllabus s where s.subject.id= ?1 AND s.academicYear = ?2")
	Syllabus findBySubjectIdAndYear(int subjectId, int year);

}
