package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActivityTypeRepository;
import domain.ActivityType;

@Service
@Transactional
public class ActivityTypeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ActivityTypeRepository activityTypeRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public ActivityTypeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ActivityType create() {
		ActivityType result;

		result = new ActivityType();

		return result;
	}

	public ActivityType findOne(int activityTypeId) {
		Assert.notNull(activityTypeId);

		ActivityType result;

		result = activityTypeRepository.findOne(activityTypeId);

		return result;
	}

	public Collection<ActivityType> findAll() {

		Collection<ActivityType> result;

		result = activityTypeRepository.findAll();

		return result;
	}

	public void save(ActivityType activityType) {
		Assert.notNull(activityType);

		activityTypeRepository.save(activityType);
	}

	public void delete(ActivityType activityType) {
		Assert.notNull(activityType);

		activityTypeRepository.delete(activityType);
	}

	// Other business methods -------------------------------------------------
}