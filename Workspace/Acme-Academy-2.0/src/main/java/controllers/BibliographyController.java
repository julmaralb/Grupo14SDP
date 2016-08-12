package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import domain.Bibliography;

import services.BibliographyService;

@Controller
@RequestMapping("/bibliography")
public class BibliographyController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BibliographyService bibliographyService;

	// Constructors -----------------------------------------------------------

	public BibliographyController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/listBibliographies", method = RequestMethod.GET)
	public ModelAndView listBibliographies(@RequestParam int syllabusId) {

		ModelAndView result;
		Collection<Bibliography> bibliographies;

		bibliographies = bibliographyService.findBySyllabusId(syllabusId);

		result = new ModelAndView("bibliography/list");
		result.addObject("bibliographies", bibliographies);
		result.addObject("requestURI", "bibliography/listBibliographies.do");

		return result;
	}
}