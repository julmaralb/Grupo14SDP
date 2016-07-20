package controllers.administrator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CommentService;
import controllers.AbstractController;
import domain.Comment;

@Controller
@RequestMapping("/comment/administrator")
public class CommentAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CommentService commentService;

	// Constructors -----------------------------------------------------------

	public CommentAdministratorController() {
		super();
	}

	// Other ------------------------------------------------------------------

	@RequestMapping(value = "/flagComment", method = RequestMethod.GET)
	public ModelAndView flagComment(@RequestParam int commentId) {
		ModelAndView result;

		Comment comment;

		comment = commentService.findById(commentId);

		commentService.flagComment(comment);

		result = new ModelAndView("redirect:/trip/list.do");

		return result;
	}
}