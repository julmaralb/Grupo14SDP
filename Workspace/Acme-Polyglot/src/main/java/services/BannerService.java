package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Agent;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private BannerRepository bannerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AgentService agentService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public BannerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Banner create() {
		Assert.isTrue(actorService.checkAuthority("AGENT"));
		Banner result;

		result = new Banner();

		return result;
	}

	public Banner findOne(int bannerId) {
		Assert.notNull(bannerId);

		Banner result;

		result = bannerRepository.findOne(bannerId);

		return result;

	}

	public Collection<Banner> findAll() {

		Collection<Banner> result;

		result = bannerRepository.findAll();

		return result;
	}

	public void save(Banner banner) {
		Assert.notNull(banner);
		Assert.isTrue(actorService.checkAuthority("AGENT"));
		Assert.isTrue(banner.getSponsorship().getAgent().equals(actorService.findByPrincipal()));

		bannerRepository.save(banner);
	}

	public void delete(Banner banner) {
		Assert.notNull(banner);
		Assert.isTrue(actorService.checkAuthority("AGENT"));
		Assert.isTrue(banner.getSponsorship().getAgent().equals(actorService.findByPrincipal()));

		bannerRepository.delete(banner);

	}

	// Other business methods -------------------------------------------------

	public Collection<Banner> findAllByPrincipal() {
		Collection<Banner> result;
		Agent principal;

		principal = agentService.findByPrincipal();
		result = bannerRepository.findAllByAgentId(principal.getId());

		return result;
	}

	// Este método busca todos los banners de los sponsorships del lenguageExchange pasado
	// por parámetro y del idioma que indique el código y elije uno al azar, si no hay de
	// ese idioma lo coje al azar entre los que están en inglés y si no hay en inglés coje
	// uno cualquiera de los banners.
	public Banner findRandomToDisplay(int languageExchangeId, String code) {
		Banner result;
		ArrayList<Banner> allGivenCode;
		ArrayList<Banner> allEnglish;
		ArrayList<Banner> all;
		Random r = new Random();

		allGivenCode = bannerRepository.findAllByLangageExchangeIdAndCode(
				languageExchangeId, code);
		allEnglish = bannerRepository.findAllByLangageExchangeIdAndCode(
				languageExchangeId, "en");
		all = bannerRepository.findAllByLangageExchange(languageExchangeId);

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