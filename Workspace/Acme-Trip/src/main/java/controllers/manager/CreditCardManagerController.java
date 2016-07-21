package controllers.manager;

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

import services.ActorService;
import services.CreditCardService;
import controllers.AbstractController;
import domain.Actor;
import domain.CreditCard;

@Controller
@RequestMapping("/creditCard/manager")
public class CreditCardManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CreditCardService creditCardService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public CreditCardManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<CreditCard> creditCards;

		creditCards = creditCardService.findAllByPrincipal();

		result = new ModelAndView("creditCard/list");
		result.addObject("requestURI", "creditCard/manager/list.do");
		result.addObject("creditCards", creditCards);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		CreditCard creditCard;

		creditCard = creditCardService.create();
		result = createEditModelAndView(creditCard);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int creditCardId) {

		ModelAndView result;
		CreditCard creditCard;
		Actor principal;

		principal = actorService.findByPrincipal();
		creditCard = creditCardService.findOne(creditCardId);
		Assert.notNull(creditCard);
		Assert.isTrue(creditCard.getManager().equals(principal));
		result = createEditModelAndView(creditCard);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CreditCard creditCard, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(creditCard);
		} else {
			try {
				creditCardService.save(creditCard);
				result = new ModelAndView(
						"redirect:/creditCard/manager/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(creditCard,
						"creditCard.expired.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid CreditCard creditCard,
			BindingResult binding) {
		ModelAndView result;

		try {
			creditCardService.delete(creditCard);
			result = new ModelAndView("redirect:/creditCard/manager/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(creditCard,
					"creditCard.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(CreditCard creditCard) {
		ModelAndView result;

		result = createEditModelAndView(creditCard, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(CreditCard creditCard,
			String message) {
		ModelAndView result;
		Collection<CreditCard> creditCards;

		result = new ModelAndView("creditCard/edit");
		creditCards = creditCardService.findAllByPrincipal();
		result.addObject("creditCard", creditCard);
		result.addObject("creditCards", creditCards);
		result.addObject("message", message);

		return result;
	}
}