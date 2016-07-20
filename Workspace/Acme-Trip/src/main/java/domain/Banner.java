package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Banner extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Banner() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String photo;
	private Collection<String> keywords;
	private int maxDisplayTimes;
	private int dayDisplays;

	@NotNull
	@URL
	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@NotNull
	@ElementCollection
	@Column(name = "keywords")
	public Collection<String> getKeywords() {
		return keywords;
	}

	public void setKeywords(Collection<String> keywords) {
		this.keywords = keywords;
	}

	@Min(1)
	public int getMaxDisplayTimes() {
		return maxDisplayTimes;
	}

	public void setMaxDisplayTimes(int maxDisplayTimes) {
		this.maxDisplayTimes = maxDisplayTimes;
	}

	@Min(0)
	public int getDayDisplays() {
		return dayDisplays;
	}

	public void setDayDisplays(int dayDisplays) {
		this.dayDisplays = dayDisplays;
	}

	// Relationships ----------------------------------------------------------

	private Campaign campaign;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
}