package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Reservation;

@Repository
public interface ReservationRepository extends
		JpaRepository<Reservation, Integer> {

	@Query("select r from Customer c join c.reservations r where c.id= ?1")
	Collection<Reservation> findByCustomerId(int customerId);

	@Query("select r from Customer c join c.reservations r where r not in (select r2 from Customer c join c.ownOpenMatches om join om.reservation r2) AND c.id= ?1")
	Collection<Reservation> findAllNotUsedByCustomerId(int customerId);
}
