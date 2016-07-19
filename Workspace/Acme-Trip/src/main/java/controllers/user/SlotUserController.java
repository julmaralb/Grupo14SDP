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
import services.DailyPlanService;
import services.SlotService;
import services.UserService;
import controllers.AbstractController;
import domain.Activity;
import domain.DailyPlan;
import domain.Slot;
import domain.User;

@Controller
@RequestMapping("/slot/user")
public class SlotUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SlotService slotService;

	@Autowired
	private DailyPlanService dailyPlanService;

	@Autowired
	private UserService userService;

	@Autowired
	private ActivityService activityService;

	// Constructors -----------------------------------------------------------

	public SlotUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int dailyPlanId) {
		ModelAndView result;
		DailyPlan dailyPlan;
		Slot slot;

		slot = slotService.create();
		dailyPlan = dailyPlanService.findOne(dailyPlanId);
		slot.setDailyPlan(dailyPlan);
		result = createEditModelAndView(slot);
		result.addObject("dailyPlanId", dailyPlanId);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int slotId, int dailyPlanId) {

		ModelAndView result;
		Slot slot;
		User principal;

		principal = userService.findByPrincipal();
		slot = slotService.findOne(slotId);
		Assert.isTrue(slot.getDailyPlan().getTrip().getOwner().equals(principal));
		Assert.notNull(slot);
		result = createEditModelAndView(slot);
		result.addObject("dailyPlanId", dailyPlanId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Slot slot, int dailyPlanId,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(slot);
		} else {
			try {
				DailyPlan dailyPlan;
				dailyPlan = dailyPlanService.findOne(dailyPlanId);
				slot.setDailyPlan(dailyPlan);
				slotService.save(slot);
				result = new ModelAndView("redirect:/slot/list.do?dailyPlanId="
						+ dailyPlanId);
			} catch (Throwable oops) {
				result = createEditModelAndView(slot, "slot.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Slot slot, BindingResult binding) {
		ModelAndView result;

		try {
			slotService.delete(slot);
			result = new ModelAndView("redirect:/");
		} catch (Throwable oops) {
			result = createEditModelAndView(slot, "slot.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Slot slot) {
		ModelAndView result;

		result = createEditModelAndView(slot, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Slot slot, String message) {
		ModelAndView result;
		Collection<Activity> activities;

		activities = activityService.findAllByPrincipal();
		;
		result = new ModelAndView("slot/edit");
		result.addObject("slot", slot);
		result.addObject("activities", activities);
		result.addObject("message", message);

		return result;
	}
}