package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class Renting extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Renting() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String code;
	private CreditCard creditCard;
	private double totalPrice;
	private Date start;
	private Date end;

	@Pattern(regexp = "^\\d{6}-\\w{4}$")
	@NotNull
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Valid
	@NotNull
	public CreditCard getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(CreditCard creditCard) {
		this.creditCard = creditCard;
	}

	@Min(0)
	@NotNull
	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
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

	// Relationships ----------------------------------------------------------

	private SportEquipment sportEquipment;
	private Customer customer;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public SportEquipment getSportEquipment() {
		return sportEquipment;
	}

	public void setSportEquipment(SportEquipment sportEquipment) {
		this.sportEquipment = sportEquipment;
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