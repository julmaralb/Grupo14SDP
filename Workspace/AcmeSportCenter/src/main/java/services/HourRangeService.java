package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.HourRangeRepository;
import domain.HourRange;

@Service
@Transactional
public class HourRangeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private HourRangeRepository hourRangeRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public HourRangeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public HourRange create() {
		HourRange result;

		result = new HourRange();

		return result;
	}

	public HourRange findOne(int hourRangeId) {
		Assert.notNull(hourRangeId);

		HourRange result;

		result = hourRangeRepository.findOne(hourRangeId);

		return result;

	}

	public Collection<HourRange> findAll() {

		Collection<HourRange> result;

		result = hourRangeRepository.findAll();

		return result;
	}

	public void save(HourRange hourRange) {
		Assert.notNull(hourRange);

		hourRangeRepository.save(hourRange);
	}

	public void delete(HourRange hourRange) {
		Assert.notNull(hourRange);

		hourRangeRepository.delete(hourRange);

	}

	// Other business methods -------------------------------------------------

	public Collection<HourRange> findByCourtIdAndDay(int courtId, String day) {
		Collection<HourRange> result;

		result = hourRangeRepository.findByCourtAndDayId(courtId, day);

		return result;
	}

}