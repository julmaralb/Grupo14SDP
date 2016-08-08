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

import utilities.AbstractTest;
import domain.LanguageExchange;
import domain.LanguageExchangeDescription;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LanguageExchangeDescriptionServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private LanguageExchangeDescriptionService languageExchangeDescriptionService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private LanguageExchangeService languageExchangeService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - C-Level - 5.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage language exchanges, which includes 
	 * creating, listing, editing, and cancelling them. Note that a 
	 * polyglot is not allowed to edit or cancel an exchange that he or she's not created.
	 * 
	 * Positive test case: A new language exchange description is created.
	 * 
	 */

	@Test
	public void testLanguageExchangeDescriptionCreate1() {
		Collection<LanguageExchangeDescription> allBefore;
		Collection<LanguageExchangeDescription> allAfter;
		LanguageExchangeDescription languageExchangeDescription;
		LanguageExchange languageExchange;
		
		allBefore = languageExchangeDescriptionService.findAll();

		authenticate("polyglot1");
		
		languageExchangeDescription = languageExchangeDescriptionService.create();
		languageExchange = languageExchangeService.findOne(13);
		
		languageExchangeDescription.setLanguageExchange(languageExchange);
		languageExchangeDescription.setCode("fa");
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		languageExchangeDescription.setInfoLinks(links);
		languageExchangeDescription.setTags(tags);
		languageExchangeDescription.setText("text");
		languageExchangeDescription.setTitle("title");
		
		languageExchangeDescriptionService.save(languageExchangeDescription);
		allAfter= languageExchangeDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage language exchanges, which includes 
	 * creating, listing, editing, and cancelling them. Note that a 
	 * polyglot is not allowed to edit or cancel an exchange that he or she's not created.
	 * 
	 * Negative test case: A new language exchange description is not created because polyglot1
	 * was not the owner of the exchange selected.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageExchangeDescriptionCreate2() {
		Collection<LanguageExchangeDescription> allBefore;
		Collection<LanguageExchangeDescription> allAfter;
		LanguageExchangeDescription languageExchangeDescription;
		LanguageExchange languageExchange;
		
		allBefore = languageExchangeDescriptionService.findAll();

		authenticate("polyglot1");
		
		languageExchangeDescription = languageExchangeDescriptionService.create();
		languageExchange = languageExchangeService.findOne(16);
		
		languageExchangeDescription.setLanguageExchange(languageExchange);
		languageExchangeDescription.setCode("fa");
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		languageExchangeDescription.setInfoLinks(links);
		languageExchangeDescription.setTags(tags);
		languageExchangeDescription.setText("text");
		languageExchangeDescription.setTitle("title");
		
		languageExchangeDescriptionService.save(languageExchangeDescription);
		allAfter= languageExchangeDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage language exchanges, which includes 
	 * creating, listing, editing, and cancelling them. Note that a 
	 * polyglot is not allowed to edit or cancel an exchange that he or she's not created.
	 * 
	 * Negative test case: The language exchange description is not deleted because polyglot1
	 * was not the owner.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageExchangeDescriptionDelete() {
		Collection<LanguageExchangeDescription> allBefore;
		Collection<LanguageExchangeDescription> allAfter;
		LanguageExchangeDescription languageExchangeDescription;
		
		allBefore = languageExchangeDescriptionService.findAll();

		authenticate("polyglot1");
		
		languageExchangeDescription = languageExchangeDescriptionService.findOne(40);		
		
		languageExchangeDescriptionService.delete(languageExchangeDescription);
		allAfter= languageExchangeDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() + 1 == allBefore.size());

		authenticate(null);
	}

}
