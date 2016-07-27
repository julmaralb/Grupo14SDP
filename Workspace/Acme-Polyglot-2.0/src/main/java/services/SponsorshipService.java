package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SponsorshipRepository;
import domain.Agent;
import domain.Banner;
import domain.Sponsorship;
import domain.SponsorshipDescription;

@Service
@Transactional
public class SponsorshipService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SponsorshipRepository sponsorshipRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AgentService agentService;

	// Constructors -----------------------------------------------------------

	public SponsorshipService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Sponsorship create() {
		Sponsorship result;
		Agent principal;
		Collection<Banner> banners;
		Collection<SponsorshipDescription> sponsorshipDescriptions;

		result = new Sponsorship();
		principal = agentService.findByPrincipal();
		banners = new ArrayList<Banner>();
		sponsorshipDescriptions = new ArrayList<SponsorshipDescription>();

		result.setAgent(principal);
		result.setBanners(banners);
		result.setSponsorshipDescriptions(sponsorshipDescriptions);

		return result;
	}

	public Sponsorship findOne(int sponsorshipId) {
		Assert.notNull(sponsorshipId);

		Sponsorship result;

		result = sponsorshipRepository.findOne(sponsorshipId);

		return result;

	}

	public Collection<Sponsorship> findAll() {

		Collection<Sponsorship> result;

		result = sponsorshipRepository.findAll();

		return result;
	}

	public void save(Sponsorship sponsorship) {
		Assert.notNull(sponsorship);
		Agent principal;

		principal = agentService.findByPrincipal();
		sponsorship.setAgent(principal);

		sponsorshipRepository.save(sponsorship);
	}

	public void delete(Sponsorship sponsorship) {
		Assert.notNull(sponsorship);

		sponsorshipRepository.delete(sponsorship);

	}

	// Other business methods -------------------------------------------------

	public Collection<Sponsorship> findAllByPrincipal() {
		Collection<Sponsorship> result;
		Agent principal;

		principal = agentService.findByPrincipal();
		result = sponsorshipRepository.findAllByAgentId(principal.getId());

		return result;
	}
}