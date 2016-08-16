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
import services.FolderService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;

@Controller
@RequestMapping("/folder/actor")
public class FolderActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private FolderService folderService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public FolderActorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<Folder> folders;

		folders = folderService.findAllByPrincipal();

		result = new ModelAndView("folder/list");
		result.addObject("requestURI", "folder/actor/list.do");
		result.addObject("folders", folders);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {
		ModelAndView result;
		Folder folder;
		Actor principal;

		principal = actorService.findByPrincipal();
		folder = new Folder();
		folder.setActor(principal);
		result = createEditModelAndView(folder);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int folderId) {

		ModelAndView result;
		Folder folder;
		Actor principal;

		principal = actorService.findByPrincipal();
		folder = folderService.findOne(folderId);
		Assert.isTrue(folder.getActor().equals(principal));
		Assert.notNull(folder);
		result = createEditModelAndView(folder);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid Folder folder, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(folder);
		} else {
			try {
				folderService.save(folder);
				result = new ModelAndView("redirect:/folder/actor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(folder, "folder.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "delete")
	public ModelAndView delete(@Valid Folder folder, BindingResult binding) {
		ModelAndView result;

		try {
			folderService.delete(folder);
			result = new ModelAndView("redirect:/folder/actor/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(folder, oops.toString());
		}

		return result;
	}

	@RequestMapping(value = "/deleteFolder", method = RequestMethod.GET)
	public ModelAndView deleteFolder(@RequestParam int folderId) {
		ModelAndView result;
		Folder folder;
		Actor principal;

		principal = actorService.findByPrincipal();
		folder = folderService.findOne(folderId);
		Assert.isTrue(folder.getActor().equals(principal));

		folderService.delete(folder);

		result = new ModelAndView("redirect:/folder/actor/list.do");

		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Folder folder) {
		ModelAndView result;

		result = createEditModelAndView(folder, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Folder folder, String message) {
		ModelAndView result;

		result = new ModelAndView("folder/edit");
		result.addObject("folder", folder);
		result.addObject("message", message);

		return result;
	}
}