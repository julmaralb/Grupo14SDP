package controllers.lecturer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.SubjectService;
import controllers.AbstractController;
import domain.Subject;

@Controller
@RequestMapping("/subject/lecturer")
public class SubjectLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SubjectService subjectService;

	// Constructors -----------------------------------------------------------

	public SubjectLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Subject> subjects;

		subjects = subjectService.findByPrincipal();

		result = new ModelAndView("subject/list");
		result.addObject("subjects", subjects);
		result.addObject("requestURI", "subject/lecturer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
