package controllers.lecturer;

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

import services.BibliographyService;
import controllers.AbstractController;
import domain.Bibliography;

@Controller
@RequestMapping("/bibliography/lecturer")
public class BibliographyLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BibliographyService bibliographyService;

	// Constructors -----------------------------------------------------------

	public BibliographyLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Bibliography> bibliographies;

		bibliographies = bibliographyService.findAll();

		result = new ModelAndView("bibliography/list");
		result.addObject("bibliographies", bibliographies);
		result.addObject("requestURI", "bibliography/lecturer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Bibliography bibliography;

		bibliography = bibliographyService.create();
		result = createEditModelAndView(bibliography);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int bibliographyId) {

		ModelAndView result;
		Bibliography bibliography;

		bibliography = bibliographyService.findOne(bibliographyId);
		Assert.notNull(bibliography);
		result = createEditModelAndView(bibliography);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Bibliography bibliography,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(bibliography);
		} else {
			try {
				bibliographyService.save(bibliography);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(bibliography,
						"bibliography.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Bibliography bibliography,
			BindingResult binding) {
		ModelAndView result;

		try {
			bibliographyService.delete(bibliography);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(bibliography,
					"bibliography.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Bibliography bibliography) {
		ModelAndView result;

		result = createEditModelAndView(bibliography, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Bibliography bibliography,
			String message) {
		ModelAndView result;

		result = new ModelAndView("bibliography/edit");
		result.addObject("bibliography", bibliography);
		result.addObject("message", message);

		return result;
	}
}