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
import domain.Message;

@Service
@Transactional
public class FolderService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private FolderRepository folderRepository;

	@Autowired
	private ActorService actorService;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public FolderService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Folder create() {
		Folder result;

		result = new Folder();

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

		folderRepository.delete(folder);
	}

	// Other business methods -------------------------------------------------

	/* Crea todos los systemFolders a un nuevo actor del sistema */
	public Collection<Folder> initializeFolders(Actor actor) {

		Collection<Folder> result;
		Collection<String> names;
		Collection<Message> messages;

		result = new ArrayList<Folder>();
		names = new ArrayList<String>();
		messages = new ArrayList<Message>();

		names.add("In Folder");
		names.add("Out Folder");
		names.add("Trash Folder");
		names.add("Starred Folder");
		names.add("Spam Folder");

		for (String string : names) {
			Folder temp;

			temp = this.create();

			temp.setIsSystem(true);
			temp.setName(string);
			temp.setActor(actor);
			temp.setMessages(messages);

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
}