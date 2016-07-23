package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class Language extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Language() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Collection<Description> descriptions;
	private int counter;

	@ElementCollection
	@Valid
	@NotNull
	public Collection<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Collection<Description> descriptions) {
		this.descriptions = descriptions;
	}

	@Min(0)
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	// Relationships ----------------------------------------------------------

	private Collection<LanguageExchange> languageExchanges;

	@Valid
	@ManyToMany
	public Collection<LanguageExchange> getLanguageExchanges() {
		return languageExchanges;
	}

	public void setLanguageExchanges(
			Collection<LanguageExchange> languageExchanges) {
		this.languageExchanges = languageExchanges;
	}
}