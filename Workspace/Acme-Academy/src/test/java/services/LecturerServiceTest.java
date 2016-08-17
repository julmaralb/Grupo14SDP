package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Lecturer;
import domain.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LecturerServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private LecturerService lecturerService;

	// Other services needed -----------------------

	// Tests ---------------------------------------

	/**
	 * 6.2 An actor who is not authenticated must be able to: - List the
	 * lecturers and navigate to the subjects that they teach.
	 * 
	 * Positive Test: Listar los lecturer y navegar hasta los subjects
	 */
	@Test
	public void testListLecturer1() {

		Collection<Lecturer> lecturers;
		Collection<Subject> subjects;

		subjects = new ArrayList<Subject>();
		lecturers = lecturerService.findAll();

		for (Lecturer l : lecturers) {
			subjects.addAll(l.getSubjects());
		}
		Assert.isTrue(lecturers.size() == 3,
				"El numero de Lecturer no coincide");
		Assert.isTrue(subjects.size() == 4, "El numero de subjects no coincide");

	}

	/**
	 * 7.1 An actor who is authenticated must be able to: - Perform the same
	 * actions as an actor who is not authenticated, except for registering to
	 * the system.
	 * 
	 * Positive Test: Listar los lecturer y navegar hasta los subjects
	 */
	@Test
	public void testListLecturer2() {

		authenticate("student1");

		Collection<Lecturer> lecturers;
		Collection<Subject> subjects;

		subjects = new ArrayList<Subject>();
		lecturers = lecturerService.findAll();

		for (Lecturer l : lecturers) {
			subjects.addAll(l.getSubjects());
		}
		Assert.isTrue(lecturers.size() == 3,
				"El numero de Lecturer no coincide");
		Assert.isTrue(subjects.size() == 4, "El numero de subjects no coincide");

		unauthenticate();

	}

}
