package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.MessageRepository;
import domain.Actor;
import domain.Folder;
import domain.Message;

@Service
@Transactional
public class MessageService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private MessageRepository messageRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private FolderService folderService;

	// Constructors -----------------------------------------------------------

	public MessageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Message create() {
		Message result;
		Actor principal;
		Date moment;
		Folder folder;

		result = new Message();
		principal = actorService.findByPrincipal();
		folder = folderService.findByNameAndActorId("Out Box",
				principal.getId());
		moment = new Date();

		result.setSender(principal);
		result.setSentMoment(moment);
		result.setReceivedMoment(moment);
		result.setFolder(folder);

		return result;
	}

	public Message findOne(int messageId) {
		Assert.notNull(messageId);

		Message result;

		result = messageRepository.findOne(messageId);

		return result;
	}

	public Collection<Message> findAll() {

		Collection<Message> result;

		result = messageRepository.findAll();

		return result;
	}

	public void save(Message message) {
		Actor principal;

		Assert.notNull(message);

		principal = actorService.findByPrincipal();

		if (message.getVersion() == 0) {
			message.setSender(principal);
		}
		messageRepository.save(message);

	}

	public void delete(Message message) {
		Assert.notNull(message);

		messageRepository.delete(message);
	}

	// Other business methods -------------------------------------------------

	public Collection<Message> findAllByFolderId(int folderId) {
		Collection<Message> result;
		Actor principal;
		Folder folder;

		principal = actorService.findByPrincipal();
		folder = folderService.findOne(folderId);
		Assert.isTrue(folder.getActor().equals(principal));
		result = messageRepository.findAllByFolderId(folderId);

		return result;
	}

	public void sendMessage(Message message) {
		Actor sender;
		Collection<Message> senderMessages;
		Folder senderOutFolder;
		long milliseconds;
		Date moment;

		milliseconds = System.currentTimeMillis() - 100;
		moment = new Date(milliseconds);

		Assert.notNull(message);

		sender = actorService.findByPrincipal();
		senderOutFolder = folderService.findByNameAndActorId("Out Box",
				sender.getId());

		senderMessages = senderOutFolder.getMessages();

		message.setSentMoment(moment);
		message.setSender(sender);
		message.setFolder(senderOutFolder);
		senderMessages.add(message);
		senderOutFolder.setMessages(senderMessages);

		receiveMessage(message);
		save(message);
	}

	public void receiveMessage(Message message) {

		Message messageReceived;
		Folder recipientInFolder;
		Collection<Message> recipientMessages;
		Actor recipient;
		long milliseconds;
		Date moment;

		milliseconds = System.currentTimeMillis() - 100;
		moment = new Date(milliseconds);
		messageReceived = create();
		recipient = message.getRecipient();

		messageReceived.setText(message.getText());
		messageReceived.setSentMoment(message.getSentMoment());
		message.setReceivedMoment(moment);
		messageReceived.setTitle(message.getTitle());
		messageReceived.setInfoLinks(message.getInfoLinks());
		messageReceived.setTags(message.getTags());
		messageReceived.setRecipient(recipient);
		messageReceived.setSender(message.getSender());
		messageReceived.setCode(message.getCode());

		recipientInFolder = folderService.findByNameAndActorId("In Box",
				recipient.getId());
		messageReceived.setFolder(recipientInFolder);
		recipientMessages = recipientInFolder.getMessages();
		recipientMessages.add(messageReceived);
		recipientInFolder.setMessages(recipientMessages);

		save(messageReceived);
	}

	public void deleteMessage(Message message) {
		Folder folder;
		Actor principal;
		Collection<String> folderNames;
		Folder principalTrashFolder;

		folder = message.getFolder();
		principal = actorService.findByPrincipal();
		folderNames = new ArrayList<String>();
		principalTrashFolder = folderService.findByNameAndActorId(
				"Trash Box", principal.getId());

		folderNames.add("In Box");
		folderNames.add("Out Box");
		folderNames.add("Spam Box");

		if (folderNames.contains(folder.getName())) {
			message.setFolder(principalTrashFolder);
		} else {
			delete(message);
		}
	}

	public void moveToSpam(Message message) {
		Folder principalSpamFolder;
		Actor principal;

		principal = actorService.findByPrincipal();
		principalSpamFolder = folderService.findByNameAndActorId("Spam Box",
				principal.getId());

		message.setFolder(principalSpamFolder);
	}

	public void moveMessage(Message message, Folder folder) {
		Assert.isTrue(folder.getIsSystem() == false);
		message.setFolder(folder);
	}
}