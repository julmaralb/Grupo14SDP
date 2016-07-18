package controllers.student;

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
import services.DeliverableService;
import services.StudentService;
import controllers.AbstractController;
import domain.Assignment;
import domain.Deliverable;
import domain.Student;

@Controller
@RequestMapping("/deliverable/student")
public class DeliverableStudentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private DeliverableService deliverableService;

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private StudentService studentService;

	// Constructors -----------------------------------------------------------

	public DeliverableStudentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int assignmentId) {

		ModelAndView result;
		Collection<Deliverable> deliverables;
		boolean allInstantiated;

		deliverables = deliverableService.findByAssignmentId(assignmentId);
		allInstantiated = assignmentService
				.allRubricsInstantiated(assignmentId);

		result = new ModelAndView("deliverable/list");
		result.addObject("deliverables", deliverables);
		result.addObject("allInstantiated", allInstantiated);
		result.addObject("requestURI", "deliverable/student/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int assignmentId) {
		ModelAndView result;
		Deliverable deliverable;
		Assignment assignment;

		deliverable = deliverableService.create();
		assignment = assignmentService.findOne(assignmentId);
		deliverable.setAssignment(assignment);
		result = createEditModelAndView(deliverable);
		result.addObject("assignmentId", assignmentId);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int deliverableId) {

		ModelAndView result;
		Deliverable deliverable;
		Student principal;

		principal = studentService.findByPrincipal();
		deliverable = deliverableService.findOne(deliverableId);
		Assert.isTrue(deliverable.getAssignment().getGroup().getStudents()
				.contains(principal));
		Assert.notNull(deliverable);
		result = createEditModelAndView(deliverable);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Deliverable deliverable, int assignmentId,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(deliverable);
		} else {
			try {
				Assignment assignment;
				assignment = assignmentService.findOne(assignmentId);
				deliverable.setAssignment(assignment);
				deliverableService.save(deliverable);
				result = new ModelAndView(
						"redirect:/deliverable/student/list.do?assignmentId="
								+ assignmentId);
			} catch (Throwable oops) {
				result = createEditModelAndView(deliverable,
						"deliverable.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Deliverable deliverable,
			BindingResult binding) {
		ModelAndView result;

		try {
			deliverableService.delete(deliverable);
			result = new ModelAndView("redirect:/");
		} catch (Throwable oops) {
			result = createEditModelAndView(deliverable,
					"deliverable.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Deliverable deliverable) {
		ModelAndView result;

		result = createEditModelAndView(deliverable, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Deliverable deliverable,
			String message) {
		ModelAndView result;

		result = new ModelAndView("deliverable/edit");
		result.addObject("deliverable", deliverable);
		result.addObject("message", message);

		return result;
	}
}