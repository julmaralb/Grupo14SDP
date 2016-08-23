package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

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
		Agent principal;
		principal = agentService.findByPrincipal();
		Assert.isTrue(sponsorshipDescription.getSponsorship().getAgent().equals(principal));

		sponsorshipDescriptionRepository.save(sponsorshipDescription);
	}

	public void delete(SponsorshipDescription sponsorshipDescription) {
		Assert.notNull(sponsorshipDescription);
		Agent principal;
		principal = agentService.findByPrincipal();
		Assert.isTrue(sponsorshipDescription.getSponsorship().getAgent().equals(principal));

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
		ArrayList<SponsorshipDescription> allGivenCode;
		ArrayList<SponsorshipDescription> allEnglish;
		ArrayList<SponsorshipDescription> all;
		Random r = new Random();

		allGivenCode = sponsorshipDescriptionRepository
				.findAllBySponsorshipIdAndCode(sponsorshipId, code);
		allEnglish = sponsorshipDescriptionRepository
				.findAllBySponsorshipIdAndCode(sponsorshipId, "en");
		all = sponsorshipDescriptionRepository
				.findAllBySponsorship(sponsorshipId);

		if (!allGivenCode.isEmpty()) {
			result = allGivenCode.get(r.nextInt(allGivenCode.size()));
		} else if (allGivenCode.isEmpty() && !allEnglish.isEmpty()) {
			result = allEnglish.get(r.nextInt(allEnglish.size()));
		} else if (allEnglish.isEmpty() && !all.isEmpty()) {
			result = all.get(r.nextInt(all.size()));
		}else{
			result = allGivenCode.get(r.nextInt(allGivenCode.size()));
		}
		return result;
	}
}