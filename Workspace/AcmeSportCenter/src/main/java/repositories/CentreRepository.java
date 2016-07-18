package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Centre;

@Repository
public interface CentreRepository extends JpaRepository<Centre, Integer> {

}
