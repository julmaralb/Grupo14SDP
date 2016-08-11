package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.hibernate.validator.constraints.URL;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Deliverable extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Deliverable() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Date moment;
	private String contents;
	private Integer deliverableVersion;
	private Double mark;

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	public Date getMoment() {
		return moment;
	}

	public void setMoment(Date moment) {
		this.moment = moment;
	}

	@URL
	@NotNull
	@NotBlank
	public String getContents() {
		return contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
	}

	@Range(min = 1)
	public Integer getDeliverableVersion() {
		return deliverableVersion;
	}

	public void setDeliverableVersion(Integer deliverableVersion) {
		this.deliverableVersion = deliverableVersion;
	}

	@Min(0)
	public Double getMark() {
		return mark;
	}

	public void setMark(Double mark) {
		this.mark = mark;
	}

	// Relationships ----------------------------------------------------------

	private Student student;
	private Assignment assignment;
	private Collection<Assessment> assessments;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliverable")
	public Collection<Assessment> getAssessments() {
		return assessments;
	}

	public void setAssessments(Collection<Assessment> assessments) {
		this.assessments = assessments;
	}

}
