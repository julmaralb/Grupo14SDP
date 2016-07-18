package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActivityTypeService;
import domain.ActivityType;

@Controller
@RequestMapping("/activityType")
public class ActivityTypeController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActivityTypeService activityTypesService;

	// Constructors -----------------------------------------------------------

	public ActivityTypeController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<ActivityType> activityTypes;

		activityTypes = activityTypesService.findAll();

		result = new ModelAndView("activityType/list");
		result.addObject("activityTypes", activityTypes);
		result.addObject("requestURI", "activityType/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
}