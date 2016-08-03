package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Renting;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Integer> {

	@Query("select r from Renting r where r.customer.id = ?1")
	Collection<Renting> findAllByCustomerId(int customerId);

}
