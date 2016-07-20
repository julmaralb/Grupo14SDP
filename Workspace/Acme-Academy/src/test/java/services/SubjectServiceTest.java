package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.AssignmentRepository;
import repositories.StudentRepository;

import utilities.AbstractTest;
import domain.Subject;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class SubjectServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private SubjectService subjectService;
	
	@Autowired
	private LecturerService lecturerService;
	
	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private Md5PasswordEncoder encoder;
	
	@Autowired
	private AssignmentRepository assignmentRepository;

	// Test ---------------------------------------

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

}
