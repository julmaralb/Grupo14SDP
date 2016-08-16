package controllers.customer;

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

import services.DisplayPriceService;
import services.RentingService;
import services.ReservationService;
import services.SportEquipmentService;
import controllers.AbstractController;
import domain.Renting;
import domain.Reservation;
import domain.SportEquipment;

@Controller
@RequestMapping("/renting/customer")
public class RentingCustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RentingService rentingService;

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private SportEquipmentService sportEquipmentService;

	@Autowired
	private DisplayPriceService displayPriceService;

	// Constructors -----------------------------------------------------------

	public RentingCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Renting> rentings;

		rentings = rentingService.findAllByPrincipal();

		result = new ModelAndView("renting/list");
		result.addObject("rentings", rentings);
		result.addObject("requestURI", "renting/customer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int sportEquipmentId,
			int reservationId) {
		ModelAndView result;
		Renting renting;
		Reservation reservation;
		SportEquipment sportEquipment;
		Double price;

		renting = rentingService.create();
		sportEquipment = sportEquipmentService.findOne(sportEquipmentId);
		reservation = reservationService.findOne(reservationId);
		price = displayPriceService.calculateRentingPrice(sportEquipment
				.getPrice());
		renting.setDay(reservation.getDay());
		renting.setStart(reservation.getStart());
		renting.setEnd(reservation.getEnd());
		renting.setSportEquipment(sportEquipment);
		renting.setTotalPrice(price);
		result = createEditModelAndView(renting);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int rentingId) {

		ModelAndView result;
		Renting renting;

		renting = rentingService.findOne(rentingId);
		Assert.notNull(renting);
		result = createEditModelAndView(renting);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Renting renting, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(renting);
		} else {
			try {
				rentingService.save(renting);
				result = new ModelAndView("redirect:list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(renting, "reservation.creditCard.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Renting renting, BindingResult binding) {
		ModelAndView result;

		try {
			rentingService.delete(renting);
			result = new ModelAndView("redirect:list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(renting, "renting.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Renting renting) {
		ModelAndView result;

		result = createEditModelAndView(renting, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Renting renting,
			String message) {
		ModelAndView result;

		result = new ModelAndView("renting/edit");
		result.addObject("renting", renting);
		result.addObject("message", message);

		return result;
	}
}