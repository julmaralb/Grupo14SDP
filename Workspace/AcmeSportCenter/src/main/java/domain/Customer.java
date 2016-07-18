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
public class Customer extends Actor {

	// Constructors -----------------------------------------------------------

	public Customer() {
		super();
	}

	// Attributes -------------------------------------------------------------
	// Relationships ----------------------------------------------------------

	private Collection<Reservation> reservations;
	private Collection<OpenMatch> ownOpenMatches;
	private Collection<OpenMatch> pOpenMatches;

	@Valid
	@OneToMany(mappedBy = "customer")
	public Collection<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(Collection<Reservation> reservations) {
		this.reservations = reservations;
	}

	@Valid
	@OneToMany(mappedBy = "owner")
	public Collection<OpenMatch> getOwnOpenMatches() {
		return ownOpenMatches;
	}

	public void setOwnOpenMatches(Collection<OpenMatch> ownOpenMatches) {
		this.ownOpenMatches = ownOpenMatches;
	}

	@Valid
	@ManyToMany(mappedBy = "players")
	public Collection<OpenMatch> getpOpenMatches() {
		return pOpenMatches;
	}

	public void setpOpenMatches(Collection<OpenMatch> pOpenMatches) {
		this.pOpenMatches = pOpenMatches;
	}

}