package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Polyglot;

@Repository
public interface PolyglotRepository extends JpaRepository<Polyglot, Integer> {

	@Query("select p from Polyglot p where p.userAccount.id = ?1")
	Polyglot findByUserAccountId(int userAccountId);

}
