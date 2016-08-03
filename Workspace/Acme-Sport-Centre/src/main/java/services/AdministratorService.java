package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Court;
import domain.Day;
import domain.HourRange;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------

	// Supporting services ----------------------------------------------------

	@Autowired
	private CourtService courtService;

	@Autowired
	private DayService dayService;

	@Autowired
	private HourRangeService hourRangeService;

	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	// Este método crea el día de hoy junto con todos sus horarios para todas las pistas del sistema.
	public void createTodaySchedules() {
		Collection<Court> courts;
		Calendar now = Calendar.getInstance();
		Date today;

		now.set(Calendar.HOUR_OF_DAY, 0);
		now.set(Calendar.MINUTE, 0);
		now.set(Calendar.SECOND, 0);

		today = now.getTime();

		// Comprobamos que el día de hoy no haya sido ya creado.
		if (dayService.findByDay(today).size() == 0) {

			courts = courtService.findAll();

			// Recorremos cada pista del sistema y le creamos a cada una el día de hoy.
			for (Court c : courts) {
				Collection<HourRange> hourRanges = new ArrayList<HourRange>();
				Day day;
				Date hora;
				Collection<Day> courtDays = c.getDays();
				day = dayService.create();
				day.setDay(today);
				day.setCourt(c);

				// Creamos para el día de hoy de cada pista todos los rangos horarios desde las 9 de la mañana a las 21 de la noche
				// en bloques de 1 hora.
				for (int i = 9; i <= 21; i++) {
					HourRange hr = hourRangeService.create();
					hr.setAvailable(true);
					now.set(Calendar.HOUR_OF_DAY, i);
					hora = now.getTime();
					hr.setStart(hora);
					now.set(Calendar.HOUR_OF_DAY, i + 1);
					hora = now.getTime();
					hr.setEnd(hora);
					hourRangeService.save(hr);
					hourRanges.add(hr);

				}
				day.setHourRanges(hourRanges);
				dayService.save(day);
				courtDays.add(day);
				c.setDays(courtDays);
			}
		}
	}
}