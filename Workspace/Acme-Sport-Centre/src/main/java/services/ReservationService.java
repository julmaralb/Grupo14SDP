package services;

import java.util.Calendar;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ReservationRepository;
import domain.Court;
import domain.CreditCard;
import domain.Customer;
import domain.Day;
import domain.HourRange;
import domain.Reservation;

@Service
@Transactional
public class ReservationService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private ReservationRepository reservationRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private DayService dayService;

	@Autowired
	private HourRangeService hourRangeService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private CourtService courtService;
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public ReservationService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Reservation create() {
		Reservation result;
		String code;

		result = new Reservation();
		code = getAleatoryCode();

		result.setCode(code);

		return result;
	}

	public Reservation findOne(int reservationId) {
		Assert.notNull(reservationId);

		Reservation result;

		result = reservationRepository.findOne(reservationId);

		return result;
	}

	public Collection<Reservation> findAll() {

		Collection<Reservation> result;

		result = reservationRepository.findAll();

		return result;
	}

	public void save(Reservation reservation, int hourRangeId) {
		Assert.notNull(reservation);

		HourRange hourRange;

		hourRange = hourRangeService.findOne(hourRangeId);
		Day day = dayService.findByHourRangeId(hourRangeId);
		Assert.isTrue(reservation.getCourt().getDays().contains(day));
		Assert.isTrue(day.getHourRanges().contains(hourRange));

		// Validate Credit Card

		CreditCard cc;
		boolean valida;

		cc = reservation.getCreditCard();
		valida = validExpCreditCard(cc);

		Assert.isTrue(valida);

		reservationRepository.save(reservation);
		Assert.isTrue(hourRange.isAvailable() == true);
		hourRange.setAvailable(false);
	}

	public void delete(Reservation reservation) {
		Assert.notNull(reservation);

		reservationRepository.delete(reservation);
	}

	// Other business methods -------------------------------------------------

	public Collection<Reservation> findByCustomerId() {
		Collection<Reservation> result;
		Customer principal;
		int customerId;

		principal = customerService.findByPrincipal();
		customerId = principal.getId();

		result = reservationRepository.findByCustomerId(customerId);

		return result;
	}

	public void bookCourt(int courtId, int hourRangeId) {
		Reservation reservation;
		Court court;
		Day day;
		HourRange hourRange;

		reservation = create();
		day = dayService.findByHourRangeId(hourRangeId);
		hourRange = hourRangeService.findOne(hourRangeId);
		Assert.isTrue(hourRange.isAvailable() == true);
		court = courtService.findOne(courtId);

		reservation.setDay(day.getDay());
		reservation.setStart(hourRange.getStart());
		reservation.setEnd(hourRange.getEnd());
		reservation.setCustomer(customerService.findByPrincipal());
		reservation.setCourt(court);

		// Validate Credit Card

		// CreditCard cc;
		// boolean valida;
		//
		// cc = reservation.getCreditCard();
		// valida = validExpCreditCard(cc);
		//
		// Assert.isTrue(valida);

		// save(reservation);

		// hourRange.setAvailable(false);
	}

	public Reservation createReservation(int courtId, int hourRangeId, int dayId) {
		Assert.isTrue(actorService.checkAuthority("CUSTOMER"));
		Reservation result;
		Court court;
		HourRange hourRange;
		Day day;

		result = create();
		day = dayService.findOne(dayId);

		hourRange = hourRangeService.findOne(hourRangeId);
		Assert.isTrue(hourRange.isAvailable() == true);
		court = courtService.findOne(courtId);

		result.setDay(day.getDay());
		result.setStart(hourRange.getStart());
		result.setEnd(hourRange.getEnd());
		result.setCustomer(customerService.findByPrincipal());
		result.setCourt(court);

		return result;
	}

	public Collection<Reservation> findAllNotUsedByCustomerId() {
		Collection<Reservation> result;
		Customer principal;
		int customerId;

		principal = customerService.findByPrincipal();
		customerId = principal.getId();
		result = reservationRepository.findAllNotUsedByCustomerId(customerId);

		return result;
	}

	public boolean validExpCreditCard(CreditCard creditCard) {
		boolean result;
		Calendar currentDate;

		currentDate = Calendar.getInstance();
		result = false;

		if (creditCard.getExpYear() > currentDate.get(Calendar.YEAR)) {
			result = true;
		}
		if (creditCard.getExpYear() == currentDate.get(Calendar.YEAR)) {
			if (creditCard.getExpMonth() > currentDate.get(Calendar.MONTH)) {
				result = true;
			}
		}
		return result;
	}

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
}