package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
public class Court extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Court() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private String category;

	@NotBlank
	@NotNull
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotBlank
	@NotNull
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Day> days;
	private Collection<Reservation> reservations;
	private Centre centre;
	private Collection<Report> reports;

	@Valid
	@OneToMany(mappedBy = "court")
	public Collection<Day> getDays() {
		return days;
	}

	public void setDays(Collection<Day> days) {
		this.days = days;
	}

	@Valid
	@OneToMany(mappedBy = "court")
	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Centre getCentre() {
		return centre;
	}

	public void setCentre(Centre centre) {
		this.centre = centre;
	}

	@Valid
	@OneToMany(mappedBy = "court")
	public Collection<Report> getReports() {
		return reports;
	}

	public void setReports(Collection<Report> reports) {
		this.reports = reports;
	}
}