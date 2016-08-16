package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ChargeRecordRepository;
import domain.Actor;
import domain.ChargeRecord;
import domain.CreditCard;

@Service
@Transactional
public class ChargeRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ChargeRecordRepository chargeRecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private CreditCardService creditCardService;

	// Constructors -----------------------------------------------------------

	public ChargeRecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ChargeRecord create() {
		ChargeRecord result;
		Date moment;

		result = new ChargeRecord();
		moment = new Date();

		result.setMoment(moment);

		return result;
	}

	public ChargeRecord findOne(int chargeRecordId) {
		Assert.notNull(chargeRecordId);
		Actor principal;
		ChargeRecord result;
		principal=actorService.findByPrincipal();
		result = chargeRecordRepository.findOne(chargeRecordId);
		
		Assert.isTrue(result.getCreditCard().getManager().getId()==principal.getId());
		
		return result;
	}

	public Collection<ChargeRecord> findAll() {

		Collection<ChargeRecord> result;

		result = chargeRecordRepository.findAll();

		return result;
	}

	public void save(ChargeRecord chargeRecord) {
		Assert.notNull(chargeRecord);
		long milliseconds;

		milliseconds = System.currentTimeMillis() - 100;
		chargeRecord.setMoment(new Date(milliseconds));

		chargeRecordRepository.save(chargeRecord);
	}

	public void delete(ChargeRecord chargeRecord) {
		Assert.notNull(chargeRecord);

		chargeRecordRepository.delete(chargeRecord);
	}

	// Other business methods -------------------------------------------------

	public Collection<ChargeRecord> findAllByCreditCardIdAndPrincipal(
			int creditCardId) {
		Collection<ChargeRecord> result;
		Actor principal;
		CreditCard cc;
		
		principal = actorService.findByPrincipal();
		result = chargeRecordRepository.findAllByCreditCardIdAndManagerId(
				creditCardId, principal.getId());
		cc=creditCardService.findOne(creditCardId);
		
		Assert.isTrue(cc.getManager().getId()==principal.getId());
		return result;
	}

	public Collection<ChargeRecord> findAllFromToday() {
		Collection<ChargeRecord> result;
		Calendar c;
		
		c= Calendar.getInstance();
		c.set(Calendar.HOUR_OF_DAY, 0);
	    c.set(Calendar.MINUTE, 0);
	    c.set(Calendar.SECOND, 0);
	    Date today = c.getTime();
		result = chargeRecordRepository.findAllFromToday(today);

		return result;

	}
}