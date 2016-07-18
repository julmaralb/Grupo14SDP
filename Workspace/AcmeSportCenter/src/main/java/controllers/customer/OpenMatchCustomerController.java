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

import services.CustomerService;
import services.OpenMatchService;
import services.ReservationService;
import controllers.AbstractController;
import domain.Customer;
import domain.OpenMatch;
import domain.Reservation;

@Controller
@RequestMapping("/openMatch/customer")
public class OpenMatchCustomerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private OpenMatchService openMatchService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private ReservationService reservationService;

	// Constructors -----------------------------------------------------------

	public OpenMatchCustomerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<OpenMatch> openMatches;
		Customer principal;

		openMatches = openMatchService.findAll();

		result = new ModelAndView("openMatch/list");
		result.addObject("openMatches", openMatches);
		result.addObject("requestURI", "openMatch/customer/list.do");
		try {
			principal = customerService.findByPrincipal();
			result.addObject("principal", principal);
		} catch (Throwable oops) {
			result = new ModelAndView("openMatch/list");
		}

		return result;
	}

	@RequestMapping(value = "/listMine", method = RequestMethod.GET)
	public ModelAndView listMine() {

		ModelAndView result;
		Collection<OpenMatch> openMatches;
		Customer principal;

		openMatches = openMatchService.findByOwnOrJoinedByCustomerId();

		result = new ModelAndView("openMatch/list");
		result.addObject("openMatches", openMatches);
		result.addObject("requestURI", "openMatch/customer/list.do");
		try {
			principal = customerService.findByPrincipal();
			result.addObject("principal", principal);
		} catch (Throwable oops) {
			result = new ModelAndView("openMatch/list");
		}

		return result;
	}

	// Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		OpenMatch openMatch;

		openMatch = openMatchService.create();
		result = createEditModelAndView(openMatch);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int openMatchId) {

		ModelAndView result;
		OpenMatch openMatch;

		try {
			openMatch = openMatchService.findOne(openMatchId);
			Assert.notNull(openMatch);
			openMatchService.checkOwner(openMatch);
			result = createEditModelAndView(openMatch);
		} catch (Throwable oops) {
			result = new ModelAndView("redirect:list.do");
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute OpenMatch openMatch,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(openMatch);
		} else {
			try {
				openMatchService.save(openMatch);
				result = new ModelAndView("redirect:listMine.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(openMatch,
						"openMatch.commit.error");
			}
		}

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(OpenMatch openMatch, BindingResult binding) {
		ModelAndView result;

		try {
			openMatchService.delete(openMatch);
			result = new ModelAndView("redirect:listMine.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(openMatch, "openMatch.commit.error");
		}

		return result;
	}

	// Other ------------------------------------------------------------------

	@RequestMapping(value = "/join", method = RequestMethod.GET)
	public ModelAndView join(@RequestParam int openMatchId) {

		ModelAndView result;
		OpenMatch openMatch;

		openMatch = openMatchService.findOne(openMatchId);
		Assert.notNull(openMatch);
		openMatchService.join(openMatch);

		result = new ModelAndView("redirect:listMine.do");

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(OpenMatch openMatch) {

		ModelAndView result;

		result = createEditModelAndView(openMatch, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(OpenMatch openMatch,
			String message) {

		ModelAndView result;
		Collection<Reservation> reservations;

		reservations = reservationService.findAllNotUsedByCustomerId();
		result = new ModelAndView("openMatch/edit");
		result.addObject("openMatch", openMatch);
		if (openMatch.getReservation() != null) {
			reservations.add(openMatch.getReservation());

		}
		result.addObject("reservations", reservations);
		result.addObject("message", message);

		return result;
	}
}