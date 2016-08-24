package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.PolyglotRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Actor;
import domain.Folder;
import domain.LanguageExchange;
import domain.Polyglot;
import forms.PolyglotForm;

@Service
@Transactional
public class PolyglotService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private PolyglotRepository polyglotRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	@Autowired
	private FolderService folderService;

	// Constructors -----------------------------------------------------------

	public PolyglotService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Polyglot create() {
		Polyglot result;
		UserAccount userAccount;

		userAccount = createUserAccount();
		result = new Polyglot();
		userAccount.setIsNotBanned(true);

		result.setUserAccount(userAccount);

		return result;
	}

	public Polyglot findOne(int polyglotId) {
		Assert.notNull(polyglotId);

		Polyglot result;

		result = polyglotRepository.findOne(polyglotId);

		return result;

	}

	public Collection<Polyglot> findAll() {

		Collection<Polyglot> result;

		result = polyglotRepository.findAll();

		return result;
	}

	public void save(Polyglot polyglot) {
		Assert.notNull(polyglot.getUserAccount().getUsername());
		Assert.notNull(polyglot.getUserAccount().getPassword());
		Assert.notNull(polyglot);
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		if (polyglot.getId() == 0) {
			Assert.isTrue(!actorService.checkAuthority("ADMIN")
					&& !actorService.checkAuthority("AGENT")
					&& !actorService.checkAuthority("POLYGLOT"));

			String password;
			password = polyglot.getUserAccount().getPassword();
			password = encoder.encodePassword(password, null);
			polyglot.getUserAccount().setPassword(password);
			Collection<Folder> folders;

			folders = folderService.initializeFolders(polyglot);
			polyglot.setFolders(folders);
		}
		polyglotRepository.save(polyglot);
	}

	public void delete(Polyglot polyglot) {
		Assert.notNull(polyglot);

		polyglotRepository.delete(polyglot);

	}

	// Other business methods -------------------------------------------------

	public Polyglot findByPrincipal() {
		Polyglot result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public Polyglot findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Polyglot result;

		result = polyglotRepository.findByUserAccountId(userAccount.getId());
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
		authority.setAuthority("POLYGLOT");
		// authorities = new ArrayList<Authority>();
		// authorities.add(authority);

		// result.setAuthorities(authorities);
		result.addAuthority(authority);

		return result;
	}

	public Polyglot reconstruct(PolyglotForm polyglotForm) {
		Polyglot result;

		Collection<LanguageExchange> organisedLanguageExchanges;
		Collection<LanguageExchange> participatedLanguageExchanges;

		organisedLanguageExchanges = new ArrayList<LanguageExchange>();
		participatedLanguageExchanges = new ArrayList<LanguageExchange>();

		result = create();
		result.getUserAccount().setPassword(polyglotForm.getPassword());
		result.getUserAccount().setUsername(polyglotForm.getUsername());

		result.setId(0);
		result.setVersion(0);
		result.setEmail(polyglotForm.getEmail());
		result.setName(polyglotForm.getName());
		result.setPhone(polyglotForm.getPhone());
		result.setSurname(polyglotForm.getUsername());
		result.setOrganisedLanguageExchanges(organisedLanguageExchanges);
		result.setParticipatedLanguageExchanges(participatedLanguageExchanges);

		if (polyglotForm.getTermsAccepted() == false) {
			throw new IllegalArgumentException(
					"You must accept the terms and condiditions");
		}
		if (!polyglotForm.getPassword()
				.equals(polyglotForm.getSecondPassword())) {
			throw new IllegalArgumentException("Passwords must match");
		}
		if (polyglotForm.getEmail() == "" && polyglotForm.getPhone() == "") {
			throw new IllegalArgumentException(
					"Must have a contact mean either phone or email");
		}
		return result;
	}

	public void modifyProfile(Polyglot polyglot) {
		Assert.notNull(polyglot);

		Actor result = actorService.findByPrincipal();
		String name;
		String surname;
		String phone;
		String email;

		phone = polyglot.getPhone();
		surname = polyglot.getSurname();
		name = polyglot.getName();
		email = polyglot.getEmail();

		result.setPhone(phone);
		result.setSurname(surname);
		result.setName(name);
		result.setEmail(email);
		
		if(result.getEmail() == "" && result.getPhone() == ""){
			throw new IllegalArgumentException("Must have a contact mean either phone or email");
		}
	}
}