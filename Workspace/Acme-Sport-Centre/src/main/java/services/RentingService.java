package services;

import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.RentingRepository;
import domain.Court;
import domain.CreditCard;
import domain.Customer;
import domain.Renting;
import domain.Reservation;
import domain.SportEquipment;

@Service
@Transactional
public class RentingService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private RentingRepository rentingRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ReservationService reservationService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public RentingService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Renting create() {
		Assert.isTrue(actorService.checkAuthority("CUSTOMER"));
		Renting result;
		String code;
		Customer principal;

		result = new Renting();
		code = getAleatoryCode();
		principal = customerService.findByPrincipal();

		result.setCode(code);
		result.setCustomer(principal);

		return result;
	}

	public Renting findOne(int rentingId) {
		Assert.notNull(rentingId);

		Renting result;

		result = rentingRepository.findOne(rentingId);

		return result;

	}

	public Collection<Renting> findAll() {

		Collection<Renting> result;

		result = rentingRepository.findAll();

		return result;
	}

	public void save(Renting renting) {
		Assert.notNull(renting);
		Customer principal;
		Assert.isTrue(checkCanRent(renting));

		principal = customerService.findByPrincipal();
		renting.setCustomer(principal);

		// Validate Credit Card

		CreditCard cc;
		boolean valida;

		cc = renting.getCreditCard();
		valida = reservationService.validExpCreditCard(cc);

		Assert.isTrue(valida);

		rentingRepository.save(renting);
	}

	public void delete(Renting renting) {
		Assert.notNull(renting);

		rentingRepository.delete(renting);

	}

	// Other business methods -------------------------------------------------

	public static String getAleatoryCode() {
		String result = "";
		Random random = new Random();
		int letra;
		Boolean f;

		while (result.length() < 6) {
			result += Integer.toString(random.nextInt(10));
		}

		result += "-";

		while (result.length() < 11) {
			if (Math.random() < 0.51) {
				result += Integer.toString(random.nextInt(10));
			} else {
				f = true;
				while (f) {
					letra = random.nextInt(102);
					if ((letra > 96 && letra < 102)) {
						result += (char) letra;
						f = false;
					}
				}
			}
		}
		return result;
	}

	public Collection<Renting> findAllByPrincipal() {
		Collection<Renting> result;
		Customer principal;

		principal = customerService.findByPrincipal();
		result = rentingRepository.findAllByCustomerId(principal.getId());

		return result;
	}

	public boolean checkCanRent(Renting renting) {
		boolean res = false;
		SportEquipment sportEquipment;
		Court court;
		Collection<Reservation> reservations;
		Customer principal;

		sportEquipment = renting.getSportEquipment();
		court = sportEquipment.getCourt();
		principal = customerService.findByPrincipal();
		reservations = principal.getReservations();

		for (Reservation r : reservations) {
			if (r.getCourt().equals(court)) {
				res = true;
				break;
			}
		}
		return res;
	}
}