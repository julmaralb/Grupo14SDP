package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.CentreService;
import domain.Centre;

@Controller
@RequestMapping("/centre")
public class CentreController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CentreService centreService;

	// Constructors -----------------------------------------------------------

	public CentreController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Centre> centres;

		centres = centreService.findAll();

		result = new ModelAndView("centre/list");
		result.addObject("centres", centres);
		result.addObject("requestURI", "centre/list.do");

		return result;

	}
}