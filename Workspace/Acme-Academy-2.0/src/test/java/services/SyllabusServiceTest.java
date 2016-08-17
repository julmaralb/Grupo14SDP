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

import repositories.SyllabusRepository;

import utilities.AbstractTest;
import domain.Bibliography;
import domain.Subject;
import domain.Syllabus;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SyllabusServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private SyllabusService syllabusService;

	// Other services needed -----------------------

	@Autowired
	private SubjectService subjectService;

	@Autowired
	private BibliographyService bibliographyService;

	@Autowired
	private SyllabusRepository syllabusRepository;

	// Tests ---------------------------------------

	/**
	 * 3.1 An actor who is authenticated as a lecturer must be able to: Create a
	 * syllabus and associate it with a subject that he or she teaches.
	 * 
	 * Positive Test: Un lecturer crea un syllabus y lo asocia a un subject que
	 * enseña
	 */

	@Test
	public void testCrearSyllabus1() {

		authenticate("lecturer1");

		Syllabus syllabus;
		Subject subject;
		Collection<String> goals;
		Bibliography bibliography;
		Collection<Bibliography> bibliographies;
		Integer before = syllabusService.findAll().size();
		Integer subjectBefore;

		syllabus = syllabusService.create();
		subject = subjectService.findOne(29);
		bibliography = bibliographyService.findOne(31);
		subjectBefore = subject.getSyllabi().size();

		bibliographies = new ArrayList<Bibliography>();
		bibliographies.add(bibliography);
		syllabus.setAcademicYear(2017);
		syllabus.setEvaluationAndGradingPolicies("Policies test");
		syllabus.setSummary("Sumary test");
		syllabus.setSubject(subject);
		goals = new ArrayList<String>();
		goals.add("goal1test");
		syllabus.setGoals(goals);
		syllabus.setPrerequisites(goals);
		syllabus.setBibliographies(bibliographies);
		syllabusService.save(syllabus);
		Assert.isTrue(before + 1 == syllabusService.findAll().size(),
				"No se ha guardado el nuevo syllabus");

		subject.getSyllabi().add(syllabus);
		subjectService.save(subject);

		Assert.isTrue(subjectBefore + 1 == subject.getSyllabi().size(),
				"Subject no tiene sin gun syllabus nuevo");
		unauthenticate();

	}

	/**
	 * 3.1 An actor who is authenticated as a lecturer must be able to: Create a
	 * syllabus and associate it with a subject that he or she teaches.
	 * 
	 * Negative Test: Un lecturer crea un syllabus sin Policies
	 */

	@Test(expected = ConstraintViolationException.class)
	public void testCrearSyllabus2() {

		authenticate("lecturer1");

		Syllabus syllabus;
		Subject subject;
		Collection<String> goals;
		Bibliography bibliography;
		Collection<Bibliography> bibliographies;

		syllabus = syllabusService.create();
		subject = subjectService.findOne(29);
		bibliography = bibliographyService.findOne(31);

		bibliographies = new ArrayList<Bibliography>();
		bibliographies.add(bibliography);
		syllabus.setAcademicYear(2017);
		syllabus.setSummary("Sumary test");
		syllabus.setSubject(subject);
		goals = new ArrayList<String>();
		goals.add("goal1test");
		syllabus.setGoals(goals);
		syllabus.setPrerequisites(goals);
		syllabus.setBibliographies(bibliographies);
		syllabusService.save(syllabus);
		subject.getSyllabi().add(syllabus);
		subjectService.save(subject);

		syllabusRepository.flush();

		unauthenticate();

	}

	/**
	 * 3.1 An actor who is authenticated as a lecturer must be able to: Create a
	 * syllabus and associate it with a subject that he or she teaches.
	 * 
	 * Negative Test: Un lecturer crea un syllabus y lo asocia a un subject que
	 * no enseña
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCrearSyllabus3() {

		authenticate("lecturer1");

		Syllabus syllabus;
		Subject subject;
		Collection<String> goals;
		Bibliography bibliography;
		Collection<Bibliography> bibliographies;

		syllabus = syllabusService.create();
		subject = subjectService.findOne(32);
		bibliography = bibliographyService.findOne(31);

		bibliographies = new ArrayList<Bibliography>();
		bibliographies.add(bibliography);
		syllabus.setAcademicYear(2017);
		syllabus.setEvaluationAndGradingPolicies("Policies test");
		syllabus.setSummary("Sumary test");
		syllabus.setSubject(subject);
		goals = new ArrayList<String>();
		goals.add("goal1test");
		syllabus.setGoals(goals);
		syllabus.setPrerequisites(goals);
		syllabus.setBibliographies(bibliographies);
		syllabusService.save(syllabus);
		subject.getSyllabi().add(syllabus);
		subjectService.save(subject);

		unauthenticate();

	}

	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Positive Test: Un lecturer lista sus syllabus
	 * 
	 */

	@Test
	public void testListSyllabus1() {

		authenticate("lecturer1");

		Collection<Syllabus> syllabus;

		syllabus = syllabusService.findByPrincipal();

		Assert.isTrue(syllabus.size() == 1, "El numero de syllabus no coincide");

		unauthenticate();

	}
	


	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Positive Test: Modificar un syllabus
	 * 
	 */

	@Test
	public void testModificarSyllabus1() {

		authenticate("lecturer1");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(30);

		syllabus.setAcademicYear(2017);

		syllabusService.save(syllabus);

		Assert.isTrue(syllabusService.findOne(30).getAcademicYear() == 2017,
				"El año no coincide");

		unauthenticate();

	}

	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Negative Test: Modificar un syllabus quedando sin academic year
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testModificarSyllabus2() {

		authenticate("lecturer2");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(33);

		syllabus.setAcademicYear(null);

		syllabusService.save(syllabus);

		syllabusRepository.flush();

		unauthenticate();

	}
	
	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Negative Test: Modificar un syllabus quedando sin policies
	 * 
	 */

	@Test(expected = ConstraintViolationException.class)
	public void testModificarSyllabus3() {

		authenticate("lecturer2");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(33);

		syllabus.setEvaluationAndGradingPolicies(null);

		syllabusService.save(syllabus);

		syllabusRepository.flush();

		unauthenticate();

	}
	
	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Negative Test: Modificar un syllabus quedando sin Summary
	 * 
	 */

	@Test(expected = ConstraintViolationException.class)
	public void testModificarSyllabus4() {

		authenticate("lecturer2");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(33);

		syllabus.setSummary(null);

		syllabusService.save(syllabus);

		syllabusRepository.flush();

		unauthenticate();

	}

	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Negative Test: Modificar un syllabus quedando sin Goals
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testModificarSyllabus5() {

		authenticate("lecturer2");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(33);

		syllabus.setGoals(null);

		syllabusService.save(syllabus);

		unauthenticate();

	}
	
	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Negative Test: Modificar un syllabus quedando sin Subject
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testModificarSyllabus6() {

		authenticate("lecturer2");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(33);

		syllabus.setSubject(null);

		syllabusService.save(syllabus);

		unauthenticate();

	}
	
	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Negative Test: Modificar un syllabus quedando sin Bibliographies
	 * 
	 */

	@Test(expected = NullPointerException.class)
	public void testModificarSyllabus7() {

		authenticate("lecturer2");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(33);

		syllabus.setBibliographies(null);

		syllabusService.save(syllabus);

		unauthenticate();

	}
	
	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Positive Test: Eliminar un syllabus
	 * 
	 */

	@Test
	public void testEliminarSyllabus1() {

		authenticate("lecturer1");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(30);

		syllabusService.delete(syllabus);

		unauthenticate();

	}

	
	/**
	 * 3.2 An actor who is authenticated as a lecturer must be able to: List,
	 * edit, and delete the syllabuses that he or she´s created.
	 * 
	 * Negative Test: Eliminar un syllabus que no corresponde al principal
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testEliminarSyllabus2() {

		authenticate("lecturer2");

		Syllabus syllabus;

		syllabus = syllabusService.findOne(30);

		syllabusService.delete(syllabus);

		unauthenticate();

	}

}
