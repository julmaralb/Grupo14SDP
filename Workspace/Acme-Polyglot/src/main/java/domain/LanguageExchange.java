package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "registrationDate") })
public class LanguageExchange extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public LanguageExchange() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Date registrationDate;
	private String exchangePlace;
	private boolean cancelled;

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
	private Collection<LanguageExchangeDescription> languageExchangeDescriptions;
	private Collection<Sponsorship> sponsorships;

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
	@ManyToMany(mappedBy = "languageExchanges")
	public Collection<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(Collection<Language> languages) {
		this.languages = languages;
	}

	@Valid
	@OneToMany(mappedBy = "languageExchange")
	public Collection<LanguageExchangeDescription> getLanguageExchangeDescriptions() {
		return languageExchangeDescriptions;
	}

	public void setLanguageExchangeDescriptions(
			Collection<LanguageExchangeDescription> languageExchangeDescriptions) {
		this.languageExchangeDescriptions = languageExchangeDescriptions;
	}

	@Valid
	@OneToMany(mappedBy = "languageExchange")
	public Collection<Sponsorship> getSponsorships() {
		return sponsorships;
	}

	public void setSponsorships(Collection<Sponsorship> sponsorships) {
		this.sponsorships = sponsorships;
	}
}