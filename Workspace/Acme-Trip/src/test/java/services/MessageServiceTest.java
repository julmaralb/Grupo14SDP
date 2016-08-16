package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Actor;
import domain.Message;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class MessageServiceTest extends AbstractTest {

		// Service under test -------------------------
			@Autowired
			private MessageService messageService;
				
				
		// Other services needed -----------------------
			@Autowired
			private ActorService actorService;
			
		// Tests ---------------------------------------
			
	/**
	 * An actor who is authenticated must be able to:
	 * 		-Exchange messages with other users
	 * 
	 * Positive Test: Admin envia un mensaje a un user.
	 */
		@Test
		public void testCreateMessageAdmintoUser() {
		authenticate("admin");
		Message message;
		
		Actor actor = actorService.findOne(62);
		message = messageService.create();
		message.setRecipient(actor);
		message.setBody("Body message Test");
		message.setSubject("Subject message Test");
		message.setPriority(1);
		messageService.sendMessage(message);
		
		Assert.isTrue(messageService.isRecipient(message, actor));
		
		unauthenticate();
	}
		
		/**
		 * An actor who is authenticated must be able to:
		 * 		-Exchange messages with other users
		 * 
		 * Negative Test: Admin envia un mensaje nulo
		 */
			@Test(expected = IllegalArgumentException.class)
			public void testCreateMessageAdmintoUser2() {
			authenticate("admin");
		
			messageService.sendMessage(null);
			
			unauthenticate();
		}
			
			/**
			 *	 An actor who is authenticated must be able to:
			 * 		-Exchange messages with other users
			 * 
			 * Negative Test: Admin envia un mensaje sin receptor.
			 * 
			 */
			
			// Test crear un message sin receptor
			@Test(expected = NullPointerException.class)
			public void testCreateMessageWithoutRecipient() {
				authenticate("admin");
				
				Message message;				
				message = messageService.create();

				message.setBody("Body message");
				message.setSubject("Subject message");
				message.setPriority(1);
				messageService.sendMessage(message);

			}
			
			/**
			 * An actor who is authenticated must be able to:
			 * 		-Manage his or her messages and message boxes.
			 * 
			 * Positive Test: Borrar un mensaje que está en una carpeta que no es Trash Folder.
			 */
				@Test
				public void testDeleteMessage() {
				authenticate("admin");
				Message message;
				
				Actor actor = actorService.findOne(62);
				message = messageService.create();
				message.setRecipient(actor);
				message.setBody("Body message Test");
				message.setSubject("Subject message Test");
				message.setPriority(1);
				messageService.sendMessage(message);
								
				messageService.deleteMessage(message);
				Assert.isTrue(message.getFolder().getName().equals("Trash Folder"), "El mensaje no se ha movido a Trash Folder");
				unauthenticate();
			}
				
				/**
				 * An actor who is authenticated must be able to:
				 * 		-Manage his or her messages and message boxes.
				 * 
				 * Positive Test: Eliminar un mensaje que está en Trash Folder
				 */
					@Test
					public void testDeleteMessage2() {
					authenticate("admin");
					Message message;
					Message message2;
					message=messageService.findOne(148);
										
					messageService.deleteMessage(message);
					message2=messageService.findOne(148);
					Assert.isTrue(message2==null, "El mensaje no se ha eliminado");
					unauthenticate();
				}
				
					/**
					 * An actor who is authenticated must be able to:
					 * 		-Manage his or her messages and message boxes.
					 * 
					 * Positive Test: Mover un mensaje a spam
					 */
						@Test
						public void testMoveToSpam1() {
						authenticate("admin");
						Message message;
						String nameBefore;
						Message message2;
						String after;
						
						message=messageService.findOne(144);
						nameBefore=message.getFolder().getName();
						messageService.moveToSpam(message);
						message2=messageService.findOne(144);
						after=message2.getFolder().getName();
						Assert.isTrue(!nameBefore.equals(after), "El mensaje no se ha movido");
						unauthenticate();
					}
						
			
}
