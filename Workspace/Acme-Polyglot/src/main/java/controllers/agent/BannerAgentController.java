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
import services.BannerService;
import services.SponsorshipService;
import controllers.AbstractController;
import domain.Agent;
import domain.Banner;
import domain.Sponsorship;

@Controller
@RequestMapping("/banner/agent")
public class BannerAgentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BannerService bannerService;

	@Autowired
	private SponsorshipService sponsorshipService;

	@Autowired
	private AgentService agentService;

	// Constructors -----------------------------------------------------------

	public BannerAgentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Banner> banners;

		banners = bannerService.findAllByPrincipal();

		result = new ModelAndView("banner/list");
		result.addObject("requestURI", "banner/agent/list.do");
		result.addObject("banners", banners);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Banner banner;

		banner = bannerService.create();
		result = createEditModelAndView(banner);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int bannerId) {

		ModelAndView result;
		Banner banner;
		Agent principal;

		banner = bannerService.findOne(bannerId);
		principal = agentService.findByPrincipal();
		Assert.isTrue(banner.getSponsorship().getAgent().equals(principal));
		Assert.notNull(banner);
		result = createEditModelAndView(banner);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Banner banner, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(banner);
		} else {
			try {
				bannerService.save(banner);
				result = new ModelAndView("redirect:/banner/agent/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(banner, "banner.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Banner banner, BindingResult binding) {
		ModelAndView result;

		try {
			bannerService.delete(banner);
			result = new ModelAndView("redirect:/banner/agent/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(banner, "banner.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Banner banner) {
		ModelAndView result;

		result = createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Banner banner, String message) {
		ModelAndView result;
		Collection<Sponsorship> sponsorships;

		result = new ModelAndView("banner/edit");
		sponsorships = sponsorshipService.findAllByPrincipal();
		result.addObject("sponsorships", sponsorships);
		result.addObject("banner", banner);
		result.addObject("message", message);

		return result;
	}
}