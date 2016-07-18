package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.OpenMatch;

@Repository
public interface OpenMatchRepository extends JpaRepository<OpenMatch, Integer> {

	@Query("select om from OpenMatch om where om.owner = (select c from Customer c where c.id = ?1) OR (select c from Customer c where c.id = ?1) member of om.players")
	Collection<OpenMatch> findOwnOrJoinedByCustomerId(int customerId);
}