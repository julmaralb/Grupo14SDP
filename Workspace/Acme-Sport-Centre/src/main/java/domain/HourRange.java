package domain;

import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
public class HourRange extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public HourRange() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Date start;
	private Date end;
	private boolean available;

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

	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	// Relationships ----------------------------------------------------------

}
