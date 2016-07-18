package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SlotService;
import domain.Slot;

@Controller
@RequestMapping("/slot")
public class SlotController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SlotService slotService;

	// Constructors -----------------------------------------------------------

	public SlotController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int dailyPlanId) {
		ModelAndView result;
		Collection<Slot> slots;

		slots = slotService.findAllByDailyPlanId(dailyPlanId);

		result = new ModelAndView("slot/list");
		result.addObject("requestURI", "slot/list.do");
		result.addObject("slots", slots);

		return result;
	}
}