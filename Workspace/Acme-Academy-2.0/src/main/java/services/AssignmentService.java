package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AssignmentRepository;
import domain.Assignment;
import domain.Lecturer;
import domain.Rubric;
import domain.Student;

@Service
@Transactional
public class AssignmentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AssignmentRepository assignmentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private StudentService studentService;

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private RubricService rubricService;

	// Constructors -----------------------------------------------------------

	public AssignmentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Assignment create() {
		Assignment result;
		Rubric defaultRubric;
		Collection<Rubric> rubrics;

		defaultRubric = rubricService.create();
		defaultRubric.setExplanation("default");
		defaultRubric.setPercentage(100);

		result = new Assignment();
		rubrics = new ArrayList<>();
		rubrics.add(defaultRubric);
		result.setRubrics(rubrics);

		return result;
	}

	public Assignment findOne(int assignmentId) {
		Assert.notNull(assignmentId);

		Assignment result;

		result = assignmentRepository.findOne(assignmentId);

		return result;

	}

	public Collection<Assignment> findAll() {

		Collection<Assignment> result;

		result = assignmentRepository.findAll();

		return result;
	}

	public void save(Assignment assignment) {
		Assert.notNull(assignment);
		Date current = new Date();

		if (assignment.getRubrics().contains(null)) {
			Rubric defaultRubric;
			Collection<Rubric> rubrics;

			defaultRubric = rubricService.create();
			defaultRubric.setExplanation("default");
			defaultRubric.setPercentage(100);
			defaultRubric.setNumber(1);
			defaultRubric.setAssignment(assignment);

			rubrics = new ArrayList<>();
			rubrics.add(defaultRubric);
			assignment.setRubrics(rubrics);
		}
		Assert.isTrue(assignment.getOpening().before(assignment.getDeadline()));
		Assert.isTrue(!assignment.getOpening().before(current));
		assignmentRepository.save(assignment);
	}

	public void delete(Assignment assignment) {
		Assert.notNull(assignment);

		assignmentRepository.delete(assignment);

	}

	// Other business methods -------------------------------------------------

	public Collection<Assignment> findAllByStudentId(int studentId) {
		Collection<Assignment> result;

		result = assignmentRepository.findAllByStudentId(studentId);

		return result;
	}

	public Collection<Assignment> findAllByPrincipal() {
		Collection<Assignment> result;
		Student principal;

		principal = studentService.findByPrincipal();
		result = findAllByStudentId(principal.getId());

		return result;
	}

	public Collection<Assignment> findByGroupIdAndStudentId(int groupId,
			int studentId) {
		Collection<Assignment> result;

		result = assignmentRepository.findByGroupIdAndStudentId(groupId,
				studentId);

		return result;
	}

	public Collection<Assignment> findByGroupIdAndStudentPrincipal(int groupId) {
		Collection<Assignment> result;
		Student principal;

		principal = studentService.findByPrincipal();
		result = findByGroupIdAndStudentId(groupId, principal.getId());

		return result;
	}

	public Collection<Assignment> findByGroupIdAndLecturerId(int groupId,
			int studentId) {
		Collection<Assignment> result;

		result = assignmentRepository.findByGroupIdAndLecturerId(groupId,
				studentId);

		return result;
	}

	public Collection<Assignment> findByGroupIdAndLecturerPrincipal(int groupId) {
		Collection<Assignment> result;
		Lecturer principal;

		principal = lecturerService.findByPrincipal();
		result = findByGroupIdAndLecturerId(groupId, principal.getId());

		return result;
	}

	public Collection<Assignment> findAllByLecturerPrincipal() {
		Collection<Assignment> result;
		Lecturer principal;

		principal = lecturerService.findByPrincipal();
		result = assignmentRepository.findAllByLecturerId(principal.getId());

		return result;
	}

	public boolean allRubricsInstantiated(int assignmentId) {
		boolean result = false;
		Collection<Rubric> rubrics;

		rubrics = rubricService.findNotInstantiatedByAssignmentId(assignmentId);

		if (rubrics.size() == 0) {
			result = true;
		}
		return result;
	}
}