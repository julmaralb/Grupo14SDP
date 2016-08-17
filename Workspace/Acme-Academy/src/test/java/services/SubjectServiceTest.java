package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.SubjectRepository;

import utilities.AbstractTest;
import domain.Lecturer;
import domain.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SubjectServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private SubjectService subjectService;

	// Other services needed -----------------------

	@Autowired
	private LecturerService lecturerService;
	
	@Autowired
	private SubjectRepository subjectRepository;

	// Tests ---------------------------------------

	/**
	 * Acme-Academy - Level C - 8.1 An actor who is authenticated as a lecturer
	 * must be able to: List the subjects that he or she teaches.
	 * 
	 */

	@Test
	public void testListSubjectTeaches() {

		Collection<Subject> subjects3;

		subjects3 = subjectService.findAll();
		Assert.isTrue(subjects3 != null, "HOLA");

	}

	/**
	 * 6.2 An actor who is not authenticated must be able to: - List the
	 * lecturers and navigate to the subjects that they teach.
	 * 
	 * Positive Test: Listar los lecturer y navegar hasta los subjects
	 */
	@Test
	public void testListSubject1() {

		Collection<Lecturer> lecturers;
		Collection<Subject> subjects;

		subjects = subjectService.findAll();
		lecturers = new ArrayList<Lecturer>();

		for (Subject s : subjects) {
			if (!lecturers.contains(s.getLecturer())) {
				lecturers.add(s.getLecturer());
			}
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
	public void testListSubject2() {

		authenticate("student1");

		Collection<Lecturer> lecturers;
		Collection<Subject> subjects;

		subjects = subjectService.findAll();
		lecturers = new ArrayList<Lecturer>();

		for (Subject s : subjects) {
			if (!lecturers.contains(s.getLecturer())) {
				lecturers.add(s.getLecturer());
			}
		}
		Assert.isTrue(lecturers.size() == 3,
				"El numero de Lecturer no coincide");
		Assert.isTrue(subjects.size() == 4, "El numero de subjects no coincide");

		unauthenticate();
	}

	/**
	 * 8.1 An actor who is authenticated as a lecturer must be able to: - List
	 * the subjects that he or she teaches.
	 * 
	 * Positive Test: Un lecturer lista sus subjects
	 */
	@Test
	public void testListSubject3() {

		authenticate("lecturer2");

		Collection<Subject> subjects;

		subjects = subjectService.findByPrincipal();

		Assert.isTrue(subjects.size() == 2, "El numero de subjects no coincide");

		unauthenticate();
	}

	/**
	 * 10.1 An actor who is authenticated as an administrator must be able to:
	 * 		- Manage the subjects that are taught in an academy.
	 * 
	 * Positive Test: Un admin crea un nuevo subject
	 */
	@Test
	public void testCrearSubject1() {

		authenticate("admin");
		
		Subject subject;
		Collection<Subject> subjects;
		Collection<Subject> subjectsBefore;
		Collection<Subject> subjectsAfter;
		Lecturer lecturer;
		
		subject=subjectService.create();
		subjectsBefore = subjectService.findAll();
		subject.setCode("AC-123");
		subject.setTitle("Title Test");
		lecturer = lecturerService.findOne(13);
		subject.setLecturer(lecturer);
		subject.setSyllabus("Syllabus Test");
		
		subjectService.save(subject);
		subjects=subjectService.findByLecturerId(13);
		subjects.add(subject);
		lecturer.setSubjects(subjects);
		lecturerService.save(lecturer);
		subjectsAfter=subjectService.findAll();
		
		
		Assert.isTrue(subjectsBefore.size() +1==subjectsAfter.size() , "El numero de subjects no coincide");
		Assert.isTrue(subjectService.findByLecturerId(13).size()==3, "El numero de subjects del lecturer no coincide");
		
		
		unauthenticate();
	}
	
	/**
	 * 10.1 An actor who is authenticated as an administrator must be able to:
	 * 		- Manage the subjects that are taught in an academy.
	 * 
	 * Negative Test: Un admin crea un nuevo subject sin el nombre
	 */
	@Test (expected=ConstraintViolationException.class)
	public void testCrearSubject2() {

		authenticate("admin");
		
		Subject subject;
		Collection<Subject> subjects;
		Lecturer lecturer;
		
		subject=subjectService.create();
		subject.setCode("AC-123");
		lecturer = lecturerService.findOne(13);
		subject.setLecturer(lecturer);
		subject.setSyllabus("Syllabus Test");
		
		subjectService.save(subject);
		subjects=subjectService.findByLecturerId(13);
		subjects.add(subject);
		lecturer.setSubjects(subjects);
		lecturerService.save(lecturer);		
		
		subjectRepository.flush();
		
		unauthenticate();
	}
	
	/**
	 * 10.1 An actor who is authenticated as an administrator must be able to:
	 * 		- Manage the subjects that are taught in an academy.
	 * 
	 * Negative Test: Un admin crea un nuevo subject sin asignar un lecturer
	 */
	@Test (expected=IllegalArgumentException.class)
	public void testCrearSubject3() {

		authenticate("admin");
		
		Subject subject;
		
		subject=subjectService.create();
		subject.setCode("AC-123");
		subject.setTitle("Title Test");
		subject.setSyllabus("Syllabus Test");
		
		subjectService.save(subject);
		
		unauthenticate();
	}

}
