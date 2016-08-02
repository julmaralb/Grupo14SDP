package domain;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Access(AccessType.PROPERTY)
public class DisplayPrice extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public DisplayPrice() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Double courtPrice;
	private Double tax;

	@NotNull
	@Min(0)
	public Double getCourtPrice() {
		return courtPrice;
	}

	public void setCourtPrice(Double courtPrice) {
		this.courtPrice = courtPrice;
	}

	@NotNull
	@Min(0)
	public Double getTax() {
		return tax;
	}

	public void setTax(Double tax) {
		this.tax = tax;
	}

	// Relationships ----------------------------------------------------------

}