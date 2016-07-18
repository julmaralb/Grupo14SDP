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
import services.LecturerService;
import services.SubjectService;
import services.SyllabusService;
import controllers.AbstractController;
import domain.Bibliography;
import domain.Subject;
import domain.Syllabus;

@Controller
@RequestMapping("/syllabus/lecturer")
public class SyllabusLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SyllabusService syllabusService;

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private BibliographyService bibliographyService;

	// Constructors -----------------------------------------------------------

	public SyllabusLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Syllabus> syllabi;

		syllabi = syllabusService.findByPrincipal();

		result = new ModelAndView("syllabus/list");
		result.addObject("syllabi", syllabi);
		result.addObject("requestURI", "syllabus/lecturer/list.do");

		return result;
	}

	@RequestMapping(value = "/listBibliographies", method = RequestMethod.GET)
	public ModelAndView listBibliographies(@RequestParam int syllabusId) {

		ModelAndView result;
		Collection<Bibliography> bibliographies;

		bibliographies = bibliographyService.findBySyllabusId(syllabusId);

		result = new ModelAndView("bibliography/list");
		result.addObject("bibliographies", bibliographies);
		result.addObject("requestURI",
				"bibliography/lecturer/listBibliographies.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Syllabus syllabus;

		syllabus = syllabusService.create();
		result = createEditModelAndView(syllabus);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int syllabusId) {

		ModelAndView result;
		Syllabus syllabus;

		syllabus = syllabusService.findOne(syllabusId);
		Assert.notNull(syllabus);
		Assert.isTrue(syllabus.getSubject().getLecturer()
				.equals(lecturerService.findByPrincipal()));
		result = createEditModelAndView(syllabus);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Syllabus syllabus, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(syllabus);
		} else {
			try {
				lecturerService.incrementCounter(syllabus);
				syllabusService.save(syllabus);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(syllabus,
						"syllabus.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Syllabus syllabus, BindingResult binding) {
		ModelAndView result;

		try {
			syllabusService.delete(syllabus);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(syllabus, "syllabus.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Syllabus syllabus) {
		ModelAndView result;

		result = createEditModelAndView(syllabus, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Syllabus syllabus,
			String message) {
		ModelAndView result;
		Collection<Subject> subjects;
		Collection<Bibliography> bibliographies;

		subjects = subjectService.findByPrincipal();
		bibliographies = bibliographyService.findAll();
		result = new ModelAndView("syllabus/edit");
		result.addObject("syllabus", syllabus);
		result.addObject("subjects", subjects);
		result.addObject("bibliographies", bibliographies);
		result.addObject("message", message);

		return result;
	}
}