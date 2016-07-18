package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RubricRepository;
import domain.Assignment;
import domain.Lecturer;
import domain.Rubric;
import domain.Student;

@Service
@Transactional
public class RubricService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RubricRepository rubricRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private StudentService studentService;

	// Constructors -----------------------------------------------------------

	public RubricService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Rubric create() {
		Rubric result;

		result = new Rubric();
		result.setInstantiated(false);
		result.setNumber(0);

		return result;
	}

	public Rubric findOne(int rubricId) {
		Assert.notNull(rubricId);

		Rubric result;

		result = rubricRepository.findOne(rubricId);

		return result;
	}

	public Collection<Rubric> findAll() {

		Collection<Rubric> result;

		result = rubricRepository.findAll();

		return result;
	}

	public void save(Rubric rubric) {
		Assert.notNull(rubric);

		rubric.setNumber(rubric.getAssignment().getRubrics().size() + 1);

		// Resta el porcentaje del nuevo Rubric al default.
		Rubric def;
		def = findDefaultByAssignmentId(rubric.getAssignment().getId());
		def.setPercentage(def.getPercentage() - rubric.getPercentage());

		rubricRepository.save(rubric);
	}

	public void delete(Rubric rubric) {
		Assert.notNull(rubric);

		rubricRepository.delete(rubric);
	}

	// Other business methods -------------------------------------------------

	public Rubric findDefaultByAssignmentId(int assignmentId) {
		Rubric result;

		result = rubricRepository.findDefaultByAssignmentId(assignmentId);

		return result;
	}

	public Collection<Rubric> findByAssignmentId(int assignmentId) {
		Collection<Rubric> result;
		Lecturer principal;
		Assignment assignment;

		assignment = assignmentService.findOne(assignmentId);
		principal = lecturerService.findByPrincipal();
		result = rubricRepository.findByAssignmentId(assignmentId);
		Assert.isTrue(assignment.getGroup().getSubject().getLecturer()
				.equals(principal));

		return result;
	}

	public Collection<Rubric> findByAssignmentIdWithoutPercentage0(
			int assignmentId) {
		Collection<Rubric> result;
		Student principal;
		Assignment assignment;

		assignment = assignmentService.findOne(assignmentId);
		principal = studentService.findByPrincipal();
		result = rubricRepository
				.findByAssignmentIdWithoutPercentage0(assignmentId);
		Assert.isTrue(assignment.getGroup().getStudents().contains(principal));

		return result;
	}

	public Collection<Rubric> findNotInstantiatedByAssignmentId(int assignmentId) {
		Collection<Rubric> result;

		result = rubricRepository
				.findNotInstantiatedByAssignmentId(assignmentId);

		return result;
	}
	
	public Rubric findByAssignmentIdAndNumber(int assignmentId, int number){
		Rubric result;
		
		result = rubricRepository.findByAssignmentIdAndNumber(assignmentId, number);
		
		return result;
	}
}