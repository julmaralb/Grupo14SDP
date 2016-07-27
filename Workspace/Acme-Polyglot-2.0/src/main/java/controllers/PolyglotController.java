package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.PolyglotService;
import domain.Actor;
import domain.Polyglot;
import forms.PolyglotForm;

@Controller
@RequestMapping("/polyglot")
public class PolyglotController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private PolyglotService polyglotService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public PolyglotController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Polyglot> polyglots;

		polyglots = polyglotService.findAll();

		result = new ModelAndView("polyglot/list");
		result.addObject("polyglots", polyglots);
		result.addObject("requestURI", "polyglot/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		PolyglotForm polyglotForm;

		polyglotForm = new PolyglotForm();
		result = createEditModelAndView(polyglotForm);

		return result;
	}

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public ModelAndView terms() {
		ModelAndView result;

		result = new ModelAndView("polyglot/terms");

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Actor polyglot;

		polyglot = actorService.findByPrincipal();
		Assert.notNull(polyglot);

		res = new ModelAndView("polyglot/modifyProfile");
		res.addObject("polyglot", polyglot);

		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid PolyglotForm polyglotForm, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(polyglotForm);
		} else {
			try {
				Polyglot polyglot = polyglotService.reconstruct(polyglotForm);
				polyglotService.save(polyglot);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(polyglotForm, "polyglot.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView modifyProfile(@Valid Polyglot polyglot, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView2(polyglot, null);
		} else {
			try {
				polyglotService.modifyProfile(polyglot);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView2(polyglot, "polyglot.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(PolyglotForm polyglotForm) {

		ModelAndView result;

		result = createEditModelAndView(polyglotForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(PolyglotForm polyglotForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("polyglot/edit");
		result.addObject("polyglotForm", polyglotForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView2(Polyglot polyglot, String message) {
		ModelAndView res;

		res = new ModelAndView("polyglot/modifyProfile");
		res.addObject("polyglot", polyglot);
		res.addObject("message", message);

		return res;
	}
}