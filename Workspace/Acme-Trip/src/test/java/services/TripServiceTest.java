package services;

import java.util.Collection;
import java.util.Date;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Trip;
import domain.User;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class TripServiceTest extends AbstractTest{
	
		// Service under test -------------------------
		@Autowired
		private TripService tripService;
		
		// Other services needed -----------------------
			
		@Autowired
		private UserService userService;
		
		
		// Tests ---------------------------------------
	
		/**
		 * Requirement:
		 * An actor who is not authenticated must be able to:
		 * 		- Navigate through the catalogue of trips that are registered in the system.
		 *
		 * Positive test case: Listar trips
		**/
		
		@Test 
		public void testListarTrip() {
			
			Collection<Trip> trips;
			trips=tripService.findAll();
			Assert.notNull(trips,"No se ha listado ningún Trip");
			
		}
		
				
		/**
		 * Requirement:
		 * An actor who is not authenticated must be able to:
		 * 		- Search for a trip using a single key word that must appear anywhere in the 
		 * 			information that the system stores about it, including the trips themselves, 
		 * 			the daily plans of which they are composed, their slots, or the activities 
		 * 			that they involve.
		 *
		 * Positive test case: Listar trips usando una palabra
		**/
		
		@Test 
		public void testListarTripUsingWord() {
			
			Collection<Trip> trips;
			String word;
			word="trip1Description";
			trips=tripService.findByKeyword(word);
			Assert.isTrue(trips.isEmpty()==false,"No se ha listado ningún Trip");
			
		}
		/**
		 * Requirement:
		 * An actor who is not authenticated must be able to:
		 * 		- Search for a trip using a single key word that must appear anywhere in the 
		 * 			information that the system stores about it, including the trips themselves, 
		 * 			the daily plans of which they are composed, their slots, or the activities 
		 * 			that they involve.
		 *
		 * Test: Listar trips usando una palabra que no existe
		**/

		@Test(expected = IllegalArgumentException.class)
		public void testListarTripUsingWord2() {
			
			Collection<Trip> trips;
			String word;
			word="aksldjlgjs";
			trips=tripService.findByKeyword(word);
			Assert.isTrue(trips.isEmpty()==false,"No se ha listado ningún Trip");
			
		}
		
		/**
		 * Requirement:
		 * An actor who is not authenticated must be able to:
		 * 		- Search for a trip using a single key word that must appear anywhere in the 
		 * 			information that the system stores about it, including the trips themselves, 
		 * 			the daily plans of which they are composed, their slots, or the activities 
		 * 			that they involve.
		 *
		 * Test: Listar trips usando una palabra vacia
		**/

		@Test(expected = IllegalArgumentException.class)
		public void testListarTripUsingWord3() {
			
			Collection<Trip> trips;
			String word;
			word="";
			trips=tripService.findByKeyword(word);
			Assert.isTrue(trips.isEmpty()==false,"No se ha listado ningún Trip");
			
		}
		
		/** Batería de pruebas **/
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Positive Test: Listar sus propios trips
		**/

		@Test
		public void testListarOwnTrips1() {
			Collection<Trip> trips;
			
			authenticate("user2");
			
			trips=tripService.findByPrincipal();
	
			Assert.isTrue(trips.size()==2,"El numero de trips no concuerda");
						
			unauthenticate();
		}
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Positive Test: Registrar un trip
		**/

		@SuppressWarnings("deprecation")
		@Test
		public void testRegistrarTrip1() {
			Trip trip;
			Collection<Trip> tripsBefore;
			User user;
			
			authenticate("user2");
			
			Date startDate=new Date(3,3,2017);
			Date endDate= new Date(3,3,2018);
			user=userService.findByPrincipal();
			tripsBefore= tripService.findByPrincipal();
			trip=tripService.create();
			trip.setTitle("Trip Test");
			trip.setDescription("Description Test");
			trip.setOwner(user);
			trip.setStartingDate(startDate);
			trip.setEndingDate(endDate);
			tripService.save(trip);
			
			Assert.isTrue(tripsBefore.size()+ 1==tripService.findByPrincipal().size(),"El numero de trips no concuerda");
						
			unauthenticate();
			
		}
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Negative Test: Registrar un trip nulo
		**/

		@Test(expected=IllegalArgumentException.class)
		public void testRegistrarTrip2() {
	
			authenticate("user2");
			
			tripService.save(null);
			
			unauthenticate();
			
		}
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Negative Test: Registrar un trip sin titulo
		**/

		@SuppressWarnings("deprecation")
		@Test(expected=ConstraintViolationException.class)
		public void testRegistrarTrip3() {
			Trip trip;
			Collection<Trip> tripsBefore;
			User user;
			
			authenticate("user2");
			
			Date startDate=new Date(3,3,2017);
			Date endDate= new Date(3,3,2018);
			user=userService.findByPrincipal();
			tripsBefore= tripService.findByPrincipal();
			trip=tripService.create();
			trip.setTitle(null);
			trip.setDescription("Description Test");
			trip.setOwner(user);
			trip.setStartingDate(startDate);
			trip.setEndingDate(endDate);
			tripService.save(trip);
			
			Assert.isTrue(tripsBefore.size()+ 1==tripService.findByPrincipal().size(),"El numero de trips no concuerda");
						
			unauthenticate();
			
		}
			
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Negative Test: Registrar un trip sin descripcion
		**/

		@SuppressWarnings("deprecation")
		@Test(expected=ConstraintViolationException.class)
		public void testRegistrarTrip4() {
			Trip trip;
			Collection<Trip> tripsBefore;
			User user;
			
			authenticate("user2");
			
			Date startDate=new Date(3,3,2017);
			Date endDate= new Date(3,3,2018);
			user=userService.findByPrincipal();
			tripsBefore= tripService.findByPrincipal();
			trip=tripService.create();
			trip.setTitle("Titulo Test");
			trip.setDescription(null);
			trip.setOwner(user);
			trip.setStartingDate(startDate);
			trip.setEndingDate(endDate);
			tripService.save(trip);
			
			Assert.isTrue(tripsBefore.size()+ 1==tripService.findByPrincipal().size(),"El numero de trips no concuerda");
						
			unauthenticate();
			
		}
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Positive Test: Modificar un trip propio
		**/

		@Test
		public void testModificarTrip1() {
			Trip trip;
			Trip trip2;
			
			authenticate("user2");
			
			trip=tripService.findOne(105);
			
			trip.setTitle("Nuevo Titulo Test");
			tripService.save(trip);
			
			trip2=tripService.findOne(105);
			
			Assert.isTrue(trip2.getTitle().equals("Nuevo Titulo Test"), "El titulo no ha cambiado");
			
			unauthenticate();
			
		}
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Negative Test: Modificar un trip de otro usuario
		**/

		@Test(expected=IllegalArgumentException.class)
		public void testModificarTrip2() {
			Trip trip;
			
			authenticate("user2");
			
			trip=tripService.findOne(99);
			
			trip.setTitle("Nuevo Titulo Test");
			tripService.save(trip);
			
			unauthenticate();
			
		}
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Negative Test: Modificar un trip con titulo nulo
		**/

		@Test(expected=ConstraintViolationException.class)
		public void testModificarTrip3() {
			Trip trip;
			
			authenticate("user1");
			
			trip=tripService.findOne(99);
			
			trip.setTitle(null);
			tripService.save(trip);
			
			unauthenticate();
			
		}
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Negative Test: Modificar un trip con descripcion nula
		**/

		@Test(expected=ConstraintViolationException.class)
		public void testModificarTrip4() {
			Trip trip;
			
			authenticate("user1");
			
			trip=tripService.findOne(99);
			
			trip.setDescription(null);
			tripService.save(trip);
			
			unauthenticate();
			
		}
		
		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Positive Test: Eliminar un trip propio
		**/

		@Test
		public void testEliminarTrip1() {
			Trip trip;
			Collection<Trip> tripsBefore;
			
			authenticate("user2");
			
			tripsBefore=tripService.findByPrincipal();
			
			trip=tripService.findOne(105);
			
			tripService.delete(trip);
			
			Assert.isTrue(tripsBefore.size()-1==tripService.findByPrincipal().size(), "No se ha eliminado el Trip");
			
			unauthenticate();
			
		}
		

		/**
		 * Requirement:
		 * 	An actor who is authenticated as a user must be able to:
		 * 		- Manage his or her trips, which involves listing, registering, modifying, 
		 * 			and deleting them.
		 *
		 * Test: Eliminar un trip de otro usuario
		**/

		@Test(expected=IllegalArgumentException.class)
		public void testEliminarTrip2() {
			Trip trip;
			
			authenticate("user1");
			
			trip=tripService.findOne(105);
			
			tripService.delete(trip);
			
			unauthenticate();
			
		}
		
		
		
		
		//TODO REQUISITO FUNCIONAL B-9.1
		
		
		
}
