package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.Valid;

@Entity
@Access(AccessType.PROPERTY)
public class Polyglot extends Actor {

	// Constructors -----------------------------------------------------------

	public Polyglot() {
		super();
	}

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Collection<LanguageExchange> OrganisedLanguageExchanges;
	private Collection<LanguageExchange> ParticipatedLanguageExchanges;

	@Valid
	@OneToMany(mappedBy = "owner")
	public Collection<LanguageExchange> getOrganisedLanguageExchanges() {
		return OrganisedLanguageExchanges;
	}

	public void setOrganisedLanguageExchanges(
			Collection<LanguageExchange> organisedLanguageExchanges) {
		OrganisedLanguageExchanges = organisedLanguageExchanges;
	}

	@Valid
	@ManyToMany(mappedBy = "participants")
	public Collection<LanguageExchange> getParticipatedLanguageExchanges() {
		return ParticipatedLanguageExchanges;
	}

	public void setParticipatedLanguageExchanges(
			Collection<LanguageExchange> participatedLanguageExchanges) {
		ParticipatedLanguageExchanges = participatedLanguageExchanges;
	}
}