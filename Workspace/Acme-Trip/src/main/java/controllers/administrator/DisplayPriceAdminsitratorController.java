package controllers.administrator;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import services.DisplayPriceService;
import controllers.AbstractController;
import domain.DisplayPrice;

@Controller
@RequestMapping("/displayPrice/administrator")
public class DisplayPriceAdminsitratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private DisplayPriceService displayPriceService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------

	public DisplayPriceAdminsitratorController() {
		super();
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit() {

		ModelAndView result;
		DisplayPrice displayPrice;

		displayPrice = displayPriceService.findDisplayPrice();
		Assert.notNull(displayPrice);
		result = createEditModelAndView(displayPrice);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid DisplayPrice displayPrice,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(displayPrice);
		} else {
			try {
				displayPriceService.save(displayPrice);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(displayPrice,
						"displayPrice.commit.error");
			}
		}
		return result;
	}

	// Charge Displays --------------------------------------------------------

	@RequestMapping(value = "/chargeDisplays", method = RequestMethod.GET)
	public ModelAndView chargeDisplays() {
		ModelAndView result;

		administratorService.chargeTodaysDisplays();

		result = new ModelAndView("redirect:/chargeRecord/administrator/list.do");

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(DisplayPrice displayPrice) {
		ModelAndView result;

		result = createEditModelAndView(displayPrice, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(DisplayPrice displayPrice,
			String message) {
		ModelAndView result;

		result = new ModelAndView("displayPrice/edit");
		result.addObject("displayPrice", displayPrice);
		result.addObject("message", message);

		return result;
	}
}