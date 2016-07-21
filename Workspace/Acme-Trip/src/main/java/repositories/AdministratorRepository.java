package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Banner;
import domain.Manager;
import domain.User;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	// Queries nivel C

	@Query("select count(s) from User s")
	Integer numberOfUsers();

	@Query("select count(t) from Trip t")
	Integer numberOfTrips();

	@Query("select avg(u.trips.size),SQRT(sum((u.trips.size - (select avg(u.trips.size) from User u))*(u.trips.size - (select avg(u.trips.size) from User u)))/(select count(u) from User u)) from User u")
	Collection<Double> avgAndStandardDevTripsPerUser();

	@Query("select avg(dp.size),SQRT(sum((dp.size - (select avg(dp.size) from User u join u.trips t join t.dailyPlans dp))*(dp.size - (select avg(dp.size) from User u join u.trips t join t.dailyPlans dp)))/(select count(u) from User u)) from User u join u.trips t join t.dailyPlans dp")
	Collection<Double> avgAndStandardDevDailyPlansPerUser();

	@Query("select u from User u where u.trips.size >= (select max(u2.trips.size)*0.8 from User u2)")
	Collection<User> usersWithMoreThan80PMaxTripsRegistered();

	// Queries nivel B

	@Query("select min(m.campaigns.size), max(m.campaigns.size), avg(m.campaigns.size) from Manager m")
	Collection<Double> minMaxAvgCampaignsPerManager();

	@Query("select Distinct (select sum(cr.price) from Campaign c join c.creditCard cc join cc.chargeRecords cr)/(select count(c) from Campaign c) from Campaign c ")
	Double avgAmountMoneyPerCampaign();

	@Query("select sum(datediff(c.finishMoment,c.startMoment))*1.0/count(c),SQRT(sum(((datediff(c.finishMoment,c.startMoment)) - (select sum(datediff(c.finishMoment,c.startMoment))*1.0/count(c) from Campaign c)) * ((datediff(c.finishMoment,c.startMoment)) - (select sum(datediff(c.finishMoment,c.startMoment))*1.0/count(c) from Campaign c))) / count(c)) from Campaign c")
	Collection<Double> avgAndStandardDevDaysThatCampaignsLast();

	@Query("select m from Manager m where m.campaigns.size >= ALL (select m1.campaigns.size from Manager m1)")
	Collection<Manager> managerWithMoreCampaigns();
	
	@Query("select b from Banner b where b.displayTimes > ((select avg(b.displayTimes) from Banner b) + (select avg(b.displayTimes)*0.1 from Banner b)) AND b.campaign.cancelled = false")
	Collection<Banner> activeBannersDisplayedMoreThan10PAvg();
	
	@Query("select b from Banner b where b.displayTimes > ((select avg(b.displayTimes) from Banner b) - (select avg(b.displayTimes)*0.1 from Banner b)) AND b.campaign.cancelled = false")
	Collection<Banner> activeBannersDisplayedLessThan10PAvg();
	
}
