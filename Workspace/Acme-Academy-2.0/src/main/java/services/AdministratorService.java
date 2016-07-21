package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import domain.Assignment;
import domain.Group;
import domain.Lecturer;
import domain.Student;
import domain.Subject;

import repositories.AdministratorRepository;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	AdministratorRepository administratorRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	// Other business methods -------------------------------------------------

	public Double avgNumberOfStudentsPerSubject() {
		Double result;

		result = administratorRepository.avgNumberOfStudentsPerSubject();

		return result;
	}

	public Collection<String> studentRolesSubject() {
		Collection<Student> studentRolesSubject;
		Collection<String> studentRolesSubject2;
		
		studentRolesSubject=administratorRepository.studentsEnrolledMoreOrLessThan20PAvg();
		studentRolesSubject2= new ArrayList<String>();
				
		for (Student s:studentRolesSubject){
			studentRolesSubject2.add(s.getName());
		}
		
		return studentRolesSubject2;
	}

	public Double averageSubjectPerLecturer() {
		Double result;
		
		result=administratorRepository.avgSubjectsLecturerTeaches();
		
		return result;
	}

	public Collection<String> subjectTeachLecturer() {
		Collection<Lecturer> lecturers;
		Collection<String> result;
		
		result=new ArrayList<String>();
		lecturers=administratorRepository.lecturersTeachMoreORLessThan20PAvg();
		for(Lecturer l:lecturers){
			result.add(l.getName());
		}
		
		return result;
	}

	public Double averageStudentPerGroup() {
		Double averageStudentPerGroup;
		averageStudentPerGroup=administratorRepository.avgStudentsPerGroup();
		return averageStudentPerGroup;
	}

	public Collection<String> groupStudent() {
		Collection<Group> groupStudent;
		Collection<String> result;
		
		groupStudent=administratorRepository.groupsEnrolledMoreOrLessThan20PAvg();
		result=new ArrayList<String>();
		for(Group g:groupStudent){
			result.add(g.getName());
		}
		
		return result;
	}

	public Collection<String> lecturersUploadMoreLM() {
		Collection<Lecturer> lecturersUploadMoreLM;
		Collection<String> result;
		
		result=new ArrayList<String>();
		
		lecturersUploadMoreLM=administratorRepository.lecturersWhoUploadMoreLearningMaterials();
		for(Lecturer l:lecturersUploadMoreLM){
			result.add(l.getName());
		}
		return result;
	}

	public Double averageLearningMaterialPerGroup() {
		Double result;
		
		result=administratorRepository.avgLearningMaterialsPerGroup();
		return result;
	}

	public Collection<String> subjectMoreLMAvailable() {
		Collection<Subject> subjectMoreLMAvailable;
		Collection<String> result;
		
		result=new ArrayList<String>();
		subjectMoreLMAvailable=administratorRepository.subjectsWithMoreLearningMaterials();
		for(Subject s:subjectMoreLMAvailable){
			result.add(s.getTitle());
		}
		return result;
	}

	public Double averageSocialIdentitiesPerActor() {
		Double result;
		result=administratorRepository.avgSocialIdentitiesPerActor();
		return result;
	}

	public Double avgSillabiPerSubject() {
		Double result;
		result= administratorRepository.avgSillabiPerSubject();
		
		return result;
	}

	public Double avgBibliographiesPerSyllabus() {
		Double result;
		result= administratorRepository.avgBibliographiesPerSyllabus();
		return result;
	}

	public Collection<String> subjectsWithLargestBibliography() {
		Collection<Subject> subjectsWithLargestBibliography;
		Collection<String> result;
		
		subjectsWithLargestBibliography=administratorRepository.subjectsWithLargestBibliography();
		
		result=new ArrayList<String>();
		for(Subject s:subjectsWithLargestBibliography){
			result.add(s.getTitle());
		}
		
		return result;
	}

	public Collection<String> assignmentsWithMoreOrLessThan20PAvgRubrics() {
		Collection<Assignment> assignmentsWithMoreOrLessThan20PAvgRubrics;
		Collection<String> result;
		
		assignmentsWithMoreOrLessThan20PAvgRubrics=administratorRepository.assignmentsWithMoreOrLessThan20PAvgRubrics();
		
		result=new ArrayList<String>();
		for(Assignment a:assignmentsWithMoreOrLessThan20PAvgRubrics){
			result.add(a.getTitle());
		}
		
		return result;
	}
	
	public Collection<String> lecturerWithMoreRubricsPerAssignment(){
		Collection<Lecturer> lecturerWithMoreRubricsPerAssignment;
		Collection<String> result;
		
		lecturerWithMoreRubricsPerAssignment=administratorRepository.lecturerWithMoreRubricsPerAssignment();
		
		result=new ArrayList<String>();
		for(Lecturer l:lecturerWithMoreRubricsPerAssignment){
			result.add(l.getName());
		}
	
		return result;
	}
	
	public Collection<String> lecturerWithLessRubricsPerAssignment(){
		Collection<Lecturer> lecturerWithLessRubricsPerAssignment;
		Collection<String> result;
		
		lecturerWithLessRubricsPerAssignment=administratorRepository.lecturerWithLessRubricsPerAssignment();
		
		result=new ArrayList<String>();
		for(Lecturer l:lecturerWithLessRubricsPerAssignment){
			result.add(l.getName());
		}
		
		return result;
	}
	
	
	
	

}
