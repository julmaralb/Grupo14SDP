package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.UserRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Activity;
import domain.Actor;
import domain.Folder;
import domain.Trip;
import domain.User;
import forms.UserForm;

@Service
@Transactional
public class UserService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private UserRepository userRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private FolderService folderService;

	// Constructors -----------------------------------------------------------

	public UserService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public User create() {
		User result;
		UserAccount userAccount;

		userAccount = createUserAccount();
		result = new User();

		result.setUserAccount(userAccount);

		return result;
	}

	public User findOne(int userId) {
		Assert.notNull(userId);

		User result;

		result = userRepository.findOne(userId);

		return result;

	}

	public Collection<User> findAll() {

		Collection<User> result;

		result = userRepository.findAll();

		return result;
	}

	public void save(User user) {
		Assert.notNull(user);
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		if (user.getId() == 0) {

			String password;
			password = user.getUserAccount().getPassword();
			password = encoder.encodePassword(password, null);
			user.getUserAccount().setPassword(password);

			Collection<Folder> folders;

			folders = folderService.initializeFolders(user);
			user.setFolders(folders);

		}
		userRepository.save(user);
	}

	public void delete(User user) {
		Assert.notNull(user);

		userRepository.delete(user);

	}

	// Other business methods -------------------------------------------------

	public User findByPrincipal() {
		User result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public User findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		User result;

		result = userRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

	// Register methods -------------------------------------------------------

	public UserAccount createUserAccount() {
		UserAccount result;
		// Collection<Authority> authorities;
		Authority authority;

		result = new UserAccount();
		// result.setUsername("");
		// result.setPassword("");

		authority = new Authority();
		authority.setAuthority("USER");
		// authorities = new ArrayList<Authority>();
		// authorities.add(authority);

		// result.setAuthorities(authorities);
		result.addAuthority(authority);

		return result;
	}

	public User reconstruct(UserForm userForm) {
		User result;

		Collection<Activity> activities;
		Collection<Trip> trips;
		Collection<Folder> folders;

		activities = new ArrayList<Activity>();
		trips = new ArrayList<Trip>();

		result = create();
		folders = folderService.initializeFolders(result);
		result.getUserAccount().setPassword(userForm.getPassword());
		result.getUserAccount().setUsername(userForm.getUsername());

		result.setId(0);
		result.setVersion(0);
		result.setEmail(userForm.getEmail());
		result.setName(userForm.getName());
		result.setPhone(userForm.getPhone());
		result.setSurname(userForm.getUsername());
		result.setTrips(trips);
		result.setActivities(activities);
		result.setFolders(folders);

		if (userForm.getTermsAccepted() == false) {
			throw new IllegalArgumentException(
					"You must accept the terms and condiditions");
		}
		if (!userForm.getPassword().equals(userForm.getSecondPassword())) {
			throw new IllegalArgumentException("Passwords must match");
		}
		return result;
	}

	public void modifyProfile(User user) {

		Assert.notNull(user);

		Actor result = actorService.findByPrincipal();
		String name;
		String surname;
		String phone;
		String email;

		phone = user.getPhone();
		surname = user.getSurname();
		name = user.getName();
		email = user.getEmail();

		result.setPhone(phone);
		result.setSurname(surname);
		result.setName(name);
		result.setEmail(email);
	}
}