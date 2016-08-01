package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Keyword extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Keyword() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String keyword;
	private int count;

	@NotNull
	@NotBlank
	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	@Min(1)
	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	// Relationships ----------------------------------------------------------

	private Actor actor;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}
}