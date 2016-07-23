package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
@Access(AccessType.PROPERTY)
public class Description {

	// Constructors -----------------------------------------------------------

	public Description() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String title;
	private String text;
	private Collection<String> infoLinks;
	private Collection<String> tags;
	private String code;

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
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@ElementCollection
	@Column(name = "infoLinks")
	public Collection<String> getInfoLinks() {
		return infoLinks;
	}

	public void setInfoLinks(Collection<String> infoLinks) {
		this.infoLinks = infoLinks;
	}

	@ElementCollection
	@Column(name = "tags")
	public Collection<String> getTags() {
		return tags;
	}

	public void setTags(Collection<String> tags) {
		this.tags = tags;
	}

	@Pattern(regexp = "^([a-z]{2})$")
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	// Relationships ----------------------------------------------------------

}
