package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class SponsorshipDescription extends Description {

	// Constructors -----------------------------------------------------------

	public SponsorshipDescription() {
		super();
	}

	// Attributes -------------------------------------------------------------

	// Relationships ----------------------------------------------------------

	private Sponsorship sponsorship;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Sponsorship getSponsorship() {
		return sponsorship;
	}

	public void setSponsorship(Sponsorship sponsorship) {
		this.sponsorship = sponsorship;
	}

}
