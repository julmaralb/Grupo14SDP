package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActivityRepository;
import domain.Activity;
import domain.User;

@Service
@Transactional
public class ActivityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActivityRepository activityRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public ActivityService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Activity create() {
		Activity result;

		result = new Activity();

		return result;
	}

	public Activity findOne(int activityId) {
		Assert.notNull(activityId);

		Activity result;

		result = activityRepository.findOne(activityId);

		return result;
	}

	public Collection<Activity> findAll() {

		Collection<Activity> result;

		result = activityRepository.findAll();

		return result;
	}

	public void save(Activity activity) {
		Assert.notNull(activity);

		activityRepository.save(activity);
	}

	public void delete(Activity activity) {
		Assert.notNull(activity);

		activityRepository.delete(activity);
	}

	// Other business methods -------------------------------------------------

	public Collection<Activity> findAllByPrincipal() {
		Collection<Activity> result;
		User principal;

		principal = userService.findByPrincipal();
		result = activityRepository.findAllByUserId(principal.getId());

		return result;
	}
}