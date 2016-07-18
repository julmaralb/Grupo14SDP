package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Table(name = "`Group`")
@Access(AccessType.PROPERTY)
public class Group extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Group() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private String description;
	private int academicYear;

	@NotNull
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Range(min = 2016)
	@NotNull
	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Student> students;
	private Subject subject;
	private Collection<LearningMaterial> learningMaterials;
	private Collection<Assignment> assignments;

	@Valid
	@ManyToMany()
	public Collection<Student> getStudents() {
		return students;
	}

	public void setStudents(Collection<Student> students) {
		this.students = students;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Valid
	@OneToMany(mappedBy = "group")
	public Collection<LearningMaterial> getLearningMaterials() {
		return learningMaterials;
	}

	public void setLearningMaterials(
			Collection<LearningMaterial> learningMaterials) {
		this.learningMaterials = learningMaterials;
	}

	@Valid
	@OneToMany(mappedBy = "group")
	public Collection<Assignment> getAssignments() {
		return assignments;
	}

	public void setAssignments(Collection<Assignment> assignments) {
		this.assignments = assignments;
	}
}