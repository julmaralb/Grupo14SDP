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
import domain.Centre;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CentreServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private CentreService centreService;

	// Other services ---------------------------------------------------------

	// Tests ------------------------------------------------------------------

	/**
	 * Acme-SportCentre -3.1 A user who is authenticated as an administrator
	 * must be able to: Manage centres. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Positive test case: A new centre is created.
	 * 
	 */

	@Test
	public void testCentreCreate1() {
		Collection<Centre> result;
		Centre newCentre;

		authenticate("admin");

		result = centreService.findAll();
		Assert.isTrue(result.size() == 2);

		newCentre = centreService.create();
		newCentre.setName("Nuevo centro");
		newCentre.setAddress("centreAddress");
		newCentre.setPhone("954362514");
		newCentre.setPicture("http://picture.com");
		centreService.save(newCentre);

		result = centreService.findAll();
		Assert.isTrue(result.size() == 3);

		authenticate(null);
	}

	/**
	 * Acme-SportCentre - 3.1 A user who is authenticated as an administrator
	 * must be able to: Manage centres. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Positive test case: A new centre is created.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCentreCreate2() {
		Collection<Centre> result;
		Centre newCentre;

		authenticate("customer1");

		result = centreService.findAll();
		Assert.isTrue(result.size() == 2);

		newCentre = centreService.create();
		newCentre.setName("Nuevo centro");
		newCentre.setAddress("centreAddress");
		newCentre.setPhone("954362514");
		newCentre.setPicture("http://picture.com");
		centreService.save(newCentre);

		result = centreService.findAll();
		Assert.isTrue(result.size() == 3);

		authenticate(null);
	}

	/**
	 * Acme-SportCentre - 3.1 A user who is authenticated as an administrator
	 * must be able to: Manage centres. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Positive test case: A new centre is created.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCentreDelete1() {
		Centre centre;

		authenticate("supervisor1");

		centre = centreService.findOne(5);

		centreService.delete(centre);

		authenticate(null);
	}

}
