package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorshipDescriptionRepository;
import domain.Agent;
import domain.SponsorshipDescription;

@Service
@Transactional
public class SponsorshipDescriptionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SponsorshipDescriptionRepository sponsorshipDescriptionRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AgentService agentService;

	// Constructors -----------------------------------------------------------

	public SponsorshipDescriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SponsorshipDescription create() {
		SponsorshipDescription result;

		result = new SponsorshipDescription();

		return result;
	}

	public SponsorshipDescription findOne(int sponsorshipDescriptionId) {
		Assert.notNull(sponsorshipDescriptionId);

		SponsorshipDescription result;

		result = sponsorshipDescriptionRepository
				.findOne(sponsorshipDescriptionId);

		return result;

	}

	public Collection<SponsorshipDescription> findAll() {

		Collection<SponsorshipDescription> result;

		result = sponsorshipDescriptionRepository.findAll();

		return result;
	}

	public void save(SponsorshipDescription sponsorshipDescription) {
		Assert.notNull(sponsorshipDescription);

		sponsorshipDescriptionRepository.save(sponsorshipDescription);
	}

	public void delete(SponsorshipDescription sponsorshipDescription) {
		Assert.notNull(sponsorshipDescription);

		sponsorshipDescriptionRepository.delete(sponsorshipDescription);

	}

	// Other business methods -------------------------------------------------

	public Collection<SponsorshipDescription> findAllByPrincipal() {
		Collection<SponsorshipDescription> result;
		Agent principal;

		principal = agentService.findByPrincipal();
		result = sponsorshipDescriptionRepository.findAllByAgentId(principal
				.getId());

		return result;
	}

	public SponsorshipDescription findBySponsorshipIdAndCode(
			int sponsorshipId, String code) {
		SponsorshipDescription result;

		result = sponsorshipDescriptionRepository.findBySponsorshipIdAndCode(
				sponsorshipId, code);

		return result;
	}
}