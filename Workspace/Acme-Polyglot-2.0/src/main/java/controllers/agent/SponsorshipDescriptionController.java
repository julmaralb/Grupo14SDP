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
import services.SponsorshipDescriptionService;
import services.SponsorshipService;
import controllers.AbstractController;
import domain.Agent;
import domain.Sponsorship;
import domain.SponsorshipDescription;

@Controller
@RequestMapping("/sponsorshipDescription/agent")
public class SponsorshipDescriptionController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private SponsorshipDescriptionService sponsorshipDescriptionService;

	@Autowired
	private SponsorshipService sponsorshipService;

	@Autowired
	private AgentService agentService;

	// Constructors -----------------------------------------------------------

	public SponsorshipDescriptionController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<SponsorshipDescription> sponsorshipDescriptions;

		sponsorshipDescriptions = sponsorshipDescriptionService
				.findAllByPrincipal();

		result = new ModelAndView("sponsorshipDescription/list");
		result.addObject("requestURI", "sponsorshipDescription/agent/list.do");
		result.addObject("sponsorshipDescriptions", sponsorshipDescriptions);

		return result;
	}

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam String code, int sponsorshipId) {

		ModelAndView result;
		SponsorshipDescription sponsorshipDescription;

		sponsorshipDescription = sponsorshipDescriptionService
				.findBySponsorshipIdAndCode(sponsorshipId, code);

		result = new ModelAndView("sponsorshipDescription/display");
		result.addObject("sponsorshipDescription", sponsorshipDescription);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		SponsorshipDescription sponsorshipDescription;

		sponsorshipDescription = sponsorshipDescriptionService.create();
		result = createEditModelAndView(sponsorshipDescription);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int sponsorshipDescriptionId) {

		ModelAndView result;
		SponsorshipDescription sponsorshipDescription;
		Agent principal;

		sponsorshipDescription = sponsorshipDescriptionService
				.findOne(sponsorshipDescriptionId);
		principal = agentService.findByPrincipal();
		Assert.isTrue(sponsorshipDescription.getSponsorship().getAgent()
				.equals(principal));
		Assert.notNull(sponsorshipDescription);
		result = createEditModelAndView(sponsorshipDescription);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(
			@Valid SponsorshipDescription sponsorshipDescription,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(sponsorshipDescription,
					binding.toString());
		} else {
			try {
				sponsorshipDescriptionService.save(sponsorshipDescription);
				result = new ModelAndView(
						"redirect:/sponsorshipDescription/agent/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(sponsorshipDescription,
						"sponsorshipDescription.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(
			@Valid SponsorshipDescription sponsorshipDescription,
			BindingResult binding) {
		ModelAndView result;

		try {
			sponsorshipDescriptionService.delete(sponsorshipDescription);
			result = new ModelAndView(
					"redirect:/sponsorshipDescription/agent/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(sponsorshipDescription,
					"sponsorshipDescription.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			SponsorshipDescription sponsorshipDescription) {
		ModelAndView result;

		result = createEditModelAndView(sponsorshipDescription, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			SponsorshipDescription sponsorshipDescription, String message) {
		ModelAndView result;
		Collection<Sponsorship> sponsorships;

		result = new ModelAndView("sponsorshipDescription/edit");
		sponsorships = sponsorshipService.findAllByPrincipal();
		result.addObject("sponsorships", sponsorships);
		result.addObject("sponsorshipDescription", sponsorshipDescription);
		result.addObject("message", message);

		return result;
	}
}