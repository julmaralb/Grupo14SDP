package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.CreditCard;
import domain.Manager;

import security.UserAccount;
import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ManagerServiceTest extends AbstractTest {

	
		// Service under test -------------------------

		@Autowired
		private ManagerService managerService;

		// Other services needed -----------------------

		@Autowired
		private CreditCardService creditCardService;
		
		// Tests ---------------------------------------
		
		/**
		 * An actor who is authenticated as an administrator must be able to
		 * 		- Register managers and other administrators
		 * 
		 * Positive Test: Un admin registra un nuevo manager
		 * 
		 */
		@Test
		public void NewManagerTest(){
			
			Manager manager;
			UserAccount userAccount;
			
			authenticate("admin");
			
			manager=managerService.create();
			userAccount=manager.getUserAccount();
			
			manager.setName("Nuevo manager Test");
			manager.setSurname("Nuevo Surname Test");
			manager.setEmail("nuevoMagagerTest@email.com");
			manager.setPhone("+954669357");
			userAccount.setUsername("managerTest");
			userAccount.setPassword("managerTest");
			manager.setUserAccount(userAccount);
			
			managerService.save(manager);
			
			
			
			unauthenticate();
			
		}
		
		/**
		 * An actor who is authenticated as an administrator must be able to
		 * 		- Register managers and other administrators
		 * 
		 * Test: Un user registra un nuevo manager
		 * 
		 */
		@Test(expected = IllegalArgumentException.class)
		public void NewManagerTest2(){
			
			Manager manager;
			UserAccount userAccount;
			
			authenticate("user1");
			
			manager=managerService.create();
			userAccount=manager.getUserAccount();
			
			manager.setName("Nuevo manager Test");
			manager.setSurname("Nuevo Surname Test");
			manager.setEmail("nuevoMagagerTest@email.com");
			manager.setPhone("+954669357");
			userAccount.setUsername("managerTest");
			userAccount.setPassword("managerTest");
			manager.setUserAccount(userAccount);
			
			managerService.save(manager);
			
			
			
			unauthenticate();
			
		}
		
		/**
		 * An actor who is authenticated as an administrator must be able to
		 * 		- Register managers and other administrators
		 * 
		 * Test: Registrar un nuevo manager sin estar logueado en el sistema
		 * 
		 */
		@Test(expected = IllegalArgumentException.class)
		public void NewManagerTest3(){
			
			Manager manager;
			UserAccount userAccount;
			
			manager=managerService.create();
			userAccount=manager.getUserAccount();
			
			manager.setName("Nuevo manager Test");
			manager.setSurname("Nuevo Surname Test");
			manager.setEmail("nuevoMagagerTest@email.com");
			manager.setPhone("+954669357");
			userAccount.setUsername("managerTest");
			userAccount.setPassword("managerTest");
			manager.setUserAccount(userAccount);
			
			managerService.save(manager);
			
		}
		
		/**
		 * An actor who is authenticated as an administrator must be able to
		 * 		- Register managers and other administrators
		 * 
		 * Test: Un manager registra un nuevo manager
		 * 
		 */
		@Test(expected = IllegalArgumentException.class)
		public void NewManagerTest4(){
			
			authenticate("manager1");
			
			Manager manager;
			UserAccount userAccount;
			
			manager=managerService.create();
			userAccount=manager.getUserAccount();
			
			manager.setName("Nuevo manager Test");
			manager.setSurname("Nuevo Surname Test");
			manager.setEmail("nuevoMagagerTest@email.com");
			manager.setPhone("+954669357");
			userAccount.setUsername("managerTest");
			userAccount.setPassword("managerTest");
			manager.setUserAccount(userAccount);
			managerService.save(manager);
			
			unauthenticate();
		}
		
		/**
		 * An actor who is authenticated as an administrator must be able to
		 * 		- Register managers and other administrators
		 * 
		 * Test: Un admin registra un nuevo manager
		 * 
		 */
		@Test(expected = IllegalArgumentException.class)
		public void NewManagerTest5(){
						
			authenticate("admin");
									
			managerService.save(null);
						
			unauthenticate();
			
		}
		
	
}
