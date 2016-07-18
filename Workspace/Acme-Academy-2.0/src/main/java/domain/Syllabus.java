package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Syllabus extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Syllabus() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Integer academicYear;
	private String summary;
	private Collection<String> goals;
	private Collection<String> prerequisites;
	private String evaluationAndGradingPolicies;

	@Min(2016)
	public Integer getAcademicYear() {
		return academicYear;
	}

	public void setAcademicYear(Integer academicYear) {
		this.academicYear = academicYear;
	}

	@NotNull
	@NotBlank
	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	@NotNull
	@ElementCollection
	@Column(name = "goals")
	public Collection<String> getGoals() {
		return goals;
	}

	public void setGoals(Collection<String> goals) {
		this.goals = goals;
	}

	@ElementCollection
	@Column(name = "prerequisites")
	public Collection<String> getPrerequisites() {
		return prerequisites;
	}

	public void setPrerequisites(Collection<String> prerequisites) {
		this.prerequisites = prerequisites;
	}

	@NotNull
	@NotBlank
	public String getEvaluationAndGradingPolicies() {
		return evaluationAndGradingPolicies;
	}

	public void setEvaluationAndGradingPolicies(
			String evaluationAndGradingPolicies) {
		this.evaluationAndGradingPolicies = evaluationAndGradingPolicies;
	}

	// Relationships ----------------------------------------------------------

	private Subject subject;
	private Collection<Bibliography> bibliographies;

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	@Valid
	@NotNull
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH })
	public Collection<Bibliography> getBibliographies() {
		return bibliographies;
	}

	public void setBibliographies(Collection<Bibliography> bibliographies) {
		this.bibliographies = bibliographies;
	}
}