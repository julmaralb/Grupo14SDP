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

import services.CourtService;
import services.SportEquipmentService;
import controllers.AbstractController;
import domain.Court;
import domain.SportEquipment;

@Controller
@RequestMapping("/sportEquipment/administrator")
public class SportEquipmentAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SportEquipmentService sportEquipmentService;

	@Autowired
	private CourtService courtService;

	// Constructors -----------------------------------------------------------

	public SportEquipmentAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<SportEquipment> sportEquipments;

		sportEquipments = sportEquipmentService.findAll();

		result = new ModelAndView("sportEquipment/list");
		result.addObject("sportEquipments", sportEquipments);
		result.addObject("requestURI", "sportEquipment/administrator/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SportEquipment sportEquipment;

		sportEquipment = sportEquipmentService.create();
		result = createEditModelAndView(sportEquipment);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sportEquipmentId) {

		ModelAndView result;
		SportEquipment sportEquipment;

		sportEquipment = sportEquipmentService.findOne(sportEquipmentId);
		Assert.notNull(sportEquipment);
		result = createEditModelAndView(sportEquipment);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid SportEquipment sportEquipment,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(sportEquipment);
		} else {
			try {
				sportEquipmentService.save(sportEquipment);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(sportEquipment,
						"sportEquipment.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid SportEquipment sportEquipment,
			BindingResult binding) {
		ModelAndView result;

		try {
			sportEquipmentService.delete(sportEquipment);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(sportEquipment,
					"sportEquipment.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(SportEquipment sportEquipment) {
		ModelAndView result;

		result = createEditModelAndView(sportEquipment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			SportEquipment sportEquipment, String message) {
		ModelAndView result;
		Collection<Court> courts;

		result = new ModelAndView("sportEquipment/edit");
		courts = courtService.findAll();
		result.addObject("sportEquipment", sportEquipment);
		result.addObject("courts", courts);
		result.addObject("message", message);

		return result;
	}
}