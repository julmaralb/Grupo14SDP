package services;

import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SubjectRepository;
import domain.Lecturer;
import domain.Subject;

@Service
@Transactional
public class SubjectService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SubjectRepository subjectRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private LecturerService lecturerService;

	// Constructors -----------------------------------------------------------

	public SubjectService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Subject create() {
		Subject result;
		String code;

		result = new Subject();
		code = generateRandomCode();
		result.setCode(code);

		return result;
	}

	public Subject findOne(int subjectId) {
		Assert.notNull(subjectId);

		Subject result;

		result = subjectRepository.findOne(subjectId);

		return result;
	}

	public Collection<Subject> findAll() {

		Collection<Subject> result;

		result = subjectRepository.findAll();

		return result;
	}

	public void save(Subject subject) {
		Assert.notNull(subject);

		subjectRepository.save(subject);
	}

	public void delete(Subject subject) {
		Assert.notNull(subject);

		subjectRepository.delete(subject);
	}

	// Other business methods -------------------------------------------------

	public Collection<Subject> findByLecturerId(int lecturerId) {
		Collection<Subject> result;

		result = subjectRepository.findByLecturerId(lecturerId);

		return result;
	}

	public Collection<Subject> findByPrincipal() {
		Collection<Subject> result;
		Lecturer principal;

		principal = lecturerService.findByPrincipal();
		result = findByLecturerId(principal.getId());

		return result;
	}

	public static String generateRandomCode() {
		char[] chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
		String result = "";
		Random random = new Random();
		String letras = "";
		String numeros = "";
		while (numeros.length() < 3) {
			numeros += 0 + (int) (Math.random() * 9);
		}
		while (letras.length() < 2) {
			letras += chars[random.nextInt(chars.length)];
		}
		result = letras + "-" + numeros;
		return result;

	}
}