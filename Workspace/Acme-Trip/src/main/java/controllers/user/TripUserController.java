package controllers.user;

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

import services.TripService;
import services.UserService;
import controllers.AbstractController;
import domain.Trip;
import domain.User;

@Controller
@RequestMapping("/trip/user")
public class TripUserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private TripService tripService;

	@Autowired
	private UserService userService;

	// Constructors -----------------------------------------------------------

	public TripUserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Trip> trips;

		trips = tripService.findByPrincipal();

		result = new ModelAndView("trip/list");
		result.addObject("requestURI", "trip/user/list.do");
		result.addObject("trips", trips);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Trip trip;

		trip = tripService.create();
		result = createEditModelAndView(trip);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int tripId) {

		ModelAndView result;
		Trip trip;
		User pricipal;

		pricipal = userService.findByPrincipal();
		trip = tripService.findOne(tripId);
		Assert.isTrue(trip.getOwner().equals(pricipal));
		Assert.notNull(trip);
		result = createEditModelAndView(trip);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Trip trip, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(trip);
		} else {
			try {
				tripService.save(trip);
				result = new ModelAndView("redirect:/trip/user/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(trip, "trip.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Trip trip, BindingResult binding) {
		ModelAndView result;

		try {
			tripService.delete(trip);
			result = new ModelAndView("redirect:/trip/user/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(trip, "trip.commit.error");
		}
		return result;
	}

	// Others -----------------------------------------------------------------

	@RequestMapping(value = "/copyTrip", method = RequestMethod.GET)
	public ModelAndView copyTrip(@RequestParam int tripId) {
		ModelAndView result;

		Trip trip;

		trip = tripService.findOne(tripId);

		tripService.copyTrip(trip);

		result = new ModelAndView("redirect:/trip/user/list.do");

		return result;
	}

	@RequestMapping(value = "/subscribe", method = RequestMethod.GET)
	public ModelAndView subscribe(@RequestParam int tripId) {
		ModelAndView result;

		Trip trip;

		trip = tripService.findOne(tripId);

		tripService.subscribeToTrip(trip);

		result = new ModelAndView("redirect:/trip/list.do");

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Trip trip) {
		ModelAndView result;

		result = createEditModelAndView(trip, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Trip trip, String message) {
		ModelAndView result;

		result = new ModelAndView("trip/edit");
		result.addObject("trip", trip);
		result.addObject("message", message);

		return result;
	}
}