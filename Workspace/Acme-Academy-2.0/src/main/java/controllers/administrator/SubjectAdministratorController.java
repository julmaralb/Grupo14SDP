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

import services.LecturerService;
import services.SubjectService;
import controllers.AbstractController;
import domain.Lecturer;
import domain.Subject;

@Controller
@RequestMapping("/subject/administrator")
public class SubjectAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private LecturerService lecturerService;

	// Constructors -----------------------------------------------------------

	public SubjectAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Subject subject;

		subject = subjectService.create();
		result = createEditModelAndView(subject);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int subjectId) {

		ModelAndView result;
		Subject subject;

		subject = subjectService.findOne(subjectId);
		Assert.notNull(subject);
		result = createEditModelAndView(subject);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Subject subject, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(subject);
		} else {
			try {
				subjectService.save(subject);
				result = new ModelAndView("redirect:/subject/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(subject, "subject.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Subject subject, BindingResult binding) {
		ModelAndView result;

		try {
			subjectService.delete(subject);
			result = new ModelAndView("redirect:/subject/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(subject, "subject.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Subject subject) {
		ModelAndView result;

		result = createEditModelAndView(subject, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Subject subject,
			String message) {
		ModelAndView result;
		Collection<Lecturer> lecturers;

		lecturers = lecturerService.findAll();
		result = new ModelAndView("subject/edit");
		result.addObject("subject", subject);
		result.addObject("lecturers", lecturers);
		result.addObject("message", message);

		return result;
	}
}