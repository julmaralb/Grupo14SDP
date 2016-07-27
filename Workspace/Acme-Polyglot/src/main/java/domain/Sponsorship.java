package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Sponsorship extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Sponsorship() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;

	@NotNull
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Banner> banners;
	private Collection<SponsorshipDescription> sponsorshipDescriptions;
	private Agent agent;
	private LanguageExchange languageExchange;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "sponsorship")
	public Collection<Banner> getBanners() {
		return banners;
	}

	public void setBanners(Collection<Banner> banners) {
		this.banners = banners;
	}

	@Valid
	@OneToMany(mappedBy = "sponsorship")
	public Collection<SponsorshipDescription> getSponsorshipDescriptions() {
		return sponsorshipDescriptions;
	}

	public void setSponsorshipDescriptions(
			Collection<SponsorshipDescription> sponsorshipDescriptions) {
		this.sponsorshipDescriptions = sponsorshipDescriptions;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

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