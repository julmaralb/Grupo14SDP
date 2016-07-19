package controllers.administrator;

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

import services.ActivityTypeService;
import controllers.AbstractController;
import domain.ActivityType;

@Controller
@RequestMapping("/activityType/administrator")
public class ActivityTypeAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActivityTypeService activityTypeService;

	// Constructors -----------------------------------------------------------

	public ActivityTypeAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ActivityType> activityTypes;

		activityTypes = activityTypeService.findAll();

		result = new ModelAndView("activityType/list");
		result.addObject("requestURI", "activityType/administrator/list.do");
		result.addObject("activityTypes", activityTypes);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		ActivityType activityType;

		activityType = activityTypeService.create();
		result = createEditModelAndView(activityType);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int activityTypeId) {

		ModelAndView result;
		ActivityType activityType;

		activityType = activityTypeService.findOne(activityTypeId);
		Assert.notNull(activityType);
		result = createEditModelAndView(activityType);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActivityType activityType,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(activityType);
		} else {
			try {
				activityTypeService.save(activityType);
				result = new ModelAndView(
						"redirect:/activityType/administrator/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(activityType,
						"activityType.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid ActivityType activityType,
			BindingResult binding) {
		ModelAndView result;

		try {
			activityTypeService.delete(activityType);
			result = new ModelAndView(
					"redirect:/activityType/administrator/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(activityType,
					"activityType.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(ActivityType activityType) {
		ModelAndView result;

		result = createEditModelAndView(activityType, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(ActivityType activityType,
			String message) {
		ModelAndView result;

		result = new ModelAndView("activityType/edit");
		result.addObject("activityType", activityType);
		result.addObject("message", message);

		return result;
	}
}