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
import domain.Banner;
import domain.Sponsorship;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class BannerServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private BannerService bannerService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private SponsorshipService sponsorshipService;
	
	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - B-Level - 3.1 
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, 
	 * editing, and removing them.
	 * 
	 * Positive test case: A new banner is created.
	 * 
	 */

	@Test
	public void testCreateBanner1() {
		Banner banner;
		Sponsorship sponsorship;
		Collection<Banner> all;

		authenticate("agent1");
		
		banner = bannerService.create();
		sponsorship = sponsorshipService.findOne(56);
		all = bannerService.findAll();
		Assert.isTrue(all.size()== 5);
		
		banner.setCode("it");
		banner.setPhoto("http://foto.com");
		banner.setSponsorship(sponsorship);
		bannerService.save(banner);
		all = bannerService.findAll();
		Assert.isTrue(all.size()== 6);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - B-Level - 3.1 
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, 
	 * editing, and removing them.
	 * 
	 * Negative test case: A new banner is not created because the 
	 * sponsorship selected does not belong to agent1.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateBanner2() {
		Banner banner;
		Sponsorship sponsorship;
		Collection<Banner> all;

		authenticate("agent1");
		
		banner = bannerService.create();
		sponsorship = sponsorshipService.findOne(61);
		all = bannerService.findAll();
		Assert.isTrue(all.size()== 5);
		
		banner.setCode("it");
		banner.setPhoto("http://foto.com");
		banner.setSponsorship(sponsorship);
		bannerService.save(banner);
		all = bannerService.findAll();
		Assert.isTrue(all.size()== 6);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - B-Level - 3.1 
	 * An actor who is authenticated as an agent must be able to:
	 * Manage his sponsorships, which includes creating, listing, 
	 * editing, and removing them.
	 * 
	 * Negative test case: A new banner is not created because the 
	 * sponsorship selected does not belong to agent1.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testCreateBanner3() {
		Banner banner;
		Sponsorship sponsorship;
		Collection<Banner> all;

		authenticate("admin");
		
		banner = bannerService.create();
		sponsorship = sponsorshipService.findOne(56);
		all = bannerService.findAll();
		Assert.isTrue(all.size()== 5);
		
		banner.setCode("it");
		banner.setPhoto("http://foto.com");
		banner.setSponsorship(sponsorship);
		bannerService.save(banner);
		all = bannerService.findAll();
		Assert.isTrue(all.size()== 6);
		
		authenticate(null);
	}

}
