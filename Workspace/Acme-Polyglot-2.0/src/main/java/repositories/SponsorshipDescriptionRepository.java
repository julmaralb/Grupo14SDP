package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.SponsorshipDescription;

@Repository
public interface SponsorshipDescriptionRepository extends
		JpaRepository<SponsorshipDescription, Integer> {

	@Query("select sd from SponsorshipDescription sd where sd.sponsorship.agent.id = ?1")
	Collection<SponsorshipDescription> findAllByAgentId(int agentId);

	@Query("select sd from Sponsorship s join s.sponsorshipDescriptions sd where s.id = ?1 AND sd.code = ?2")
	SponsorshipDescription findBySponsorshipIdAndCode(int sponsorshipId,
			String code);

}
