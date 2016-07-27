package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.LanguageExchangeService;
import domain.LanguageExchange;

@Controller
@RequestMapping("/languageExchange")
public class LanguageExchangeController extends AbstractController{

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list3MonthsAgo", method = RequestMethod.GET)
	public ModelAndView list3MonthsAgo() {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;

		languageExchanges = languageExchangeService.find3MonthsAgo();

		result = new ModelAndView("languageExchange/list");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("requestURI", "languageExchange/list3MonthsAgo.do");

		return result;
	}

	@RequestMapping(value = "/list3MonthsTime", method = RequestMethod.GET)
	public ModelAndView list3MonthsTime() {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;

		languageExchanges = languageExchangeService.find3MonthsTime();

		result = new ModelAndView("languageExchange/list");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("requestURI", "languageExchange/list3MonthsTime.do");

		return result;
	}
}