package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "title"), @Index(columnList = "description"), @Index(columnList = "inappropriate") })
public class Activity extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Activity() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String title;
	private String description;
	private Collection<String> photos;
	private boolean inappropriate;

	@NotNull
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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

	@NotNull
	public boolean isInappropriate() {
		return inappropriate;
	}

	public void setInappropriate(boolean inappropriate) {
		this.inappropriate = inappropriate;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Slot> slots;
	private User user;
	private ActivityType activityType;
	private Collection<ActivityComment> activityComments;

	@Valid
	@OneToMany(mappedBy = "activity")
	public Collection<Slot> getSlots() {
		return slots;
	}

	public void setSlots(Collection<Slot> slots) {
		this.slots = slots;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public ActivityType getActivityType() {
		return activityType;
	}

	public void setActivityType(ActivityType activityType) {
		this.activityType = activityType;
	}

	@Valid
	@OneToMany(mappedBy = "activity")
	public Collection<ActivityComment> getActivityComments() {
		return activityComments;
	}

	public void setActivityComments(Collection<ActivityComment> activityComments) {
		this.activityComments = activityComments;
	}
}