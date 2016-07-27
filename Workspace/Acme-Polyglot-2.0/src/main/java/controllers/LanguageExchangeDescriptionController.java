package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.BannerService;
import services.LanguageExchangeDescriptionService;
import domain.Banner;
import domain.LanguageExchange;
import domain.LanguageExchangeDescription;

@Controller
@RequestMapping("/languageExchangeDescription")
public class LanguageExchangeDescriptionController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LanguageExchangeDescriptionService languageExchangeDescriptionService;

	@Autowired
	private BannerService bannerService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeDescriptionController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam String code,
			int languageExchangeId) {

		ModelAndView result;
		LanguageExchangeDescription languageExchangeDescription;
		LanguageExchange languageExchange;
		Banner banner;

		languageExchangeDescription = languageExchangeDescriptionService
				.findByExchangeIdAndCode(languageExchangeId, code);
		languageExchange = languageExchangeDescription.getLanguageExchange();

		if (!languageExchange.getSponsorships().isEmpty()) {
			banner = bannerService
					.findRandomToDisplay(languageExchangeId, code);
		} else {
			banner = null;
		}

		result = new ModelAndView("languageExchangeDescription/display");
		result.addObject("languageExchangeDescription",
				languageExchangeDescription);
		result.addObject("languageExchange", languageExchange);
		result.addObject("banner", banner);

		return result;
	}
}