package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.URL;

@Entity
@Access(AccessType.PROPERTY)
public class Bibliography extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Bibliography() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String title;
	private Collection<String> authors;
	private String locator;
	private String URL;
	private Integer counter;

	@NotNull
	@NotBlank
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	@ElementCollection
	@Column(name = "authors")
	public Collection<String> getAuthors() {
		return authors;
	}

	public void setAuthors(Collection<String> authors) {
		this.authors = authors;
	}

	@NotNull
	@NotBlank
	public String getLocator() {
		return locator;
	}

	public void setLocator(String locator) {
		this.locator = locator;
	}

	@URL
	public String getURL() {
		return URL;
	}

	public void setURL(String uRL) {
		URL = uRL;
	}

	@Min(0)
	public Integer getCounter() {
		return counter;
	}

	public void setCounter(Integer counter) {
		this.counter = counter;
	}

	// Relationships ----------------------------------------------------------

	private Collection<Syllabus> syllabi;

	@Valid
	@ManyToMany(mappedBy = "bibliographies")
	public Collection<Syllabus> getSyllabi() {
		return syllabi;
	}

	public void setSyllabi(Collection<Syllabus> syllabi) {
		this.syllabi = syllabi;
	}
}