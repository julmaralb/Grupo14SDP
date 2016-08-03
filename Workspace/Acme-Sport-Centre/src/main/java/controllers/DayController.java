package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.DayService;
import domain.Day;

@Controller
@RequestMapping("/day")
public class DayController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private DayService dayService;

	// Constructors -----------------------------------------------------------

	public DayController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int centreId) {

		ModelAndView result;
		Collection<Day> days;

		days = dayService.findAllDistinct();

		result = new ModelAndView("day/list");
		result.addObject("days", days);
		result.addObject("centreId", centreId);
		result.addObject("requestURI", "day/list.do");

		return result;

	}

	// Inactivo
	@RequestMapping(value = "/listByCentre", method = RequestMethod.GET)
	public ModelAndView listByCentre(@RequestParam int centreId) {

		ModelAndView result;
		Collection<Day> days;

		days = dayService.findAllDistinct();

		result = new ModelAndView("day/list");
		result.addObject("days", days);
		result.addObject("centreId", centreId);
		result.addObject("requestURI", "day/list.do");

		return result;

	}

}