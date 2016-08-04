/* AdministratorController.java
 *
 * Copyright (C) 2013 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 * 
 */

package controllers;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.AdministratorService;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

	// Supported Services -----------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	// Action-1 ---------------------------------------------------------------

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("administrator/action-2");

		return result;
	}

	// Dashboard --------------------------------------------------------------

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;

		Collection<Object[]> languagesAndCountOfExchangesInvolved;
		Collection<Object[]> polyglotsAndCountOfExchangesOrganised;
		Collection<Object[]> polyglotsAndCountOfExchangesJoined;
		Collection<Object[]> languageExchangesAndCountOfSponsorships;
		Collection<Object[]> polyglotsAndCountOfSponsoredExchanges;
		Collection<Object[]> avgMinMaxSponsoredExchangesPerPolyglot;

		languagesAndCountOfExchangesInvolved = administratorService
				.languagesAndCountOfExchangesInvolved();
		polyglotsAndCountOfExchangesOrganised = administratorService
				.polyglotsAndCountOfExchangesOrganised();
		polyglotsAndCountOfExchangesJoined = administratorService
				.polyglotsAndCountOfExchangesJoined();
		languageExchangesAndCountOfSponsorships = administratorService
				.languageExchangesAndCountOfSponsorships();
		polyglotsAndCountOfSponsoredExchanges = administratorService
				.polyglotsAndCountOfSponsoredExchanges();
		avgMinMaxSponsoredExchangesPerPolyglot = administratorService
				.avgMinMaxSponsoredExchangesPerPolyglot();

		result = new ModelAndView("administrator/dashboard");
		result.addObject("languagesAndCountOfExchangesInvolved",
				languagesAndCountOfExchangesInvolved);
		result.addObject("polyglotsAndCountOfExchangesOrganised",
				polyglotsAndCountOfExchangesOrganised);
		result.addObject("polyglotsAndCountOfExchangesJoined",
				polyglotsAndCountOfExchangesJoined);
		result.addObject("languageExchangesAndCountOfSponsorships",
				languageExchangesAndCountOfSponsorships);
		result.addObject("polyglotsAndCountOfSponsoredExchanges",
				polyglotsAndCountOfSponsoredExchanges);
		result.addObject("avgMinMaxSponsoredExchangesPerPolyglot",
				avgMinMaxSponsoredExchangesPerPolyglot);

		return result;
	}

}