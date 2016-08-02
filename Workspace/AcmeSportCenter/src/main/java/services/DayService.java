package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DayRepository;
import domain.Day;

@Service
@Transactional
public class DayService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private DayRepository dayRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public DayService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Day create() {
		Day result;

		result = new Day();

		return result;
	}

	public Day findOne(int dayId) {
		Assert.notNull(dayId);

		Day result;

		result = dayRepository.findOne(dayId);

		return result;

	}

	public Collection<Day> findAll() {

		Collection<Day> result;

		result = dayRepository.findAll();

		return result;
	}

	public void save(Day day) {
		Assert.notNull(day);

		dayRepository.save(day);
	}

	public void delete(Day day) {
		Assert.notNull(day);

		dayRepository.delete(day);

	}

	// Other business methods -------------------------------------------------

	public Collection<Day> findByDayAndCentreId(Date day, int centreId) {
		Collection<Day> result;

		result = dayRepository.findByDateAndCentreId(day, centreId);

		return result;
	}

	public Collection<Day> findAllDistinct() {
		Collection<Day> result;

		result = dayRepository.findAllDistinct();

		return result;
	}

	public Day findByHourRangeId(int hourRangeId) {
		Day result;

		result = dayRepository.findByHourRangeId(hourRangeId);

		return result;
	}

	public Collection<Day> findByDay(Date day) {
		Collection<Day> result;

		result = dayRepository.findByDate(day);

		return result;
	}
}