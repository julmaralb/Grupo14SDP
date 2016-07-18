package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CourtService;
import services.DayService;
import domain.Court;
import domain.Day;

@Controller
@RequestMapping("/reservation")
public class ReservationController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private CourtService courtService;

	@Autowired
	private DayService dayService;

	// Constructors -----------------------------------------------------------

	public ReservationController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping(value = "/schedule", method = RequestMethod.GET)
	public ModelAndView schedule(@RequestParam int dayId,
			@RequestParam int centreId) {
		ModelAndView result;
		// Collection<HourRange> hourRanges;
		Collection<Court> courts;
		Collection<Day> days;
		Day day;

		// hourRanges = hourRangeService.findAll();
		courts = courtService.findByCentreId(centreId);
		day = dayService.findOne(dayId);
		// days = dayService.findByDay(day.getDay());
		days = dayService.findByDayAndCentreId(day.getDay(), centreId);
		result = new ModelAndView("reservation/schedule");
		// result.addObject("hourRanges", hourRanges);
		result.addObject("courts", courts);
		result.addObject("day", day);
		result.addObject("days", days);
		result.addObject("requestURI", "reservation/schedule.do");

		return result;
	}
}