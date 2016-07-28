package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Folder;

@Repository
public interface FolderRepository extends JpaRepository<Folder, Integer> {

	@Query("select f from Folder f where f.actor.id = ?1")
	Collection<Folder> findAllByActorId(int actorId);

	@Query("select f from Folder f where f.name = ?1 and f.actor.id = ?2")
	Folder findByNameAndActorId(String name, int actorId);

	@Query("select f from Folder f where f.actor.id = ?1 and f.isSystem = false")
	Collection<Folder> findAllNonSystemFoldersByActorId(int actorId);
}
