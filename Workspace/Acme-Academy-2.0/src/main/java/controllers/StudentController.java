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

import services.StudentService;
import domain.Student;
import forms.StudentForm;

@Controller
@RequestMapping("/student")
public class StudentController extends AbstractController {

	// Services ---------------------------------------------------------------

	@Autowired
	private StudentService studentService;

	// Constructors -----------------------------------------------------------

	public StudentController() {
		super();
	}

	// Listing ----------------------------------------------------------------

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list() {

		ModelAndView result;
		Collection<Student> students;

		students = studentService.findAll();

		result = new ModelAndView("student/list");
		result.addObject("students", students);
		result.addObject("requestURI", "student/list.do");

		return result;
	}

	// Creation ---------------------------------------------------------------

	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public ModelAndView create() {

		ModelAndView result;
		StudentForm studentForm;

		studentForm = new StudentForm();
		result = createEditModelAndView(studentForm);

		return result;
	}

	@RequestMapping(value = "/terms", method = RequestMethod.GET)
	public ModelAndView terms() {
		ModelAndView result;

		result = new ModelAndView("student/terms");

		return result;
	}

	// Edition ----------------------------------------------------------------

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.GET)
	public ModelAndView edit() {
		ModelAndView res;
		Student student;

		student = studentService.findByPrincipal();
		Assert.notNull(student);

		res = new ModelAndView("student/modifyProfile");
		res.addObject("student", student);

		return res;

	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST, params = "save")
	public ModelAndView save(@Valid StudentForm studentForm,
			BindingResult binding) {

		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView(studentForm);
		} else {
			try {
				Student student = studentService.reconstruct(studentForm);
				studentService.save(student);
				result = new ModelAndView("redirect:/security/login.do");
			} catch (Throwable oops) {
				result = createEditModelAndView(studentForm,
						"student.commit.error");
			}
		}
		return result;
	}

	@RequestMapping(value = "/modifyProfile", method = RequestMethod.POST, params = "save")
	public ModelAndView modifyProfile(@Valid Student student,
			BindingResult binding) {
		ModelAndView result;

		if (binding.hasErrors()) {
			result = createEditModelAndView2(student, null);
		} else {
			try {
				studentService.modifyProfile(student);
				result = new ModelAndView("redirect:/");
			} catch (Throwable oops) {
				result = createEditModelAndView2(student,
						"student.commit.error");
			}
		}
		return result;
	}

	// Ancillary methods ------------------------------------------------------

	protected ModelAndView createEditModelAndView(StudentForm studentForm) {

		ModelAndView result;

		result = createEditModelAndView(studentForm, null);

		return result;
	}

	protected ModelAndView createEditModelAndView(StudentForm studentForm,
			String message) {
		ModelAndView result;

		result = new ModelAndView("student/edit");
		result.addObject("studentForm", studentForm);
		result.addObject("message", message);

		return result;
	}

	protected ModelAndView createEditModelAndView2(Student student,
			String message) {
		ModelAndView res;

		res = new ModelAndView("student/modifyProfile");
		res.addObject("student", student);
		res.addObject("message", message);

		return res;
	}
}