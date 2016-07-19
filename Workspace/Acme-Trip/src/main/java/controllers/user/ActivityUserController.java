package controllers.user;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityService;
import services.ActivityTypeService;
import services.UserService;
import controllers.AbstractController;
import domain.Activity;
import domain.ActivityType;
import domain.User;

@Controller
@RequestMapping("/activity/user")
public class ActivityUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActivityService activityService;

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityTypeService activityTypeService;

	// Constructors -----------------------------------------------------------

	public ActivityUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findAllByPrincipal();

		result = new ModelAndView("activity/list");
		result.addObject("requestURI", "activity/user/list.do");
		result.addObject("activities", activities);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Activity activity;

		activity = activityService.create();
		result = createEditModelAndView(activity);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int activityId) {

		ModelAndView result;
		Activity activity;
		User pricipal;
		pricipal = userService.findByPrincipal();

		activity = activityService.findOne(activityId);
		Assert.isTrue(activity.getUser().equals(pricipal));
		Assert.notNull(activity);
		result = createEditModelAndView(activity);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Activity activity, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(activity);
		} else {
			try {
				activityService.save(activity);
				result = new ModelAndView("redirect:/activity/user/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(activity,
						"activity.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Activity activity, BindingResult binding) {
		ModelAndView result;

		try {
			activityService.delete(activity);
			result = new ModelAndView("redirect:/activity/user/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(activity, "activity.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Activity activity) {
		ModelAndView result;

		result = createEditModelAndView(activity, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Activity activity,
			String message) {
		ModelAndView result;
		Collection<ActivityType> activityTypes;

		result = new ModelAndView("activity/edit");
		activityTypes = activityTypeService.findAll();
		result.addObject("activity", activity);
		result.addObject("activityTypes", activityTypes);
		result.addObject("message", message);

		return result;
	}
}