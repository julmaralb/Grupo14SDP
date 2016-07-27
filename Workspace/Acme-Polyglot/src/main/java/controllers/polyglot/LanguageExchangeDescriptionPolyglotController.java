package controllers.polyglot;

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

import services.LanguageExchangeDescriptionService;
import services.LanguageExchangeService;
import services.PolyglotService;
import controllers.AbstractController;
import domain.LanguageExchange;
import domain.LanguageExchangeDescription;
import domain.Polyglot;

@Controller
@RequestMapping("/languageExchangeDescription/polyglot")
public class LanguageExchangeDescriptionPolyglotController extends
		AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeDescriptionService languageExchangeDescriptionService;

	@Autowired
	private PolyglotService polyglotService;

	@Autowired
	private LanguageExchangeService languageExchangeService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeDescriptionPolyglotController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<LanguageExchangeDescription> languageExchangeDescriptions;

		languageExchangeDescriptions = languageExchangeDescriptionService
				.findAllByPrincipal();

		result = new ModelAndView("languageExchangeDescription/list");
		result.addObject("requestURI",
				"languageExchangeDescription/polyglot/list.do");
		result.addObject("languageExchangeDescriptions",
				languageExchangeDescriptions);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		LanguageExchangeDescription languageExchangeDescription;

		languageExchangeDescription = languageExchangeDescriptionService
				.create();
		result = createEditModelAndView(languageExchangeDescription);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int languageExchangeDescriptionId) {

		ModelAndView result;
		LanguageExchangeDescription languageExchangeDescription;
		Polyglot principal;

		principal = polyglotService.findByPrincipal();
		languageExchangeDescription = languageExchangeDescriptionService
				.findOne(languageExchangeDescriptionId);
		Assert.isTrue(languageExchangeDescription.getLanguageExchange()
				.getOwner().equals(principal));
		Assert.notNull(languageExchangeDescription);
		result = createEditModelAndView(languageExchangeDescription);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(
			@Valid LanguageExchangeDescription languageExchangeDescription,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(languageExchangeDescription,
					binding.toString());
		} else {
			try {
				languageExchangeDescriptionService
						.save(languageExchangeDescription);
				result = new ModelAndView(
						"redirect:/languageExchangeDescription/polyglot/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(languageExchangeDescription,
						"languageExchangeDescription.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(
			@Valid LanguageExchangeDescription languageExchangeDescription,
			BindingResult binding) {
		ModelAndView result;

		try {
			languageExchangeDescriptionService
					.delete(languageExchangeDescription);
			result = new ModelAndView(
					"redirect:/languageExchangeDescription/polyglot/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(languageExchangeDescription,
					"languageExchangeDescription.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			LanguageExchangeDescription languageExchangeDescription) {
		ModelAndView result;

		result = createEditModelAndView(languageExchangeDescription, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			LanguageExchangeDescription languageExchangeDescription,
			String message) {
		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;

		result = new ModelAndView("languageExchangeDescription/edit");
		languageExchanges = languageExchangeService.findAllByPrincipal();
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("languageExchangeDescription",
				languageExchangeDescription);
		result.addObject("message", message);

		return result;
	}
}