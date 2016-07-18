package controllers.lecturer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssessmentService;
import services.AssignmentService;
import services.DeliverableService;
import controllers.AbstractController;
import domain.Assignment;
import domain.Deliverable;

@Controller
@RequestMapping("/deliverable/lecturer")
public class DeliverableLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private DeliverableService deliverableService;

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private AssessmentService assessmentService;

	// Constructors -----------------------------------------------------------

	public DeliverableLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int assignmentId) {

		ModelAndView result;
		Collection<Deliverable> deliverables;
		Assignment assignment;
		boolean allInstantiated;
		boolean computable;

		deliverables = deliverableService
				.findByAssignmentIdAndLecturer(assignmentId);
		allInstantiated = assignmentService
				.allRubricsInstantiated(assignmentId);
		assignment = assignmentService.findOne(assignmentId);
		computable = assessmentService.computable(assignment);

		result = new ModelAndView("deliverable/list");
		result.addObject("deliverables", deliverables);
		result.addObject("allInstantiated", allInstantiated);
		result.addObject("computable", computable);
		result.addObject("requestURI", "deliverable/lecturer/list.do");

		return result;
	}

	@RequestMapping(value = "/computeMark", method = RequestMethod.GET)
	public ModelAndView computeMark(@RequestParam int deliverableId) {

		ModelAndView result;
		Deliverable deliverable;
		Assignment assignment;
		boolean computable;

		deliverable = deliverableService.findOne(deliverableId);
		assignment = deliverable.getAssignment();
		computable = assessmentService.computable(assignment);
		Assert.notNull(deliverable);
		Assert.isTrue(computable);
		assessmentService.computeMark(deliverable);

		result = new ModelAndView(
				"redirect:/deliverable/lecturer/list.do?assignmentId="
						+ assignment.getId());

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}