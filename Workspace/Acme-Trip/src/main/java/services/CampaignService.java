package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

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
		Actor principal;
		Manager manager;
		Campaign result;

		result = campaignRepository.findOne(campaignId);
		manager=result.getManager();
		principal=actorService.findByPrincipal();
		Assert.isTrue(manager.getId()==(principal.getId()));
		
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
		Manager manager;
		
		Assert.isTrue(campaign.getStartMoment().before(campaign.getFinishMoment()));
		
		principal = managerService.findByPrincipal();
		manager= campaign.getManager();
		Assert.isTrue(manager.getId()==(principal.getId()));
		
		campaign.setManager(principal);
			
		campaignRepository.save(campaign);
	}

	public void delete(Campaign campaign) {
		Assert.notNull(campaign);
		Actor principal;
		Manager manager;
		Date currentDate;
		currentDate =new Date();
		
		principal = actorService.findByPrincipal();
		
		manager= campaign.getManager();
		Assert.isTrue(manager.getId()==(principal.getId()));
		Assert.isTrue(!campaign.getStartMoment().before(currentDate));
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

	public void cancelCampaign(Campaign campaign) {
		Calendar currentDate;
		Calendar startMoment;
		Date currentDate2;
		boolean sePuedeCancelar = false;
		Manager manager;
		Actor principal;
		
		currentDate2=new Date();
		currentDate = Calendar.getInstance();
		startMoment = Calendar.getInstance();
		startMoment.setTime(campaign.getStartMoment());
		manager= campaign.getManager();
		principal=actorService.findByPrincipal();
		
		Assert.isTrue(manager.getId()==(principal.getId()));
		Assert.isTrue(!campaign.getStartMoment().before(currentDate2));
		if (currentDate.before(startMoment)) {
			sePuedeCancelar = true;
		}
		if (sePuedeCancelar) {
			campaign.setCancelled(true);
		}
	
		
	}

	public boolean canBeModifiedOrDeleted(Campaign campaign) {
		boolean result = false;
		Calendar currentDate;
		Calendar startMoment;
		currentDate = Calendar.getInstance();
		startMoment = Calendar.getInstance();
		startMoment.setTime(campaign.getStartMoment());
		System.out.println(startMoment);

		if (currentDate.before(startMoment)) {
			result = true;
		}
		return result;
	}
}