package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Trip extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Trip() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String title;
	private Date startingDate;
	private Date endingDate;
	private String description;
	private Collection<String> photos;

	@NotNull
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getStartingDate() {
		return startingDate;
	}

	public void setStartingDate(Date startingDate) {
		this.startingDate = startingDate;
	}

	@NotNull
	@Temporal(TemporalType.TIMESTAMP)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getEndingDate() {
		return endingDate;
	}

	public void setEndingDate(Date endingDate) {
		this.endingDate = endingDate;
	}

	@NotNull
	@NotBlank
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@ElementCollection
	@Column(name = "photos")
	public Collection<String> getPhotos() {
		return photos;
	}

	public void setPhotos(Collection<String> photos) {
		this.photos = photos;
	}

	// Relationships ----------------------------------------------------------

	private Collection<User> subscriptors;
	private User owner;
	private Collection<DailyPlan> dailyPlans;
	private Collection<TripComment> tripComments;

	@Valid
	@ManyToMany()
	public Collection<User> getSubscriptors() {
		return subscriptors;
	}

	public void setSubscriptors(Collection<User> subscriptors) {
		this.subscriptors = subscriptors;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "trip")
	public Collection<DailyPlan> getDailyPlans() {
		return dailyPlans;
	}

	public void setDailyPlans(Collection<DailyPlan> dailyPlans) {
		this.dailyPlans = dailyPlans;
	}

	@Valid
	@OneToMany(mappedBy = "trip")
	public Collection<TripComment> getTripComments() {
		return tripComments;
	}

	public void setTripComments(Collection<TripComment> tripComments) {
		this.tripComments = tripComments;
	}
}