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

import security.Authority;
import security.UserAccount;
import utilities.AbstractTest;
import domain.Supervisor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SupervisorServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private SupervisorService supervisorService;

	// Other services ---------------------------------------------------------

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-SportCentre - 14.2 
	 * A user who is authenticated as an administrator
	 * must be able to: Register supervisors
	 * 
	 * Positive test case: A supervisor is created.
	 * 
	 */

	@Test
	public void testCreateSupervisor() {
		Supervisor result;
		UserAccount userAccount;
		Collection<Supervisor> supervisorsBefore;
		Collection<Supervisor> supervisorsAfter;
		int size;

		authenticate("admin");

		result = supervisorService.create();
		userAccount = supervisorService.createSupervisorAccount();
		supervisorsBefore = supervisorService.findAll();
		size = supervisorsBefore.size();
		
		result.setAddress("address");
		result.setEmail("email@hotmail.com");
		result.setName("name");
		result.setSurname("surname");
		result.setPhone("954362514");
		result.setUserAccount(userAccount);
		supervisorService.save(result);

		supervisorsAfter = supervisorService.findAll();
		Assert.isTrue(supervisorsAfter.size() == size+1);

		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 14.2 
	 * A user who is authenticated as an administrator
	 * must be able to: Register supervisors
	 * 
	 * Negative test case: A new supervisor is not created because it wasn´t an
	 * admin.
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateSupervisor2() {
		Supervisor result;
		UserAccount userAccount;
		Collection<Supervisor> supervisorsBefore;
		Collection<Supervisor> supervisorsAfter;
		int size;

		authenticate("customer1");

		result = supervisorService.create();
		userAccount = supervisorService.createSupervisorAccount();
		supervisorsBefore = supervisorService.findAll();
		size = supervisorsBefore.size();
		
		result.setAddress("address");
		result.setEmail("email@hotmail.com");
		result.setName("name");
		result.setSurname("surname");
		result.setPhone("954362514");
		result.setUserAccount(userAccount);
		supervisorService.save(result);

		supervisorsAfter = supervisorService.findAll();
		Assert.isTrue(supervisorsAfter.size() == size+1);

		authenticate(null);
	}
	
	/**
	 * Acme-SportCentre - 14.2 
	 * A user who is authenticated as an administrator
	 * must be able to: Register supervisors
	 * 
	 * Negative test case: A new supervisor doesn't have SUPERVISOR authority.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateSupervisor3() {
		Supervisor result;
		UserAccount userAccount;
		Collection<Supervisor> supervisorsBefore;
		Collection<Supervisor> supervisorsAfter;
		int size;

		authenticate("admin");

		result = supervisorService.create();
		userAccount = supervisorService.createSupervisorAccount();
		
		for(Authority a : userAccount.getAuthorities()){
			Assert.isTrue(a.getAuthority().equals("CUSTOMER"));
		}
		supervisorsBefore = supervisorService.findAll();
		size = supervisorsBefore.size();
		
		result.setAddress("address");
		result.setEmail("email@hotmail.com");
		result.setName("name");
		result.setSurname("surname");
		result.setPhone("954362514");
		result.setUserAccount(userAccount);
		supervisorService.save(result);

		supervisorsAfter = supervisorService.findAll();
		Assert.isTrue(supervisorsAfter.size() == size+1);

		authenticate(null);
	}

}
