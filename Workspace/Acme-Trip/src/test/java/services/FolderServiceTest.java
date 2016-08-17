package services;

import java.util.Collection;

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
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class FolderServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private FolderService folderService;

	// Other services needed -----------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private UserService userService;

	// Tests ---------------------------------------

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Positive Test: Un usuario crea una nueva folder
	 * 
	 */
	@Test
	public void testNewFolder1() {
		// Declare variables
		User user;
		Folder folder;
		Integer numberOfFolders;
		Collection<Folder> folders;

		// Load objects to test
		authenticate("user1");
		user = userService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test
		numberOfFolders = user.getFolders().size();
		folders = user.getFolders();
		folder = folderService.create();
		folder.setName("Nueva carpeta Test");
		folder.setIsSystem(false);
		folder.setActor(user);
		folders.add(folder);
		folderService.save(folder);
		// user.setFolders(folders);
		// userService.save(user);

		// Checks results
		Assert.isTrue(folder.getActor().equals(user)); // First check

		Assert.isTrue(
				user.getFolders().size() == numberOfFolders + 1,
				"El actor no tiene el mismo número de carpetas que antes + 1 tras crearse una nueva carpeta"); // Third
																												// check
		unauthenticate();

	}

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Test: Crear una nueva folder nula
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNewFolder2() {
		// Declare variables
		User user;

		// Load objects to test
		authenticate("user1");
		user = userService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test

		folderService.save(null);

		unauthenticate();

	}

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Test: Crear una nueva folder sin actor
	 * 
	 */
	@Test
	public void testNewFolder3() {
		// Declare variables
		Actor user;
		Folder folder;

		// Load objects to test
		authenticate("user1");
		user = actorService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test

		folder = folderService.create();
		folder.setName("Nueva Carpeta Test");
		folder.setIsSystem(false);
		folder.setActor(null);

		folderService.save(folder);

		unauthenticate();

	}

	// TODO Rename and Delete

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Positive Test: Modificar el nombre de una folder
	 * 
	 */
	@Test
	public void testModifyFolder1() {
		// Declare variables
		User user;
		Folder folder;
		Collection<Folder> folders;
		Folder folderModify;

		// Load objects to test
		authenticate("user1");
		user = userService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test
		folders = user.getFolders();
		folder = folderService.create();
		folder.setName("Test");
		folder.setIsSystem(false);
		folder.setActor(user);
		folders.add(folder);
		folderService.save(folder);

		folderModify = folderService.findByNameAndActorId("Test", 62);
		Assert.notNull(folderModify, "La carpeta es nula");

		folderModify.setName("Nuevo Test");
		folderService.save(folderModify);

		Assert.notNull(folderService.findByNameAndActorId("Nuevo Test", 62),
				"No existe la carpeta con ese nombre");

		unauthenticate();

	}

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Negative Test: Modificar el nombre de una folder de otro usuario
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testModifyFolder2() {
		// Declare variables
		User user;
		Folder folder;
		Collection<Folder> folders;
		Folder folderModify;

		// Load objects to test
		authenticate("user1");
		user = userService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test
		folders = user.getFolders();
		folder = folderService.create();
		folder.setName("Test");
		folder.setIsSystem(false);
		folder.setActor(user);
		folders.add(folder);
		folderService.save(folder);

		folderModify = folderService.findByNameAndActorId("Test", 62);
		Assert.notNull(folderModify, "La carpeta es nula");

		authenticate("user2");

		folderModify.setName("Nuevo Test");
		folderService.save(folderModify);

		Assert.notNull(folderService.findByNameAndActorId("Nuevo Test", 68),
				"No existe la carpeta con ese nombre");

		Assert.notNull(folderService.findByNameAndActorId("Nuevo Test", 62),
				"No existe la carpeta con ese nombre");

		unauthenticate();

	}

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Negative Test: Modificar el nombre de una folder del sistema
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testModifyFolder3() {
		// Declare variables
		User user;
		Folder folder;

		// Load objects to test
		authenticate("user1");
		user = userService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test
		folder = folderService.findByNameAndActorId("In Folder", 62);

		Assert.notNull(folder, "La folder es null");

		folder.setName("Nuevo Test");
		folderService.save(folder);

		unauthenticate();

	}

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Positive Test: Eliminar una folder que no es del sistema
	 * 
	 */
	@Test
	public void testDeleteFolder1() {
		// Declare variables
		User user;
		Folder folder;
		Collection<Folder> folders;
		Folder folderModify;

		// Load objects to test
		authenticate("user1");
		user = userService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test
		folders = user.getFolders();
		folder = folderService.create();
		folder.setName("Test");
		folder.setIsSystem(false);
		folder.setActor(user);
		folders.add(folder);
		folderService.save(folder);

		folderModify = folderService.findByNameAndActorId("Test", 62);
		Assert.notNull(folderModify, "La carpeta es nula");

		folderModify.setName("Nuevo Test");
		folderService.save(folderModify);

		Assert.notNull(folderService.findByNameAndActorId("Nuevo Test", 62),
				"No existe la carpeta con ese nombre2");

		unauthenticate();

	}

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Negative Test: Eliminar una folder que es del sistema
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFolder2() {
		Folder folder;

		authenticate("user1");

		folder = folderService.findByNameAndActorId("In Folder", 62);

		Assert.notNull(folder, "No se ha encontrado ninguna carpeta");

		folderService.delete(folder);

		Assert.isTrue(
				folderService.findByNameAndActorId("In Folder", 62) == null,
				"La carpeta no se ha borrado");

		unauthenticate();

	}

	/**
	 * An actor who is authenticated must be able to: -Manage his or her
	 * messages and message boxes.
	 * 
	 * Negative Test: Modificar el nombre de una folder del sistema
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testDeleteFolder3() {
		// Declare variables
		User user;
		Folder folder;
		Folder folderDelete;
		Collection<Folder> folders;

		// Load objects to test
		authenticate("user1");
		user = userService.findByPrincipal();

		// Checks basic requirements
		Assert.notNull(user, "El usuario no se ha logueado correctamente.");

		// Execution of test
		folders = user.getFolders();
		folder = folderService.create();
		folder.setName("Test");
		folder.setIsSystem(false);
		folder.setActor(user);
		folders.add(folder);
		folderService.save(folder);
		
		folderDelete=folderService.findByNameAndActorId("Test", 62);
		authenticate("user2");
		folderService.delete(folderDelete);
		
		unauthenticate();

	}

}
