package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CampaignRepository;
import domain.Actor;
import domain.Banner;
import domain.Campaign;
import domain.Manager;

@Service
@Transactional
public class CampaignService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CampaignRepository campaignRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private ManagerService managerService;

	// Constructors -----------------------------------------------------------

	public CampaignService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Campaign create() {
		Campaign result;
		Manager principal;
		Collection<Banner> banners;

		result = new Campaign();
		principal = managerService.findByPrincipal();
		banners = new ArrayList<Banner>();

		result.setManager(principal);
		result.setBanners(banners);

		return result;
	}

	public Campaign findOne(int campaignId) {
		Assert.notNull(campaignId);

		Campaign result;

		result = campaignRepository.findOne(campaignId);

		return result;
	}

	public Collection<Campaign> findAll() {

		Collection<Campaign> result;

		result = campaignRepository.findAll();

		return result;
	}

	public void save(Campaign campaign) {
		Assert.notNull(campaign);
		Manager principal;

		principal = managerService.findByPrincipal();
		campaign.setManager(principal);

		campaignRepository.save(campaign);
	}

	public void delete(Campaign campaign) {
		Assert.notNull(campaign);

		campaignRepository.delete(campaign);
	}

	// Other business methods -------------------------------------------------

	public Collection<Campaign> findAllByPrincipal() {
		Collection<Campaign> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = campaignRepository.findAllByManagerId(principal.getId());

		return result;
	}
}