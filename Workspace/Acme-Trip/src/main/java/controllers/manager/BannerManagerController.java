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

import services.BannerService;
import controllers.AbstractController;
import domain.Banner;

@Controller
@RequestMapping("/banner/manager")
public class BannerManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private BannerService bannerService;

	// Constructors -----------------------------------------------------------

	public BannerManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Banner> banners;

		banners = bannerService.findAllByPrincipal();

		result = new ModelAndView("banner/list");
		result.addObject("requestURI", "banner/administrator/list.do");
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

		banner = bannerService.findOne(bannerId);
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
				result = new ModelAndView(
						"redirect:/banner/manager/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(banner,
						"banner.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Banner banner, BindingResult binding) {
		ModelAndView result;

		try {
			bannerService.delete(banner);
			result = new ModelAndView(
					"redirect:/banner/manager/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(banner,
					"banner.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Banner banner) {
		ModelAndView result;

		result = createEditModelAndView(banner, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Banner banner,
			String message) {
		ModelAndView result;

		result = new ModelAndView("banner/edit");
		result.addObject("banner", banner);
		result.addObject("message", message);

		return result;
	}
}