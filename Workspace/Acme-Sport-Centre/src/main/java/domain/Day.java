package domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Access(AccessType.PROPERTY)
@Table(indexes = { @Index(columnList = "day") })
public class Day extends DomainEntity {

	// Constructors -----------------------------------------------------------

	public Day() {
		super();
	}

	// Attributes -------------------------------------------------------------

	private Date day;

	@NotNull
	@Temporal(TemporalType.DATE)
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	public Date getDay() {
		return day;
	}

	public void setDay(Date day) {
		this.day = day;
	}

	// Relationships ----------------------------------------------------------

	private Collection<HourRange> hourRanges;
	private Court court;

	@Valid
	@OneToMany(cascade = CascadeType.ALL)
	public Collection<HourRange> getHourRanges() {
		return hourRanges;
	}

	public void setHourRanges(Collection<HourRange> hourRanges) {
		this.hourRanges = hourRanges;
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
