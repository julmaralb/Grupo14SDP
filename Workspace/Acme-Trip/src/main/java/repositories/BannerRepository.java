package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Banner;

@Repository
public interface BannerRepository extends JpaRepository<Banner, Integer> {

	@Query("select b from Banner b where b.campaign.manager.id = ?1")
	Collection<Banner> findAllByManagerId(int managerId);

	@Query("select b from Banner b where b.campaign.id= ?1 AND b.campaign.manager.id = ?2")
	Collection<Banner> findAllByCampaignAndManagerId(int campaignId, int managerId);

}
