package controllers.student;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.AssignmentService;
import controllers.AbstractController;
import domain.Assignment;

@Controller
@RequestMapping("/assignment/student")
public class AssignmentStudentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AssignmentService assignmentService;

	// Constructors -----------------------------------------------------------

	public AssignmentStudentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Assignment> assignments;

		assignments = assignmentService.findAllByPrincipal();

		result = new ModelAndView("assignment/list");
		result.addObject("assignments", assignments);
		result.addObject("requestURI", "assignment/student/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listByGroup", method = RequestMethod.GET)
	public ModelAndView listByGroup(@RequestParam int groupId) {

		ModelAndView result;
		Collection<Assignment> assignments;

		assignments = assignmentService.findByGroupIdAndStudentPrincipal(groupId);

		result = new ModelAndView("assignment/list");
		result.addObject("assignments", assignments);
		result.addObject("requestURI", "assignment/student/listByGroup.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
}