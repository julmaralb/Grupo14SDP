package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BannerRepository;
import domain.Actor;
import domain.Banner;

@Service
@Transactional
public class BannerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private BannerRepository bannerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

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
}