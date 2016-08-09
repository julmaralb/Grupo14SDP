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

import domain.LanguageExchange;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class KeywordServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private KeywordService keywordService;

	// Other services ---------------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;
	
	@Autowired
	private ActorService actorService;
	
	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot 2.0 - B-Level - 1.1
	 * Every actor, including not authenticated ones, must be able to:
	 * Search for a language exchange using the languages in which the exchange is going
	 *	to take place or a single key word that must occur anywhere in the title, the piece of
	 *	text, the place, or the tags that describe it.
	 * 
	 * Positive test case: All the languageExchanges with the keyword are listed.
	 * 
	 */

	@Test
	public void testListByKeyword1() {
		Collection<LanguageExchange> languageExchanges;
		String keyword;
		
		keyword = "Virg";
		
		languageExchanges = languageExchangeService.findByKeyword(keyword);
		
		Assert.isTrue(languageExchanges.size()==3);

	}
	
	/**
	 * Acme-Polyglot 2.0 - B-Level - 1.1
	 * Every actor, including not authenticated ones, must be able to:
	 * Search for a language exchange using the languages in which the exchange is going
	 *	to take place or a single key word that must occur anywhere in the title, the piece of
	 *	text, the place, or the tags that describe it.
	 * 
	 * Negative test case: The keyword is not added to polyglot2 keywords. But it is so
	 * the test returns the expected exception.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testProcessKeyword1() {
		String keyword;
		
		authenticate("polyglot2");
		
		keyword = "Virg";
		
		keywordService.processKeyword(keyword);
		Assert.isTrue(actorService.findByPrincipal().getKeywords().size()==0);
	
		authenticate(null);

	}
	
	/**
	 * Acme-Polyglot 2.0 - B-Level - 1.1
	 * Every actor, including not authenticated ones, must be able to:
	 * Search for a language exchange using the languages in which the exchange is going
	 *	to take place or a single key word that must occur anywhere in the title, the piece of
	 *	text, the place, or the tags that describe it.
	 * 
	 * Negative test case: The user is not authenticated so the keyword is not processed.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testProcessKeyword2() {
		String keyword;
		
		authenticate(null);
		keyword = "Virg";
		
		keywordService.processKeyword(keyword);
		Assert.isTrue(actorService.findByPrincipal().getKeywords().size()==1);
		
	}
	
	
}