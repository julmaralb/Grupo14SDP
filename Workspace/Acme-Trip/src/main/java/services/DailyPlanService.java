package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DailyPlanRepository;
import domain.DailyPlan;

@Service
@Transactional
public class DailyPlanService {
	
	// Managed repository -----------------------------------------------------

			@Autowired
			private DailyPlanRepository dailyPlanRepository;

			// Supporting services ----------------------------------------------------

			// Constructors -----------------------------------------------------------

			public DailyPlanService() {
				super();
			}

			// Simple CRUD methods ----------------------------------------------------

			public DailyPlan create() {
				DailyPlan result;

				result = new DailyPlan();

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

		


}
