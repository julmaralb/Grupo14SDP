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
import domain.LanguageExchange;
import domain.Sponsorship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SponsorshipServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private SponsorshipService sponsorshipService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private LanguageExchangeService languageExchangeService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - B-Level - 3.1 
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, 
	 * editing, and removing them.
	 * 
	 * Positive test case: A new sponsorship is created.
	 * 
	 */

	@Test
	public void testSponsorshipCreate1() {
		Collection<Sponsorship> allBefore;
		Collection<Sponsorship> allAfter;
		Sponsorship sponsorship;
		LanguageExchange languageExchange;
		
		allBefore = sponsorshipService.findAll();

		authenticate("agent1");
		
		sponsorship = sponsorshipService.create();
		languageExchange = languageExchangeService.findOne(50);
		sponsorship.setName("Name");
		sponsorship.setLanguageExchange(languageExchange);
		
		sponsorshipService.save(sponsorship);
		allAfter= sponsorshipService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - B-Level - 3.1 
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, 
	 * editing, and removing them.
	 * 
	 * Negative test case: A new sponsorship is not created because
	 * the actor was not an agent.
	 * 
	 */

	@Test(expected = AssertionError.class)
	public void testSponsorshipCreate2() {
		Collection<Sponsorship> allBefore;
		Collection<Sponsorship> allAfter;
		Sponsorship sponsorship;
		LanguageExchange languageExchange;
		
		allBefore = sponsorshipService.findAll();

		authenticate("polyglot1");
		
		sponsorship = sponsorshipService.create();
		languageExchange = languageExchangeService.findOne(50);
		sponsorship.setName("Name");
		sponsorship.setLanguageExchange(languageExchange);
		
		sponsorshipService.save(sponsorship);
		allAfter= sponsorshipService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - B-Level - 3.1 
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, 
	 * editing, and removing them.
	 * 
	 * Negative test case: A new sponsorship is not delete because
	 * the actor was not the owner.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testSponsorshipDelete() {
		Collection<Sponsorship> allBefore;
		Collection<Sponsorship> allAfter;
		Sponsorship sponsorship;
		
		allBefore = sponsorshipService.findAll();

		authenticate("agent1");
		
		sponsorship = sponsorshipService.findOne(61);
		
		sponsorshipService.delete(sponsorship);
		allAfter= sponsorshipService.findAll();
		
		Assert.isTrue(allAfter.size() +1 == allBefore.size());

		authenticate(null);
	}
}