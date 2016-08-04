package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CustomerRepository;
import security.Authority;
import security.LoginService;
import security.UserAccount;
import domain.Customer;
import forms.CustomerForm;

@Service
@Transactional
public class CustomerService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CustomerRepository customerRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	// Simple CRUD methods ----------------------------------------------------

	public Customer create() {
		Customer result;
		UserAccount userAcount;

		userAcount = createByCustomer();
		result = new Customer();

		result.setUserAccount(userAcount);

		return result;
	}

	public Customer findOne(int customerId) {
		Assert.notNull(customerId);

		Customer result;

		result = customerRepository.findOne(customerId);

		return result;
	}

	public Collection<Customer> findAll() {
		Collection<Customer> result;

		result = customerRepository.findAll();

		return result;
	}

	public void save(Customer customer) {
		Assert.notNull(customer);
		Md5PasswordEncoder encoder;
		encoder = new Md5PasswordEncoder();

		String password;
		password = customer.getUserAccount().getPassword();
		password = encoder.encodePassword(password, null);
		customer.getUserAccount().setPassword(password);

		customerRepository.save(customer);
	}

	public void delete(Customer customer) {
		Assert.notNull(customer);

		customerRepository.delete(customer);
	}

	// Other business methods -------------------------------------------------

	public Customer findByPrincipal() {
		Customer result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public Customer findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Customer result;

		result = customerRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}

	// Register methods -------------------------------------------------------

	public UserAccount createByCustomer() {
		UserAccount result;
		// Collection<Authority> authorities;
		Authority authority;

		result = new UserAccount();
		// result.setUsername("");
		// result.setPassword("");

		authority = new Authority();
		authority.setAuthority("CUSTOMER");
		// authorities = new ArrayList<Authority>();
		// authorities.add(authority);

		// result.setAuthorities(authorities);
		result.addAuthority(authority);

		return result;
	}

	public Collection<Customer> findPlayersByOpenMatchId(int openMatchId) {
		Collection<Customer> result;

		result = customerRepository.findPlayersByOpenMatchId(openMatchId);

		return result;
	}

	public void modifyProfile(Customer customer) {

		Assert.notNull(customer);

		Customer result = findByPrincipal();
		String name;
		String surname;
		String phone;
		String email;
		String address;

		phone = customer.getPhone();
		surname = customer.getSurname();
		name = customer.getName();
		email = customer.getEmail();
		address = customer.getAddress();

		result.setPhone(phone);
		result.setSurname(surname);
		result.setName(name);
		result.setEmail(email);
		result.setAddress(address);

	}

	public Customer reconstruct(CustomerForm customerForm) {
		Customer result;

		result = create();
		result.getUserAccount().setPassword(customerForm.getPassword());
		result.getUserAccount().setUsername(customerForm.getUsername());

		result.setId(0);
		result.setVersion(0);
		result.setAddress(customerForm.getAddress());
		result.setEmail(customerForm.getEmail());
		result.setName(customerForm.getName());
		result.setPhone(customerForm.getPhone());
		result.setSurname(customerForm.getUsername());

		if (customerForm.getTermsAccepted() == false) {
			throw new IllegalArgumentException(
					"You must accpet the terms and condiditions");
		}
		if (!customerForm.getPassword()
				.equals(customerForm.getSecondPassword())) {
			throw new IllegalArgumentException("Passwords must match");
		}
		return result;
	}
}