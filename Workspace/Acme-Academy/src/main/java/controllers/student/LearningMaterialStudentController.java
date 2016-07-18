package controllers.student;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.LearningMaterialService;
import controllers.AbstractController;
import domain.LearningMaterial;

@Controller
@RequestMapping("/learningMaterial/student")
public class LearningMaterialStudentController extends AbstractController {

	@Autowired
	private LearningMaterialService learningMaterialService;

	// Constructors -----------------------------------------------------------

	public LearningMaterialStudentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int groupId) {

		ModelAndView result;
		Collection<LearningMaterial> learningMaterials;

		learningMaterials = learningMaterialService.findByGroupId(groupId);

		result = new ModelAndView("learningMaterial/list");
		result.addObject("learningMaterials", learningMaterials);
		result.addObject("requestURI", "learningMaterial/student/list.do");

		return result;
	}
}