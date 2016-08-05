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

import domain.Centre;
import domain.Court;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CourtServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private CourtService courtService;

	// Other services ---------------------------------------------------------

	@Autowired
	private CentreService centreService;

	// Tests ------------------------------------------------------------------

	/**
	 * Acme-SportCentre - 14.4
	 * A user who is authenticated as an administrator
	 * must be able to: Manage courts. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Positive test case: A new court is created.
	 * 
	 */

	@Test
	public void testCourtCreate1() {
		Collection<Court> result;
		Court newCourt;
		Centre centre;

		authenticate("admin");

		result = courtService.findAll();
		centre = centreService.findOne(8);
		Assert.isTrue(result.size() == 3);

		newCourt = courtService.create();
		newCourt.setName("Nueva pista");
		newCourt.setCategory("FOOTBALL");
		newCourt.setCentre(centre);
		courtService.save(newCourt);

		result = courtService.findAll();
		Assert.isTrue(result.size() == 4);

		authenticate(null);
	}

	/**
	 Acme-SportCentre - 14.4
	 * A user who is authenticated as an administrator
	 * must be able to: Manage courts. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Negative test case: A new court is not created because it wasn´t an
	 * admin.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCourtCreate2() {
		Collection<Court> result;
		Court newCourt;
		Centre centre;

		authenticate("customer1");

		result = courtService.findAll();
		centre = centreService.findOne(8);
		Assert.isTrue(result.size() == 3);

		newCourt = courtService.create();
		newCourt.setName("Nueva pista");
		newCourt.setCategory("FOOTBALL");
		newCourt.setCentre(centre);
		courtService.save(newCourt);

		result = courtService.findAll();
		Assert.isTrue(result.size() == 4);

		authenticate(null);
	}

	/**
	 * Acme-SportCentre - 14.4 
	 * A user who is authenticated as an administrator
	 * must be able to: Manage courts. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Negative test case: A court is not deleted because it wasn´t an admin.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCourtDelete1() {
		Court court;

		authenticate("supervisor1");

		court = courtService.findOne(9);

		courtService.delete(court);

		authenticate(null);
	}
}