package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LanguageDescription;

@Repository
public interface LanguageDescriptionRepository extends
		JpaRepository<LanguageDescription, Integer> {

	@Query("select ld from Language l join l.languageDescriptions ld where l.id = ?1 AND ld.code = ?2")
	LanguageDescription findByLanguageIdAndCode(int languageId, String code);

}
