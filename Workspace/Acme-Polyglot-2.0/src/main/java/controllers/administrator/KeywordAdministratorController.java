package controllers.administrator;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.KeywordService;
import controllers.AbstractController;
import domain.Keyword;

@Controller
@RequestMapping("/keyword/administrator")
public class KeywordAdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private KeywordService keywordService;

	// Constructors -----------------------------------------------------------

	public KeywordAdministratorController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Object[]> keywords;

		keywords = keywordService.generalKeywordsStatistics();

		result = new ModelAndView("keyword/list");
		result.addObject("keywords", keywords);
		result.addObject("requestURI", "keyword/administrator/list.do");

		return result;
	}

	@RequestMapping(value = "/listByActor", method = RequestMethod.GET)
	public ModelAndView listByActor(@RequestParam int actorId) {

		ModelAndView result;
		Collection<Keyword> keywords;

		keywords = keywordService.findAllByActorId(actorId);

		result = new ModelAndView("keyword/list2");
		result.addObject("keywords", keywords);
		result.addObject("requestURI", "keyword/administrator/listByActor.do");

		return result;
	}

}
