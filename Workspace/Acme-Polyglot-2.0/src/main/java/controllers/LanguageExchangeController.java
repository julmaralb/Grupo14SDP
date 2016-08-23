package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.KeywordService;
import services.LanguageExchangeService;
import services.LanguageService;
import domain.Language;
import domain.LanguageExchange;

@Controller
@RequestMapping("/languageExchange")
public class LanguageExchangeController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;

	@Autowired
	private KeywordService keywordService;

	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Collection<Language> languages;

		languageExchanges = languageExchangeService.findAll();
		languages = languageService.findAll();

		result = new ModelAndView("languageExchange/list");
		result.addObject("requestURI", "languageExchange/list.do");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("languages", languages);

		return result;
	}

	@RequestMapping(value = "/list3MonthsAgo", method = RequestMethod.GET)
	public ModelAndView list3MonthsAgo() {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Collection<Language> languages;

		languageExchanges = languageExchangeService.find3MonthsAgo();
		languages = languageService.findAll();

		result = new ModelAndView("languageExchange/list");
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("requestURI", "languageExchange/list3MonthsAgo.do");
		result.addObject("languages", languages);

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
		result.addObject("requestURI", "languageExchange/list3MonthsTime.do");
		result.addObject("languages", languages);

		return result;
	}

	@RequestMapping(value = "/listKeyword1", method = RequestMethod.POST)
	public ModelAndView listKeyword1(@RequestParam String keyword) {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Collection<Language> languages;
		try {
			languageExchanges = languageExchangeService.findByKeyword(keyword);
			languages = languageService.findAll();
			result = new ModelAndView("languageExchange/list");
			result.addObject("requestURI", "languageExchange/listKeyword.do");
			result.addObject("languageExchanges", languageExchanges);
			result.addObject("languages", languages);
		} catch (Throwable error) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/listKeyword2", method = RequestMethod.POST)
	public ModelAndView listKeyword2(@RequestParam String keyword) {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Collection<Language> languages;
		try {
			languageExchanges = languageExchangeService.findByKeyword(keyword);
			languages = languageService.findAll();
			keywordService.processKeyword(keyword);
			result = new ModelAndView("languageExchange/list");
			result.addObject("requestURI", "languageExchange/listKeyword.do");
			result.addObject("languageExchanges", languageExchanges);
			result.addObject("languages", languages);
		} catch (Throwable error) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/listByLanguage", method = RequestMethod.GET)
	public ModelAndView listByLanguage(@RequestParam int languageId) {

		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;
		Collection<Language> languages;
		try {
			languageExchanges = languageExchangeService
					.findByLanguageId(languageId);
			languages = languageService.findAll();
			result = new ModelAndView("languageExchange/list");
			result.addObject("requestURI", "languageExchange/listByLanguage.do");
			result.addObject("languageExchanges", languageExchanges);
			result.addObject("languages", languages);
		} catch (Throwable error) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}
}