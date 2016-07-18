package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import domain.Slot;

@Repository
public interface SlotRepository extends JpaRepository<Slot, Integer> {

}
