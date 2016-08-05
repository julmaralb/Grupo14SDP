package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Court;
import domain.CreditCard;
import domain.Day;
import domain.HourRange;
import domain.Reservation;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ReservationServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private ReservationService reservationService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private CourtService courtService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private DayService dayService;
	
	@Autowired
	private HourRangeService hourRangeService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Positive test case: A new Reservation is created.
	 * 
	 */

	@Test
	public void testCreateReservation() {
		Collection<Reservation> reservationsBefore;
		Collection<Reservation> reservationsAfter;
		Reservation newReservation;
		Court court;
		CreditCard cc;
		Day day;
		HourRange hr;
		
		authenticate("customer1");

		newReservation = reservationService.create();
		reservationsBefore = reservationService.findAll();
		court = courtService.findOne(6);
		day = dayService.findOne(38);
		hr = hourRangeService.findOne(15);
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		newReservation.setCourt(court);
		newReservation.setCreditCard(cc);
		newReservation.setCustomer(customerService.findByPrincipal());
		newReservation.setDay(day.getDay());
		newReservation.setStart(hr.getStart());
		newReservation.setEnd(hr.getEnd());
		newReservation.setTotalPrice(15);
		
		reservationService.save(newReservation, hr.getId());
		reservationsAfter = reservationService.findAll();
		
		Assert.isTrue(reservationsAfter.size() == reservationsBefore.size()+1);

		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Positive test case: A new Reservation is created.
	 * 
	 */

	@Test
	public void testCreateReservation2() {
		Collection<Reservation> reservationsBefore;
		Collection<Reservation> reservationsAfter;
		Reservation newReservation;
		CreditCard cc;
		
		
		authenticate("customer1");

		newReservation = reservationService.createReservation(6, 15, 38);
		reservationsBefore = reservationService.findAll();
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		
		newReservation.setCreditCard(cc);
		newReservation.setTotalPrice(15);
		
		reservationService.save(newReservation, 15);
		reservationsAfter = reservationService.findAll();
		
		Assert.isTrue(reservationsAfter.size() == reservationsBefore.size()+1);

		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Negative test case: A new Reservation is not created.
	 * because the hour range is not available
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateReservation3() {
		Collection<Reservation> reservationsBefore;
		Collection<Reservation> reservationsAfter;
		Reservation newReservation;
		CreditCard cc;
		
		
		authenticate("customer1");

		newReservation = reservationService.createReservation(6, 14, 38);
		reservationsBefore = reservationService.findAll();
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		
		newReservation.setCreditCard(cc);
		newReservation.setTotalPrice(15);
		
		reservationService.save(newReservation, 14);
		reservationsAfter = reservationService.findAll();
		
		Assert.isTrue(reservationsAfter.size() == reservationsBefore.size()+1);

		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Positive test case: A new Reservation is not created.
	 * because it was not a customer.
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateReservation4() {
		Collection<Reservation> reservationsBefore;
		Collection<Reservation> reservationsAfter;
		Reservation newReservation;
		CreditCard cc;
		
		
		authenticate("admin");

		newReservation = reservationService.createReservation(6, 15, 38);
		reservationsBefore = reservationService.findAll();
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		
		newReservation.setCreditCard(cc);
		newReservation.setTotalPrice(15);
		
		reservationService.save(newReservation, 15);
		reservationsAfter = reservationService.findAll();
		
		Assert.isTrue(reservationsAfter.size() == reservationsBefore.size()+1);

		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Positive test case: A new Reservation is not created.
	 * because it was not a customer.
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateReservation5() {
		Collection<Reservation> reservationsBefore;
		Collection<Reservation> reservationsAfter;
		Reservation newReservation;
		CreditCard cc;
		
		
		authenticate("supervisor1");

		newReservation = reservationService.createReservation(6, 15, 38);
		reservationsBefore = reservationService.findAll();
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		
		newReservation.setCreditCard(cc);
		newReservation.setTotalPrice(15);
		
		reservationService.save(newReservation, 15);
		reservationsAfter = reservationService.findAll();
		
		Assert.isTrue(reservationsAfter.size() == reservationsBefore.size()+1);

		authenticate(null);
	}
	
	
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Positive test case: The credit card is valid.
	 * 
	 */

	@Test
	public void testValidCreditCard() {
		CreditCard cc;
			
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		Assert.isTrue(reservationService.validExpCreditCard(cc));
	}
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Positive test case: The credit card is not valid.
	 * because the year has expired.
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testValidCreditCard2() {
		CreditCard cc;
			
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2015);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		Assert.isTrue(reservationService.validExpCreditCard(cc));
	}
	
	/**
	 * Acme-SportCentre - 12.2 
	 * A user who is authenticated as a customer
	 * must be able to: . Book a court and create 
	 * a reservation for it.
	 * 
	 * Positive test case: The credit card is not valid.
	 * because the month has expired.
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testValidCreditCard3() {
		CreditCard cc;
			
		cc = new CreditCard();
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(6);
		cc.setExpYear(2016);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		Assert.isTrue(reservationService.validExpCreditCard(cc));
	}
	
	/**
	 * Acme-SportCentre - 12.3 
	 * A user who is authenticated as a customer
	 * must be able to: . List his reservations. 
	 * 
	 * Positive test case: The reservations listed are
	 * the principal's reservations.
	 * 
	 */

	@Test
	public void testListReservations() {
		Collection<Reservation> reservations;
		boolean res = true;
	
		authenticate("customer1");

		reservations = reservationService.findByCustomerId();
		
		for(Reservation r: reservations){
			if(!r.getCustomer().equals(customerService.findByPrincipal())){
				res = false;
				break;
			}
		}
		Assert.isTrue(res);
		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 12.3 
	 * A user who is authenticated as a customer
	 * must be able to: . List his reservations. 
	 * 
	 * Positive test case: The reservations listed are
	 * the principal's not used reservations.
	 * 
	 */

	@Test
	public void testListReservations2() {
		Collection<Reservation> reservations;
		boolean res = true;
	
		authenticate("customer1");

		reservations = reservationService.findAllNotUsedByCustomerId();
		
		for(Reservation r: reservations){
			if(!r.getCustomer().equals(customerService.findByPrincipal())){
				res = false;
				break;
			}
		}
		Assert.isTrue(res);
		Assert.isTrue(reservations.size()==1);
		authenticate(null);
	}

}
