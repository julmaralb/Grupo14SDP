package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LearningMaterialRepository;
import domain.Group;
import domain.LearningMaterial;
import domain.Lecturer;
import domain.Student;

@Service
@Transactional
public class LearningMaterialService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LearningMaterialRepository learningMaterialRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private GroupService groupService;

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private StudentService studentService;

	// Constructors -----------------------------------------------------------

	public LearningMaterialService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public LearningMaterial create() {
		LearningMaterial result;

		result = new LearningMaterial();

		return result;
	}

	public LearningMaterial findOne(int learningMaterialId) {
		Assert.notNull(learningMaterialId);

		LearningMaterial result;

		result = learningMaterialRepository.findOne(learningMaterialId);

		return result;
	}

	public Collection<LearningMaterial> findAll() {

		Collection<LearningMaterial> result;

		result = learningMaterialRepository.findAll();

		return result;
	}

	public void save(LearningMaterial learningMaterial) {
		Assert.notNull(learningMaterial);

		learningMaterialRepository.save(learningMaterial);
	}

	public void delete(LearningMaterial learningMaterial) {
		Assert.notNull(learningMaterial);

		learningMaterialRepository.delete(learningMaterial);
	}

	// Other business methods -------------------------------------------------

	public Collection<LearningMaterial> findByGroupId(int groupId) {
		Collection<LearningMaterial> result;
		Group group;
		Student principal;

		group = groupService.findOne(groupId);
		principal = studentService.findByPrincipal();
		Assert.isTrue(group.getStudents().contains(principal));
		result = learningMaterialRepository.findByGroupId(groupId);

		return result;
	}

	public Collection<LearningMaterial> findByGroupIdAndLecturer(int groupId) {
		Collection<LearningMaterial> result;
		Group group;
		Lecturer principal;

		group = groupService.findOne(groupId);
		principal = lecturerService.findByPrincipal();
		Assert.isTrue(group.getSubject().getLecturer().equals(principal));
		result = learningMaterialRepository.findByGroupId(groupId);

		return result;
	}
}