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
import services.GroupService;
import controllers.AbstractController;
import domain.Assignment;
import domain.Group;

@Controller
@RequestMapping("/assignment/lecturer")
public class AssignmentLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private GroupService groupService;

	// Constructors -----------------------------------------------------------

	public AssignmentLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------
	
	@RequestMapping(value = "/listByGroup", method = RequestMethod.GET)
	public ModelAndView listByGroup(@RequestParam int groupId) {

		ModelAndView result;
		Collection<Assignment> assignments;

		assignments = assignmentService.findByGroupIdAndLecturerPrincipal(groupId);

		result = new ModelAndView("assignment/list");
		result.addObject("assignments", assignments);
		result.addObject("requestURI", "assignment/lecturer/listByGroup.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Assignment assignment;

		assignment = assignmentService.create();
		result = createEditModelAndView(assignment);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int assignmentId) {

		ModelAndView result;
		Assignment assignment;

		assignment = assignmentService.findOne(assignmentId);
		Assert.notNull(assignment);
		result = createEditModelAndView(assignment);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Assignment assignment, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(assignment);
		} else {
			try {
				assignmentService.save(assignment);
				result = new ModelAndView("redirect:/group/lecturer/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(assignment,
						"assignment.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Assignment assignment,
			BindingResult binding) {
		ModelAndView result;

		try {
			assignmentService.delete(assignment);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(assignment,
					"assignment.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Assignment assignment) {
		ModelAndView result;

		result = createEditModelAndView(assignment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Assignment assignment,
			String message) {
		ModelAndView result;
		Collection<Group> groups;

		result = new ModelAndView("assignment/edit");
		groups = groupService.findByLecturerPrincipal();
		result.addObject("assignment", assignment);
		result.addObject("groups", groups);
		result.addObject("message", message);

		return result;
	}
}