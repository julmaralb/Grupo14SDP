package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Subject extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Subject() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String code;
	private String title;

	@NotNull
	@Pattern(regexp = "^\\w{2}-\\d{3}$")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@NotNull
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	// Relationships ----------------------------------------------------------

	private Lecturer lecturer;
	private Collection<Group> groups;
	private Collection<Syllabus> syllabi;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Lecturer getLecturer() {
		return lecturer;
	}

	public void setLecturer(Lecturer lecturer) {
		this.lecturer = lecturer;
	}

	@Valid
	@OneToMany(mappedBy = "subject")
	public Collection<Group> getGroups() {
		return groups;
	}

	public void setGroups(Collection<Group> groups) {
		this.groups = groups;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "subject")
	public Collection<Syllabus> getSyllabi() {
		return syllabi;
	}

	public void setSyllabi(Collection<Syllabus> syllabi) {
		this.syllabi = syllabi;
	}
}