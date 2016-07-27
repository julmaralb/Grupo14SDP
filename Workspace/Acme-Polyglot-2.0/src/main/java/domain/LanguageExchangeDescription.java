package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class LanguageExchangeDescription extends Description {

	// Constructors -----------------------------------------------------------

	public LanguageExchangeDescription() {
		super();
	}

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private LanguageExchange languageExchange;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public LanguageExchange getLanguageExchange() {
		return languageExchange;
	}

	public void setLanguageExchange(LanguageExchange languageExchange) {
		this.languageExchange = languageExchange;
	}
}