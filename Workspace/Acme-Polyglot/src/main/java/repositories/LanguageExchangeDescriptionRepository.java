package repositories;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LanguageExchangeDescription;

@Repository
public interface LanguageExchangeDescriptionRepository extends
		JpaRepository<LanguageExchangeDescription, Integer> {

	@Query("select led from LanguageExchangeDescription led where led.languageExchange.owner.id = ?1")
	Collection<LanguageExchangeDescription> findAllByPolyglotId(int polyglotId);

	// @Query("select led from LanguageExchange le join le.languageExchangeDescriptions led where le.id = ?1 AND led.code = ?2")
	// LanguageExchangeDescription findByExchangeIdAndCode(int
	// languageExchangeId,
	// String code);
	
	@Query("select led from LanguageExchange le join le.languageExchangeDescriptions led where le.id = ?1 AND led.code = ?2")
	ArrayList<LanguageExchangeDescription> findAllByLanguageExchangeIdAndCode(int languageExchangeId,
			String code);

	@Query("select led from LanguageExchange le join le.languageExchangeDescriptions led where le.id = ?1")
	ArrayList<LanguageExchangeDescription> findAllByLanguageExchange(int languageExchangeId);

}
