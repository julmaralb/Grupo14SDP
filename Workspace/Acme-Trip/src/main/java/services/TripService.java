package services;

import java.util.Collection;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TripRepository;
import domain.Trip;
import domain.User;

@Service
@Transactional
public class TripService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TripRepository tripRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public TripService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Trip create() {
		Trip result;
		User principal;

		result = new Trip();
		principal = userService.findByPrincipal();
		result.setOwner(principal);

		return result;
	}

	public Trip findOne(int tripId) {
		Assert.notNull(tripId);

		Trip result;

		result = tripRepository.findOne(tripId);

		return result;
	}

	public Collection<Trip> findAll() {

		Collection<Trip> result;

		result = tripRepository.findAll();

		return result;
	}

	public void save(Trip trip) {
		Assert.notNull(trip);
		
		User principal;

		principal = userService.findByPrincipal();
		trip.setOwner(principal);

		tripRepository.save(trip);
	}

	public void delete(Trip trip) {
		Assert.notNull(trip);

		tripRepository.delete(trip);
	}

	// Other business methods -------------------------------------------------

	public Collection<Trip> findByKeyword(String keyword) {
		Assert.notNull(keyword);
		Assert.isTrue(keyword.length() != 0);

		Collection<Trip> result;
		result = tripRepository.findByKeyword(keyword);

		return result;
	}

	public Collection<Trip> findByActivityTypeId(int activityTypeId) {
		Collection<Trip> result;

		result = tripRepository.findByActivityTypeId(activityTypeId);

		return result;
	}

	public Collection<Trip> findByUserId(int userId) {
		Collection<Trip> result;

		result = tripRepository.findByUserId(userId);

		return result;
	}

	public Collection<Trip> findByPrincipal() {
		Collection<Trip> result;
		User principal;

		principal = userService.findByPrincipal();
		result = tripRepository.findByUserId(principal.getId());

		return result;
	}
}