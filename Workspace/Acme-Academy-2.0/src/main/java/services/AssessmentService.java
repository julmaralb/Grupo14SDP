package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AssessmentRepository;
import domain.Assessment;
import domain.Assignment;
import domain.Deliverable;
import domain.Rubric;

@Service
@Transactional
public class AssessmentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AssessmentRepository assessmentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private RubricService rubricService;

	// Constructors -----------------------------------------------------------

	public AssessmentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Assessment create() {
		Assessment result;

		result = new Assessment();
		result.setNumber(0);

		return result;
	}

	public Assessment findOne(int assessmentId) {
		Assert.notNull(assessmentId);

		Assessment result;

		result = assessmentRepository.findOne(assessmentId);

		return result;
	}

	public Collection<Assessment> findAll() {

		Collection<Assessment> result;

		result = assessmentRepository.findAll();

		return result;
	}

	public void save(Assessment assessment) {
		Assert.notNull(assessment);

		assessmentRepository.save(assessment);
	}

	public void delete(Assessment assessment) {
		Assert.notNull(assessment);

		assessmentRepository.delete(assessment);
	}

	// Other business methods -------------------------------------------------

	public Collection<Assessment> findByDeliverableId(int deliverableId) {
		Collection<Assessment> result;

		result = assessmentRepository.findByDeliverableId(deliverableId);

		return result;
	}

	public Double computeMark(Deliverable deliverable) {
		Collection<Assessment> assessments;
		Assignment assignment;
		Double mark;

		assignment = deliverable.getAssignment();
		mark = 0.0;
		assessments = deliverable.getAssessments();
		for (Assessment a : assessments) {
			Rubric r = rubricService.findByAssignmentIdAndNumber(
					assignment.getId(), a.getNumber());
			mark = mark + (r.getPercentage() * 1.0 / 100)
					* (a.getPoints() * 1.0);
		}
		deliverable.setMark(mark);

		return mark;
	}

	public boolean computable(Assignment assignment) {
		boolean compute = false;

		// Si todos los rubcris han sido instanciados procedemos a calcular la
		// nota.
		Collection<Rubric> notInstantiated;
		notInstantiated = rubricService
				.findNotInstantiatedByAssignmentId(assignment.getId());
		if (notInstantiated.size() == 0) {
			compute = true;
		}
		return compute;
	}
}