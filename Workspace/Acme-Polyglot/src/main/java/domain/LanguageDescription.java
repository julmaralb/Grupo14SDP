package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class LanguageDescription extends Description {

	// Constructors -----------------------------------------------------------

	public LanguageDescription() {
		super();
	}

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Language language;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Language getLanguage() {
		return language;
	}

	public void setLanguage(Language language) {
		this.language = language;
	}
}
