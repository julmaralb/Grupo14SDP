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
import domain.Folder;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class FolderServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private FolderService folderService;

	// Other services needed -----------------------

	@Autowired
	private ActorService actorService;

	// Tests ---------------------------------------

	@Test
	public void testNewFolder() {
		// Declare variables
		Actor user;
		Folder folder;
		Integer numberOfFolders;

		// Load objects to test
		authenticate("user1");
		user = actorService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test
		numberOfFolders = user.getFolders().size();

		folder = folderService.create();
		folder.setName("Nueva carpeta");
		folder.setIsSystem(false);
		folder.setActor(user);

		folderService.save(folder);

		// Checks results
		Assert.isTrue(folder.getActor().equals(user)); // First check

		Assert.isTrue(
				user.getFolders().size() == numberOfFolders + 1,
				"El actor no tiene el mismo número de carpetas que antes + 1 tras crearse una nueva carpeta"); // Third
																												// check
		unauthenticate();

	}
}
