package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.LanguageExchange;

@Repository
public interface LanguageExchangeRepository extends
		JpaRepository<LanguageExchange, Integer> {

	@Query("select le from LanguageExchange le where le.registrationDate BETWEEN ?1 AND ?2")
	Collection<LanguageExchange> findBetween(Date day1, Date date2);

	@Query("select le from LanguageExchange le where le.owner.id = ?1")
	Collection<LanguageExchange> findAllByOwnerId(int ownerId);

	@Query("select p.participatedLanguageExchanges from Polyglot p where p.id = ?1")
	Collection<LanguageExchange> findAllJoinedByPolyglotId(int polyglotId);

}
