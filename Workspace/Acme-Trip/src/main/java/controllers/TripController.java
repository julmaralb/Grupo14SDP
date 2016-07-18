package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.TripService;
import domain.Trip;

@Controller
@RequestMapping("/trip")
public class TripController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private TripService tripService;

	// Constructors -----------------------------------------------------------

	public TripController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Trip> trips;

		trips = tripService.findAll();

		result = new ModelAndView("trip/list");
		result.addObject("trips", trips);
		result.addObject("requestURI", "trip/list.do");

		return result;
	}

	@RequestMapping(value = "/listKeyword", method = RequestMethod.POST)
	public ModelAndView listKeyword(@RequestParam String keyword) {

		ModelAndView result;
		Collection<Trip> trips;
		try {
			trips = tripService.findByKeyword(keyword);
			result = new ModelAndView("trip/list");
			result.addObject("requestURI", "trips/listKeyword.do");
			result.addObject("trips", trips);
		} catch (Throwable error) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	@RequestMapping(value = "/listByActivityType", method = RequestMethod.GET)
	public ModelAndView listByActivityType(@RequestParam int activityTypeId) {

		ModelAndView result;
		Collection<Trip> trips;
		try {
			trips = tripService.findByActivityTypeId(activityTypeId);
			result = new ModelAndView("trip/list");
			result.addObject("requestURI", "trips/listByActivityType.do");
			result.addObject("trips", trips);
		} catch (Throwable error) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}
	
	@RequestMapping(value = "/listByUser", method = RequestMethod.GET)
	public ModelAndView listByUser(@RequestParam int userId) {

		ModelAndView result;
		Collection<Trip> trips;
		try {
			trips = tripService.findByUserId(userId);
			result = new ModelAndView("trip/list");
			result.addObject("requestURI", "trip/listByUser.do");
			result.addObject("trips", trips);
		} catch (Throwable error) {
			result = new ModelAndView("redirect:list.do");
		}
		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
}
