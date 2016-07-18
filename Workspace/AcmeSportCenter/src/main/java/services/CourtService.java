package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CourtRepository;
import domain.Court;

@Service
@Transactional
public class CourtService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CourtRepository courtRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public CourtService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Court create() {
		Court result;

		result = new Court();

		return result;
	}

	public Court findOne(int courtId) {
		Assert.notNull(courtId);

		Court result;

		result = courtRepository.findOne(courtId);

		return result;

	}

	public Collection<Court> findAll() {

		Collection<Court> result;

		result = courtRepository.findAll();

		return result;
	}

	public void save(Court court) {
		Assert.notNull(court);

		courtRepository.save(court);
	}

	public void delete(Court court) {
		Assert.notNull(court);

		courtRepository.delete(court);

	}

	// Other business methods -------------------------------------------------

	public Collection<Court> findByCentreId(int centreId) {
		Collection<Court> result;

		result = courtRepository.findByCentreId(centreId);

		return result;

	}

}