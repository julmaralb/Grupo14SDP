package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Renting;

@Repository
public interface RentingRepository extends JpaRepository<Renting, Integer> {

}
