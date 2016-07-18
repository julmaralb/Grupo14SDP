package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SlotRepository;
import domain.Slot;

@Service
@Transactional
public class SlotService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private SlotRepository slotRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SlotService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Slot create() {
		Slot result;

		result = new Slot();

		return result;
	}

	public Slot findOne(int slotId) {
		Assert.notNull(slotId);

		Slot result;

		result = slotRepository.findOne(slotId);

		return result;
	}

	public Collection<Slot> findAll() {

		Collection<Slot> result;

		result = slotRepository.findAll();

		return result;
	}

	public void save(Slot slot) {
		Assert.notNull(slot);

		slotRepository.save(slot);
	}

	public void delete(Slot slot) {
		Assert.notNull(slot);

		slotRepository.delete(slot);
	}

	// Other business methods -------------------------------------------------

	public Collection<Slot> findAllByDailyPlanId(int dailyPlanId) {
		Collection<Slot> result;

		result = slotRepository.findAllByDailyPlanId(dailyPlanId);

		return result;
	}
}