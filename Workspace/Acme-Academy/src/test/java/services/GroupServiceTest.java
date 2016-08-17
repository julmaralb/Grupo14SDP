package services;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.GroupRepository;

import utilities.AbstractTest;
import domain.Group;
import domain.Student;
import domain.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class GroupServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private GroupService groupService;

	// Other services needed -----------------------

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private StudentService studentService;
	
	@Autowired
	private GroupRepository groupRepository;

	// Tests ---------------------------------------

	/**
	 * 8.2 An actor who is authenticated as a lecturer must be able to: - Create
	 * a group of students and associate it with a subject.
	 * 
	 * Positive Test: Un lecturer crea un group y le asigna un subject
	 */
	@Test
	public void testCrearGroup1() {

		authenticate("lecturer2");
		Subject subject;
		Group group;
		Integer before;

		before = groupService.findAll().size();
		subject = subjectService.findOne(30);
		group = groupService.create();
		group.setAcademicYear(2017);
		group.setDescription("Test description");
		group.setName("Name test");
		group.setSubject(subject);
		groupService.save(group);

		unauthenticate();

		Assert.isTrue(groupService.findAll().size() == before + 1,
				"El grupo no se ha guardado");
	}

	/**
	 * 8.2 An actor who is authenticated as a lecturer must be able to: - Create
	 * a group of students and associate it with a subject.
	 * 
	 * Negative Test: Un lecturer crea un group sin subject
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCrearGroup2() {

		authenticate("lecturer2");

		Group group;

		group = groupService.create();
		group.setAcademicYear(2017);
		group.setDescription("Test description");
		group.setName("Name test");
		groupService.save(group);

		unauthenticate();

	}

	/**
	 * 8.2 An actor who is authenticated as a lecturer must be able to: - Create
	 * a group of students and associate it with a subject.
	 * 
	 * Negative Test: Un lecturer crea un group sin academicYear y le asigna un
	 * subject
	 */
	@Test(expected = ConstraintViolationException.class)
	public void testCrearGroup3() {

		authenticate("lecturer2");
		Subject subject;
		Group group;

		subject = subjectService.findOne(30);
		group = groupService.create();
		group.setDescription("Test description");
		group.setName("Name test");
		group.setSubject(subject);
		groupService.save(group);

		groupRepository.flush();
		
		unauthenticate();

	}

	/**
	 * 9.1 An actor who is authenticated as a student must be able to: - Enrol a
	 * group of students so that he or she becomes a member of that group.
	 * 
	 * Positive Test: Un student se une a un grupo
	 */
	@Test
	public void testEnrolAGroup1() {

		authenticate("student4");
		Student student;
		Group group;

		student = studentService.findByPrincipal();
		group = groupService.findOne(33);

		Boolean before = group.getStudents().contains(student);
		groupService.join(group);

		Boolean after = group.getStudents().contains(student);

		Assert.isTrue(!(before == after), "El student no se unió al grupo");
		unauthenticate();

	}

	/**
	 * 9.1 An actor who is authenticated as a student must be able to: - Enrol a
	 * group of students so that he or she becomes a member of that group.
	 * 
	 * Negative Test: Un student se une a un grupo en el que ya está
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testEnrolAGroup2() {

		authenticate("student1");
		Group group;

		group = groupService.findOne(33);

		groupService.join(group);

		unauthenticate();

	}

}
