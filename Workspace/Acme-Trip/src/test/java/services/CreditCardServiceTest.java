package services;

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
import domain.CreditCard;
import domain.Manager;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CreditCardServiceTest extends AbstractTest {
		
			
			// Service under test -------------------------
			
			@Autowired
			private CreditCardService creditCardService;
			

			// Other services needed -----------------------

			@Autowired
			private ManagerService managerService;
			
			// Tests ---------------------------------------
			
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Positive Test: Un manager añade una nueva credit card
	 */
	@Test
	public void TestAñadirCreditCard1(){
		CreditCard creditCard;
		Manager manager;
		
		authenticate("manager1");
		
		manager=managerService.findByPrincipal();
		creditCard=creditCardService.create();
		creditCard.setBrandName("Mastercard");
		creditCard.setCVV(567);
		creditCard.setExpMonth(6);
		creditCard.setExpYear(2020);
		creditCard.setHolderName("HolderTest");
		creditCard.setNumber("5083917486675816");
		creditCard.setManager(manager);
		creditCardService.save(creditCard);
		manager.getCreditCards().add(creditCard);
		managerService.save(manager);
		Assert.isTrue(manager.getCreditCards().contains(creditCard),"La Nueva tarjeta no se encuentra en las tarjetas del manager ");
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Negative Test: Un user añade una nueva credit card
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestAñadirCreditCard2(){
		CreditCard creditCard;
			
		authenticate("user1");
		
		creditCard=creditCardService.create();
		creditCard.setBrandName("Mastercard");
		creditCard.setCVV(567);
		creditCard.setExpMonth(6);
		creditCard.setExpYear(2020);
		creditCard.setHolderName("HolderTest");
		creditCard.setNumber("5083917486675816");
		creditCardService.save(creditCard);
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Test: Un manager añade una nueva credit card null
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestAñadirCreditCard3(){
					
		authenticate("manager1");
	
		creditCardService.save(null);
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Positive Test: Un manager lista todas sus creditCard
	 */
	@Test
	public void TestListarCreditCard1(){
		Collection<CreditCard> creditCards;
		
		authenticate("manager1");
		
		creditCards = creditCardService.findAllByPrincipal();
		
		Assert.isTrue(creditCards.size()==2, "El numero de creditCards no coincide");
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Negative Test: Un manager lista todas las creditCard en el sistema
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestListarCreditCard2(){
		
		authenticate("manager1");
	
		creditCardService.findAll();
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Negative Test: Un manager lista una creditCard de otro manager
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestListarCreditCard3(){
		
		authenticate("manager1");
	
		creditCardService.findOne(33);
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Positive Test: Un manager borra una creditCard suya
	 */
	@Test
	public void TestBorraCreditCard1(){
		CreditCard creditCard;
		Manager manager;
		authenticate("manager1");
	
		creditCard = creditCardService.findOne(27);
		
		manager=managerService.findByPrincipal();
		manager.getCreditCards().remove(creditCard);
		creditCardService.delete(creditCard);
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Negative Test: Un manager borra una creditCard de otro manager
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestBorraCreditCard2(){
		CreditCard creditCard;
		//Manager manager;
		authenticate("manager1");
	
		creditCard = creditCardService.findOne(33);
		
		//manager=managerService.findByPrincipal();
		//manager.getCreditCards().remove(creditCard);
		creditCardService.delete(creditCard);
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her credit cards, which includes listing, registering, and deleting them.
	 * 
	 * Negative Test: Un usuario borra una creditCard de un manager
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestBorraCreditCard3(){
		CreditCard creditCard;
		
		authenticate("manager2");
	
		creditCard = creditCardService.findOne(33);
		
		authenticate("user1");
		
		creditCardService.delete(creditCard);
		
		unauthenticate();
	}
	
}
