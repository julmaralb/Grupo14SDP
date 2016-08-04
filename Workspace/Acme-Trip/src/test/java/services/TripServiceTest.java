package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Trip;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class TripServiceTest extends AbstractTest{
	
		// Service under test -------------------------
		@Autowired
		private TripService tripService;
		
		// Other services needed -----------------------
			
		
		
		
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
		@Rollback(value = true)
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
		@Rollback(value = true)
		public void testListarTripUsingWord3() {
			
			Collection<Trip> trips;
			String word;
			word="";
			trips=tripService.findByKeyword(word);
			Assert.isTrue(trips.isEmpty()==false,"No se ha listado ningún Trip");
			
		}
		
		
		//TODO REQUISITO FUNCIONAL B-7.1
		
		
		
}
