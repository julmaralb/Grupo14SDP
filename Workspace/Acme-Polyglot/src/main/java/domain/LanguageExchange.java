package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class LanguageExchange extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public LanguageExchange() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Collection<Description> descriptions;
	private Date registrationDate;
	private String exchangePlace;
	private boolean cancelled;

	@ElementCollection
	@Valid
	@NotNull
	public Collection<Description> getDescriptions() {
		return descriptions;
	}

	public void setDescriptions(Collection<Description> descriptions) {
		this.descriptions = descriptions;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@NotNull
	@NotBlank
	public String getExchangePlace() {
		return exchangePlace;
	}

	public void setExchangePlace(String exchangePlace) {
		this.exchangePlace = exchangePlace;
	}

	@NotNull
	public boolean isCancelled() {
		return cancelled;
	}

	public void setCancelled(boolean cancelled) {
		this.cancelled = cancelled;
	}

	// Relationships ----------------------------------------------------------

	private Polyglot owner;
	private Collection<Polyglot> participants;
	private Collection<Language> languages;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Polyglot getOwner() {
		return owner;
	}

	public void setOwner(Polyglot owner) {
		this.owner = owner;
	}

	@Valid
	@ManyToMany
	public Collection<Polyglot> getParticipants() {
		return participants;
	}

	public void setParticipants(Collection<Polyglot> participants) {
		this.participants = participants;
	}

	@Valid
	@ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH }, mappedBy = "languageExchanges")
	public Collection<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Collection<Language> languages) {
		this.languages = languages;
	}
}