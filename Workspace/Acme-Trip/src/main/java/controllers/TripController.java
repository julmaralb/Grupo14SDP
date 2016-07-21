package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.TripService;
import domain.Banner;
import domain.Trip;

@Controller
@RequestMapping("/trip")
public class TripController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private TripService tripService;
	
	@Autowired
	private BannerService bannerService;

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

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int tripId) {
		ModelAndView result;
		Trip trip;
		Banner banner;

		trip = tripService.findOne(tripId);
		banner = bannerService.findRandomToDisplay(trip);

		result = new ModelAndView("trip/display");
		result.addObject("trip", trip);
		result.addObject("banner", banner);

		return result;
	}

	// Creation ---------------------------------------------------------------

	// Edition ----------------------------------------------------------------

	// Ancillary methods ------------------------------------------------------
}
