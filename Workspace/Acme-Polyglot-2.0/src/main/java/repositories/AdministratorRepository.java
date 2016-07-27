package repositories;

import java.util.Collection;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Language;
import domain.LanguageExchange;
import domain.Polyglot;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	// C-level queries
	
	@Query("select DISTINCT l,l.counter from Language l")
	Map<Language, Integer> languagesAndCountOfExchangesInvolved();
	
	@Query("select DISTINCT p,p.organisedLanguageExchanges.size from Polyglot p")
	Map<Polyglot, Integer> polyglotsAndCountOfExchangesOrganised();
	
	@Query("select DISTINCT p,p.participatedLanguageExchanges.size from Polyglot p")
	Map<Polyglot, Integer> polyglotsAndCountOfExchangesJoined();
	
	// B-level queries
	
	@Query("select DISTINCT le,le.sponsorships.size from LanguageExchange le")
	Map<LanguageExchange, Integer> languageExchangesAndCountOfSponsorships();
	
	@Query("select DISTINCT p,s.size from Polyglot p join p.organisedLanguageExchanges le join le.sponsorships s")
	Map<Polyglot, Integer> polyglotsAndCountOfSponsoredExchanges();
	
	@Query("select sum(l.sponsorships.size)*1.0/count(l),min(l.sponsorships.size),max(l.sponsorships.size) from LanguageExchange l")
	Collection<Double> avgMinMaxSponsoredExchangesPerPolyglot();
	
}
