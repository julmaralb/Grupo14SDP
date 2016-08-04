package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	// C-level queries

	@Query("select DISTINCT l,l.counter from Language l")
	Collection<Object[]> languagesAndCountOfExchangesInvolved();

	@Query("select DISTINCT p,p.organisedLanguageExchanges.size from Polyglot p")
	Collection<Object[]> polyglotsAndCountOfExchangesOrganised();

	@Query("select DISTINCT p,p.participatedLanguageExchanges.size from Polyglot p")
	Collection<Object[]> polyglotsAndCountOfExchangesJoined();

	// B-level queries

	@Query("select DISTINCT le,le.sponsorships.size from LanguageExchange le")
	Collection<Object[]> languageExchangesAndCountOfSponsorships();

	@Query("select DISTINCT p,s.size from Polyglot p join p.organisedLanguageExchanges le join le.sponsorships s")
	Collection<Object[]> polyglotsAndCountOfSponsoredExchanges();

	@Query("select sum(l.sponsorships.size)*1.0/count(l),min(l.sponsorships.size),max(l.sponsorships.size) from LanguageExchange l")
	Collection<Object[]> avgMinMaxSponsoredExchangesPerPolyglot();

}
