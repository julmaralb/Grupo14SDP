package controllers.lecturer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.SubjectService;
import services.SyllabusService;
import controllers.AbstractController;
import domain.Subject;
import domain.Syllabus;

@Controller
@RequestMapping("/subject/lecturer")
public class SubjectLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private SyllabusService syllabusService;

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

	@RequestMapping(value = "/listSyllabi", method = RequestMethod.GET)
	public ModelAndView listSyllabi(@RequestParam int subjectId) {

		ModelAndView result;
		Collection<Syllabus> syllabi;

		syllabi = syllabusService.findBySubjectIdAndPrincipal(subjectId);

		result = new ModelAndView("syllabus/list");
		result.addObject("syllabi", syllabi);
		result.addObject("requestURI", "subject/lecturer/listSyllabi.do");

		return result;
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int subjectId) {
		ModelAndView result;
		Subject subject;
		Syllabus syllabus;

		subject = subjectService.findOne(subjectId);
		syllabus = syllabusService.findBySubjectIdAndCurrentYear(subjectId);
		result = new ModelAndView("subject/display");
		result.addObject("subject", subject);
		result.addObject("syllabus", syllabus);

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
