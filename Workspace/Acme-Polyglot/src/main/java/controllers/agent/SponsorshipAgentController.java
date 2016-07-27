package controllers.agent;

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

import services.AgentService;
import services.LanguageExchangeService;
import services.SponsorshipService;
import controllers.AbstractController;
import domain.Agent;
import domain.LanguageExchange;
import domain.Sponsorship;

@Controller
@RequestMapping("/sponsorship/agent")
public class SponsorshipAgentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;

	@Autowired
	private AgentService agentService;

	@Autowired
	private SponsorshipService sponsorshipService;

	// Constructors -----------------------------------------------------------

	public SponsorshipAgentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Sponsorship> sponsorships;

		sponsorships = sponsorshipService.findAllByPrincipal();

		result = new ModelAndView("sponsorship/list");
		result.addObject("requestURI", "sponsorship/agent/list.do");
		result.addObject("sponsorships", sponsorships);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Sponsorship sponsorship;

		sponsorship = sponsorshipService.create();
		result = createEditModelAndView(sponsorship);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sponsorshipId) {

		ModelAndView result;
		Sponsorship sponsorship;
		Agent pricipal;

		pricipal = agentService.findByPrincipal();
		sponsorship = sponsorshipService.findOne(sponsorshipId);
		Assert.isTrue(sponsorship.getAgent().equals(pricipal));
		Assert.notNull(sponsorship);
		result = createEditModelAndView(sponsorship);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Sponsorship sponsorship,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(sponsorship, binding.toString());
		} else {
			try {
				sponsorshipService.save(sponsorship);
				result = new ModelAndView("redirect:/sponsorship/agent/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(sponsorship,
						"sponsorship.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Sponsorship sponsorship,
			BindingResult binding) {
		ModelAndView result;

		try {
			sponsorshipService.delete(sponsorship);
			result = new ModelAndView("redirect:/sponsorship/agent/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(sponsorship,
					"sponsorship.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Sponsorship sponsorship) {
		ModelAndView result;

		result = createEditModelAndView(sponsorship, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Sponsorship sponsorship,
			String message) {
		ModelAndView result;
		Collection<LanguageExchange> languageExchanges;

		result = new ModelAndView("sponsorship/edit");
		languageExchanges = languageExchangeService.findAll();
		result.addObject("sponsorship", sponsorship);
		result.addObject("languageExchanges", languageExchanges);
		result.addObject("message", message);

		return result;
	}
}