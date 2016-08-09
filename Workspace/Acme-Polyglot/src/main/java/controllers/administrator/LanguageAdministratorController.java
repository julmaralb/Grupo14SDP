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

import services.LanguageService;
import controllers.AbstractController;
import domain.Language;

@Controller
@RequestMapping("/language/administrator")
public class LanguageAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public LanguageAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Language> languages;

		languages = languageService.findAll();

		result = new ModelAndView("language/list");
		result.addObject("requestURI", "language/administrator/list.do");
		result.addObject("languages", languages);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Language language;

		language = languageService.create();
		result = createEditModelAndView(language);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int languageId) {

		ModelAndView result;
		Language language;

		language = languageService.findOne(languageId);
		Assert.notNull(language);
		result = createEditModelAndView(language);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Language language, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(language,binding.toString());
		} else {
			try {
				languageService.save(language);
				result = new ModelAndView(
						"redirect:/language/administrator/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(language,
						"language.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Language language, BindingResult binding) {
		ModelAndView result;

		try {
			languageService.delete(language);
			result = new ModelAndView(
					"redirect:/language/administrator/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(language, "language.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Language language) {
		ModelAndView result;

		result = createEditModelAndView(language, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Language language,
			String message) {
		ModelAndView result;

		result = new ModelAndView("language/edit");
		result.addObject("language", language);
		result.addObject("message", message);

		return result;
	}
}