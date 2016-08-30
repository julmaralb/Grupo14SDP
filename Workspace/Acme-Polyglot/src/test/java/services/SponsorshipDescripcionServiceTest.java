package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.Sponsorship;
import domain.SponsorshipDescription;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SponsorshipDescripcionServiceTest  extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private SponsorshipDescriptionService sponsorshipDescriptionService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private SponsorshipService sponsorshipService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - B-Level - 3.1
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, editing,
	 * and removing them.
	 * 
	 * Positive test case: A new sponsorship description is created.
	 * 
	 */

	@Test
	public void testsponsorshipDescriptionCreate1() {
		Collection<SponsorshipDescription> allBefore;
		Collection<SponsorshipDescription> allAfter;
		SponsorshipDescription sponsorshipDescription;
		Sponsorship sponsorship;
		
		allBefore = sponsorshipDescriptionService.findAll();

		authenticate("agent1");
		
		sponsorshipDescription = sponsorshipDescriptionService.create();
		sponsorship = sponsorshipService.findOne(19);
		
		sponsorshipDescription.setSponsorship(sponsorship);
		sponsorshipDescription.setCode("fa");
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		sponsorshipDescription.setInfoLinks(links);
		sponsorshipDescription.setTags(tags);
		sponsorshipDescription.setText("text");
		sponsorshipDescription.setTitle("title");
		
		sponsorshipDescriptionService.save(sponsorshipDescription);
		allAfter= sponsorshipDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - B-Level - 3.1
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, editing,
	 * and removing them.
	 * 
	 * Negative test case: A new sponsorship exchange description is not created because agent1
	 * was not the owner of the sponsorship selected.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testsponsorshipDescriptionCreate2() {
		Collection<SponsorshipDescription> allBefore;
		Collection<SponsorshipDescription> allAfter;
		SponsorshipDescription sponsorshipDescription;
		Sponsorship sponsorship;
		
		allBefore = sponsorshipDescriptionService.findAll();

		authenticate("agent1");
		
		sponsorshipDescription = sponsorshipDescriptionService.create();
		sponsorship = sponsorshipService.findOne(29);
		
		sponsorshipDescription.setSponsorship(sponsorship);
		sponsorshipDescription.setCode("fa");
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		sponsorshipDescription.setInfoLinks(links);
		sponsorshipDescription.setTags(tags);
		sponsorshipDescription.setText("text");
		sponsorshipDescription.setTitle("title");
		
		sponsorshipDescriptionService.save(sponsorshipDescription);
		allAfter= sponsorshipDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - B-Level - 3.1
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, editing,
	 * and removing them.
	 * 
	 * Negative test case: The sponsorship exchange description is not delete because agent1
	 * was not the owner.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testsponsorshipDescriptionDelete() {
		Collection<SponsorshipDescription> allBefore;
		Collection<SponsorshipDescription> allAfter;
		SponsorshipDescription sponsorshipDescription;

		
		allBefore = sponsorshipDescriptionService.findAll();

		authenticate("agent1");
		
		sponsorshipDescription = sponsorshipDescriptionService.findOne(67);

	
		sponsorshipDescriptionService.delete(sponsorshipDescription);
		allAfter= sponsorshipDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() - 1);

		authenticate(null);
	}

}
