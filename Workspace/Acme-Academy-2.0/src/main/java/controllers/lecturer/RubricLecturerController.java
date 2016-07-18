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

import services.AssignmentService;
import services.RubricService;
import controllers.AbstractController;
import domain.Assignment;
import domain.Rubric;

@Controller
@RequestMapping("/rubric/lecturer")
public class RubricLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RubricService rubricService;

	@Autowired
	private AssignmentService assignmentService;

	// Constructors -----------------------------------------------------------

	public RubricLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int assignmentId) {

		ModelAndView result;
		Collection<Rubric> rubrics;

		rubrics = rubricService.findByAssignmentId(assignmentId);

		result = new ModelAndView("rubric/list");
		result.addObject("rubrics", rubrics);
		result.addObject("requestURI", "rubric/lecturer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Rubric rubric;

		rubric = rubricService.create();
		result = createEditModelAndView(rubric);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int rubricId) {

		ModelAndView result;
		Rubric rubric;

		rubric = rubricService.findOne(rubricId);
		Assert.notNull(rubric);
		result = createEditModelAndView(rubric);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Rubric rubric, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(rubric);
		} else {
			try {
				rubricService.save(rubric);
				result = new ModelAndView("redirect:/group/lecturer/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(rubric, "rubric.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Rubric rubric, BindingResult binding) {
		ModelAndView result;

		try {
			rubricService.delete(rubric);
			result = new ModelAndView("redirect:/group/lecturer/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(rubric, "rubric.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Rubric rubric) {
		ModelAndView result;

		result = createEditModelAndView(rubric, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Rubric rubric, String message) {
		ModelAndView result;
		Collection<Assignment> assignments;

		assignments = assignmentService.findAllByLecturerPrincipal();
		result = new ModelAndView("rubric/edit");
		result.addObject("rubric", rubric);
		result.addObject("assignments", assignments);
		result.addObject("message", message);

		return result;
	}
}