package controllers.actor;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.LanguageExchangeService;
import services.LanguageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Language;
import domain.LanguageExchange;

@Controller
@RequestMapping("/languageExchange/actor")
public class LanguageExchangeActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeActorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list3MonthsAgo", method = RequestMethod.GET)
	public ModelAndView list3MonthsAgo() {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Actor principal;
		Collection<Language> languages;

		languageExchanges = languageExchangeService.find3MonthsAgo();
		principal = actorService.findByPrincipal();
		languages = languageService.findAll();

		result = new ModelAndView("languageExchange/list");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("principal", principal);
		result.addObject("languages", languages);
		result.addObject("requestURI",
				"languageExchange/actor/list3MonthsAgo.do");

		return result;
	}

	@RequestMapping(value = "/list3MonthsTime", method = RequestMethod.GET)
	public ModelAndView list3MonthsTime() {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Actor principal;
		Collection<Language> languages;

		languageExchanges = languageExchangeService.find3MonthsTime();
		principal = actorService.findByPrincipal();
		languages = languageService.findAll();

		result = new ModelAndView("languageExchange/list");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("principal", principal);
		result.addObject("languages", languages);
		result.addObject("requestURI",
				"languageExchange/actor/list3MonthsTime.do");

		return result;
	}
}