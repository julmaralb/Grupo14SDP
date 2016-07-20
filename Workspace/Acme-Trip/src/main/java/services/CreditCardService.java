package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CreditCardRepository;
import domain.Actor;
import domain.Campaign;
import domain.ChargeRecord;
import domain.CreditCard;
import domain.Manager;

@Service
@Transactional
public class CreditCardService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CreditCardRepository creditCardRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ManagerService managerService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public CreditCardService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public CreditCard create() {
		CreditCard result;
		Manager principal;
		Collection<Campaign> campaigns;
		Collection<ChargeRecord> chargeRecords;

		result = new CreditCard();
		principal = managerService.findByPrincipal();
		campaigns = new ArrayList<Campaign>();
		chargeRecords = new ArrayList<ChargeRecord>();
		result.setManager(principal);
		result.setCampaigns(campaigns);
		result.setChargeRecords(chargeRecords);

		return result;
	}

	public CreditCard findOne(int creditCardId) {
		Assert.notNull(creditCardId);

		CreditCard result;

		result = creditCardRepository.findOne(creditCardId);

		return result;
	}

	public Collection<CreditCard> findAll() {

		Collection<CreditCard> result;

		result = creditCardRepository.findAll();

		return result;
	}

	public void save(CreditCard creditCard) {
		Assert.notNull(creditCard);
		Manager principal;

		principal = managerService.findByPrincipal();
		creditCard.setManager(principal);
		creditCardRepository.save(creditCard);
	}

	public void delete(CreditCard creditCard) {
		Assert.notNull(creditCard);

		creditCardRepository.delete(creditCard);
	}

	// Other business methods -------------------------------------------------

	public Collection<CreditCard> findAllByPrincipal() {
		Collection<CreditCard> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = creditCardRepository.findAllByManagerId(principal.getId());

		return result;
	}
}