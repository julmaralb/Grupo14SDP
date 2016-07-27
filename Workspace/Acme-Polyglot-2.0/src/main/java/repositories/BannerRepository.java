package repositories;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

	@Query("select b from Banner b where b.sponsorship.agent.id = ?1")
	Collection<Banner> findAllByAgentId(int agentId);

	@Query("select b from LanguageExchange le join le.sponsorships s join s.banners b where le.id= ?1 AND b.code = ?2")
	ArrayList<Banner> findAllByLangageExchangeIdAndCode(int languageExchangeId,
			String code);

	@Query("select b from LanguageExchange le join le.sponsorships s join s.banners b where le.id= ?1")
	ArrayList<Banner> findAllByLangageExchange(int languageExchangeId);

}
