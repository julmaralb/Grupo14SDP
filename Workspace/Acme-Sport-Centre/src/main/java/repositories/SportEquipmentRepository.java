package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.SportEquipment;

@Repository
public interface SportEquipmentRepository extends
		JpaRepository<SportEquipment, Integer> {

}
