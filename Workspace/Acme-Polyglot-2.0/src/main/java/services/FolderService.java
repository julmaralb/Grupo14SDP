package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.FolderRepository;
import domain.Actor;
import domain.Folder;
import domain.LanguageExchange;
import domain.Message;
import domain.Polyglot;

@Service
@Transactional
public class FolderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FolderRepository folderRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private PolyglotService polyglotService;

	// Constructors -----------------------------------------------------------

	public FolderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Folder create() {
		Folder result;
		Collection<Message> messages;

		result = new Folder();
		messages = new ArrayList<Message>();
		
		result.setMessages(messages);

		return result;
	}

	public Folder findOne(int folderId) {
		Assert.notNull(folderId);

		Folder result;

		result = folderRepository.findOne(folderId);

		return result;
	}

	public Collection<Folder> findAll() {

		Collection<Folder> result;

		result = folderRepository.findAll();

		return result;
	}

	public void save(Folder folder) {
		Assert.notNull(folder);
		Actor principal;

		principal = actorService.findByPrincipal();
		folder.setActor(principal);

		folderRepository.save(folder);
	}

	public void delete(Folder folder) {
		Assert.notNull(folder);
		Assert.isTrue(folder.getIsSystem()== false);
		Collection<Message> messages;
		Folder trashFolder;
		Actor principal;

		messages = folder.getMessages();
		principal = actorService.findByPrincipal();
		Assert.isTrue(folder.getIsSystem()== false);
		trashFolder = findByNameAndActorId("Trash Box", principal.getId());
		for (Message m : messages) {
			m.setFolder(trashFolder);
		}

		folderRepository.delete(folder);
	}

	// Other business methods -------------------------------------------------

	/* Crea todos los systemFolders a un nuevo actor del sistema */
	public Collection<Folder> initializeFolders(Actor actor) {

		Collection<Folder> result;
		Collection<String> names;

		result = new ArrayList<Folder>();
		names = new ArrayList<String>();

		names.add("In Box");
		names.add("Out Box");
		names.add("Trash Box");
		names.add("Spam Box");

		for (String string : names) {
			Folder temp;

			temp = this.create();

			temp.setIsSystem(true);
			temp.setName(string);
			temp.setActor(actor);

			result.add(temp);
		}
		actor.setFolders(result);

		return result;
	}

	public Collection<Folder> findAllByPrincipal() {
		Collection<Folder> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = folderRepository.findAllByActorId(principal.getId());

		return result;
	}

	public Folder findByNameAndActorId(String name, int actorId) {
		Folder result;

		result = folderRepository.findByNameAndActorId(name, actorId);

		return result;
	}

	public Collection<Folder> findAllNonSystemByPrincipal() {
		Collection<Folder> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = folderRepository.findAllNonSystemFoldersByActorId(principal
				.getId());

		return result;
	}

	public void createExchangeFolder(LanguageExchange languageExchange) {
		Folder folder;
		Polyglot principal;
		String name;

		principal = polyglotService.findByPrincipal();
		name = languageExchange.getName() + " Box";

		if (findByNameAndActorId(name, principal.getId()) == null) {
			folder = create();
			folder.setName(name);
			folder.setActor(principal);
			folder.setIsSystem(false);
			folder.setExchangeName(languageExchange.getName());
			save(folder);
		}
	}

	public Folder findByExchangeNameAndActorId(String exchangeName, int actorId) {
		Folder result;

		result = folderRepository.findByExchangeNameAndActorId(exchangeName,
				actorId);

		return result;
	}
}