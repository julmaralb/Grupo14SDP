package controllers.customer;

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

import services.ReservationService;
import controllers.AbstractController;
import domain.Reservation;

@Controller
@RequestMapping("/reservation/customer")
public class ReservationCustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ReservationService reservationService;

	// Constructors -----------------------------------------------------------

	public ReservationCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Reservation> reservations;

		reservations = reservationService.findByCustomerId();

		result = new ModelAndView("reservation/list");
		result.addObject("reservations", reservations);
		result.addObject("requestURI", "reservation/customer/list.do");

		return result;
	}

	// Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Reservation reservation;

		reservation = reservationService.create();
		result = createEditModelAndView(reservation);

		return result;
	}

	@RequestMapping(value = "/createReservation", method = RequestMethod.POST)
	public ModelAndView createResevation(@RequestParam int courtId,
			@RequestParam int hourRangeId, @RequestParam int dayId) {

		ModelAndView result;
		Reservation reservation;

		try {
			reservation = reservationService.createReservation(courtId,
					hourRangeId, dayId);
			result = createEditModelAndView(reservation);
			result.addObject("hourRangeId", hourRangeId);
		} catch (Throwable error) {
			result = new ModelAndView(
					"redirect:/../AcmeSportCenter/reservation/schedule.do");
		}
		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int reservationId) {

		ModelAndView result;
		Reservation reservation;

		reservation = reservationService.findOne(reservationId);
		Assert.notNull(reservation);
		result = createEditModelAndView(reservation);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Reservation reservation,
			BindingResult binding, @RequestParam int hourRangeId) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(reservation, binding.toString());
		} else {
			try {
				reservationService.save(reservation, hourRangeId);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(reservation,
						"reservation.creditCard.error");
			}
		}
		return result;
	}

	// BookCourt --------------------------------------------------------------

	@RequestMapping(value = "/bookCourt", method = RequestMethod.POST)
	public ModelAndView bookCourt(@RequestParam int courtId,
			@RequestParam int hourRangeId) {
		ModelAndView result;
		boolean error1 = false;

		try {
			reservationService.bookCourt(courtId, hourRangeId);
			result = new ModelAndView("redirect:../customer/list.do");
		} catch (Throwable error) {
			error1 = true;
			result = new ModelAndView(
					"redirect:/../AcmeSportCenter/reservation/schedule.do");
			result.addObject("showError", error1);
		}

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Reservation reservation) {

		ModelAndView result;

		result = createEditModelAndView(reservation, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Reservation reservation,
			String message) {

		ModelAndView result;

		result = new ModelAndView("reservation/edit");
		result.addObject("reservation", reservation);
		result.addObject("message", message);

		return result;
	}
}