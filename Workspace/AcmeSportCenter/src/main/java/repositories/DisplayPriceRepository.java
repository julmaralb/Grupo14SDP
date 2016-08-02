package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.DisplayPrice;

@Repository
public interface DisplayPriceRepository extends
		JpaRepository<DisplayPrice, Integer> {

}
