package controllers.student;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.GroupService;
import services.StudentService;

import controllers.AbstractController;
import domain.Group;
import domain.Student;

@Controller
@RequestMapping("/group/student")
public class GroupStudentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private GroupService groupService;

	@Autowired
	private StudentService studentService;

	// Constructors -----------------------------------------------------------

	public GroupStudentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Group> groups;
		Student principal;

		groups = groupService.findAll();
		principal = studentService.findByPrincipal();

		result = new ModelAndView("group/list");
		result.addObject("groups", groups);
		result.addObject("principal", principal);
		result.addObject("requestURI", "group/student/list.do");

		return result;
	}
	
	@RequestMapping(value = "/listMyGroups", method = RequestMethod.GET)
	public ModelAndView listMyGroups() {

		ModelAndView result;
		Collection<Group> groups;

		groups = groupService.findByStudentPrincipal();
		
		result = new ModelAndView("group/list");
		result.addObject("groups", groups);
		result.addObject("requestURI", "group/student/listMyGroups.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------
	
	// Other ------------------------------------------------------------------

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int groupId) {

		ModelAndView result;
		Group group;

		group = groupService.findOne(groupId);
		Assert.notNull(group);
		groupService.join(group);

		result = new ModelAndView("redirect:list.do");

		return result;
	}


	// Ancillary methods ------------------------------------------------------

}
