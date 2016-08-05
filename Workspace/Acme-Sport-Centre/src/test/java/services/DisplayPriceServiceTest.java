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
import domain.DisplayPrice;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class DisplayPriceServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private DisplayPriceService displayPriceService;

	// Other services ---------------------------------------------------------

	// Tests ------------------------------------------------------------------

	/**
	 * Acme-SportCentre - 14.5 
	 * A user who is authenticated as an administrator
	 * must be able to: Change the price that the system charges when booking a
	 * court and the taxes that are applied.
	 * 
	 * Positive test case: There is only one display price.
	 * 
	 */

	@Test
	public void testFindDisplayPrice() {
		Collection<DisplayPrice> result;

		authenticate("admin");

		result = displayPriceService.findAll();

		Assert.isTrue(result.size() == 1);

		authenticate(null);
	}
	
	
	/**
	 * Acme-SportCentre - 14.5 
	 * A user who is authenticated as an administrator
	 * must be able to: Change the price that the system charges when booking a
	 * court and the taxes that are applied.
	 * 
	 * Negative test case: The display price is not saved because it wasn´t an
	 * admin.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testSaveDisplayPrice() {
		DisplayPrice result;

		authenticate("customer1");

		result = displayPriceService.findOne(54);
		result.setCourtPrice(15.0);
		displayPriceService.save(result);

		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 14.5 
	 * A user who is authenticated as an administrator
	 * must be able to: Change the price that the system charges when booking a
	 * court and the taxes that are applied.
	 * 
	 * Negative test case: The display price is not saved because it wasn´t an
	 * admin.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testSaveDisplayPrice2() {
		DisplayPrice result;

		authenticate("supervisor1");

		result = displayPriceService.findOne(54);
		result.setTax(22.0);
		displayPriceService.save(result);

		authenticate(null);
	}
}