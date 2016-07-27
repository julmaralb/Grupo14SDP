package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DescriptionRepository;
import domain.Description;

@Service
@Transactional
public class DescriptionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private DescriptionRepository descriptionRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public DescriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Description create() {
		Description result;

		result = new Description();

		return result;
	}

	public Description findOne(int descriptionId) {
		Assert.notNull(descriptionId);

		Description result;

		result = descriptionRepository.findOne(descriptionId);

		return result;

	}

	public Collection<Description> findAll() {

		Collection<Description> result;

		result = descriptionRepository.findAll();

		return result;
	}

	public void save(Description description) {
		Assert.notNull(description);

		descriptionRepository.save(description);
	}

	public void delete(Description description) {
		Assert.notNull(description);

		descriptionRepository.delete(description);

	}

	// Other business methods -------------------------------------------------
}