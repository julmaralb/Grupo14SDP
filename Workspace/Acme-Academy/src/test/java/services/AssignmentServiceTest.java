package services;

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
import domain.Assignment;
import domain.Group;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AssignmentServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private AssignmentService assignmentService;

	// Other services needed -----------------------

	@Autowired
	private GroupService groupService;
	
	// Tests ---------------------------------------

	
	/**
	 * 8.3 An actor who is authenticated as a lecturer must be able to:
	 * 		- Create an assignment regarding a subject and assign it to a group of students.
	 * 
	 * Positive Test: Crear un assignment nuevo y asignarselo a un grupo
	 */
	@SuppressWarnings("deprecation")
	@Test 
	public void testNewAssignment1() {
		
		authenticate("lecturer1");
		
		Group group;
		Assignment assignment;
		Integer before;
				
		group=groupService.findOne(33);
		before = group.getAssignments().size();
		assignment=assignmentService.create();
		assignment.setDeadline(new Date(2017,3,3));
		assignment.setDescription("description Test");
		assignment.setOpening(new Date());
		assignment.setPoints(1);
		assignment.setTitle("Title Test");
		assignment.setGroup(group);
		assignmentService.save(assignment);
		group.getAssignments().add(assignment);
		groupService.save(group);
		
		Assert.isTrue(before+1==groupService.findOne(33).getAssignments().size(),"El grupo no se le ha asignado ningun assignment nuevo");
		
		unauthenticate();
	}
	
	/**
	 * 8.3 An actor who is authenticated as a lecturer must be able to:
	 * 		- Create an assignment regarding a subject and assign it to a group of students.
	 * 
	 * Negative Test: Crear un assignment nuevo sin fecha de fin y asignarselo a un grupo
	 */
	@SuppressWarnings("deprecation")
	@Test (expected = NullPointerException.class)
	public void testNewAssignment2() {
		
		authenticate("lecturer1");
		
		Group group;
		Assignment assignment;
		
		group=groupService.findOne(33);
		assignment=assignmentService.create();
		assignment.setTitle("Title Test");
		assignment.setOpening(new Date());
		assignment.setDescription("description Test");
		assignment.setPoints(1);
		assignment.setGroup(group);
		assignmentService.save(assignment);
		group.getAssignments().add(assignment);
		groupService.save(group);
		
		unauthenticate();
	}
	
	/**
	 * 8.3 An actor who is authenticated as a lecturer must be able to:
	 * 		- Create an assignment regarding a subject and assign it to a group of students.
	 * 
	 * Negative Test: Crear un assignment nuevo sin fecha de inicio y asignarselo a un grupo
	 */
	@SuppressWarnings("deprecation")
	@Test (expected = NullPointerException.class)
	public void testNewAssignment3() {
		
		authenticate("lecturer1");
		
		Group group;
		Assignment assignment;
		
		group=groupService.findOne(33);
		assignment=assignmentService.create();
		assignment.setTitle("Title Test");
		assignment.setDeadline(new Date(2017,3,3));
		assignment.setDescription("description Test");
		assignment.setPoints(1);
		assignment.setGroup(group);
		assignmentService.save(assignment);
		group.getAssignments().add(assignment);
		groupService.save(group);
		
		unauthenticate();
	}
}
