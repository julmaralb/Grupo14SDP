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
import domain.Assignment;
import domain.Deliverable;
import domain.Group;
import domain.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
		"classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml"})
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DeliverableServiceTest extends AbstractTest {
	
				// Service under test -------------------------

				@Autowired
				private DeliverableService deliverableService;
				
				// Other services needed -----------------------
				
				@Autowired
				private AssignmentService assignmentService;
				
				@Autowired
				private GroupService groupService;
				
				// Tests ---------------------------------------
	
	
	/**
	 * 	9.2 An actor who is authenticated as a student must be able to:
	 * 		- Create and upload a deliverable or a new version of a deliverable that was 
	 * 			uploaded previously.
	 * 
	 * Positive Test: Un student crea y sube un deliverable
	 */
	@Test
	public void testCreaDeliverable1(){
		authenticate("student1");
		
		Deliverable deliverable;
		Assignment assignment;
		
		deliverable = deliverableService.create();
		assignment=assignmentService.findOne(46);
		
		deliverable.setContents("www.contentTest.com");
		deliverable.setAssignment(assignment);
		
		deliverableService.save(deliverable);
		assignment.getDeliverables().add(deliverable);
		assignmentService.save(assignment);
		
		Assert.isTrue(assignment.getDeliverables().contains(deliverable),"El deliverable no se envió");

		unauthenticate();
	
	}
	
	/**
	 * 	9.2 An actor who is authenticated as a student must be able to:
	 * 		- Create and upload a deliverable or a new version of a deliverable that was 
	 * 			uploaded previously. 
	 * 
	 * Negative Test: Un student crea un deliverable sin assignment
	 */
	@Test (expected=NullPointerException.class)
	public void testCreaDeliverable2(){
		authenticate("student1");
		
		Deliverable deliverable;
		
		deliverable = deliverableService.create();
		
		deliverable.setContents("www.contentTest.com");
		
		deliverableService.save(deliverable);

		unauthenticate();
	
	}

	
}
