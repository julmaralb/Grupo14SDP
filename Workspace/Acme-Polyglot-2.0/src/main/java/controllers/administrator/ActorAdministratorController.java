package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.AdministratorService;
import controllers.AbstractController;
import domain.Actor;

@Controller
@RequestMapping("/actor/administrator")
public class ActorAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------

	public ActorAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Actor> actors;

		actors = actorService.findAll();

		result = new ModelAndView("actor/list");
		result.addObject("actors", actors);
		result.addObject("requestURI", "actor/administrator/list.do");

		return result;
	}

	// Ban --------------------------------------------------------------------

	@RequestMapping(value = "/ban", method = RequestMethod.GET)
	public ModelAndView ban(@RequestParam int actorId) {
		ModelAndView result;

		Actor actor;

		actor = actorService.findOne(actorId);

		administratorService.disableAccount(actor);

		result = new ModelAndView("redirect:/actor/administrator/list.do");

		return result;
	}
	
	@RequestMapping(value = "/unban", method = RequestMethod.GET)
	public ModelAndView unban(@RequestParam int actorId) {
		ModelAndView result;

		Actor actor;

		actor = actorService.findOne(actorId);

		administratorService.enableAccount(actor);

		result = new ModelAndView("redirect:/actor/administrator/list.do");

		return result;
	}
}