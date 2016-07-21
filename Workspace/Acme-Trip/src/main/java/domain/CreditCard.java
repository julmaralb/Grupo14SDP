package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.CreditCardNumber;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

@Entity
@Access(AccessType.PROPERTY)
public class CreditCard extends DomainEntity {

	// Constructors -------------------------------------------------------

	public CreditCard() {
		super();
	}

	// Attributes ---------------------------------------------------------

	private String holderName;
	private String brandName;
	private String number;
	private int expMonth;
	private int expYear;
	private int CVV;

	@CreditCardNumber
	@NotNull
	@NotBlank
	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	@NotBlank
	@NotNull
	public String getHolderName() {
		return holderName;
	}

	public void setHolderName(String holderName) {
		this.holderName = holderName;
	}

	@NotBlank
	@NotNull
	public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	@Range(min = 1, max = 12)
	@NotNull
	public int getExpMonth() {
		return expMonth;
	}

	public void setExpMonth(int expMonth) {
		this.expMonth = expMonth;
	}

	@Range(min = 2000)
	@NotNull
	public int getExpYear() {
		return expYear;
	}

	public void setExpYear(int expYear) {
		this.expYear = expYear;
	}

	@Range(min = 100, max = 999)
	@NotNull
	public int getCVV() {
		return CVV;
	}

	public void setCVV(int cvv) {
		this.CVV = cvv;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Campaign> campaigns;
	private Collection<ChargeRecord> chargeRecords;
	private Manager manager;

	@Valid
	@OneToMany(mappedBy = "creditCard")
	public Collection<Campaign> getCampaigns() {
		return campaigns;
	}

	public void setCampaigns(Collection<Campaign> campaigns) {
		this.campaigns = campaigns;
	}

	@Valid
	@OneToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE,
			CascadeType.DETACH, CascadeType.REFRESH }, mappedBy = "creditCard")
	public Collection<ChargeRecord> getChargeRecords() {
		return chargeRecords;
	}

	public void setChargeRecords(Collection<ChargeRecord> chargeRecords) {
		this.chargeRecords = chargeRecords;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Manager getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
}