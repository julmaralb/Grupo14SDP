package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DailyPlanService;
import domain.DailyPlan;

@Controller
@RequestMapping("/dailyPlan")
public class DailyPlanController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private DailyPlanService dailyPlanService;

	// Constructors -----------------------------------------------------------

	public DailyPlanController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int tripId) {
		ModelAndView result;
		Collection<DailyPlan> dailyPlans;

		dailyPlans = dailyPlanService.findAllByTripId(tripId);

		result = new ModelAndView("dailyPlan/list");
		result.addObject("requestURI", "dailyPlan/list.do");
		result.addObject("dailyPlans", dailyPlans);

		return result;
	}
}