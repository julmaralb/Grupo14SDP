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
import domain.Lecturer;
import domain.Subject;

@Controller
@RequestMapping("/lecturer")
public class LecturerController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private SubjectService subjectService;

	// Constructors -----------------------------------------------------------

	public LecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Lecturer> lecturers;

		lecturers = lecturerService.findAll();

		result = new ModelAndView("lecturer/list");
		result.addObject("lecturers", lecturers);
		result.addObject("requestURI", "lecturer/list.do");

		return result;
	}

	@RequestMapping(value = "/listSubjects", method = RequestMethod.GET)
	public ModelAndView listSubjects(@RequestParam int lecturerId) {

		ModelAndView result;
		Collection<Subject> subjects;

		subjects = subjectService.findByLecturerId(lecturerId);

		result = new ModelAndView("subject/list");
		result.addObject("subjects", subjects);
		result.addObject("requestURI", "subject/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
