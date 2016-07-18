package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LecturerRepository;
import security.LoginService;
import security.UserAccount;
import domain.Lecturer;

@Service
@Transactional
public class LecturerService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private LecturerRepository lecturerRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public LecturerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Lecturer create() {
		Lecturer result;

		result = new Lecturer();

		return result;
	}

	public Lecturer findOne(int lecturerId) {
		Assert.notNull(lecturerId);

		Lecturer result;

		result = lecturerRepository.findOne(lecturerId);

		return result;
	}

	public Collection<Lecturer> findAll() {

		Collection<Lecturer> result;

		result = lecturerRepository.findAll();

		return result;
	}

	public void save(Lecturer lecturer) {
		Assert.notNull(lecturer);

		lecturerRepository.save(lecturer);
	}

	public void delete(Lecturer lecturer) {
		Assert.notNull(lecturer);

		lecturerRepository.delete(lecturer);
	}

	// Other business methods -------------------------------------------------

	public Lecturer findByPrincipal() {
		Lecturer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public Lecturer findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Lecturer result;

		result = lecturerRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

	public Lecturer findBySubjectId(int subjectId) {
		Lecturer result;

		result = lecturerRepository.findBySubjectId(subjectId);

		return result;
	}
}