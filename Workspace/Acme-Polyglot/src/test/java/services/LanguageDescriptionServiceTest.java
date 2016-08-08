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
import domain.Language;
import domain.LanguageDescription;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LanguageDescriptionServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private LanguageDescriptionService languageDescriptionService;

	// Other services ---------------------------------------------------------
	
	@Autowired
	private LanguageService languageService;

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - C-Level - 6.2
	 * An actor who is authenticated as an administrator must be able to:
	 * Manage languages, which includes registering, listing, editing, and 
	 * removing them from the system (as long as they are not used).
	 * 
	 * Positive test case: A new language description is created.
	 * 
	 */

	@Test
	public void testLanguageDescriptionCreate1() {
		Collection<LanguageDescription> allBefore;
		Collection<LanguageDescription> allAfter;
		LanguageDescription languageDescription;
		Language language;
		
		allBefore = languageDescriptionService.findAll();

		authenticate("admin");
		
		languageDescription = languageDescriptionService.create();
		language = languageService.findOne(27);
		
		languageDescription.setLanguage(language);
		languageDescription.setCode("fa");
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		languageDescription.setInfoLinks(links);
		languageDescription.setTags(tags);
		languageDescription.setText("text");
		languageDescription.setTitle("title");
		
		languageDescriptionService.save(languageDescription);
		allAfter= languageDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 6.2
	 * An actor who is authenticated as an administrator must be able to:
	 * Manage languages, which includes registering, listing, editing, and 
	 * removing them from the system (as long as they are not used).
	 * 
	 * Positive test case: A new language description is not created because
	 * the actor was not an admin.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageDescriptionCreate2() {
		Collection<LanguageDescription> allBefore;
		Collection<LanguageDescription> allAfter;
		LanguageDescription languageDescription;
		Language language;
		
		allBefore = languageDescriptionService.findAll();

		authenticate("polyglot1");
		
		languageDescription = languageDescriptionService.create();
		language = languageService.findOne(27);
		
		languageDescription.setLanguage(language);
		languageDescription.setCode("fa");
		Collection<String> links = new ArrayList<String>();
		links.add("link1");
		Collection<String> tags = new ArrayList<String>();
		links.add("tag");
		languageDescription.setInfoLinks(links);
		languageDescription.setTags(tags);
		languageDescription.setText("text");
		languageDescription.setTitle("title");
		
		languageDescriptionService.save(languageDescription);
		allAfter= languageDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 6.2
	 * An actor who is authenticated as an administrator must be able to:
	 * Manage languages, which includes registering, listing, editing, and 
	 * removing them from the system (as long as they are not used).
	 * 
	 * Positive test case: A new language description is not created because
	 * the actor was not an admin.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageDescriptionDelete() {
		Collection<LanguageDescription> allBefore;
		Collection<LanguageDescription> allAfter;
		LanguageDescription languageDescription;
		
		allBefore = languageDescriptionService.findAll();

		authenticate("polyglot1");
		
		languageDescription = languageDescriptionService.findOne(49);
	
		languageDescriptionService.delete(languageDescription);
		allAfter= languageDescriptionService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() - 1);

		authenticate(null);
	}
	
}