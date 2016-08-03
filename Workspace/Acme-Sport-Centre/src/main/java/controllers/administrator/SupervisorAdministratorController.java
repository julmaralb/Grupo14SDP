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

import services.SupervisorService;
import controllers.AbstractController;
import domain.Supervisor;

@Controller
@RequestMapping("/supervisor/administrator")
public class SupervisorAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SupervisorService supervisorService;

	// Constructors -----------------------------------------------------------

	public SupervisorAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Supervisor> supervisors;

		supervisors = supervisorService.findAll();

		result = new ModelAndView("supervisor/list");
		result.addObject("supervisors", supervisors);
		result.addObject("requestURI", "supervisor/adminsitrator/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Supervisor supervisor;

		supervisor = supervisorService.create();
		result = createEditModelAndView(supervisor);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int supervisorId) {

		ModelAndView result;
		Supervisor supervisor;

		supervisor = supervisorService.findOne(supervisorId);
		Assert.notNull(supervisor);
		result = createEditModelAndView(supervisor);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Supervisor supervisor, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(supervisor);
		} else {
			try {
				supervisorService.save(supervisor);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(supervisor,
						"supervisor.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Supervisor supervisor) {
		ModelAndView result;

		result = createEditModelAndView(supervisor, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Supervisor supervisor,
			String message) {
		ModelAndView result;

		result = new ModelAndView("supervisor/edit");
		result.addObject("supervisor", supervisor);
		result.addObject("message", message);

		return result;
	}
}