package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.LanguageExchangeService;
import services.LanguageService;
import domain.Language;
import domain.LanguageExchange;

@Controller
@RequestMapping("/languageExchange")
public class LanguageExchangeController extends AbstractController{

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;
	
	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list3MonthsAgo", method = RequestMethod.GET)
	public ModelAndView list3MonthsAgo() {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Collection<Language> languages;

		languageExchanges = languageExchangeService.find3MonthsAgo();
		languages = languageService.findAll();

		result = new ModelAndView("languageExchange/list");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("languages", languages);
		result.addObject("requestURI", "languageExchange/list3MonthsAgo.do");

		return result;
	}

	@RequestMapping(value = "/list3MonthsTime", method = RequestMethod.GET)
	public ModelAndView list3MonthsTime() {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Collection<Language> languages;

		languageExchanges = languageExchangeService.find3MonthsTime();
		languages = languageService.findAll();

		result = new ModelAndView("languageExchange/list");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("languages", languages);
		result.addObject("requestURI", "languageExchange/list3MonthsTime.do");

		return result;
	}
}