package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Supervisor;

@Repository
public interface SupervisorRepository extends
		JpaRepository<Supervisor, Integer> {

	@Query("select s from Supervisor s where s.userAccount.id = ?1")
	Supervisor findByUserAccountId(int userAccountId);

}
