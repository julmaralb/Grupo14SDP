package services;

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
}