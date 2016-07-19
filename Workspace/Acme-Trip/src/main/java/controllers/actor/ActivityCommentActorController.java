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

import services.ActivityCommentService;
import services.ActivityService;
import services.ActorService;
import controllers.AbstractController;
import domain.Activity;
import domain.ActivityComment;
import domain.Actor;

@Controller
@RequestMapping("/activityComment/actor")
public class ActivityCommentActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ActivityCommentService activityCommentService;

	@Autowired
	private ActorService actorService;

	@Autowired
	private ActivityService activityService;

	// Constructors -----------------------------------------------------------
	public ActivityCommentActorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int activityId) {
		ModelAndView result;
		Collection<ActivityComment> activityComments;

		activityComments = activityCommentService
				.findAllByActivityId(activityId);

		result = new ModelAndView("activityComment/list");
		result.addObject("requestURI", "activityComment/actor/list.do");
		result.addObject("activityComments", activityComments);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create(@RequestParam int activityId) {
		ModelAndView result;
		ActivityComment activityComment;
		Activity activity;

		activityComment = activityCommentService.create();
		activity = activityService.findOne(activityId);
		activityComment.setActivity(activity);
		result = createEditModelAndView(activityComment);
		result.addObject("activityId", activityId);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int activityCommentId, int activityId) {

		ModelAndView result;
		ActivityComment activityComment;
		Actor pricipal;

		pricipal = actorService.findByPrincipal();
		activityComment = activityCommentService.findOne(activityCommentId);
		Assert.isTrue(activityComment.getActor().equals(pricipal));
		Assert.notNull(activityComment);
		result = createEditModelAndView(activityComment);
		result.addObject("activityId", activityId);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid ActivityComment activityComment,
			int activityId, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(activityComment);
		} else {
			try {
				Activity activity;
				activity = activityService.findOne(activityId);
				activityComment.setActivity(activity);
				activityCommentService.save(activityComment);
				result = new ModelAndView(
						"redirect:/activityComment/actor/list.do?activityId="
						+ activityId);
			} catch (Throwable oops) {
				result = createEditModelAndView(activityComment,
						"activityComment.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid ActivityComment activityComment,
			BindingResult binding) {
		ModelAndView result;

		try {
			activityCommentService.delete(activityComment);
			result = new ModelAndView("redirect:/activityComment/actor/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(activityComment,
					"activityComment.commit.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			ActivityComment activityComment) {
		ModelAndView result;

		result = createEditModelAndView(activityComment, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			ActivityComment activityComment, String message) {
		ModelAndView result;

		result = new ModelAndView("activityComment/edit");
		result.addObject("activityComment", activityComment);
		result.addObject("message", message);

		return result;
	}
}