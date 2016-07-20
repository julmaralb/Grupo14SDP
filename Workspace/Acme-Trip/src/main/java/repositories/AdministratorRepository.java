package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	@Query("select count(s) from User s")
	Integer numberOfUsers();

	@Query("select count(t) from Trip t")
	Integer numberOfTrips();

}
