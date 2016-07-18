package services;

import java.util.Calendar;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SyllabusRepository;
import domain.Bibliography;
import domain.Lecturer;
import domain.Syllabus;

@Service
@Transactional
public class SyllabusService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SyllabusRepository syllabusRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private LecturerService lecturerService;

	// Constructors -----------------------------------------------------------

	public SyllabusService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Syllabus create() {
		Syllabus result;

		result = new Syllabus();

		return result;
	}

	public Syllabus findOne(int syllabusId) {
		Assert.notNull(syllabusId);

		Syllabus result;

		result = syllabusRepository.findOne(syllabusId);

		return result;
	}

	public Collection<Syllabus> findAll() {

		Collection<Syllabus> result;

		result = syllabusRepository.findAll();

		return result;
	}

	public void save(Syllabus syllabus) {
		Assert.notNull(syllabus);
		Collection<Bibliography> bibliographies;
		Lecturer principal = lecturerService.findByPrincipal();

		bibliographies = syllabus.getBibliographies();

		for (Bibliography b : bibliographies) {
			b.setCounter(b.getCounter() + 1);
		}
		principal.setCounter(principal.getCounter() + bibliographies.size());
		syllabusRepository.save(syllabus);
	}

	public void delete(Syllabus syllabus) {
		Assert.notNull(syllabus);

		syllabusRepository.delete(syllabus);
	}

	// Other business methods -------------------------------------------------

	public Collection<Syllabus> findByLecturerId(int lecturerId) {
		Collection<Syllabus> result;

		result = syllabusRepository.findByLecturerId(lecturerId);

		return result;
	}

	public Collection<Syllabus> findByPrincipal() {
		Collection<Syllabus> result;
		Lecturer principal;

		principal = lecturerService.findByPrincipal();
		result = findByLecturerId(principal.getId());

		return result;
	}

	public Collection<Syllabus> findBySubjectAndLecturerId(int subjectId,
			int lecturerId) {
		Collection<Syllabus> result;

		result = syllabusRepository.findBySubjectAndLecturerId(subjectId,
				lecturerId);

		return result;
	}

	public Collection<Syllabus> findBySubjectIdAndPrincipal(int subjectId) {
		Collection<Syllabus> result;
		Lecturer principal;

		principal = lecturerService.findByPrincipal();
		result = findBySubjectAndLecturerId(subjectId, principal.getId());

		return result;
	}

	public Syllabus findBySubjectIdAndYear(int subjectId, int year) {
		Syllabus result;

		result = syllabusRepository.findBySubjectIdAndYear(subjectId, year);

		return result;
	}

	public Syllabus findBySubjectIdAndCurrentYear(int subjectId) {
		Syllabus result;
		int currentYear;

		currentYear = Calendar.getInstance().get(Calendar.YEAR);
		result = findBySubjectIdAndYear(subjectId, currentYear);

		return result;
	}
}