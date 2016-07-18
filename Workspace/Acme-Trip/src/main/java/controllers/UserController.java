package controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import services.ActorService;
import services.UserService;
import domain.Actor;
import domain.User;
import forms.UserForm;

@Controller
@RequestMapping("/user")
public class UserController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private UserService userService;

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public UserController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<User> users;

		users = userService.findAll();

		result = new ModelAndView("user/list");
		result.addObject("users", users);
		result.addObject("requestURI", "user/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		UserForm userForm;

		userForm = new UserForm();
		result = createEditModelAndView(userForm);

		return result;
	}

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public ModelAndView terms() {
		ModelAndView result;

		result = new ModelAndView("user/terms");

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Actor user;

		user = actorService.findByPrincipal();
		Assert.notNull(user);

		res = new ModelAndView("user/modifyProfile");
		res.addObject("user", user);

		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid UserForm userForm, BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(userForm);
		} else {
			try {
				User user = userService.reconstruct(userForm);
				userService.save(user);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(userForm, "user.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView modifyProfile(@Valid User user, BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView2(user, null);
		} else {
			try {
				userService.modifyProfile(user);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView2(user, "user.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(UserForm userForm) {

		ModelAndView result;

		result = createEditModelAndView(userForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(UserForm userForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("user/edit");
		result.addObject("userForm", userForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView2(User user, String message) {
		ModelAndView res;

		res = new ModelAndView("user/modifyProfile");
		res.addObject("user", user);
		res.addObject("message", message);

		return res;
	}
}