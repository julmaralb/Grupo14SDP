package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

	@Query("select k from Keyword k where k.keyword = ?1 AND k.actor.id =?2")
	Keyword findByKeyword(String keyword, int actorId);

	@Query("select k from Keyword k where k.actor.id = ?1")
	Collection<Keyword> findAllByActorId(int actorId);

}
