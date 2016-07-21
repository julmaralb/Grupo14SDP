package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ChargeRecordService;
import controllers.AbstractController;
import domain.ChargeRecord;

@Controller
@RequestMapping("/chargeRecord/administrator")
public class ChargeRecordAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ChargeRecordService chargeRecordService;

	// Constructors -----------------------------------------------------------

	public ChargeRecordAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {
		ModelAndView result;
		Collection<ChargeRecord> chargeRecords;

		chargeRecords = chargeRecordService.findAllFromToday();

		result = new ModelAndView("chargeRecord/list");
		result.addObject("requestURI", "chargeRecord/administrator/list.do");
		result.addObject("chargeRecords", chargeRecords);

		return result;
	}
}