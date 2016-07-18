package services;

import java.util.ArrayList;
import java.util.Collection;

import javassist.expr.NewArray;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DailyPlanRepository;
import domain.DailyPlan;
import domain.Slot;

@Service
@Transactional
public class DailyPlanService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private DailyPlanRepository dailyPlanRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public DailyPlanService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public DailyPlan create() {
		DailyPlan result;
		Collection<Slot> slots;

		result = new DailyPlan();
		slots = new ArrayList<Slot>();
		result.setSlots(slots);

		return result;
	}

	public DailyPlan findOne(int dailyPlanId) {
		Assert.notNull(dailyPlanId);

		DailyPlan result;

		result = dailyPlanRepository.findOne(dailyPlanId);

		return result;
	}

	public Collection<DailyPlan> findAll() {

		Collection<DailyPlan> result;

		result = dailyPlanRepository.findAll();

		return result;
	}

	public void save(DailyPlan dailyPlan) {
		Assert.notNull(dailyPlan);

		dailyPlanRepository.save(dailyPlan);
	}

	public void delete(DailyPlan dailyPlan) {
		Assert.notNull(dailyPlan);

		dailyPlanRepository.delete(dailyPlan);
	}

	// Other business methods -------------------------------------------------

	public Collection<DailyPlan> findAllByTripId(int tripId) {
		Collection<DailyPlan> result;

		result = dailyPlanRepository.findAllByTripId(tripId);

		return result;
	}
}