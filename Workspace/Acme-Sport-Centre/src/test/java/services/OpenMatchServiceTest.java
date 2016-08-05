package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.OpenMatch;
import domain.Reservation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class OpenMatchServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private OpenMatchService openMatchService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ReservationService reservationService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Sport-Centre - 12.3
	 * A user who is authenticated as a customer
	 * must be able to: Manage his or her open matches, which involves listing, 
	 * registering, modifying, and deleting them. 
	 * 
	 * Positive test case: A new open match is created.
	 * 
	 */

	@Test
	public void testOpenMatchCreate1() {
		Collection<OpenMatch> openMatchesBefore;
		Collection<OpenMatch> openMatchesAfter;
		OpenMatch newOpenMatch;
		Reservation reservation;

		authenticate("customer1");

		openMatchesBefore = openMatchService.findAll();
		Assert.isTrue(openMatchesBefore.size()==2);

		newOpenMatch = openMatchService.create();
		reservation = reservationService.findOne(46);
		newOpenMatch.setTitle("Nuevo report");
		newOpenMatch.setDescription("descripcion del report");
		newOpenMatch.setMoment(new Date());
		newOpenMatch.setNumPlayers(0);
		newOpenMatch.setMaxPlayers(10);
		newOpenMatch.setOwner(customerService.findByPrincipal());
		newOpenMatch.setReservation(reservation);

		openMatchService.save(newOpenMatch);

		openMatchesAfter = openMatchService.findAll();
		
		Assert.isTrue(openMatchesAfter.size() == openMatchesBefore.size() +1);

		authenticate(null);
	}
	
	/**
	 * Acme-Sport-Centre - 12.3
	 * A user who is authenticated as a customer
	 * must be able to: Manage his or her open matches, which involves listing, 
	 * registering, modifying, and deleting them. 
	 * 
	 * Negative test case: A new open match is not created because the reservation
	 * selected was already used in another open match.
	 * 
	 */

	@Test(expected = org.springframework.dao.DataIntegrityViolationException.class)
	public void testOpenMatchCreate2() {
		Collection<OpenMatch> openMatchesBefore;
		Collection<OpenMatch> openMatchesAfter;
		OpenMatch newOpenMatch;
		Reservation reservation;

		authenticate("customer1");

		openMatchesBefore = openMatchService.findAll();
		Assert.isTrue(openMatchesBefore.size()==2);

		newOpenMatch = openMatchService.create();
		reservation = reservationService.findOne(44);
		newOpenMatch.setTitle("Nuevo report");
		newOpenMatch.setDescription("descripcion del report");
		newOpenMatch.setMoment(new Date());
		newOpenMatch.setNumPlayers(0);
		newOpenMatch.setMaxPlayers(10);
		newOpenMatch.setOwner(customerService.findByPrincipal());
		newOpenMatch.setReservation(reservation);

		openMatchService.save(newOpenMatch);

		openMatchesAfter = openMatchService.findAll();
		
		Assert.isTrue(openMatchesAfter.size() == openMatchesBefore.size() +1);

		authenticate(null);
	}
	
	/**
	 * Acme-Sport-Centre - 12.4
	 * A user who is authenticated as a customer
	 * must be able to: List all open matches and join an existing one. 
	 * 
	 * Negative test case: Did not join the open match because it was full.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testOpenMatchJoin() {
		OpenMatch openMatch;
		
		authenticate("customer1");
		
		openMatch = openMatchService.findOne(48);
		openMatch.setNumPlayers(10);
		
		openMatchService.join(openMatch);

		authenticate(null);
	}

}
