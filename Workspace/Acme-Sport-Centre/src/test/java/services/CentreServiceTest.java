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
	 * Acme-Sport-Centre - 14.3
	 * A user who is authenticated as an administrator
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
	 * Acme-Sport-Centre - 14.3 
	 * A user who is authenticated as an administrator
	 * must be able to: Manage centres. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Negative test case: The centre is not created because it wasn´t an
	 * admin.
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
	 * Acme-Sport-Centre - 14.3 
	 * A user who is authenticated as an administrator
	 * must be able to: Manage centres. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Negative test case: The centre is not deleted because it wasn´t an
	 * admin.
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCentreDelete1() {
		Centre centre;

		authenticate("supervisor1");

		centre = centreService.findOne(55);

		centreService.delete(centre);

		authenticate(null);
	}
	
	/**
	 * Acme-Sport-Centre - 11.2
	 * A user who is not authenticated
	 * must be able to: Navigate through the catalogue 
	 * of centres that are registered in the system. 
	 * 
	 * Positive test case: All centres are listed and the user
	 * is not authenticated.
	 * 
	 */

	@Test
	public void testCentresList1() {
		Collection<Centre> result;

		result = centreService.findAll();

		Assert.isTrue(result.size() == 2);

	}
	
	/**
	 * Acme-Sport-Centre - 11.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage centres. That includes creating, listing ,
	 * modifying or deleting them.
	 * 
	 * Negative test case: A new centre is created and it doesn't
	 * appear in the listing.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCentresList2() {
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
		Assert.isTrue(result.size() == 2);

		authenticate(null);
	}

}
