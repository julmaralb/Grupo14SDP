package controllers.actor;

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
import services.TripCommentService;
import services.TripService;
import controllers.AbstractController;
import domain.Actor;
import domain.Trip;
import domain.TripComment;

@Controller
@RequestMapping("/tripComment/actor")
public class TripCommentActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private TripCommentService tripCommentService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private TripService tripService;

	// Constructors -----------------------------------------------------------
	public TripCommentActorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int tripId) {
		ModelAndView result;
		Collection<TripComment> tripComments;

		tripComments = tripCommentService.findAllAppropriateByTripId(tripId);

		result = new ModelAndView("tripComment/list");
		result.addObject("requestURI", "tripComment/actor/list.do");
		result.addObject("tripComments", tripComments);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int tripId) {
		ModelAndView result;
		TripComment tripComment;
		Trip trip;

		tripComment = tripCommentService.create();
		trip = tripService.findOne(tripId);
		tripComment.setTrip(trip);
		result = createEditModelAndView(tripComment);
		result.addObject("tripId", tripId);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int tripCommentId, int tripId) {

		ModelAndView result;
		TripComment tripComment;
		Actor pricipal;

		pricipal = actorService.findByPrincipal();
		tripComment = tripCommentService.findOne(tripCommentId);
		Assert.isTrue(tripComment.getActor().equals(pricipal));
		Assert.notNull(tripComment);
		result = createEditModelAndView(tripComment);
		result.addObject("tripId", tripId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid TripComment tripComment, int tripId,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(tripComment);
		} else {
			try {
				Trip trip;
				trip = tripService.findOne(tripId);
				tripComment.setTrip(trip);
				tripCommentService.save(tripComment);
				result = new ModelAndView(
						"redirect:/tripComment/actor/list.do?tripId=" + tripId);
			} catch (Throwable oops) {
				result = createEditModelAndView(tripComment,
						"tripComment.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid TripComment tripComment,
			BindingResult binding) {
		ModelAndView result;

		try {
			tripCommentService.delete(tripComment);
			result = new ModelAndView("redirect:/tripComment/actor/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(tripComment,
					"tripComment.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(TripComment tripComment) {
		ModelAndView result;

		result = createEditModelAndView(tripComment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(TripComment tripComment,
			String message) {
		ModelAndView result;

		result = new ModelAndView("tripComment/edit");
		result.addObject("tripComment", tripComment);
		result.addObject("message", message);

		return result;
	}
}