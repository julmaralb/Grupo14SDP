package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import javax.validation.ConstraintDeclarationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Campaign;
import domain.CreditCard;
import domain.Manager;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CampaignServiceTest extends AbstractTest {

	

	// Service under test -------------------------

	@Autowired
	private CampaignService campaignService;
	

	// Other services needed -----------------------
	
	@Autowired
	private ManagerService managerService;
	
	@Autowired
	private CreditCardService creditCardService;
	
	// Tests ---------------------------------------
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her campaigns, which includes listing, creating, modifying, and 
	 * 		deleting them. A campaign may be modified or deleted as long as it is not started. 
	 * 		A campaign that has not started yet may be cancelled.
	 * 
	 * Positive Test: Un manager lista sus campaigns
	 */
	@Test
	public void TestListarCampaigns1(){
		Collection<Campaign> campaigns;
		Manager manager;
		
		authenticate("manager1");
		
		manager= managerService.findByPrincipal();
		
		campaigns=manager.getCampaigns();
		
		Assert.isTrue(campaigns.size()==3,"El numero de campaigns no coincide");
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her campaigns, which includes listing, creating, modifying, and 
	 * 		deleting them. A campaign may be modified or deleted as long as it is not started. 
	 * 		A campaign that has not started yet may be cancelled.
	 * 
	 * Test: Un manager lista sus los campaigns de otro manager
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestListarCampaigns2(){
		Collection<Campaign> campaigns;
		Manager manager;
		
		authenticate("manager1");
		
		manager= managerService.findOne(15);
		
		campaigns=manager.getCampaigns();
		
		unauthenticate();
	}
	
	/**
	 * An actor who is authenticated as a manager must be able to:
	 * 		-Manage his or her campaigns, which includes listing, creating, modifying, and 
	 * 		deleting them. A campaign may be modified or deleted as long as it is not started. 
	 * 		A campaign that has not started yet may be cancelled.
	 * 
	 * Test: Un user lista los campaigns de un manager
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestListarCampaigns3(){
		Collection<Campaign> campaigns;
		Manager manager;
		
		authenticate("user1");
		
		manager= managerService.findOne(15);
		
		campaigns=manager.getCampaigns();
		
		unauthenticate();
		
	}
	
		/**
		 * An actor who is authenticated as a manager must be able to:
		 * 		-Manage his or her campaigns, which includes listing, creating, modifying, and 
		 * 		deleting them. A campaign may be modified or deleted as long as it is not started. 
		 * 		A campaign that has not started yet may be cancelled.
		 * 
		 * Positive Test: Un manager crea una campaigns
		 */
		@Test
		public void TestCrearCampaigns1(){
			Campaign campaign;
			Manager manager;
			CreditCard creditCard;
			Collection<Campaign> campaigns;
			Date startMoment;
			Date finishMoment;
			
			authenticate("manager1");
			
			campaigns=new ArrayList<Campaign>();
			startMoment=new Date(2016, 7, 5);
			finishMoment=new Date(2016, 9, 5);
			manager= managerService.findByPrincipal();
			creditCard=creditCardService.findOne(27);
			campaign=campaignService.create();
			
			
			campaign.setTitle("Titulo Test");
			campaign.setCancelled(false);
			campaign.setStartMoment(startMoment);
			campaign.setFinishMoment(finishMoment);
			campaign.setManager(manager);
			campaign.setCreditCard(creditCard);
			campaignService.save(campaign);
			manager.getCampaigns().add(campaign);
			managerService.save(manager);
			campaigns=campaignService.findAllByPrincipal();
			
			Assert.isTrue(campaigns.size()==4,"El numero de campaigns no coincide");
			
			unauthenticate();
		}
	
		/**
		 * An actor who is authenticated as a manager must be able to:
		 * 		-Manage his or her campaigns, which includes listing, creating, modifying, and 
		 * 		deleting them. A campaign may be modified or deleted as long as it is not started. 
		 * 		A campaign that has not started yet may be cancelled.
		 * 
		 * Test: Un manager crea una campaigns a otro manager
		 */
		@Test(expected=IllegalArgumentException.class)
		public void TestCrearCampaigns2(){
			Campaign campaign;
			Manager manager;
			CreditCard creditCard;
			Date startMoment;
			Date finishMoment;
			
			authenticate("manager1");
			
			startMoment=new Date(2016, 7, 5);
			finishMoment=new Date(2016, 9, 5);
			manager= managerService.findOne(15);
			creditCard=creditCardService.findOne(33);
			campaign=campaignService.create();
			
			
			campaign.setTitle("Titulo Test");
			campaign.setCancelled(false);
			campaign.setStartMoment(startMoment);
			campaign.setFinishMoment(finishMoment);
			campaign.setManager(manager);
			campaign.setCreditCard(creditCard);
			campaignService.save(campaign);
			manager.getCampaigns().add(campaign);
			managerService.save(manager);
			
			unauthenticate();
		}
	
		
		/**
		 * An actor who is authenticated as a manager must be able to:
		 * 		-Manage his or her campaigns, which includes listing, creating, modifying, and 
		 * 		deleting them. A campaign may be modified or deleted as long as it is not started. 
		 * 		A campaign that has not started yet may be cancelled.
		 * 
		 * Test: Un manager crea una campaigns null
		 */
		@Test(expected=IllegalArgumentException.class)
		public void TestCrearCampaigns3(){
			
			authenticate("manager1");
			
			campaignService.save(null);			
			
			unauthenticate();
		}
		
		/**
		 * An actor who is authenticated as a manager must be able to:
		 * 		-Manage his or her campaigns, which includes listing, creating, modifying, and 
		 * 		deleting them. A campaign may be modified or deleted as long as it is not started. 
		 * 		A campaign that has not started yet may be cancelled.
		 * 
		 * Positive Test: Un manager elimina una campaign
		 */
		@Test
		public void TestBorrarCampaign1(){
			Campaign campaign;
			Manager manager;
			Collection<Campaign> campaigns1;
			Collection<Campaign> campaigns2;
			
			campaigns1=new ArrayList<Campaign>();
			authenticate("admin");
			campaigns1=campaignService.findAll();
			authenticate("manager1");
			campaign=campaignService.findOne(42);
			campaignService.delete(campaign);
			
			manager=managerService.findByPrincipal();			
			manager.getCampaigns().remove(campaign);
			managerService.save(manager);
			campaigns2=new ArrayList<Campaign>();
			authenticate("admin");
			campaigns2=campaignService.findAll();
			
			Assert.isTrue(campaigns1.size()==campaigns2.size()+1, "No se ha borrado");
			unauthenticate();
		}
		
		//TODO Test borrado negativos y pruebas cuando ya han empezado
		
		
		
}
