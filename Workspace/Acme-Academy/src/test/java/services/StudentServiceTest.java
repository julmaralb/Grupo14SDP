package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.StudentRepository;
import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Actor;
import domain.Student;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class StudentServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private StudentService studentService;

	// Other services needed -----------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private StudentRepository studentRepository;

	// Tests ---------------------------------------

	/**
	 * 6.1 An actor who is not authenticated must be able to: - Register to the
	 * system as a student.
	 * 
	 * Positive Test: Registrar un nuevo student
	 */
	@Test
	public void testNewStudent1() {
		Student student;
		UserAccount userAccount;
		Authority authority;
		Collection<Authority> authorities;
		Actor authenticatedUser;

		Collection<Student> before = studentService.findAll();
		student = studentService.create();
		userAccount = student.getUserAccount();

		student.setEmail("correoTest@email.com");
		student.setName("Nombre Test");
		student.setPhone("615855444");
		student.setSurname("Surname Test");
		userAccount.setUsername("test1");
		userAccount.setPassword("test1");
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority("STUDENT");
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		student.setUserAccount(userAccount);

		studentService.save(student);

		Collection<Student> after = studentService.findAll();
		Assert.isTrue(before.size() + 1 == after.size(),
				"La BBDD no ha aumentado");

		authenticate("test1");

		authenticatedUser = actorService.findByPrincipal();

		Assert.notNull(authenticatedUser,
				"No se ha podido loguear al user que se acaba de registrar."); // Second
																				// check

		unauthenticate();

	}

	/**
	 * 6.2 An actor who is not authenticated must be able to: - Register to the
	 * system as a student.
	 * 
	 * Negative Test: Registrar un nuevo student nulo
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testNewStudent2() {

		studentService.save(null);

	}

	/**
	 * 6.2 An actor who is not authenticated must be able to: - Register to the
	 * system as a student.
	 * 
	 * Negative Test: Registrar un nuevo student repetido
	 */
	@Test(expected = DataIntegrityViolationException.class)
	public void testNewStudent3() {

		Student student;
		UserAccount userAccount;
		Authority authority;
		Collection<Authority> authorities;

		student = studentService.create();
		userAccount = student.getUserAccount();

		student.setEmail("correoTest@email.com");
		student.setName("Nombre Test");
		student.setPhone("615855444");
		student.setSurname("Surname Test");
		userAccount.setUsername("student1");
		userAccount.setPassword("student1");
		authorities = userAccount.getAuthorities();
		authority = new Authority();
		authority.setAuthority("STUDENT");
		authorities.add(authority);
		userAccount.setAuthorities(authorities);
		student.setUserAccount(userAccount);
		studentService.save(student);

		studentRepository.flush();

	}

}
