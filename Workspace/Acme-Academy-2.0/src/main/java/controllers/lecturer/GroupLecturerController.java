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

import services.GroupService;
import services.SubjectService;
import controllers.AbstractController;
import domain.Group;
import domain.Subject;

@Controller
@RequestMapping("/group/lecturer")
public class GroupLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private GroupService groupService;

	@Autowired
	private SubjectService subjectService;

	// Constructors -----------------------------------------------------------

	public GroupLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Group> groups;

		groups = groupService.findByLecturerPrincipal();

		result = new ModelAndView("group/list");
		result.addObject("groups", groups);
		result.addObject("requestURI", "group/lecturer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Group group;

		group = groupService.create();
		result = createEditModelAndView(group);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int groupId) {

		ModelAndView result;
		Group group;

		group = groupService.findOne(groupId);
		Assert.notNull(group);
		result = createEditModelAndView(group);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Group group, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(group);
		} else {
			try {
				groupService.save(group);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(group, "group.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Group group, BindingResult binding) {
		ModelAndView result;

		try {
			groupService.delete(group);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(group, "group.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Group group) {
		ModelAndView result;

		result = createEditModelAndView(group, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Group group, String message) {
		ModelAndView result;
		Collection<Subject> subjects;

		result = new ModelAndView("group/edit");
		subjects = subjectService.findByPrincipal();
		result.addObject("group", group);
		result.addObject("subjects", subjects);
		result.addObject("message", message);

		return result;
	}
}