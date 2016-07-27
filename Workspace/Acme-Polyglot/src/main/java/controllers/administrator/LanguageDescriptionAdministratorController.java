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

import services.LanguageDescriptionService;
import services.LanguageService;
import controllers.AbstractController;
import domain.Language;
import domain.LanguageDescription;

@Controller
@RequestMapping("/languageDescription/administrator")
public class LanguageDescriptionAdministratorController extends
		AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageDescriptionService languageDescriptionService;

	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public LanguageDescriptionAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam String code, int languageId) {

		ModelAndView result;
		LanguageDescription languageDescription;

		languageDescription = languageDescriptionService
				.findByLanguageIdAndCode(languageId, code);

		result = new ModelAndView("languageDescription/display");
		result.addObject("languageDescription", languageDescription);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		LanguageDescription languageDescription;

		languageDescription = languageDescriptionService.create();
		result = createEditModelAndView(languageDescription);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int languageDescriptionId) {

		ModelAndView result;
		LanguageDescription languageDescription;

		languageDescription = languageDescriptionService
				.findOne(languageDescriptionId);
		Assert.notNull(languageDescription);
		result = createEditModelAndView(languageDescription);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LanguageDescription languageDescription,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(languageDescription);
		} else {
			try {
				languageDescriptionService.save(languageDescription);
				result = new ModelAndView(
						"redirect:/language/administrator/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(languageDescription,
						"languageDescription.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid LanguageDescription languageDescription,
			BindingResult binding) {
		ModelAndView result;

		try {
			languageDescriptionService.delete(languageDescription);
			result = new ModelAndView(
					"redirect:/language/administrator/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(languageDescription,
					"languageDescription.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			LanguageDescription languageDescription) {
		ModelAndView result;

		result = createEditModelAndView(languageDescription, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			LanguageDescription languageDescription, String message) {
		ModelAndView result;
		Collection<Language> languages;

		result = new ModelAndView("languageDescription/edit");
		languages = languageService.findAll();
		result.addObject("languages", languages);
		result.addObject("languageDescription", languageDescription);
		result.addObject("message", message);

		return result;
	}
}