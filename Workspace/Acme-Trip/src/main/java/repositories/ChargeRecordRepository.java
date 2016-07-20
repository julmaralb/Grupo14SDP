package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.ChargeRecord;

@Repository
public interface ChargeRecordRepository extends
		JpaRepository<ChargeRecord, Integer> {

}
