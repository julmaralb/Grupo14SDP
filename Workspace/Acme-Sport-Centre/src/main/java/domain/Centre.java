package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Centre extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Centre() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private String address;
	private String phone;
	private String picture;

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
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@URL
	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Court> courts;

	@Valid
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "centre")
	public Collection<Court> getCourts() {
		return courts;
	}

	public void setCourts(Collection<Court> courts) {
		this.courts = courts;
	}

}
