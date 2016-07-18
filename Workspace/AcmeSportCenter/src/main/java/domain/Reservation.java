package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Reservation extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Reservation() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Date day;
	private Date start;
	private Date end;
	private CreditCard creditCard;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	public Date getStart() {
		return start;
	}

	public void setStart(Date start) {
		this.start = start;
	}

	@NotNull
	@DateTimeFormat(pattern = "HH:mm")
	@Temporal(TemporalType.TIME)
	public Date getEnd() {
		return end;
	}

	public void setEnd(Date end) {
		this.end = end;
	}

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	// Relationships ----------------------------------------------------------

	private Court court;
	private Customer customer;

	@Valid
	@ManyToOne(optional = false)
	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
