package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Lecturer;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {

	@Query("select l from Lecturer l where l.userAccount.id = ?1")
	Lecturer findByUserAccountId(int userAccountId);

	@Query("select s.lecturer from Subject s where s.id = ?1")
	Lecturer findBySubjectId(int subjectId);

}
