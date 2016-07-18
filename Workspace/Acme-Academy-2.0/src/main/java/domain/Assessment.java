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
public class Assessment extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Assessment() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String explanation;
	private int points;
	private int number;

	@NotNull
	@NotBlank
	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
	}

	@Range(min = 1, max = 100)
	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}
	
	@Min(0)
	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	// Relationships ----------------------------------------------------------

	Deliverable deliverable;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Deliverable getDeliverable() {
		return deliverable;
	}

	public void setDeliverable(Deliverable deliverable) {
		this.deliverable = deliverable;
	}
}