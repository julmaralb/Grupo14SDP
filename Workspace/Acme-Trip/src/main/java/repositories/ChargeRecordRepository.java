package repositories;

import java.util.Collection;
import java.util.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.ChargeRecord;

@Repository
public interface ChargeRecordRepository extends
		JpaRepository<ChargeRecord, Integer> {

	@Query("select cc.chargeRecords from CreditCard cc where cc.id = ?1 AND cc.manager.id = ?2")
	Collection<ChargeRecord> findAllByCreditCardIdAndManagerId(
			int creditCardId, int managerId);
	
	@Query("select cr from ChargeRecord cr where cr.moment > ?1")
	Collection<ChargeRecord> findAllFromToday(Date today);

}
