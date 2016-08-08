package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Polyglot;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class PolyglotServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private PolyglotService polyglotService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private FolderService folderService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - C-Level - 4.1
	 * A user who is not authenticated  must be able to: 
	 * Register to the system as a polyglot. 
	 * Positive test case: A new polyglot is created.
	 * 
	 */

	@Test
	public void testCreatePolyglot1() {
		Polyglot polyglot;
		authenticate(null);
		
		Assert.isTrue(polyglotService.findAll().size()==3);	
		polyglot = polyglotService.create();
		folderService.initializeFolders(polyglot);
		
		polyglot.setEmail("email@gmail.com");
		polyglot.setName("Name");
		polyglot.setSurname("Surname");
		polyglot.setPhone("+954362514");
		polyglot.getUserAccount().setUsername("nuevopolyglot");
		polyglot.getUserAccount().setPassword("nuevopolyglot");
		
		polyglotService.save(polyglot);
		
		Assert.isTrue(polyglotService.findAll().size()==4);	
	}
	
	/**
	 * Acme-Polyglot - C-Level - 4.1
	 * A user who is not authenticated  must be able to: 
	 * Register to the system as a polyglot. 
	 * Negative test case: A new polyglot is not created
	 * because the password is null.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePolyglot2() {
		Polyglot polyglot;
		
		Assert.isTrue(polyglotService.findAll().size()==3);	
		polyglot = polyglotService.create();
		folderService.initializeFolders(polyglot);
		
		polyglot.setEmail("email@gmail.com");
		polyglot.setName("Name");
		polyglot.setSurname("Surname");
		polyglot.setPhone("954362514");
		polyglot.getUserAccount().setUsername("nuevopolyglot");
		polyglot.getUserAccount().setPassword(null);
		
		polyglotService.save(polyglot);
		
		Assert.isTrue(polyglotService.findAll().size()==4);	
	}
	
	/**
	 * Acme-Polyglot - C-Level - 4.1
	 * A user who is not authenticated  must be able to: 
	 * Register to the system as a polyglot. 
	 * Negative test case: A new polyglot is not created
	 * because the user is already authenticated.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreatePolyglot3() {
		Polyglot polyglot;
		
		authenticate("agent1");
		
		Assert.isTrue(polyglotService.findAll().size()==3);	
		polyglot = polyglotService.create();
		folderService.initializeFolders(polyglot);
		
		polyglot.setEmail("email@gmail.com");
		polyglot.setName("Name");
		polyglot.setSurname("Surname");
		polyglot.setPhone("954362514");
		polyglot.getUserAccount().setUsername("nuevopolyglot");
		polyglot.getUserAccount().setPassword(null);
		
		polyglotService.save(polyglot);
		
		Assert.isTrue(polyglotService.findAll().size()==4);	
		
		authenticate(null);
	}

}