package controllers.manager;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.ChargeRecordService;
import controllers.AbstractController;
import domain.ChargeRecord;

@Controller
@RequestMapping("/chargeRecord/manager")
public class ChargeRecordManagerController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private ChargeRecordService chargeRecordService;

	// Constructors -----------------------------------------------------------

	public ChargeRecordManagerController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(@RequestParam int creditCardId) {
		ModelAndView result;
		Collection<ChargeRecord> chargeRecords;

		chargeRecords = chargeRecordService
				.findAllByCreditCardIdAndPrincipal(creditCardId);

		result = new ModelAndView("chargeRecord/list");
		result.addObject("requestURI", "chargeRecord/manager/list.do");
		result.addObject("chargeRecords", chargeRecords);

		return result;
	}
}