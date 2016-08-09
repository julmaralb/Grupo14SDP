package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Folder;
import domain.LanguageExchange;
import domain.Message;
import domain.Polyglot;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class FolderServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private FolderService folderService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private PolyglotService polyglotService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private LanguageExchangeService languageExchangeService;
	
	@Autowired
	private MessageService messageService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: All system folders are created.
	 * 
	 */

	@Test
	public void testCreateSystemFolders() {
		Collection<Folder> systemFolders;
		Polyglot polyglot;
		authenticate(null);
			
		polyglot = polyglotService.create();
		folderService.initializeFolders(polyglot);
		
		polyglot.setEmail("email@gmail.com");
		polyglot.setName("Name");
		polyglot.setSurname("Surname");
		polyglot.setPhone("+954362514");
		polyglot.getUserAccount().setUsername("nuevopolyglot");
		polyglot.getUserAccount().setPassword("nuevopolyglot");
		
		polyglotService.save(polyglot);
		systemFolders = polyglot.getFolders();
		Assert.isTrue(systemFolders.size() == 4);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Negative test case: The folder is not deleted because it is a system folder.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testDeleteSystemFolder() {
		
		authenticate("polyglot1");
		Folder folder;
		
		folder = folderService.findOne(8);
		folderService.delete(folder);
			
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: A folder is deleted.
	 * 
	 */

	@Test
	public void testDeleteFolder() {
		
		authenticate("polyglot1");
		Folder folder;
		
		folder = folderService.create();
		folder.setActor(actorService.findByPrincipal());
		folder.setName("nuevoFolder");
		folder.setIsSystem(false);
		folderService.save(folder);
		
		folderService.delete(folder);
			
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: A new non-system folder is created.
	 * 
	 */

	@Test
	public void testCreateFolder() {
		Folder folder;
		Collection<Folder> allfolders;
		Polyglot polyglot;
		
		authenticate("polyglot1");
			
		folder = folderService.create();
		polyglot = polyglotService.findByPrincipal();
		allfolders = folderService.findAll();
		Assert.isTrue(allfolders.size() == 37);
		
		folder.setName("NuevoFolder");
		folder.setActor(polyglot);
		Assert.isTrue(folder.getIsSystem()==false);
		folderService.save(folder);
		
		allfolders = folderService.findAll();
		Assert.isTrue(allfolders.size() == 38);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: All folders listed are not system folders.
	 * 
	 */

	@Test
	public void testListAllNonSystemFolders() {
		Collection<Folder> folders;
		boolean res = true;
		
		authenticate("polyglot1");
			
		folders = folderService.findAllNonSystemByPrincipal();
		
		for(Folder f: folders){
			if(f.getIsSystem() == true){
				res=false;
				break;
			}
		}
		Assert.isTrue(res);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: All folders listed are principal's folders.
	 * 
	 */

	@Test
	public void testListAllPrincipal() {
		Collection<Folder> folders;
		boolean res = true;
		
		authenticate("polyglot1");
			
		folders = folderService.findAllByPrincipal();
		
		for(Folder f: folders){
			if(!f.getActor().equals(actorService.findByPrincipal())){
				res=false;
				break;
			}
		}
		Assert.isTrue(res);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: The returned folder has the name selected and it's
	 * owner is the principal.
	 * 
	 */

	@Test
	public void testListByNameAndActorId() {
		Folder folder;
		
		authenticate("polyglot1");
			
		folder= folderService.findByNameAndActorId("Trash Box", actorService.findByPrincipal().getId());
		
		Assert.isTrue(folder.getName().equals("Trash Box"));
		Assert.isTrue(folder.getActor().equals(actorService.findByPrincipal()));
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: The returned folder is the languageExchange folder
	 * and it's owner is the principal.
	 * 
	 */

	@Test
	public void testListByLanguageExchangeAndActorId() {
		Folder folder;
		LanguageExchange languageExchange;
		
		authenticate("polyglot1");
			
		languageExchange = languageExchangeService.findOne(50);
		folder= folderService.findByExchangeNameAndActorId(languageExchange.getName(), actorService.findByPrincipal().getId());
		
		Assert.isTrue(folder.getName().equals(languageExchange.getName()+" Box"));
		Assert.isTrue(folder.getActor().equals(actorService.findByPrincipal()));
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	 * Positive test case: The message's folder is the Spam Box.
	 * 
	 */

	@Test
	public void testMessageSentToSpamBox() {
		Folder inBox;
		Message message;
		
		authenticate("polyglot1");
			
		message = messageService.findOne(107);
		inBox= message.getFolder();
		Assert.isTrue(inBox.getName().equals("Out Box"));
		
		messageService.moveToSpam(message);
		Assert.isTrue(message.getFolder().getName().equals("Spam Box"));
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.3
	 * An actor who is authenticated must be able to:
	 * Manage his or her messages and message boxes. When a message 
	 * is deleted from a folder other than "trash box", it is moved to
	 *  "trash box"; when it is deleted from "trash box", it is actually
	 *   removed from the system. Actors can create additional folders, rename, 
	 *   or delete them. Actors can flag their messages as spam, in which case 
	 *   they are moved automatically to the "spam box" folder.
	 * 
	* Positive test case: The message's folder is the Trash Box.
	 * 
	 */

	@Test
	public void testMessageSentToTrashBox() {
		Folder inBox;
		Message message;
		
		authenticate("polyglot1");
			
		message = messageService.findOne(107);
		inBox= message.getFolder();
		Assert.isTrue(inBox.getName().equals("Out Box"));
		
		messageService.deleteMessage(message);
		Assert.isTrue(message.getFolder().getName().equals("Trash Box"));
		
		authenticate(null);
	}


}