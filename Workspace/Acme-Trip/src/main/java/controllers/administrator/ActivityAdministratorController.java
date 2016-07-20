package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import controllers.AbstractController;
import domain.Activity;

@Controller
@RequestMapping("/activity/administrator")
public class ActivityAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActivityService activityService;

	// Constructors -----------------------------------------------------------

	public ActivityAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findAll();

		result = new ModelAndView("activity/list");
		result.addObject("activities", activities);
		result.addObject("requestURI", "activity/administrator/list.do");

		return result;
	}

	// Other ------------------------------------------------------------------

	@RequestMapping(value = "/flagActivity", method = RequestMethod.GET)
	public ModelAndView flagActivity(@RequestParam int activityId) {
		ModelAndView result;

		Activity activity;

		activity = activityService.findOne(activityId);

		activityService.flagActivity(activity);

		result = new ModelAndView("redirect:/activity/administrator/list.do");

		return result;
	}
}