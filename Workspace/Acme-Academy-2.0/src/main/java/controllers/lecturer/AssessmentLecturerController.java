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

import services.AssessmentService;
import services.DeliverableService;
import services.RubricService;
import controllers.AbstractController;
import domain.Assessment;
import domain.Assignment;
import domain.Deliverable;
import domain.Rubric;

@Controller
@RequestMapping("/assessment/lecturer")
public class AssessmentLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AssessmentService assessmentService;

	@Autowired
	private DeliverableService deliverableService;

	@Autowired
	private RubricService rubricService;

	// Constructors -----------------------------------------------------------

	public AssessmentLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int deliverableId) {

		ModelAndView result;
		Collection<Assessment> assessments;

		assessments = assessmentService.findByDeliverableId(deliverableId);

		result = new ModelAndView("assessment/list");
		result.addObject("assessments", assessments);
		result.addObject("requestURI", "assessment/lecturer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int deliverableId) {
		ModelAndView result;
		Assessment assessment;
		Deliverable deliverable;

		assessment = assessmentService.create();
		deliverable = deliverableService.findOne(deliverableId);
		assessment.setDeliverable(deliverable);
		result = createEditModelAndView(assessment);
		result.addObject("deliverableId", deliverableId);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int assessmentId) {

		ModelAndView result;
		Assessment assessment;

		assessment = assessmentService.findOne(assessmentId);
		Assert.notNull(assessment);
		result = createEditModelAndView(assessment);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Assessment assessment, int rubricId,
			int deliverableId, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(assessment);
		} else {
			try {
				Rubric rubric = rubricService.findOne(rubricId);
				rubric.setInstantiated(true);
				Deliverable deliverable;
				deliverable = deliverableService.findOne(deliverableId);
				assessment.setDeliverable(deliverable);
				assessment.setNumber(rubric.getNumber());
				assessmentService.save(assessment);
				result = new ModelAndView(
						"redirect:/assessment/lecturer/list.do?deliverableId="
								+ deliverableId);
			} catch (Throwable oops) {
				result = createEditModelAndView(assessment,
						"assessment.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Assessment assessment,
			BindingResult binding) {
		ModelAndView result;

		try {
			assessmentService.delete(assessment);
			result = new ModelAndView("redirect:/group/lecturer/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(assessment,
					"assessment.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Assessment assessment) {
		ModelAndView result;

		result = createEditModelAndView(assessment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Assessment assessment,
			String message) {
		ModelAndView result;
		Collection<Rubric> rubrics;
		Assignment assignment = assessment.getDeliverable().getAssignment();

		rubrics = rubricService.findNotInstantiatedByAssignmentId(assignment
				.getId());
		result = new ModelAndView("assessment/edit");
		result.addObject("assessment", assessment);
		result.addObject("rubrics", rubrics);
		result.addObject("message", message);

		return result;
	}
}