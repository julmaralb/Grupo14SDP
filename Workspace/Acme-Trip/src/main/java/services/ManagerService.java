package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Folder;
import domain.Manager;

@Service
@Transactional
public class ManagerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ManagerRepository managerRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private FolderService folderService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public ManagerService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Manager create() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Manager result;

		result = new Manager();
		UserAccount userAccount;
		userAccount = createManagerAccount();
		result.setUserAccount(userAccount);

		return result;
	}

	public Manager findOne(int managerId) {
		Assert.notNull(managerId);

		Manager result;

		result = managerRepository.findOne(managerId);

		return result;
	}

	public Collection<Manager> findAll() {

		Collection<Manager> result;

		result = managerRepository.findAll();

		return result;
	}

	public void save(Manager manager) {
		Assert.notNull(manager);
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		String password;
		password = manager.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		manager.getUserAccount().setPassword(password);
		Collection<Folder> folders;
		folders = folderService.initializeFolders(manager);
		manager.setFolders(folders);

		managerRepository.save(manager);
	}

	public void delete(Manager manager) {
		Assert.notNull(manager);

		managerRepository.delete(manager);
	}

	// Other business methods -------------------------------------------------

	public Manager findByPrincipal() {
		Manager result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public Manager findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Manager result;

		result = managerRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

	public UserAccount createManagerAccount() {
		UserAccount result;
		// Collection<Authority> authorities;
		Authority authority;

		result = new UserAccount();
		// result.setUsername("");
		// result.setPassword("");

		authority = new Authority();
		authority.setAuthority("MANAGER");
		// authorities = new ArrayList<Authority>();
		// authorities.add(authority);

		// result.setAuthorities(authorities);
		result.addAuthority(authority);

		return result;
	}
}