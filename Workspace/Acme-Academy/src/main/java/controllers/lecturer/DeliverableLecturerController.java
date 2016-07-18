package controllers.lecturer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DeliverableService;

import controllers.AbstractController;
import domain.Deliverable;

@Controller
@RequestMapping("/deliverable/lecturer")
public class DeliverableLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private DeliverableService deliverableService;

	// Constructors -----------------------------------------------------------

	public DeliverableLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int assignmentId) {

		ModelAndView result;
		Collection<Deliverable> deliverables;

		deliverables = deliverableService.findByAssignmentIdAndLecturer(assignmentId);

		result = new ModelAndView("deliverable/list");
		result.addObject("deliverables", deliverables);
		result.addObject("requestURI", "deliverable/lecturer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------

}
