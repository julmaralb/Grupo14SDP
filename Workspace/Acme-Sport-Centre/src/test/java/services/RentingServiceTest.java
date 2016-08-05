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
import domain.CreditCard;
import domain.Renting;
import domain.Reservation;
import domain.SportEquipment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class RentingServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private RentingService rentingService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private SportEquipmentService sportEquipmentService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Sport-Centre - 12.5
	 * A user who is authenticated as a supervisor
	 * must be able to: When a customer has a reservation 
	 * of a court he must be able to list the equipment that 
	 * is available for that court and rent it.
	 * 
	 * Positive test case: The listed equipment belongs to the
	 * court of the principal's reservation.
	 * 
	 */

	@Test
	public void testEquipmentList() {
		Collection<SportEquipment> sportEquipments;
		Reservation reservation;
		boolean res = true;

		authenticate("customer1");

		reservation = reservationService.findOne(44);
		Assert.isTrue(reservation.getCustomer().equals(customerService.findByPrincipal()));
		sportEquipments= reservation.getCourt().getSportEquipments();
		
		for(SportEquipment se: sportEquipments){
			if(!se.getCourt().equals(reservation.getCourt())){
				res= false;
				break;
			}
		}
		Assert.isTrue(res);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Sport-Centre - 12.5
	 * A user who is authenticated as a supervisor
	 * must be able to: When a customer has a reservation 
	 * of a court he must be able to list the equipment that 
	 * is available for that court and rent it.
	 * 
	 * Positive test case: The renting is not created because
	 * the customer is trying to rent equipment from another
	 * court.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testRentingCreate1() {
		SportEquipment sportEquipment;
		Renting renting;
		CreditCard cc;
		Reservation  reservation;
		
		authenticate("customer2");

		sportEquipment = sportEquipmentService.findOne(53);
		renting = rentingService.create();
		cc = new CreditCard();
		reservation = reservationService.findOne(45);
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		renting.setCreditCard(cc);
		renting.setDay(reservation.getDay());
		renting.setStart(reservation.getStart());
		renting.setEnd(reservation.getEnd());
		renting.setSportEquipment(sportEquipment);
		renting.setTotalPrice(2.5);
		
		rentingService.save(renting);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Sport-Centre - 12.5
	 * A user who is authenticated as a supervisor
	 * must be able to: When a customer has a reservation 
	 * of a court he must be able to list the equipment that 
	 * is available for that court and rent it.
	 * 
	 * Positive test case: The renting is not created because
	 * it was not a customer.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testRentingCreate2() {
		SportEquipment sportEquipment;
		Renting renting;
		CreditCard cc;
		Reservation  reservation;
		
		authenticate("supervisor1");

		sportEquipment = sportEquipmentService.findOne(50);
		renting = rentingService.create();
		cc = new CreditCard();
		reservation = reservationService.findOne(45);
		
		cc.setBrandName("Visa");
		cc.setCVV(123);
		cc.setExpMonth(12);
		cc.setExpYear(2017);
		cc.setHolderName("Pepito");
		cc.setNumber("4716136821320487");
		
		renting.setCreditCard(cc);
		renting.setDay(reservation.getDay());
		renting.setStart(reservation.getStart());
		renting.setEnd(reservation.getEnd());
		renting.setSportEquipment(sportEquipment);
		renting.setTotalPrice(2.5);
		
		rentingService.save(renting);
		
		authenticate(null);
	}

}