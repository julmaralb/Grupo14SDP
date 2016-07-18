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

	// Services --------------------------------------------------------

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

	@RequestMapping(value = "/dashboard", method = RequestMethod.GET)
	public ModelAndView dashboard() {

		ModelAndView result;
		Double averageStudentPerSubject;
		Collection<String> studentRolesSubject;
		Double averageSubjectPerLecturer;
		Collection<String> subjectTeachLecturer;
		Double averageStudentPerGroup;
		Collection<String> groupStudent;
		Collection<String> lecturersUploadMoreLM;
		Double averageLearningMaterialPerGroup;
		Collection<String> subjectMoreLMAvailable;
		Double averageSocialIdentitiesPerActor;

		result = new ModelAndView("administrator/dashboard");

		averageStudentPerSubject = administratorService
				.avgNumberOfStudentsPerSubject();
		studentRolesSubject = administratorService.studentRolesSubject();
		averageSubjectPerLecturer = administratorService
				.averageSubjectPerLecturer();
		subjectTeachLecturer = administratorService.subjectTeachLecturer();
		averageStudentPerGroup = administratorService.averageStudentPerGroup();
		groupStudent = administratorService.groupStudent();
		lecturersUploadMoreLM = administratorService.lecturersUploadMoreLM();
		averageLearningMaterialPerGroup = administratorService
				.averageLearningMaterialPerGroup();
		subjectMoreLMAvailable = administratorService.subjectMoreLMAvailable();
		averageSocialIdentitiesPerActor = administratorService
				.averageSocialIdentitiesPerActor();

		result.addObject("averageStudentPerSubject", averageStudentPerSubject);
		result.addObject("studentRolesSubject", studentRolesSubject);
		result.addObject("averageSubjectPerLecturer", averageSubjectPerLecturer);
		result.addObject("subjectTeachLecturer", subjectTeachLecturer);
		result.addObject("averageStudentPerGroup", averageStudentPerGroup);
		result.addObject("groupStudent", groupStudent);
		result.addObject("lecturersUploadMoreLM", lecturersUploadMoreLM);
		result.addObject("averageLearningMaterialPerGroup",
				averageLearningMaterialPerGroup);
		result.addObject("subjectMoreLMAvailable", subjectMoreLMAvailable);
		result.addObject("averageSocialIdentitiesPerActor",
				averageSocialIdentitiesPerActor);

		return result;
	}

}