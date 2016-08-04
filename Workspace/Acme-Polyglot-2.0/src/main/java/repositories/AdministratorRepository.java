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

	// 2.0-C-level queries

	@Query("select avg(f.messages.size),min(f.messages.size),max(f.messages.size) from Folder f")
	Collection<Object[]> meanMinMaxMessagesPerFolder();
	
	@Query("select count(m)*1.0/(select count(m2) from Message m2) from Message m where m.code = ?1")
	Double ratioLaguagesMessages(String code);

}
