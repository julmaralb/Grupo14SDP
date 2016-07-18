package controllers.student;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.RubricService;
import controllers.AbstractController;
import domain.Rubric;

@Controller
@RequestMapping("/rubric/student")
public class RubricStudentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private RubricService rubricService;

	// Constructors -----------------------------------------------------------

	public RubricStudentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int assignmentId) {

		ModelAndView result;
		Collection<Rubric> rubrics;

		rubrics = rubricService
				.findByAssignmentIdWithoutPercentage0(assignmentId);

		result = new ModelAndView("rubric/list");
		result.addObject("rubrics", rubrics);
		result.addObject("requestURI", "rubric/student/list.do");

		return result;
	}

}
