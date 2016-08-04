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
import domain.Activity;

	@RunWith(SpringJUnit4ClassRunner.class)
	@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
			"classpath:spring/config/packages.xml" })
	@Transactional
	@TransactionConfiguration(defaultRollback = true)
	public class ActivityServiceTest extends AbstractTest {
		
			// Service under test -------------------------

				@Autowired
				private ActivityService activityService;

				// Other services needed -----------------------

				// Tests ---------------------------------------
				/**
				 * An actor who is authenticated as an administrator must be able to:
				 * 		-Flag comments and activities as inappropriate.
				 * 
				 *Positive Test: Marcar una activity como innapropiada
				 */
				@Test
				public void TestFlagActivity(){
				
					authenticate("admin");
					
					Activity activity;
					Boolean inappropiate;
					activity=activityService.findOne(95);
					inappropiate=activity.isInappropriate();
					activityService.flagActivity(activity);
				
					Assert.isTrue(inappropiate!=activity.isInappropriate(),"El mensaje no ha sido marcado como inapropiado");
					
					unauthenticate();
				}
				
				/**
				 * An actor who is authenticated as an administrator must be able to:
				 * 		-Flag comments and activities as inappropriate.
				 * 
				 *Test: Un user marca un commentario como inapropiado 
				 */
				@Test(expected = IllegalArgumentException.class)
				public void TestFlagActivity2(){
				
					authenticate("user1");
					
					Activity activity;
					activity=activityService.findOne(95);
					activityService.flagActivity(activity);
							
					unauthenticate();
				}
				
				/**
				 * An actor who is authenticated as an administrator must be able to:
				 * 		-Flag comments and activities as inappropriate.
				 * 
				 *Test: Un manager marca un commentario como inapropiado 
				 */
				@Test(expected = IllegalArgumentException.class)
				public void TestFlagActivity3(){
				
					authenticate("manager1");
					
					Activity activity;
					activity=activityService.findOne(95);
					activityService.flagActivity(activity);
				
					unauthenticate();
				}
	
}
