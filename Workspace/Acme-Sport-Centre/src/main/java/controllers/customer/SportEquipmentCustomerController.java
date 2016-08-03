package controllers.customer;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import services.ReservationService;
import controllers.AbstractController;
import domain.Customer;
import domain.Reservation;
import domain.SportEquipment;

@Controller
@RequestMapping("/sportEquipment/customer")
public class SportEquipmentCustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ReservationService reservationService;

	@Autowired
	private CustomerService customerService;

	// Constructors -----------------------------------------------------------

	public SportEquipmentCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/listByCourt", method = RequestMethod.GET)
	public ModelAndView listByCourt(@RequestParam int reservationId) {

		ModelAndView result;
		Collection<SportEquipment> sportEquipments;
		Reservation reservation;
		Customer principal;

		reservation = reservationService.findOne(reservationId);
		principal = customerService.findByPrincipal();
		Assert.isTrue(reservation.getCustomer().equals(principal));
		sportEquipments = reservation.getCourt().getSportEquipments();

		result = new ModelAndView("sportEquipment/list");
		result.addObject("sportEquipments", sportEquipments);
		result.addObject("reservationId", reservationId);
		result.addObject("requestURI", "sportEquipment/customer/listByCourt.do");

		return result;
	}
}