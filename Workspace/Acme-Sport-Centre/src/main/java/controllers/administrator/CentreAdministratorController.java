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

import services.CentreService;
import controllers.AbstractController;
import domain.Centre;

@Controller
@RequestMapping("/centre/administrator")
public class CentreAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CentreService centreService;

	// Constructors -----------------------------------------------------------

	public CentreAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Centre> centres;

		centres = centreService.findAll();

		result = new ModelAndView("centre/list");
		result.addObject("centres", centres);
		result.addObject("requestURI", "centre/administrator/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Centre centre;

		centre = centreService.create();
		result = createEditModelAndView(centre);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int centreId) {

		ModelAndView result;
		Centre centre;

		centre = centreService.findOne(centreId);
		Assert.notNull(centre);
		result = createEditModelAndView(centre);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Centre centre, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(centre);
		} else {
			try {
				centreService.save(centre);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(centre, "centre.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Centre centre, BindingResult binding) {
		ModelAndView result;

		try {
			centreService.delete(centre);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(centre, "centre.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Centre centre) {
		ModelAndView result;

		result = createEditModelAndView(centre, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Centre centre, String message) {
		ModelAndView result;

		result = new ModelAndView("centre/edit");
		result.addObject("centre", centre);
		result.addObject("message", message);

		return result;
	}
}