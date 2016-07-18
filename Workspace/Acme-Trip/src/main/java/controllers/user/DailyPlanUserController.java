package controllers.user;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DailyPlanService;
import services.TripService;
import services.UserService;
import controllers.AbstractController;
import domain.DailyPlan;
import domain.Trip;
import domain.User;

@Controller
@RequestMapping("/dailyPlan/user")
public class DailyPlanUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private TripService tripService;

	@Autowired
	private DailyPlanService dailyPlanService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public DailyPlanUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int tripId) {
		ModelAndView result;
		DailyPlan dailyPlan;
		Trip trip;

		dailyPlan = dailyPlanService.create();
		trip = tripService.findOne(tripId);
		dailyPlan.setTrip(trip);
		result = createEditModelAndView(dailyPlan);
		result.addObject("tripId", tripId);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int dailyPlanId, int tripId) {

		ModelAndView result;
		DailyPlan dailyPlan;
		User pricipal;

		pricipal = userService.findByPrincipal();
		dailyPlan = dailyPlanService.findOne(dailyPlanId);
		Assert.isTrue(dailyPlan.getTrip().getOwner().equals(pricipal));
		Assert.notNull(dailyPlan);
		result = createEditModelAndView(dailyPlan);
		result.addObject("tripId", tripId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid DailyPlan dailyPlan, int tripId,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(dailyPlan);
		} else {
			try {
				Trip trip;
				trip = tripService.findOne(tripId);
				dailyPlan.setTrip(trip);
				dailyPlanService.save(dailyPlan);
				result = new ModelAndView("redirect:/dailyPlan/list.do?tripId="
						+ tripId);
			} catch (Throwable oops) {
				result = createEditModelAndView(dailyPlan,
						"dailyPlan.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid DailyPlan dailyPlan, BindingResult binding) {
		ModelAndView result;

		try {
			dailyPlanService.delete(dailyPlan);
			result = new ModelAndView("redirect:/");
		} catch (Throwable oops) {
			result = createEditModelAndView(dailyPlan, "dailyPlan.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(DailyPlan dailyPlan) {
		ModelAndView result;

		result = createEditModelAndView(dailyPlan, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(DailyPlan dailyPlan,
			String message) {
		ModelAndView result;

		result = new ModelAndView("dailyPlan/edit");
		result.addObject("dailyPlan", dailyPlan);
		result.addObject("message", message);

		return result;
	}
}