package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Bibliography;

@Repository
public interface BibliographyRepository extends
		JpaRepository<Bibliography, Integer> {

	@Query("select b from Bibliography b where (select s from Syllabus s where s.id = ?1) member of b.syllabi")
	Collection<Bibliography> findBySyllabusId(int syllabusId);

}
