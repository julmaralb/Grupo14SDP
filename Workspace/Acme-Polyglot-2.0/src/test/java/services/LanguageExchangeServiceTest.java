package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.util.Assert;

import domain.LanguageExchange;
import domain.Polyglot;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LanguageExchangeServiceTest extends AbstractTest {

	// Service under test -----------------------------------------------------

	@Autowired
	private LanguageExchangeService languageExchangeService;

	// Other services ---------------------------------------------------------

	// Tests ------------------------------------------------------------------
	
	/**
	 * Acme-Polyglot - C-Level - 5.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage language exchanges, which includes 
	 * creating, listing, editing, and cancelling them. Note that a 
	 * polyglot is not allowed to edit or cancel an exchange that he or she's not created.
	 * 
	 * Positive test case: A new language exchange is created.
	 * 
	 */

	@Test
	public void testLanguageExchangeCreate1() {
		Collection<LanguageExchange> allBefore;
		Collection<LanguageExchange> allAfter;
		LanguageExchange languageExchange;
		
		allBefore = languageExchangeService.findAll();

		authenticate("polyglot1");
		
		languageExchange = languageExchangeService.create();
		languageExchange.setName("Name");
		languageExchange.setRegistrationDate(new Date());
		languageExchange.setExchangePlace("Place");

		languageExchangeService.save(languageExchange);
		allAfter= languageExchangeService.findAll();
		
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
	 * Negative test case: A new language exchange is not created because it was not a polyglot.
	 * 
	 */

	@Test(expected = AssertionError.class)
	public void testLanguageExchangeCreate2() {
		Collection<LanguageExchange> allBefore;
		Collection<LanguageExchange> allAfter;
		LanguageExchange languageExchange;
		
		allBefore = languageExchangeService.findAll();

		authenticate("admin");
		
		languageExchange = languageExchangeService.create();
		languageExchange.setName("Name");
		languageExchange.setRegistrationDate(new Date());
		languageExchange.setExchangePlace("Place");

		languageExchangeService.save(languageExchange);
		allAfter= languageExchangeService.findAll();
		
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
	 * Positive test case: All language exchanges are listed.
	 * 
	 */

	@Test
	public void testLanguageExchangeList() {
		Collection<LanguageExchange> all;
		
		all = languageExchangeService.findAll();
		
		Assert.isTrue(all.size()==6);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.4
	 * A user who is authenticated as an administrator
	 * must be able to: List the language exchanges that he or she's joined.
	 * 
	 * Positive test case: All language exchanges that polyglot1 has joined are listed.
	 * 
	 */

	@Test
	public void testLanguageExchangeListJoined() {
		Collection<LanguageExchange> joined;
		
		authenticate("polyglot1");
		joined = languageExchangeService.findAllJoinedByPrincipal();
		
		Assert.isTrue(joined.size()==4);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage language exchanges, which includes 
	 * creating, listing, editing, and cancelling them. Note that a 
	 * polyglot is not allowed to edit or cancel an exchange that he or she's not created.
	 * 
	 * Negative test case: The exchange is not cancelled becouse polyglot1 was not it's owner.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageExchangeCancel1() {
		LanguageExchange languageExchange;
		
		authenticate("polyglot1");
		languageExchange = languageExchangeService.findOne(53);
		
		languageExchangeService.cancelLanguageExchange(languageExchange);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.2
	 * A user who is authenticated as an administrator
	 * must be able to: Manage language exchanges, which includes 
	 * creating, listing, editing, and cancelling them. Note that a 
	 * polyglot is not allowed to edit or cancel an exchange that he or she's not created.
	 * 
	 * Positive test case: The exchange is cancelled.
	 * 
	 */

	@Test
	public void testLanguageExchangeCancel2() {
		LanguageExchange languageExchange;
		
		authenticate("polyglot1");
		languageExchange = languageExchangeService.findOne(50);
		
		languageExchangeService.cancelLanguageExchange(languageExchange);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.3
	 * A user who is authenticated as an administrator
	 * must be able to: Join or to unjoin an existing language exchange.
	 * 
	 * Positive test case: Polyglot1 joins the exchange.
	 * 
	 */

	@Test
	public void testLanguageExchangeJoin1() {
		LanguageExchange languageExchange;
		Collection<Polyglot> participantsBefore;
		Collection<Polyglot> participantsAfter;
		
		authenticate("polyglot1");
		languageExchange = languageExchangeService.findOne(53);
		participantsBefore = languageExchange.getParticipants();
		Assert.isTrue(participantsBefore.size() == 2);
		
		languageExchangeService.joinLanguageExchange(languageExchange);
		participantsAfter = languageExchange.getParticipants();
		Assert.isTrue(participantsAfter.size() == 3);
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.3
	 * A user who is authenticated as an administrator
	 * must be able to: Join or to unjoin an existing language exchange.
	 * 
	 * Positive test case: Polyglot1 joins the exchange and then unjoins it.
	 * 
	 */

	@Test
	public void testLanguageExchangeUnjoin1() {
		LanguageExchange languageExchange;
		Collection<Polyglot> participants;
		
		authenticate("polyglot1");
		languageExchange = languageExchangeService.findOne(53);
		participants = languageExchange.getParticipants();
		Assert.isTrue(participants.size() == 2);
		
		languageExchangeService.joinLanguageExchange(languageExchange);
		participants = languageExchange.getParticipants();
		Assert.isTrue(participants.size() == 3);
		
		languageExchangeService.unJoinLanguageExchange(languageExchange);
		participants = languageExchange.getParticipants();
		Assert.isTrue(participants.size() == 2);		
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.3
	 * A user who is authenticated as an administrator
	 * must be able to: Join or to unjoin an existing language exchange.
	 * 
	 * Negative test case: Polyglot1 can't unjoin an exchange he has not joined.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageExchangeUnjoin2() {
		LanguageExchange languageExchange;
		
		authenticate("polyglot1");
		languageExchange = languageExchangeService.findOne(53);
		
		languageExchangeService.unJoinLanguageExchange(languageExchange);		
		
		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 5.3
	 * A user who is authenticated as an administrator
	 * must be able to: Join or to unjoin an existing language exchange.
	 * 
	 * Negative test case: Polyglot1 can't join an exchange he already joined.
	 * 
	 */

	@Test(expected = IllegalArgumentException.class)
	public void testLanguageExchangeJoin2() {
		LanguageExchange languageExchange;
		
		authenticate("polyglot1");
		languageExchange = languageExchangeService.findOne(50);

		languageExchangeService.joinLanguageExchange(languageExchange);

		authenticate(null);
	}
	
	/**
	 * Acme-Polyglot - C-Level - 4.2
	 * An actor who is not authenticated must be able to:
	 * List the exchanges that have been organised no more than three months ago.
	 * 
	 * Positive test case: All language exchanges organised no more than three months ago.
	 * are listed.
	 * 
	 */

	@Test
	public void testLanguageExchangeList3MonthsAgo() {
		Collection<LanguageExchange> result;
		
		result = languageExchangeService.find3MonthsAgo();
		
		Assert.isTrue(result.size()==0);
		
	}
	
	/**
	 * Acme-Polyglot - C-Level - 4.3
	 * An actor who is not authenticated must be able to:
	 * List the exchanges that are going to be organised in
	 * no more than three months time.
	 * 
	 * Positive test case: All language exchanges organised no more than three months time.
	 * are listed.
	 * 
	 */

	@Test
	public void testLanguageExchangeList3MonthsTime() {
		Collection<LanguageExchange> result;
		
		result = languageExchangeService.find3MonthsTime();
		
		Assert.isTrue(result.size()==5);

	}

}