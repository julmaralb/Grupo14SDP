package controllers.lecturer;

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

import services.GroupService;
import services.LearningMaterialService;
import controllers.AbstractController;
import domain.Group;
import domain.LearningMaterial;

@Controller
@RequestMapping("/learningMaterial/lecturer")
public class LearningMaterialLecturerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private LearningMaterialService learningMaterialService;

	@Autowired
	private GroupService groupService;

	// Constructors -----------------------------------------------------------

	public LearningMaterialLecturerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int groupId) {

		ModelAndView result;
		Collection<LearningMaterial> learningMaterials;

		learningMaterials = learningMaterialService
				.findByGroupIdAndLecturer(groupId);

		result = new ModelAndView("learningMaterial/list");
		result.addObject("learningMaterials", learningMaterials);
		result.addObject("requestURI", "learningMaterial/lecturer/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		LearningMaterial learningMaterial;

		learningMaterial = learningMaterialService.create();
		result = createEditModelAndView(learningMaterial);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int learningMaterialId) {

		ModelAndView result;
		LearningMaterial learningMaterial;

		learningMaterial = learningMaterialService.findOne(learningMaterialId);
		Assert.notNull(learningMaterial);
		result = createEditModelAndView(learningMaterial);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid LearningMaterial learningMaterial,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(learningMaterial);
		} else {
			try {
				learningMaterialService.save(learningMaterial);
				result = new ModelAndView("redirect:/group/lecturer/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(learningMaterial,
						"learningMaterial.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid LearningMaterial learningMaterial,
			BindingResult binding) {
		ModelAndView result;

		try {
			learningMaterialService.delete(learningMaterial);
			result = new ModelAndView("redirect:/group/lecturer/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(learningMaterial,
					"learningMaterial.delete.error");
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(
			LearningMaterial learningMaterial) {
		ModelAndView result;

		result = createEditModelAndView(learningMaterial, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(
			LearningMaterial learningMaterial, String message) {
		ModelAndView result;
		Collection<Group> groups;

		groups = groupService.findByLecturerPrincipal();
		result = new ModelAndView("learningMaterial/edit");
		result.addObject("learningMaterial", learningMaterial);
		result.addObject("groups", groups);
		result.addObject("message", message);

		return result;
	}

}
