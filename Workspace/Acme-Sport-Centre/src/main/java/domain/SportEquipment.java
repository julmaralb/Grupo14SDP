package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class SportEquipment extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public SportEquipment() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String SKU;
	private String name;
	private String picture;
	private Double price;

	@Column(unique = true)
	@Pattern(regexp = "^\\w{2}-\\w{4}$")
	@NotNull
	public String getSKU() {
		return SKU;
	}

	public void setSKU(String sKU) {
		SKU = sKU;
	}

	@NotNull
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	@Min(0)
	@NotNull
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Renting> rentings;
	private Court court;

	@Valid
	@OneToMany(mappedBy = "sportEquipment")
	public Collection<Renting> getRentings() {
		return rentings;
	}

	public void setRentings(Collection<Renting> rentings) {
		this.rentings = rentings;
	}

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Court getCourt() {
		return court;
	}

	public void setCourt(Court court) {
		this.court = court;
	}
}