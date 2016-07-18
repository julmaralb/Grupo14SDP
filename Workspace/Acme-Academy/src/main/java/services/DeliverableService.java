package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DeliverableRepository;
import domain.Assignment;
import domain.Deliverable;
import domain.Lecturer;
import domain.Student;

@Service
@Transactional
public class DeliverableService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private DeliverableRepository deliverableRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private AssignmentService assignmentService;

	@Autowired
	private StudentService studentService;

	@Autowired
	private LecturerService lecturerService;

	// Constructors -----------------------------------------------------------

	public DeliverableService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Deliverable create() {
		Deliverable result;
		Date moment;
		Student principal;

		result = new Deliverable();
		moment = new Date();
		principal = studentService.findByPrincipal();

		result.setMoment(moment);
		result.setStudent(principal);
		result.setDeliverableVersion(1);

		return result;
	}

	public Deliverable findOne(int deliverableId) {
		Assert.notNull(deliverableId);

		Deliverable result;

		result = deliverableRepository.findOne(deliverableId);

		return result;
	}

	public Collection<Deliverable> findAll() {

		Collection<Deliverable> result;

		result = deliverableRepository.findAll();

		return result;
	}

	public void save(Deliverable deliverable) {
		Assert.notNull(deliverable);
		Date moment;
		Student principal;
		int deliverableVersion;

		moment = new Date();
		principal = studentService.findByPrincipal();
		deliverableVersion = deliverable.getAssignment().getDeliverables()
				.size();
		deliverableVersion++;

		moment.setTime(moment.getTime() - 1);
		deliverable.setMoment(moment);
		deliverable.setStudent(principal);
		deliverable.setDeliverableVersion(deliverableVersion);
		Assert.isTrue(checkInTime(deliverable));
		deliverableRepository.save(deliverable);
	}

	public void delete(Deliverable deliverable) {
		Assert.notNull(deliverable);

		deliverableRepository.delete(deliverable);
	}

	// Other business methods -------------------------------------------------

	public Collection<Deliverable> findByAssignmentId(int assignmentId) {
		Collection<Deliverable> result;
		Assignment assignment;
		Student principal;

		assignment = assignmentService.findOne(assignmentId);
		principal = studentService.findByPrincipal();
		Assert.isTrue(assignment.getGroup().getStudents().contains(principal));
		result = deliverableRepository.findByAssignmentId(assignmentId);

		return result;
	}

	public Collection<Deliverable> findByAssignmentIdAndLecturer(
			int assignmentId) {
		Collection<Deliverable> result;
		Assignment assignment;
		Lecturer principal;

		assignment = assignmentService.findOne(assignmentId);
		principal = lecturerService.findByPrincipal();
		Assert.isTrue(assignment.getGroup().getSubject().getLecturer()
				.equals(principal));
		result = deliverableRepository.findByAssignmentId(assignmentId);

		return result;
	}

	public boolean checkInTime(Deliverable deliverable) {
		boolean res = false;
		Date opening;
		Date deadline;
		Date moment;

		opening = deliverable.getAssignment().getOpening();
		deadline = deliverable.getAssignment().getDeadline();
		moment = deliverable.getMoment();

		if (moment.after(opening) && moment.before(deadline)) {
			res = true;
		}
		return res;
	}
}