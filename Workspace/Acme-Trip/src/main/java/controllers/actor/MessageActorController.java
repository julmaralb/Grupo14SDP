package controllers.actor;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.FolderService;
import services.MessageService;
import controllers.AbstractController;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Controller
@RequestMapping("/message/actor")
public class MessageActorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private MessageService messageService;

	@Autowired
	private FolderService folderService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public MessageActorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int folderId) {

		ModelAndView result;
		Collection<Message> messages;
		Actor principal;
		int spamId;
		int trashId;
		int starredId;
		Collection<Folder> folders;

		folders = folderService.findAllNonSystemByPrincipal();
		messages = messageService.findAllByFolderId(folderId);
		principal = actorService.findByPrincipal();
		spamId = folderService.findByNameAndActorId("Spam Folder",
				principal.getId()).getId();
		trashId = folderService.findByNameAndActorId("Trash Folder",
				principal.getId()).getId();
		starredId = folderService.findByNameAndActorId("Starred Folder",
				principal.getId()).getId();

		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/list.do");
		result.addObject("spamId", spamId);
		result.addObject("trashId", trashId);
		result.addObject("starredId", starredId);
		result.addObject("folders", folders);

		return result;
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);

		result = new ModelAndView("message/display");
		result.addObject("msg", message);

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Message message;

		message = messageService.create();
		result = createEditModelAndView(message);

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int messageId) {
		ModelAndView res;
		Message message;

		message = messageService.findOne(messageId);
		Assert.notNull(message);

		res = new ModelAndView("message/edit");
		res.addObject("message", message);

		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "send")
	public ModelAndView save(
			@ModelAttribute(value = "messa") @Valid Message message,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(message);
		} else {
			try {
				messageService.sendMessage(message);
				result = new ModelAndView("redirect:/folder/actor/list.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(message, "message.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);

		messageService.deleteMessage(message);

		result = new ModelAndView("redirect:/folder/actor/list.do");

		return result;
	}

	@RequestMapping(value = "/spam", method = RequestMethod.GET)
	public ModelAndView spam(@RequestParam int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);

		messageService.moveToSpam(message);

		result = new ModelAndView("redirect:/folder/actor/list.do");

		return result;
	}

	@RequestMapping(value = "/star", method = RequestMethod.GET)
	public ModelAndView star(@RequestParam int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);

		messageService.moveToStarred(message);

		result = new ModelAndView("redirect:/folder/actor/list.do");

		return result;
	}

	@RequestMapping(value = "/removeStar", method = RequestMethod.GET)
	public ModelAndView removeStar(@RequestParam int messageId) {
		ModelAndView result;
		Message message;

		message = messageService.findOne(messageId);

		messageService.moveToBackFromStarred(message);

		result = new ModelAndView("redirect:/folder/actor/list.do");

		return result;
	}

	// Move -------------------------------------------------------------------

	@RequestMapping(value = "/move", method = RequestMethod.POST)
	public ModelAndView move(@RequestParam Folder folder,
			@RequestParam int messageId) {
		ModelAndView result;
		Message message;
		message = messageService.findOne(messageId);

		try {
			messageService.moveMessage(message, folder);
			result = new ModelAndView("redirect:/folder/actor/list.do");
		} catch (Throwable oops) {
			result = createEditModelAndView(message, "message.commit.error");
		}
		return result;

	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(Message input) {
		ModelAndView result;

		result = createEditModelAndView(input, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Message input, String message2) {
		ModelAndView result;
		Collection<Actor> recipients;

		recipients = actorService.findAllButPrincipal();

		result = new ModelAndView("message/edit");
		result.addObject("messa", input);
		result.addObject("recipients", recipients);
		result.addObject("message2", message2);

		return result;
	}
}