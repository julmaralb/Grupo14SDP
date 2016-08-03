/* CustomerController.java
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import services.CustomerService;
import domain.Customer;
import forms.CustomerForm;

@Controller
@RequestMapping("/customer")
public class CustomerController extends AbstractController {

	// Services --------------------------------------------------------

	@Autowired
	private CustomerService customerService;

	// Constructors -----------------------------------------------------------

	public CustomerController() {
		super();
	}

	// Action-1 ---------------------------------------------------------------

	@RequestMapping("/action-1")
	public ModelAndView action1() {
		ModelAndView result;

		result = new ModelAndView("customer/action-1");

		return result;
	}

	// Action-2 ---------------------------------------------------------------

	@RequestMapping("/action-2")
	public ModelAndView action2() {
		ModelAndView result;

		result = new ModelAndView("customer/action-2");

		return result;
	}

	// Listing ---------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Customer> customers;

		customers = customerService.findAll();

		result = new ModelAndView("customer/list");
		result.addObject("customers", customers);
		result.addObject("requestURI", "customer/list.do");

		return result;
	}

	@RequestMapping(value = "/listPlayers", method = RequestMethod.GET)
	public ModelAndView listPlayers(@RequestParam int openMatchId) {

		ModelAndView result;
		Collection<Customer> customers;

		customers = customerService.findPlayersByOpenMatchId(openMatchId);

		result = new ModelAndView("customer/list");
		result.addObject("customers", customers);
		result.addObject("requestURI", "customer/listPlayers.do");

		return result;
	}

	// Creation --------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		CustomerForm customerForm;

		customerForm = new CustomerForm();
		result = createEditModelAndView(customerForm);

		return result;
	}

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public ModelAndView terms() {
		ModelAndView result;

		result = new ModelAndView("customer/terms");

		return result;
	}

	// Edition ---------------------------------------------------------

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Customer customer;

		customer = customerService.findByPrincipal();
		Assert.notNull(customer);

		res = new ModelAndView("customer/modifyProfile");
		res.addObject("customer", customer);

		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid CustomerForm customerForm,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(customerForm);
		} else {
			try {
				Customer customer = customerService.reconstruct(customerForm);
				customerService.save(customer);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(customerForm,
						"customer.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView modifyProfile(@Valid Customer customer,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView2(customer, null);
		} else {
			try {
				customerService.modifyProfile(customer);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView2(customer, "user.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods -----------------------------------------------

	protected ModelAndView createEditModelAndView(CustomerForm customerForm) {

		ModelAndView result;

		result = createEditModelAndView(customerForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(CustomerForm customerForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("customer/edit");
		result.addObject("customerForm", customerForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView2(Customer customer,
			String message) {
		ModelAndView res;

		res = new ModelAndView("customer/modifyProfile");
		res.addObject("customer", customer);
		res.addObject("message", message);

		return res;
	}
}