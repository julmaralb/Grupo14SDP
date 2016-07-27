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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import security.Authority;
import security.UserAccount;
import services.AdministratorService;
import domain.Administrator;
import domain.Banner;

@Controller
@RequestMapping("/administrator")
public class AdministratorController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private AdministratorService administratorService;

	// Constructors -----------------------------------------------------------

	public AdministratorController() {
		super();
	}

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

	// Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		Administrator administrator;

		administrator = administratorService.create();
		result = createEditModelAndView(administrator);

		return result;
	}

	// Edition ---------------------------------------------------------

	@RequestMapping(value = "/edit", method = RequestMethod.GET)
	public ModelAndView edit(@RequestParam int adminId) {

		ModelAndView result;
		Administrator administatror;

		administatror = administratorService.findOne(adminId);
		Assert.notNull(administatror);
		result = createEditModelAndView(administatror);

		return result;
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid @ModelAttribute Administrator administrator,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(administrator);
		} else {
			try {
				administratorService.save(administrator);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView(administrator, "administrator.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods -----------------------------------------------

	protected ModelAndView createEditModelAndView(Administrator administrator) {

		ModelAndView result;

		result = createEditModelAndView(administrator, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(Administrator administrator, String message) {

		ModelAndView result;
		UserAccount userAccount;
		Collection<Authority> authorities;

		userAccount = administrator.getUserAccount();
		authorities = Authority.listAuthorities();
		result = new ModelAndView("administrator/edit");

		result.addObject("administrator", administrator);
		result.addObject("userAccount", userAccount);

		result.addObject("authorities", authorities);
		result.addObject("message", message);

		return result;
	}
	
	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;
		Integer userRegistered;
		Integer numbersTrip;
		Double avgTripsPerUser;
		Double standardDevTripsPerUser;
		Collection<String> usersWithMoreThan80PMaxTripsRegistered;
		Double avgDailyPlansPerUser;
		Double standardDevDailyPlansPerUser;
		Collection<Double> minMaxAvgCampaignsPerManager;
		Double avgAmountMoneyPerCampaign;
		Collection<Double> avgAndStandardDevDaysThatCampaignsLast;
		Collection<String>managerWithMoreCampaigns;
		Collection<Banner>activeBannersDisplayedMoreThan10PAvg;
		Collection<Banner>activeBannersDisplayedLessThan10PAvg;
		Collection<String>userInactiveMoreOneyear;
		
		userRegistered= administratorService.userRegistered();
		result=new ModelAndView("administrator/dashboard");
		numbersTrip=administratorService.numbersTrip();
		avgTripsPerUser=administratorService.avgTripsPerUser();
		standardDevTripsPerUser=administratorService.standardDevTripsPerUser();
		usersWithMoreThan80PMaxTripsRegistered=administratorService.usersWithMoreThan80PMaxTripsRegistered();
		avgDailyPlansPerUser=administratorService.avgDailyPlansPerUser();
		standardDevDailyPlansPerUser=administratorService.standardDevDailyPlansPerUser();
		minMaxAvgCampaignsPerManager=administratorService.minMaxAvgCampaignsPerManager();
		avgAmountMoneyPerCampaign=administratorService.avgAmountMoneyPerCampaign();
		managerWithMoreCampaigns=administratorService.managerWithMoreCampaigns();
		activeBannersDisplayedMoreThan10PAvg=administratorService.activeBannersDisplayedMoreThan10PAvg();
		activeBannersDisplayedLessThan10PAvg=administratorService.activeBannersDisplayedLessThan10PAvg();
		avgAndStandardDevDaysThatCampaignsLast=administratorService.avgAndStandardDevDaysThatCampaignsLast();
		userInactiveMoreOneyear=administratorService.userInactiveMoreOneyear();
		
		result.addObject("userRegistered", userRegistered);
		result.addObject("numbersTrip", numbersTrip);
		result.addObject("avgTripsPerUser",avgTripsPerUser);
		result.addObject("standardDevTripsPerUser", standardDevTripsPerUser);
		result.addObject("usersWithMoreThan80PMaxTripsRegistered", usersWithMoreThan80PMaxTripsRegistered);
		result.addObject("avgDailyPlansPerUser", avgDailyPlansPerUser);
		result.addObject("standardDevDailyPlansPerUser", standardDevDailyPlansPerUser);
		result.addObject("minMaxAvgCampaignsPerManager", minMaxAvgCampaignsPerManager);
		result.addObject("avgAmountMoneyPerCampaign", avgAmountMoneyPerCampaign);
		result.addObject("avgAndStandardDevDaysThatCampaignsLast",avgAndStandardDevDaysThatCampaignsLast);
		result.addObject("managerWithMoreCampaigns", managerWithMoreCampaigns);
		result.addObject("activeBannersDisplayedMoreThan10PAvg", activeBannersDisplayedMoreThan10PAvg);
		result.addObject("activeBannersDisplayedLessThan10PAvg", activeBannersDisplayedLessThan10PAvg);
		result.addObject("userInactiveMoreOneyear",userInactiveMoreOneyear);
		
		return result;
	}
}