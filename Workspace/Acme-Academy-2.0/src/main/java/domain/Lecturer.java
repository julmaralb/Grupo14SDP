package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;

@Entity
@Access(AccessType.PROPERTY)
public class Lecturer extends Actor {

	// Constructors -----------------------------------------------------------

	public Lecturer() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Integer counter;

	@Min(0)
	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Subject> subjects;

	@Valid
	@OneToMany(mappedBy = "lecturer")
	public Collection<Subject> getSubjects() {
		return subjects;
	}

	public void setSubjects(Collection<Subject> subjects) {
		this.subjects = subjects;
	}
}