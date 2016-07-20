package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.CreditCard;

@Repository
public interface CreditCardRepository extends
		JpaRepository<CreditCard, Integer> {

	@Query("select cc from CreditCard cc where cc.manager.id = ?1")
	Collection<CreditCard> findAllByManagerId(int managerId);

}
