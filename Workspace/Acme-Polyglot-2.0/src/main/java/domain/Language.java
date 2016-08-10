package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

@Entity
@Access(AccessType.PROPERTY)
public class Language extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Language() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private int counter;
	private String code;

	@Min(0)
	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	@Pattern(regexp = "^([a-z]{2})$")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// Relationships ----------------------------------------------------------

	private Collection<LanguageExchange> languageExchanges;
	private Collection<LanguageDescription> languageDescriptions;

	@Valid
	@ManyToMany
	public Collection<LanguageExchange> getLanguageExchanges() {
		return languageExchanges;
	}

	public void setLanguageExchanges(
			Collection<LanguageExchange> languageExchanges) {
		this.languageExchanges = languageExchanges;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "language")
	public Collection<LanguageDescription> getLanguageDescriptions() {
		return languageDescriptions;
	}

	public void setLanguageDescriptions(
			Collection<LanguageDescription> languageDescriptions) {
		this.languageDescriptions = languageDescriptions;
	}

}