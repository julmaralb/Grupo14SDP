package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.LanguageService;
import domain.Language;

@Controller
@RequestMapping("/language")
public class languageController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public languageController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Language> languages;

		languages = languageService.findAll();

		result = new ModelAndView("language/list");
		result.addObject("requestURI", "language/list.do");
		result.addObject("languages", languages);

		return result;
	}
}