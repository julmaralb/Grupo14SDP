package services;

import java.util.ArrayList;
import java.util.Calendar;
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
		Assert.isTrue(actorService.checkAuthority("MANAGER"));
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
		
		Actor principal;
		CreditCard result;

		principal = managerService.findByPrincipal();
		result = creditCardRepository.findOne(creditCardId);
		Assert.isTrue(result.getManager().getId()==principal.getId());
		return result;
	}

	public Collection<CreditCard> findAll() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Collection<CreditCard> result;

		result = creditCardRepository.findAll();

		return result;
	}

	public void save(CreditCard creditCard) {
		Assert.notNull(creditCard);
		Assert.isTrue(actorService.checkAuthority("MANAGER"));
	
		Manager principal;

		principal = managerService.findByPrincipal();
		Assert.isTrue(creditCard.getManager().getId()==principal.getId());
		creditCard.setManager(principal);
		Assert.isTrue(checkNotExpired(creditCard));
		creditCardRepository.save(creditCard);
	}

	public void delete(CreditCard creditCard) {
		Assert.notNull(creditCard);
		Actor principal;
		
		principal = actorService.findByPrincipal();
		Assert.isTrue(creditCard.getManager().getId()==principal.getId());
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

	public boolean checkNotExpired(CreditCard creditCard) {
		boolean result;
		Calendar currentDate;

		currentDate = Calendar.getInstance();
		result = false;

		if (creditCard.getExpYear() > currentDate.get(Calendar.YEAR)) {
			result = true;
		}
		if (creditCard.getExpYear() == currentDate.get(Calendar.YEAR)) {
			if (creditCard.getExpMonth() >= currentDate.get(Calendar.MONTH)+1) {
				result = true;
			}
		}
		return result;
	}
}