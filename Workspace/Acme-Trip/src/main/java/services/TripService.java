package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TripRepository;
import domain.DailyPlan;
import domain.Folder;
import domain.Message;
import domain.Slot;
import domain.Trip;
import domain.TripComment;
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

	@Autowired
	private DailyPlanService dailyPlanService;

	@Autowired
	private MessageService messageService;

	@Autowired
	private FolderService folderService;

	// Constructors -----------------------------------------------------------

	public TripService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Trip create() {
		Trip result;
		User principal;
		Collection<DailyPlan> dailyPlans;
		Collection<TripComment> tripComments;

		result = new Trip();
		dailyPlans = new ArrayList<DailyPlan>();
		tripComments = new ArrayList<TripComment>();
		principal = userService.findByPrincipal();
		result.setOwner(principal);
		result.setDailyPlans(dailyPlans);
		result.setTripComments(tripComments);

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
		if (trip.getId() == 0) {
			trip.setOwner(principal);
		}

		notifySubscriptors(trip);
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

	// Este método crea un nuevo trip y lo inicializa con todos
	// los valores del trip que se le pasa por parámetro
	// para así tener una copia del mismo y sus dailyPlans.
	public void copyTrip(Trip trip) {
		Trip copy;
		Collection<DailyPlan> dailyPlans;
		Collection<DailyPlan> copyDailyPlans;
		Collection<String> copyPhotos;
		User principal;

		copy = create();
		copyDailyPlans = new ArrayList<DailyPlan>();
		dailyPlans = trip.getDailyPlans();
		copyPhotos = new ArrayList<String>();
		principal = userService.findByPrincipal();

		// Se crea una copia de cada dailyPlan del trip original.
		for (DailyPlan dp : dailyPlans) {
			DailyPlan dailyPlan = dailyPlanService.create();
			Collection<String> photos = new ArrayList<String>();
			Collection<Slot> slots = new ArrayList<Slot>();
			dailyPlan.setDescription(dp.getDescription());
			dailyPlan.setTitle(dp.getTitle());
			dailyPlan.setWeekDay(dp.getWeekDay());
			dailyPlan.setSlots(slots);
			dailyPlan.setTrip(copy);
			for (String p : dp.getPhotos()) {
				photos.add(p);
			}
			dailyPlan.setPhotos(photos);

			copyDailyPlans.add(dailyPlan);
		}

		copy.setDailyPlans(copyDailyPlans);
		copy.setTitle("Copy of " + trip.getTitle());
		copy.setStartingDate(trip.getStartingDate());
		copy.setEndingDate(trip.getEndingDate());
		copy.setDescription(trip.getDescription());
		copy.setOwner(principal);

		for (String p : trip.getPhotos()) {
			copyPhotos.add(p);
		}
		copy.setPhotos(copyPhotos);

		save(copy);
	}

	public void subscribeToTrip(Trip trip) {
		User principal;
		Collection<User> subscriptors;

		principal = userService.findByPrincipal();
		subscriptors = trip.getSubscriptors();

		subscriptors.add(principal);
		trip.setSubscriptors(subscriptors);

	}

	public void notifySubscriptors(Trip trip) {
		Collection<User> subscriptors;
		User sender;

		subscriptors = trip.getSubscriptors();
		sender = trip.getOwner();

		for (User u : subscriptors) {
			Message m = messageService.create();
			Folder recipientInbox = folderService.findByNameAndActorId(
					"In Folder", u.getId());
			m.setSender(sender);
			m.setRecipient(u);
			m.setFolder(recipientInbox);
			m.setSubject(trip.getTitle() + " modified");
			m.setPriority(1);
			m.setBody("My trip with title: " + trip.getId()
					+ " has been modified");
			messageService.save(m);
		}
	}
}