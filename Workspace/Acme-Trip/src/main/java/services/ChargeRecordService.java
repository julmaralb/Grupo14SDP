package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ChargeRecordRepository;
import domain.Actor;
import domain.ChargeRecord;

@Service
@Transactional
public class ChargeRecordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ChargeRecordRepository chargeRecordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public ChargeRecordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ChargeRecord create() {
		ChargeRecord result;

		result = new ChargeRecord();

		return result;
	}

	public ChargeRecord findOne(int chargeRecordId) {
		Assert.notNull(chargeRecordId);

		ChargeRecord result;

		result = chargeRecordRepository.findOne(chargeRecordId);

		return result;
	}

	public Collection<ChargeRecord> findAll() {

		Collection<ChargeRecord> result;

		result = chargeRecordRepository.findAll();

		return result;
	}

	public void save(ChargeRecord chargeRecord) {
		Assert.notNull(chargeRecord);

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

		principal = actorService.findByPrincipal();
		result = chargeRecordRepository.findAllByCreditCardIdAndManagerId(
				creditCardId, principal.getId());

		return result;
	}
}