package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LecturerService;
import services.SubjectService;
import services.SyllabusService;
import domain.Lecturer;
import domain.Subject;
import domain.Syllabus;

@Controller
@RequestMapping("/subject")
public class SubjectController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private SyllabusService syllabusService;

	// Constructors -----------------------------------------------------------

	public SubjectController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Subject> subjects;

		subjects = subjectService.findAll();

		result = new ModelAndView("subject/list");
		result.addObject("subjects", subjects);
		result.addObject("requestURI", "subject/list.do");

		return result;
	}

	@RequestMapping(value = "/listLecturers", method = RequestMethod.GET)
	public ModelAndView listLecturers(@RequestParam int subjectId) {

		ModelAndView result;
		Lecturer lecturers;

		lecturers = lecturerService.findBySubjectId(subjectId);

		result = new ModelAndView("lecturer/list");
		result.addObject("lecturers", lecturers);
		result.addObject("requestURI", "lecturer/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listSyllabi", method = RequestMethod.GET)
	public ModelAndView listSyllabi(@RequestParam int subjectId) {

		ModelAndView result;
		Collection<Syllabus> syllabi;

		syllabi = syllabusService.findBySubjectId(subjectId);

		result = new ModelAndView("syllabus/list");
		result.addObject("syllabi", syllabi);
		result.addObject("requestURI", "subject/listSyllabi.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
