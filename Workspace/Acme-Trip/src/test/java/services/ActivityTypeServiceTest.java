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

import domain.ActivityType;
import domain.Trip;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ActivityTypeServiceTest extends AbstractTest {

	
	// Service under test -------------------------
			@Autowired
			private ActivityTypeService activityTypeService;
			
			// Other services needed -----------------------
				
			@Autowired
			private TripService tripService;
			
			
			// Tests ---------------------------------------
		
			/**
			 * Requirement:
			 * An actor who is not authenticated must be able to:
			 * 		-  List the types of activities that are registered in the system and navigate 
			 * 			to the trips that involve them.
			 *
			 * Positive test case: Listar ActivityTypes y navegar a sus Trips
			 *
			**/
			
			@Test 
			public void testListarActivityTypesandTrips() {
				
				Collection<ActivityType> activityTypes;
				Collection<Trip> trips;
				activityTypes=activityTypeService.findAll();
				Assert.notNull(activityTypes,"No se ha listado ningún ActivityType");
				for(ActivityType at:activityTypes){
					trips=tripService.findByActivityTypeId(at.getId());
					Assert.notNull(trips,"No se ha listado ningún Trip");
				}
				
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to:
			 * 		-Manage activity types
			 * 
			 * Positive Test:Un administrador cambia el nombre de un activity type
			 */
			@Test
			public void TestModifyActivityType(){
				
				authenticate("admin");
				
				ActivityType activityType;
				String name;
				
				activityType=activityTypeService.findOne(58);
				name=activityType.getName();
				
				activityType.setName("Name Test");
				activityTypeService.save(activityType);
				Assert.isTrue(!name.equals(activityType.getName()), "No se ha cambiado el nombre");
			
				unauthenticate();
				
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to:
			 * 		-Manage activity types
			 * 
			 *Negative Test:Un user cambia el nombre de un activity type
			 */
			@Test(expected = IllegalArgumentException.class)
			public void TestModifyActivityType2(){
				
				authenticate("user1");
				
				ActivityType activityType;
				
				activityType=activityTypeService.findOne(58);
				
				activityType.setName("Name Test");
				activityTypeService.save(activityType);
			
				unauthenticate();
				
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to:
			 * 		-Manage activity types
			 * 
			 *Negative Test:Un manager cambia el nombre de un activity type
			 */
			@Test(expected = IllegalArgumentException.class)
			public void TestModifyActivityType3(){
				
				authenticate("manager1");
				
				ActivityType activityType;
				
				activityType=activityTypeService.findOne(58);
				
				activityType.setName("Name Test");
				activityTypeService.save(activityType);
			
				unauthenticate();
				
			}
			
			
}
