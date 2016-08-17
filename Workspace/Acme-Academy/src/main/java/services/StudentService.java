package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.StudentRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Student;
import forms.StudentForm;

@Service
@Transactional
public class StudentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private StudentRepository studentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public StudentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Student create() {
		Student result;
		UserAccount userAccount;

		userAccount = createStudentAccount();
		result = new Student();

		result.setUserAccount(userAccount);

		return result;
	}

	public Student findOne(int studentId) {
		Assert.notNull(studentId);

		Student result;

		result = studentRepository.findOne(studentId);

		return result;

	}

	public Collection<Student> findAll() {

		Collection<Student> result;

		result = studentRepository.findAll();

		return result;
	}

	public void save(Student student) {
		Assert.notNull(student, "El Student no puede ser null");
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();
		Actor principal;

		if (student.getId() >= 1) {
			principal=actorService.findByPrincipal();
			Assert.isTrue(student.getId() == principal.getId());
		}
		
		String password;
		password = student.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		student.getUserAccount().setPassword(password);

		studentRepository.save(student);
	}

	public void delete(Student student) {
		Assert.notNull(student);

		studentRepository.delete(student);

	}

	// Other business methods -------------------------------------------------

	public Student findByPrincipal() {
		Student result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public Student findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Student result;

		result = studentRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

	// Register methods -------------------------------------------------------

	public UserAccount createStudentAccount() {
		UserAccount result;
		// Collection<Authority> authorities;
		Authority authority;

		result = new UserAccount();
		// result.setUsername("");
		// result.setPassword("");

		authority = new Authority();
		authority.setAuthority("STUDENT");
		// authorities = new ArrayList<Authority>();
		// authorities.add(authority);

		// result.setAuthorities(authorities);
		result.addAuthority(authority);

		return result;
	}

	public Student reconstruct(StudentForm studentForm) {
		Student result;

		result = create();
		result.getUserAccount().setPassword(studentForm.getPassword());
		result.getUserAccount().setUsername(studentForm.getUsername());

		result.setId(0);
		result.setVersion(0);
		result.setEmail(studentForm.getEmail());
		result.setName(studentForm.getName());
		result.setPhone(studentForm.getPhone());
		result.setSurname(studentForm.getUsername());

		if (studentForm.getTermsAccepted() == false) {
			throw new IllegalArgumentException(
					"You must accept the terms and condiditions");
		}
		if (!studentForm.getPassword().equals(studentForm.getSecondPassword())) {
			throw new IllegalArgumentException("Passwords must match");
		}
		return result;
	}

	public void modifyProfile(Student student) {

		Assert.notNull(student);

		Student result = findByPrincipal();
		String name;
		String surname;
		String phone;
		String email;

		phone = student.getPhone();
		surname = student.getSurname();
		name = student.getName();
		email = student.getEmail();

		result.setPhone(phone);
		result.setSurname(surname);
		result.setName(name);
		result.setEmail(email);
	}
}