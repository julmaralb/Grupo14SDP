package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Actor;
import domain.Banner;
import domain.Trip;

@Service
@Transactional
public class BannerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private BannerRepository bannerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private TripService tripService;

	// Constructors -----------------------------------------------------------

	public BannerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Banner create() {
		Banner result;

		result = new Banner();
		result.setDayDisplays(0);

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

		bannerRepository.save(banner);
	}

	public void delete(Banner banner) {
		Assert.notNull(banner);

		bannerRepository.delete(banner);
	}

	// Other business methods -------------------------------------------------

	public Collection<Banner> findAllByPrincipal() {
		Collection<Banner> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = bannerRepository.findAllByManagerId(principal.getId());

		return result;
	}

	public Collection<Banner> findAllByCampaignIdAndPrincipal(int campaignId) {
		Collection<Banner> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = bannerRepository.findAllByCampaignAndManagerId(campaignId,
				principal.getId());

		return result;
	}

	// Este método recorre todas las keywords de todos los banner y , usando el método findByKeyword, comprueba si la en colección 
	// que se devuelve se encuentra el trip pasado por parámetro. Si el trip se encuentra en la colección devuelta esto quiere
	// decir que esa keyword se encuentra en algún luegar de la información guardada sobre él en el sistema y el banner 
	// correspondiente  se añade a una lista de posibles candidatos a ser mostrado de la cual se elige uno de manera aleatoria.
	public Banner findRandomToDisplay(Trip trip) {
		Banner result;
		Collection<Banner> all;
		ArrayList<Banner> candidates;
		Random r = new Random();

		all = bannerRepository.findAll();
		candidates = new ArrayList<Banner>();
		for (Banner b : all) {
			Collection<String> keywords = b.getKeywords();
			for (String k : keywords) {
				if (tripService.findByKeyword(k).contains(trip)) {
					candidates.add(b);
				}
			}
		}
		result = candidates.get(r.nextInt(candidates.size()));
		result.setDayDisplays(result.getDayDisplays() + 1);
		result.setMaxDisplayTimes(result.getMaxDisplayTimes() - 1);

		return result;
	}
}