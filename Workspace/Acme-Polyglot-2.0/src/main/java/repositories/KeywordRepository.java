package repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Keyword;

@Repository
public interface KeywordRepository extends JpaRepository<Keyword, Integer> {

	@Query("select k from Keyword k where k.keyword = ?1")
	Keyword findByKeyword(String keyword);

}