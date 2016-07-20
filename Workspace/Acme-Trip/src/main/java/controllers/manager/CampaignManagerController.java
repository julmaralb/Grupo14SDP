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
import services.CampaignService;
import services.CreditCardService;
import controllers.AbstractController;
import domain.Actor;
import domain.Campaign;
import domain.CreditCard;

@Controller
@RequestMapping("/campaign/manager")
public class CampaignManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CampaignService campaignService;

	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public CampaignManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Campaign> campaigns;

		campaigns = campaignService.findAllByPrincipal();

		result = new ModelAndView("campaign/list");
		result.addObject("requestURI", "campaign/manager/list.do");
		result.addObject("campaigns", campaigns);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Campaign campaign;

		campaign = campaignService.create();
		result = createEditModelAndView(campaign);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int campaignId) {

		ModelAndView result;
		Campaign campaign;
		Actor principal;

		principal = actorService.findByPrincipal();
		campaign = campaignService.findOne(campaignId);
		Assert.notNull(campaign);
		Assert.isTrue(campaign.getManager().equals(principal));
		result = createEditModelAndView(campaign);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Campaign campaign, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(campaign);
		} else {
			try {
				campaignService.save(campaign);
				result = new ModelAndView("redirect:/campaign/manager/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(campaign,
						"campaign.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Campaign campaign, BindingResult binding) {
		ModelAndView result;

		try {
			campaignService.delete(campaign);
			result = new ModelAndView("redirect:/campaign/manager/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(campaign, "campaign.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Campaign campaign) {
		ModelAndView result;

		result = createEditModelAndView(campaign, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Campaign campaign,
			String message) {
		ModelAndView result;
		Collection<CreditCard> creditCards;

		result = new ModelAndView("campaign/edit");
		creditCards = creditCardService.findAllByPrincipal();
		result.addObject("campaign", campaign);
		result.addObject("creditCards", creditCards);
		result.addObject("message", message);

		return result;
	}
}