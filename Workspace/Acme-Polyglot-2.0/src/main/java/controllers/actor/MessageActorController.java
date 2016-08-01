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
		Collection<Folder> folders;

		folders = folderService.findAllNonSystemByPrincipal();
		messages = messageService.findAllByFolderId(folderId);
		principal = actorService.findByPrincipal();
		spamId = folderService.findByNameAndActorId("Spam Box",
				principal.getId()).getId();
		trashId = folderService.findByNameAndActorId("Trash Box",
				principal.getId()).getId();

		result = new ModelAndView("message/list");
		result.addObject("messages", messages);
		result.addObject("requestURI", "message/actor/list.do");
		result.addObject("spamId", spamId);
		result.addObject("trashId", trashId);
		result.addObject("folders", folders);

		return result;
	}

	// Display ----------------------------------------------------------------

	@RequestMapping(value = "/display", method = RequestMethod.GET)
	public ModelAndView display(@RequestParam int messageId) {
		ModelAndView result;
		Message message;
		Actor principal;

		principal = actorService.findByPrincipal();
		message = messageService.findOne(messageId);
		Assert.isTrue(message.getSender().equals(principal)
				|| message.getRecipient().equals(principal));

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

	@RequestMapping(value = "/createBroadcast", method = RequestMethod.GET)
	public ModelAndView createBroadcast(@RequestParam int languageExchangeId) {

		ModelAndView result;
		Message message;

		message = messageService.create();
		result = createBroadcastModelAndView(message,null);
		result.addObject("languageExchangeId", languageExchangeId);

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
		Actor principal;

		principal = actorService.findByPrincipal();
		message = messageService.findOne(messageId);
		Assert.isTrue(message.getSender().equals(principal)
				|| message.getRecipient().equals(principal));

		messageService.deleteMessage(message);

		result = new ModelAndView("redirect:/folder/actor/list.do");

		return result;
	}

	@RequestMapping(value = "/createBroadcast", method = RequestMethod.POST, params = "broadcast")
	public ModelAndView save(
			@ModelAttribute(value = "messa") @Valid Message message,
			@RequestParam int languageExchangeId, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createBroadcastModelAndView(message);
		} else {
			try {
				messageService.broadcastMessage(message,languageExchangeId);
				result = new ModelAndView("redirect:/folder/actor/list.do");
			} catch (Throwable oops) {
				result = createBroadcastModelAndView(message, "message.commit.error");
			}
		}
		return result;
	}

	// Other ------------------------------------------------------------------

	@RequestMapping(value = "/spam", method = RequestMethod.GET)
	public ModelAndView spam(@RequestParam int messageId) {
		ModelAndView result;
		Message message;
		Actor principal;

		principal = actorService.findByPrincipal();
		message = messageService.findOne(messageId);
		Assert.isTrue(message.getSender().equals(principal)
				|| message.getRecipient().equals(principal));

		messageService.moveToSpam(message);

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
		Actor principal;
		principal = actorService.findByPrincipal();

		try {
			Assert.isTrue(message.getSender().equals(principal)
					|| message.getRecipient().equals(principal));
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
	
	protected ModelAndView createBroadcastModelAndView(Message input) {
		ModelAndView result;

		result = createBroadcastModelAndView(input, null);

		return result;
	}
	
	protected ModelAndView createBroadcastModelAndView(Message input, String message2) {
		ModelAndView result;

		result = new ModelAndView("message/createBroadcast");
		result.addObject("messa", input);
		result.addObject("message2", message2);

		return result;
	}
}