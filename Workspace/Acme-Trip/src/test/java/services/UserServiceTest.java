package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Actor;
import domain.User;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class UserServiceTest extends AbstractTest {
		
			// Service under test -------------------------

			@Autowired
			private UserService userService;
			
			// Other services needed -----------------------
			
			@Autowired
			private ActorService actorService;
			@Autowired
			private UserRepository userRepository;
			
			// Tests ---------------------------------------

			/**
			 * Requirement:
			 * An actor who is not authenticated must be able to:
			 * 		- Register to the system as a user.
			 *
			 * Positive test case: Registrarse como User
			 * 		- Acción
			 * 		+ Entrar el registro como anónimo
			 * 		+ Rellenar los campos
			 * 		+ Presionar en registrarse
			 * 		- Comprobación
			 * 		+ Comprobar que la BBDD ha aumentado en 1 usuario
			 * 		+ Comprobar que puede loguearse con el nuevo usuario creado
			 * 		+ Cerrar su sesión
			 */
			
			@Test 
			public void testNewUser() {
				// Declare variables
				User user;
				UserAccount userAccount;
				User userRegistered;
				Actor authenticatedUser;
				Authority authority;
				Collection<Authority> authorities;
				
				// Load objects to test
				
				// Checks basic requirements
							
				// Execution of test
				Collection<User> before = userService.findAll();	
				user = userService.create();
				
				user.setName("Nuevo");
				user.setSurname("User");
				user.setPhone("+123456789");
				user.setEmail("user@correo.es");
				
				userAccount=user.getUserAccount();
				
				authorities = userAccount.getAuthorities();
				authority = new Authority();
				authority.setAuthority("USER");
				authorities.add(authority);
				userAccount.setAuthorities(authorities);
				 
				userAccount.setUsername("nuevoUser");
				userAccount.setPassword("nuevoUser");
				
				user.setUserAccount(userAccount);
			
				userRegistered = userService.save(user);
				
				Collection<User> after = userService.findAll();
				
				Assert.isTrue(before.size()+1==after.size(), "La BBDD no ha aumentado");
			
				// Checks results
				/*
				authenticate("admin");
				Collection<User> users;
				users=userService.findAll();
				Assert.isTrue(users.contains(userRegistered), "El user nuevo registrado no se encuentra entre los users registrados en el sistema."); // First check
				unauthenticate();
				*/
				authenticate("nuevoUser");
				
				authenticatedUser = actorService.findByPrincipal();
				
				Assert.notNull(authenticatedUser, "No se ha podido loguear al user que se acaba de registrar."); // Second check
				
				unauthenticate();
			}
			
			/**
			 * Requirement:
			 * An actor who is not authenticated must be able to:
			 * 		- Register to the system as a user.
			 * 
			 * 
			 * Test: Registrar un nuevo entrenador en el sistema null.
			 */
			@Test(expected = IllegalArgumentException.class)
			public void testNewUser2(){
								
				userService.save(null);
				
			}
			
			/**
			 * Requirement:
			 * An actor who is not authenticated must be able to:
			 * 		- Register to the system as a user.
			 * 
			 * 
			 * Test: Registrar un user duplicado en el sistema.
			 */	
			//ConstraintViolationException
			@Test(expected = DataIntegrityViolationException.class)
			public void testNewUser3(){

									
				User user = userService.create();
				user.getUserAccount().setUsername("user1");
				user.getUserAccount().setPassword("user1");
				user.setName("User New");
				user.setSurname("User Surname");
				user.setPhone("+656789856");
								
				userService.save(user);
				
				userRepository.flush();
				
			}
			
			
			
			/**
			 * Requirement:
			 * An actor who is authenticated must be able to:
			 * 		- Do the same as a user who is not authenticated, except for registering 
			 * 			to the system.
			 * 
			 * 
			 * Test: Registrarse como user estando logueado
			 */	
			//ConstraintViolationException
			/*
			@Test
			@Rollback(value = true)
			public void testNewUser4(){

				authenticate("user1");
				
				User user = userService.create();
				user.getUserAccount().setUsername("userNuevoTest");
				user.getUserAccount().setPassword("userNuevoTest");
				user.setName("User Name Test");
				user.setSurname("User Surname Test");
				user.setPhone("+656789866");
				user.setEmail("emailTest@trip.com");
								
				userService.save(user);
				
				unauthenticate();
			}
			*/
			
			
			/**
			 * An actor who is authenticated as a user must be able to:
			 * 		-Change his or her contact information
			 * 
			 * Positive Test:Cambiar Name
			 */
			@Test
			public void testModifyUser(){
				User user;
				
				authenticate("user1");
				
				user=userService.findByPrincipal();
				
				user.setName("Nuevo Nombre User Test");
				
				userService.save(user);
				
				Assert.isTrue(user.getName().equals("Nuevo Nombre User Test"), "El nombre del usuario no coincide con el nombre modificado");
			
				unauthenticate();
			}
			
			
			/**
			 * An actor who is authenticated as a user must be able to:
			 * 		-Change his or her contact information
			 * 
			 * Test:Modificar un user sin ser el principal
			 */
			@Test
			public void testModifyUser2(){
				User user;
				
				user=userService.findOne(68);
				
				user.setName("Nuevo Name Test");
				
				userService.save(user);
				
			}
			
			/**
			 * An actor who is authenticated as a user must be able to:
			 * 		-Change his or her contact information
			 * 
			 * Test:Modificar un user con email null
			 */
			@Test
			public void testModifyUser3(){
				User user;
				
				authenticate("user1");
				
				user=userService.findByPrincipal();
				
				user.setEmail(null);
				
				userService.save(user);
				
				unauthenticate();
				
			}
			
			
			
	}

