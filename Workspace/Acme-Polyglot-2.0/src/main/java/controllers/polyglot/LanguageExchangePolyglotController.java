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

import services.LanguageExchangeService;
import services.LanguageService;
import services.PolyglotService;
import controllers.AbstractController;
import domain.Language;
import domain.LanguageExchange;
import domain.Polyglot;

@Controller
@RequestMapping("/languageExchange/polyglot")
public class LanguageExchangePolyglotController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;

	@Autowired
	private PolyglotService polyglotService;

	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangePolyglotController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Polyglot principal;

		languageExchanges = languageExchangeService.findAllByPrincipal();
		principal = polyglotService.findByPrincipal();

		result = new ModelAndView("languageExchange/list");
		result.addObject("requestURI", "languageExchange/polyglot/list.do");
		result.addObject("principal", principal);
		result.addObject("languageExchanges", languageExchanges);

		return result;
	}

	@RequestMapping(value = "/listJoined", method = RequestMethod.GET)
	public ModelAndView listJoined() {
		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Polyglot principal;

		languageExchanges = languageExchangeService.findAllJoinedByPrincipal();
		principal = polyglotService.findByPrincipal();

		result = new ModelAndView("languageExchange/list");
		result.addObject("requestURI",
				"languageExchange/polyglot/listJoined.do");
		result.addObject("principal", principal);
		result.addObject("languageExchanges", languageExchanges);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		LanguageExchange languageExchange;

		languageExchange = languageExchangeService.create();
		result = createEditModelAndView(languageExchange);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int languageExchangeId) {

		ModelAndView result;
		LanguageExchange languageExchange;
		Polyglot pricipal;

		pricipal = polyglotService.findByPrincipal();
		languageExchange = languageExchangeService.findOne(languageExchangeId);
		Assert.isTrue(languageExchange.getOwner().equals(pricipal));
		Assert.notNull(languageExchange);
		result = createEditModelAndView(languageExchange);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LanguageExchange languageExchange,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(languageExchange,
					binding.toString());
		} else {
			try {
				languageService.incrementCounter(languageExchange);
				languageExchangeService.save(languageExchange);
				result = new ModelAndView(
						"redirect:/languageExchange/polyglot/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(languageExchange,
						"languageExchange.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid LanguageExchange languageExchange,
			BindingResult binding) {
		ModelAndView result;

		try {
			languageExchangeService.delete(languageExchange);
			result = new ModelAndView(
					"redirect:/languageExchange/polyglot/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(languageExchange,
					"languageExchange.delete.error");
		}
		return result;
	}

	// Others -----------------------------------------------------------------

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int languageExchangeId) {
		ModelAndView result;

		LanguageExchange languageExchange;

		languageExchange = languageExchangeService.findOne(languageExchangeId);

		languageExchangeService.joinLanguageExchange(languageExchange);

		result = new ModelAndView("redirect:/languageExchange/polyglot/listJoined.do");

		return result;
	}

	@RequestMapping(value = "/unJoin", method = RequestMethod.GET)
	public ModelAndView unJoin(@RequestParam int languageExchangeId) {
		ModelAndView result;

		LanguageExchange languageExchange;

		languageExchange = languageExchangeService.findOne(languageExchangeId);

		languageExchangeService.unJoinLanguageExchange(languageExchange);

		result = new ModelAndView("redirect:/languageExchange/polyglot/listJoined.do");

		return result;
	}

	@RequestMapping(value = "/cancelExchange", method = RequestMethod.GET)
	public ModelAndView cancelExchange(@RequestParam int languageExchangeId) {
		ModelAndView result;

		LanguageExchange languageExchange;

		languageExchange = languageExchangeService.findOne(languageExchangeId);

		languageExchangeService.cancelLanguageExchange(languageExchange);

		result = new ModelAndView("redirect:/languageExchange/polyglot/list.do");

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			LanguageExchange languageExchange) {
		ModelAndView result;

		result = createEditModelAndView(languageExchange, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			LanguageExchange languageExchange, String message) {
		ModelAndView result;
		Collection<Language> languages;

		result = new ModelAndView("languageExchange/edit");
		languages = languageService.findAll();
		result.addObject("languageExchange", languageExchange);
		result.addObject("languages", languages);
		result.addObject("message", message);

		return result;
	}
}