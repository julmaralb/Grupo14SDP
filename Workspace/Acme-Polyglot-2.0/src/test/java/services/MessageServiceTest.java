package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Actor;
import domain.Folder;
import domain.LanguageExchange;
import domain.Message;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MessageServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private MessageService messageService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private LanguageExchangeService languageExchangeService;
	
	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.1
	 * An actor who is authenticated must be able to:
	 * Exchange private messages with other users.
	 * 
	 * Positive test case: A new message in created and sent.
	 * 
	 */
	
	@Test
	public void testSendMessage1() {
		Message message;
		Actor recipient;
		
		authenticate("polyglot1");
		
		message = messageService.create();
		recipient = actorService.findOne(31);
		Folder senderOutbox = folderService.findByNameAndActorId("Out Box", actorService.findByPrincipal().getId());
		Folder recipientInBox = folderService.findByNameAndActorId("In Box", recipient.getId());
		
		Assert.isTrue(recipientInBox.getMessages().size() ==1);
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		message.setText("text");
		message.setCode("es");
		message.setInfoLinks(links);
		message.setTags(tags);
		message.setTitle("title");
		message.setSender(recipient);
		
		messageService.sendMessage(message);
		
		Assert.isTrue(senderOutbox.getMessages().contains(message));
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.1
	 * An actor who is authenticated must be able to:
	 * Exchange private messages with other users.
	 * 
	 * Negative test case: A new message in is not sent.
	 * because the recipient was null. 
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testSendMessage2() {
		Message message;
		
		authenticate("polyglot1");
		
		message = messageService.create();
		Folder senderOutbox = folderService.findByNameAndActorId("Out Box", actorService.findByPrincipal().getId());
		
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		message.setText("text");
		message.setCode("es");
		message.setInfoLinks(links);
		message.setTags(tags);
		message.setTitle("title");
		message.setRecipient(null);
		
		messageService.sendMessage(message);
		
		Assert.isTrue(senderOutbox.getMessages().contains(message));
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.2
	 * An actor who is authenticated must be able to:
	 * Exchange private messages with other users.
	 * 
	 * Positive test case: Broadcast a message to LanguageExchange1
	 * which contains 2 participants so the number of messages in their
	 * respective exchange folder is incremented by 1.
	 * 
	 */
	
	@Test
	public void testBroadCastMessage1() {
		Message message;
		LanguageExchange languageExchange;
		
		authenticate("polyglot1");
		
		message = messageService.create();
		languageExchange = languageExchangeService.findOne(50);
		Folder p1Folder = folderService.findByNameAndActorId("LanguageExchange1 Box", 22);
		Folder p2Folder = folderService.findByNameAndActorId("LanguageExchange1 Box", 31);
		
		Assert.isTrue(messageService.findAll().size()==8);
		Assert.isTrue(p1Folder.getMessages().size()== 0);
		Assert.isTrue(p2Folder.getMessages().size()== 0);
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		message.setText("text");
		message.setCode("es");
		message.setInfoLinks(links);
		message.setTags(tags);
		message.setTitle("title");
		
		messageService.broadcastMessage(message, languageExchange.getId());
		
		Assert.isTrue(p1Folder.getMessages().size()== 1);
		Assert.isTrue(p2Folder.getMessages().size()== 1);
		Assert.isTrue(messageService.findAll().size()==10);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot 2.0 - C-Level - 3.2
	 * An actor who is authenticated must be able to:
	 * Exchange private messages with other users.
	 * 
	 * Negative test case: The message was not broadcasted 
	 * because it was null.
	 * 
	 */
	
	@Test(expected = IllegalArgumentException.class)
	public void testBroadCastMessage2() {
		LanguageExchange languageExchange;
		
		authenticate("polyglot1");
		
		languageExchange = languageExchangeService.findOne(50);
		Folder p1Folder = folderService.findByNameAndActorId("LanguageExchange1 Box", 22);
		Folder p2Folder = folderService.findByNameAndActorId("LanguageExchange1 Box", 31);
		
		Assert.isTrue(messageService.findAll().size()==8);
		Assert.isTrue(p1Folder.getMessages().size()== 0);
		Assert.isTrue(p2Folder.getMessages().size()== 0);
		
		messageService.broadcastMessage(null, languageExchange.getId());
		
		Assert.isTrue(p1Folder.getMessages().size()== 1);
		Assert.isTrue(p2Folder.getMessages().size()== 1);
		Assert.isTrue(messageService.findAll().size()==10);
		
		authenticate(null);
	}
}