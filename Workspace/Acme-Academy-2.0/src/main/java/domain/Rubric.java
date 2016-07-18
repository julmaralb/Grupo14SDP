package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class Rubric extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Rubric() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String explanation;
	private int percentage;
	private boolean instantiated;
	private int number;

	@NotNull
	@NotBlank
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Range(min = 0, max = 100)
	public int getPercentage() {
		return percentage;
	}

	public void setPercentage(int percentage) {
		this.percentage = percentage;
	}

	public boolean isInstantiated() {
		return instantiated;
	}

	public void setInstantiated(boolean instantiated) {
		this.instantiated = instantiated;
	}
	
	@Min(0)
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// Relationships ----------------------------------------------------------

	private Assignment assignment;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Assignment getAssignment() {
		return assignment;
	}

	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

}
