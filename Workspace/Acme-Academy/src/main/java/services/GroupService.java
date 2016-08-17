package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.GroupRepository;
import domain.Group;
import domain.Lecturer;
import domain.Student;

@Service
@Transactional
public class GroupService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private GroupRepository groupRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private LecturerService lecturerService;

	@Autowired
	private StudentService studentService;

	// Constructors -----------------------------------------------------------

	public GroupService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Group create() {
		Group result;

		result = new Group();

		return result;
	}

	public Group findOne(int groupId) {
		Assert.notNull(groupId);

		Group result;

		result = groupRepository.findOne(groupId);

		return result;
	}

	public Collection<Group> findAll() {

		Collection<Group> result;

		result = groupRepository.findAll();

		return result;
	}

	public void save(Group group) {
		Assert.notNull(group);
		
		Assert.notNull(group.getSubject(),"No se puede crear un grupo sin Subject");
		
		groupRepository.save(group);
	}

	public void delete(Group group) {
		Assert.notNull(group);

		groupRepository.delete(group);
	}

	// Other business methods -------------------------------------------------

	public Collection<Group> findByLecturerId(int lecturerId) {
		Collection<Group> result;

		result = groupRepository.findByLecturerId(lecturerId);

		return result;
	}

	public Collection<Group> findByLecturerPrincipal() {
		Collection<Group> result;
		Lecturer principal;

		principal = lecturerService.findByPrincipal();
		result = findByLecturerId(principal.getId());

		return result;
	}

	public Collection<Group> findByStudentId(int studentId) {
		Collection<Group> result;

		result = groupRepository.findByStudentId(studentId);

		return result;
	}

	public Collection<Group> findByStudentPrincipal() {
		Collection<Group> result;
		Student principal;

		principal = studentService.findByPrincipal();
		result = findByStudentId(principal.getId());

		return result;
	}

	public void join(Group group) {

		Collection<Student> students;
		Student principal;
		Collection<Group> groups;

		students = group.getStudents();
		principal = studentService.findByPrincipal();
		groups = principal.getGroups();

		groups.add(group);

		Assert.isTrue(!students.contains(principal));
		students.add(principal);
		group.setStudents(students);
		principal.setGroups(groups);
	}
}