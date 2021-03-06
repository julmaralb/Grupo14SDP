package domain;

import java.util.Collection;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "name"), @Index(columnList = "isSystem"), @Index(columnList = "exchangeName") })
public class Folder extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Folder() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private String name;
	private boolean isSystem;
	private String exchangeName;

	@NotNull
	@NotBlank
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@NotNull
	public boolean getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(boolean isSystem) {
		this.isSystem = isSystem;
	}
	
	public String getExchangeName() {
		return exchangeName;
	}

	public void setExchangeName(String exchangeName) {
		this.exchangeName = exchangeName;
	}
	
	// Relationships ----------------------------------------------------------

	private Actor actor;
	private Collection<Message> messages;

	@Valid
	@NotNull
	@ManyToOne(optional = false)
	public Actor getActor() {
		return actor;
	}

	public void setActor(Actor actor) {
		this.actor = actor;
	}

	@Valid
	@OneToMany(mappedBy = "folder")
	public Collection<Message> getMessages() {
		return messages;
	}

	public void setMessages(Collection<Message> messages) {
		this.messages = messages;
	}
}