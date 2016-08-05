package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SupervisorRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Supervisor;

@Service
@Transactional
public class SupervisorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SupervisorRepository supervisorRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public SupervisorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Supervisor create() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Supervisor result;

		result = new Supervisor();
		UserAccount userAccount;
		userAccount = createSupervisorAccount();
		result.setUserAccount(userAccount);

		return result;
	}

	public Supervisor findOne(int supervisorId) {
		Assert.notNull(supervisorId);

		Supervisor result;

		result = supervisorRepository.findOne(supervisorId);

		return result;
	}

	public Collection<Supervisor> findAll() {

		Collection<Supervisor> result;

		result = supervisorRepository.findAll();

		return result;
	}

	public void save(Supervisor supervisor) {
		Assert.notNull(supervisor);
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		String password;
		password = supervisor.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		supervisor.getUserAccount().setPassword(password);

		supervisorRepository.save(supervisor);

	}

	public void delete(Supervisor supervisor) {
		Assert.notNull(supervisor);

		supervisorRepository.delete(supervisor);
	}

	// Other business methods -------------------------------------------------

	public Supervisor findByPrincipal() {
		Supervisor result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public Supervisor findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Supervisor result;

		result = supervisorRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

	public UserAccount createSupervisorAccount() {
		UserAccount result;
		// Collection<Authority> authorities;
		Authority authority;

		result = new UserAccount();
		// result.setUsername("");
		// result.setPassword("");

		authority = new Authority();
		authority.setAuthority("SUPERVISOR");
		// authorities = new ArrayList<Authority>();
		// authorities.add(authority);

		// result.setAuthorities(authorities);
		result.addAuthority(authority);

		return result;
	}
}