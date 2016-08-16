package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import security.UserAccount;
import utilities.AbstractTest;
import domain.Administrator;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AdministratorServiceTest extends AbstractTest {

		
			// Service under test -------------------------

			@Autowired
			private AdministratorService administratorService;

			// Other services needed -----------------------

			// Tests ---------------------------------------
			
			/**
			 * An actor who is authenticated as an administrator must be able to
			 * 		- Register managers and other administrators
			 * 
			 * Positive Test: Un admin registra un nuevo administrator
			 * 
			 */
			@Test
			public void NewAdminTest1(){
				
				Administrator administrator;
				UserAccount userAccount;
				
				authenticate("admin");
				
				administrator=administratorService.create();
				userAccount=administrator.getUserAccount();
				
				administrator.setName("Nuevo administrator Test");
				administrator.setSurname("Nuevo Surname Test");
				administrator.setEmail("nuevoMagagerTest@email.com");
				administrator.setPhone("+954669357");
				userAccount.setUsername("managerTest");
				userAccount.setPassword("managerTest");
				administrator.setUserAccount(userAccount);
				
				administratorService.save(administrator);
				
				
				
				unauthenticate();
				
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to
			 * 		- Register managers and other administrators
			 * 
			 * Negative Test: Un user registra un nuevo administrator
			 * 
			 */
			@Test(expected = IllegalArgumentException.class)
			public void NewAdminTest2(){
				
				Administrator administrator;
				UserAccount userAccount;
				
				authenticate("user1");
				
				administrator=administratorService.create();
				userAccount=administrator.getUserAccount();
				
				administrator.setName("Nuevo administrator Test");
				administrator.setSurname("Nuevo Surname Test");
				administrator.setEmail("nuevoMagagerTest@email.com");
				administrator.setPhone("+954669357");
				userAccount.setUsername("managerTest");
				userAccount.setPassword("managerTest");
				administrator.setUserAccount(userAccount);
				
				administratorService.save(administrator);
				
				
				
				unauthenticate();
				
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to
			 * 		- Register managers and other administrators
			 * 
			 *Negative Test: Registrar un nuevo administrator sin estar logueado en el sistema
			 * 
			 */
			@Test(expected = IllegalArgumentException.class)
			public void NewAdminTest3(){
				
				Administrator administrator;
				UserAccount userAccount;
				
				administrator=administratorService.create();
				userAccount=administrator.getUserAccount();
				
				administrator.setName("Nuevo administrator Test");
				administrator.setSurname("Nuevo Surname Test");
				administrator.setEmail("nuevoMagagerTest@email.com");
				administrator.setPhone("+954669357");
				userAccount.setUsername("managerTest");
				userAccount.setPassword("managerTest");
				administrator.setUserAccount(userAccount);
				
				administratorService.save(administrator);
				
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to
			 * 		- Register managers and other administrators
			 * 
			 * Negative Test: Un manager registra un nuevo administrator
			 * 
			 */
			@Test(expected = IllegalArgumentException.class)
			public void NewAdminTest4(){
				
				authenticate("manager1");
				
				Administrator administrator;
				UserAccount userAccount;
				
				administrator=administratorService.create();
				userAccount=administrator.getUserAccount();
				
				administrator.setName("Nuevo administrator Test");
				administrator.setSurname("Nuevo Surname Test");
				administrator.setEmail("nuevoMagagerTest@email.com");
				administrator.setPhone("+954669357");
				userAccount.setUsername("managerTest");
				userAccount.setPassword("managerTest");
				administrator.setUserAccount(userAccount);
				
				administratorService.save(administrator);
				
				unauthenticate();
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to
			 * 		- Register managers and other administrators
			 * 
			 * Negative Test: Un admin registra un nuevo administrator nulo
			 * 
			 */
			@Test(expected = IllegalArgumentException.class)
			public void NewAdminTest5(){
							
				authenticate("admin");
										
				administratorService.save(null);
							
				unauthenticate();
				
			}
}
