package services;

import java.util.ArrayList;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.ChargeRecord;
import domain.CreditCard;
import domain.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ChargeRecordServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private ChargeRecordService chargeRecordService;

	// Other services needed -----------------------

	@Autowired
	private ManagerService managerService;

	// Tests ---------------------------------------

	/**
	 * An actor who is authenticated as a manager must be able to: -Display the
	 * charge records that are associated with his or her credit cards.
	 * 
	 * Positive Test:Un manager lista sus charge records
	 */
	@Test
	public void TestListarChargeRecord1() {

		Collection<CreditCard> creditCards;
		Manager manager;
		Collection<ChargeRecord> chargeRecords;

		authenticate("manager1");

		manager = managerService.findByPrincipal();
		chargeRecords = new ArrayList<ChargeRecord>();
		creditCards = manager.getCreditCards();
		for (CreditCard c : creditCards) {
			chargeRecords.addAll(chargeRecordService
					.findAllByCreditCardIdAndPrincipal(c.getId()));
		}

		Assert.isTrue(chargeRecords.size() == 4,
				"El número de ChangeRecord no coincide");
	}

	/**
	 * An actor who is authenticated as a manager must be able to: -Display the
	 * charge records that are associated with his or her credit cards.
	 * 
	 * Negative Test:Un manager lista los charge records de otro manager
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestListarChargeRecord2() {

		Collection<CreditCard> creditCards;
		Manager manager;
		Collection<ChargeRecord> chargeRecords;

		authenticate("manager1");

		manager = managerService.findOne(15);
		chargeRecords = new ArrayList<ChargeRecord>();
		creditCards = manager.getCreditCards();
		for (CreditCard c : creditCards) {
			chargeRecords.addAll(chargeRecordService
					.findAllByCreditCardIdAndPrincipal(c.getId()));
		}

	}

	/**
	 * An actor who is authenticated as a manager must be able to: -Display the
	 * charge records that are associated with his or her credit cards.
	 * 
	 * Test:Un usuario lista los charge records de un manager
	 */
	@Test(expected = AssertionError.class)
	public void TestListarChargeRecord3() {

		Collection<CreditCard> creditCards;
		Manager manager;
		Collection<ChargeRecord> chargeRecords;

		authenticate("user1");

		manager = managerService.findOne(15);
		chargeRecords = new ArrayList<ChargeRecord>();
		creditCards = manager.getCreditCards();
		for (CreditCard c : creditCards) {
			chargeRecords.addAll(chargeRecordService
					.findAllByCreditCardIdAndPrincipal(c.getId()));
		}

	}
}
