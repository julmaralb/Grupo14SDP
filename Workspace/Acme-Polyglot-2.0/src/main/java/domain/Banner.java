package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "code") })
public class Banner extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Banner() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String code;
	private String photo;

	@Pattern(regexp = "^([a-z]{2})$")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@URL
	@NotNull
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

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