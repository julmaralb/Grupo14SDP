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
import domain.Language;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LanguageServiceTest  extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private LanguageService languageService;

	// Other services ---------------------------------------------------------

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - C-Level - 6.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage languages, which includes registering, listing,
	 *  editing, and removing them from the system (as long as they are not used).
	 * 
	 * Positive test case: A new language is created.
	 * 
	 */

	@Test
	public void testLanguageCreate1() {
		Collection<Language> allBefore;
		Collection<Language> allAfter;
		Language language;
		
		allBefore = languageService.findAll();

		authenticate("admin");
		
		language = languageService.create();
		language.setCode("es");
		language.setCounter(0);

		languageService.save(language);
		allAfter= languageService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 6.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage languages, which includes registering, listing,
	 *  editing, and removing them from the system (as long as they are not used).
	 * 
	 * Negative test case: A new language is not created because it was not an admin.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageCreate2() {
		Collection<Language> allBefore;
		Collection<Language> allAfter;
		Language language;
		
		allBefore = languageService.findAll();

		authenticate("polyglot1");
		
		language = languageService.create();
		language.setCode("es");
		language.setCounter(0);

		languageService.save(language);
		allAfter= languageService.findAll();
		
		Assert.isTrue(allAfter.size() == allBefore.size() + 1);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 6.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage languages, which includes registering, listing,
	 *  editing, and removing them from the system (as long as they are not used).
	 * 
	 * Negative test case: The selected language is not deleted because it is being used.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageDelete() {
		Language language;
		
		authenticate("admin");
		
		language = languageService.findOne(27);
		
		languageService.delete(language);

		authenticate(null);
	}

}
