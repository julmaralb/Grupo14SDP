package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.BibliographyService;
import controllers.AbstractController;
import domain.Bibliography;

@Controller
@RequestMapping("/bibliography/administrator")
public class BibliographyAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BibliographyService bibliographyService;

	// Constructors -----------------------------------------------------------

	public BibliographyAdministratorController() {
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
		result.addObject("requestURI", "bibliography/administrator/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
