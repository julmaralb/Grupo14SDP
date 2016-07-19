package controllers.administrator;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.ManagerService;
import controllers.AbstractController;
import domain.Manager;

@Controller
@RequestMapping("/manager/administrator")
public class ManagerAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ManagerService managerService;

	// Constructors -----------------------------------------------------------

	public ManagerAdministratorController() {
		super();
	}

	// Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Manager manager;

		manager = managerService.create();
		result = createEditModelAndView(manager);

		return result;
	}

	// Edition ---------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int adminId) {

		ModelAndView result;
		Manager administatror;

		administatror = managerService.findOne(adminId);
		Assert.notNull(administatror);
		result = createEditModelAndView(administatror);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Manager manager,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(manager);
		} else {
			try {
				managerService.save(manager);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(manager, "manager.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods -----------------------------------------------

	protected ModelAndView createEditModelAndView(Manager manager) {

		ModelAndView result;

		result = createEditModelAndView(manager, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Manager manager,
			String message) {

		ModelAndView result;
		UserAccount userAccount;
		Collection<Authority> authorities;

		userAccount = manager.getUserAccount();
		authorities = Authority.listAuthorities();
		result = new ModelAndView("manager/edit");

		result.addObject("manager", manager);
		result.addObject("userAccount", userAccount);

		result.addObject("authorities", authorities);
		result.addObject("message", message);

		return result;
	}
}