package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;
import controllers.AbstractController;

@Controller
@RequestMapping("/day/administrator")
public class DayAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------

	public DayAdministratorController() {
		super();
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/createToday", method = RequestMethod.GET)
	public ModelAndView createToday() {

		ModelAndView result;

		administratorService.createTodaySchedules();

		result = new ModelAndView("redirect:/");

		return result;
	}
}