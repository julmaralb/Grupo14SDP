package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import security.Authority;
import security.UserAccount;
import domain.Administrator;
import domain.Banner;
import domain.ChargeRecord;
import domain.DisplayPrice;
import domain.Folder;
import domain.Manager;
import domain.User;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private FolderService folderService;

	@Autowired
	private BannerService bannerService;

	@Autowired
	private ChargeRecordService chargeRecordService;

	@Autowired
	private DisplayPriceService displayPriceService;

	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Administrator create() {
		Administrator result;
		UserAccount userAccount;

		userAccount = createAdminAccount();
		result = new Administrator();
		result.setUserAccount(userAccount);

		return result;
	}

	public Administrator findOne(int administratorId) {
		Assert.notNull(administratorId);

		Administrator result;

		result = administratorRepository.findOne(administratorId);

		return result;
	}

	public Collection<Administrator> findAll() {

		Collection<Administrator> result;

		result = administratorRepository.findAll();

		return result;
	}

	public void save(Administrator administrator) {
		Assert.notNull(administrator);
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		String password;
		password = administrator.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		administrator.getUserAccount().setPassword(password);
		Collection<Folder> folders;
		folders = folderService.initializeFolders(administrator);
		administrator.setFolders(folders);

		administratorRepository.save(administrator);
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);

		administratorRepository.delete(administrator);
	}

	// Other business methods -------------------------------------------------

	public UserAccount createAdminAccount() {
		UserAccount result;
		// Collection<Authority> authorities;
		Authority authority;

		result = new UserAccount();
		// result.setUsername("");
		// result.setPassword("");

		authority = new Authority();
		authority.setAuthority("ADMIN");
		// authorities = new ArrayList<Authority>();
		// authorities.add(authority);

		// result.setAuthorities(authorities);
		result.addAuthority(authority);

		return result;
	}

	public void chargeTodaysDisplays() {
		Collection<Banner> all;
		DisplayPrice displayPrice;
		double price;
		double tax;

		all = bannerService.findAll();
		displayPrice = displayPriceService.findDisplayPrice();
		price = displayPrice.getPrice();
		tax = displayPrice.getTax();
		for (Banner b : all) {
			if (b.getDayDisplays() > 0) {
				ChargeRecord temp = chargeRecordService.create();
				double tempPrice = (((tax / 100) * price) + price)
						* b.getDayDisplays();
				temp.setPrice(tempPrice);
				temp.setTax(tax);
				temp.setCreditCard(b.getCampaign().getCreditCard());
				chargeRecordService.save(temp);
				b.setDayDisplays(0);
			}
		}
	}

	public Integer userRegistered() {
		Integer result;
		
		result=administratorRepository.numberOfUsers();
				
		return result;
	}

	public Integer numbersTrip() {
		Integer result;
		
		result=administratorRepository.numberOfTrips();
		return result;
	}

	public Double standardDevTripsPerUser() {
		Double result;
				
		result=administratorRepository.standardDevTripsPerUser();
		
		return result;
	}

	public Double avgTripsPerUser() {
			Double result;
			result= administratorRepository.avgTripsPerUser();
		return result;
	}

	public Collection<String> usersWithMoreThan80PMaxTripsRegistered() {
		Collection<String> result;
		Collection<User> usersWithMoreThan80PMaxTripsRegistered;
		result=new ArrayList<String>();
		usersWithMoreThan80PMaxTripsRegistered=administratorRepository.usersWithMoreThan80PMaxTripsRegistered();
		for(User u:usersWithMoreThan80PMaxTripsRegistered){
			result.add(u.getName());
		}
		
		return result;
	}

	public Double avgDailyPlansPerUser() {
		Double result;
		result=administratorRepository.avgDailyPlansPerUser();
		return result;
	}

	public Double standardDevDailyPlansPerUser() {
		Double result;
		result=administratorRepository.standardDevDailyPlansPerUser();
		return result;
	}

	public Collection<Double> minMaxAvgCampaignsPerManager() {
		Collection<Double> result;
		result=administratorRepository.minMaxAvgCampaignsPerManager();
			
		return result;
	}

	public Double avgAmountMoneyPerCampaign() {
		Double result;
		result=administratorRepository.avgAmountMoneyPerCampaign();
		return result;
	}

	public Collection<String> managerWithMoreCampaigns() {
		Collection<String> result;
		Collection<Manager> managerWithMoreCampaigns;
		result=new ArrayList<String>();
		managerWithMoreCampaigns=administratorRepository.managerWithMoreCampaigns();
		for(Manager m:managerWithMoreCampaigns){
			result.add(m.getName());
		}
		return result;
	}

	public Collection<Banner> activeBannersDisplayedMoreThan10PAvg() {
		Collection<Banner>result;
		result=administratorRepository.activeBannersDisplayedMoreThan10PAvg();
		return result;
	}

	public Collection<Banner> activeBannersDisplayedLessThan10PAvg() {
		Collection<Banner>result;
		result=administratorRepository.activeBannersDisplayedLessThan10PAvg();
		return result;
	}

	public Collection<Double> avgAndStandardDevDaysThatCampaignsLast() {
		Collection<Double> result;
		result=administratorRepository.avgAndStandardDevDaysThatCampaignsLast();
		return result;
	}

	public Collection<String> userInactiveMoreOneyear() {
		// TODO Service and repository of userInactiveMoreOneyear
		return null;
	}
}