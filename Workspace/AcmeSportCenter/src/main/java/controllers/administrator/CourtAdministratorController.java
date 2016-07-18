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
import services.CourtService;
import controllers.AbstractController;
import domain.Centre;
import domain.Court;

@Controller
@RequestMapping("/court/administrator")
public class CourtAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CourtService courtService;

	@Autowired
	private CentreService centreService;

	// Constructors -----------------------------------------------------------

	public CourtAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Court> courts;

		courts = courtService.findAll();

		result = new ModelAndView("court/list");
		result.addObject("courts", courts);
		result.addObject("requestURI", "court/adminsitrator/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Court court;

		court = courtService.create();
		result = createEditModelAndView(court);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int courtId) {

		ModelAndView result;
		Court court;

		court = courtService.findOne(courtId);
		Assert.notNull(court);
		result = createEditModelAndView(court);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Court court, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(court);
		} else {
			try {
				courtService.save(court);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(court, "court.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Court court, BindingResult binding) {
		ModelAndView result;

		try {
			courtService.delete(court);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(court, "court.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Court court) {
		ModelAndView result;

		result = createEditModelAndView(court, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Court court, String message) {
		ModelAndView result;
		Collection<Centre> centres;

		result = new ModelAndView("court/edit");
		centres = centreService.findAll();
		result.addObject("court", court);
		result.addObject("centres", centres);
		result.addObject("message", message);

		return result;
	}
}