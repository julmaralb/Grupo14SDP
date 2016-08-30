package services;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Customer;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CustomerServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private CustomerService customerService;

	// Other services ---------------------------------------------------------

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-SportCentre - 11.1 
	 * A user who is not authenticated  must be able to: 
	 * Register to the system as a customer. 
	 * Positive test case: A new customer is created.
	 * 
	 */

	@Test
	public void testCreateCustomer1() {
		Customer customer;
		
		authenticate(null);
		Assert.isTrue(customerService.findAll().size()==2);	
		customer = customerService.create();
		
		customer.setAddress("address");
		customer.setEmail("email@gmail.com");
		customer.setName("Name");
		customer.setSurname("Surname");
		customer.setPhone("954362514");
		customer.getUserAccount().setUsername("nuevocustomer");
		customer.getUserAccount().setPassword("nuevocustomer");
		
		customerService.save(customer);
		
		Assert.isTrue(customerService.findAll().size()==3);	
	}
	
	/**
	 * Acme-SportCentre - 11.1 
	 * A user who is not authenticated  must be able to: 
	 * Register to the system as a customer. 
	 * Negative test case: A new customer is not created
	 * because the password is null.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCustomer2() {
		Customer customer;
		
		Assert.isTrue(customerService.findAll().size()==2);	
		customer = customerService.create();
		
		customer.setAddress("address");
		customer.setEmail("email@gmail.com");
		customer.setName("Name");
		customer.setSurname("Surname");
		customer.setPhone("954362514");
		customer.getUserAccount().setUsername("nuevocustomer");
		customer.getUserAccount().setPassword(null);
		
		customerService.save(customer);
		
		Assert.isTrue(customerService.findAll().size()==3);	
	}
	
	/**
	 * Acme-SportCentre - 11.1 
	 * A user who is not authenticated  must be able to: 
	 * Register to the system as a customer. 
	 * Negative test case: A new customer is not created
	 * because the user is already authenticated.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateCustomer3() {
		Customer customer;
		
		authenticate("supervisor1");
		
		Assert.isTrue(customerService.findAll().size()==2);	
		customer = customerService.create();
		
		customer.setAddress("address");
		customer.setEmail("email@gmail.com");
		customer.setName("Name");
		customer.setSurname("Surname");
		customer.setPhone("954362514");
		customer.getUserAccount().setUsername("nuevocustomer");
		customer.getUserAccount().setPassword(null);
		
		customerService.save(customer);
		
		Assert.isTrue(customerService.findAll().size()==2);	
		
		authenticate(null);
	}

}
