package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CentreRepository;
import domain.Centre;

@Service
@Transactional
public class CentreService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CentreRepository centreRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public CentreService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Centre create() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Centre result;

		result = new Centre();

		return result;
	}

	public Centre findOne(int centreId) {
		Assert.notNull(centreId);

		Centre result;

		result = centreRepository.findOne(centreId);

		return result;

	}

	public Collection<Centre> findAll() {

		Collection<Centre> result;

		result = centreRepository.findAll();

		return result;
	}

	public void save(Centre centre) {
		Assert.notNull(centre);

		centreRepository.save(centre);
	}

	public void delete(Centre centre) {
		Assert.notNull(centre);
		Assert.isTrue(actorService.checkAuthority("ADMIN"));

		centreRepository.delete(centre);

	}

	// Other business methods -------------------------------------------------
}