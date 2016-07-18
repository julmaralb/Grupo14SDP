package repositories;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import domain.Administrator;
import domain.Assignment;
import domain.Group;
import domain.Lecturer;
import domain.Subject;

@Repository
public interface AdministratorRepository extends
		JpaRepository<Administrator, Integer> {

	@Query("select a from Administrator a where a.userAccount.id = ?1")
	Administrator findByUserAccountId(int userAccountId);

	// Dashboard queries level C
	@Query("select DISTINCT(select count(e) from Subject s join s.groups g join g.students e)*1.00 / (select count(s) from Subject s) from Subject s")
	Double avgNumberOfStudentsPerSubject();

	@Query("select s from Student s join s.groups g group by s having count(g) < (select DISTINCT(select avg(s.groups.size) from Student s)-(select avg(s.groups.size)*0.2 from Student s) from Student s) OR count(g) > (select DISTINCT(select avg(s.groups.size) from Student s)+(select avg(s.groups.size)*0.2 from Student s) from Student s)")
	Collection<domain.Student> studentsEnrolledMoreOrLessThan20PAvg();

	@Query("select avg(l.subjects.size) from Lecturer l")
	Double avgSubjectsLecturerTeaches();

	@Query("select l from Lecturer l join l.subjects s group by l having count(s) < ((select avg(l.subjects.size) from Lecturer l) - (select avg(l.subjects.size)*0.2 from Lecturer l)) OR count(s) > ((select avg(l.subjects.size) from Lecturer l) + (select avg(l.subjects.size)*0.2 from Lecturer l))")
	Collection<Lecturer> lecturersTeachMoreORLessThan20PAvg();

	@Query("select avg(g.students.size) from Group g")
	Double avgStudentsPerGroup();

	@Query("select g from Group g join g.students s group by g having count(s) < ((select avg(g.students.size) from Group g) - (select avg(g.students.size)*0.2 from Group g)) OR count(s) > ((select avg(g.students.size) from Group g) + (select avg(g.students.size)*0.2 from Group g))")
	Collection<Group> groupsEnrolledMoreOrLessThan20PAvg();

	// Dashboard queries level B

	@Query("select l from Lecturer l where (select sum(g.learningMaterials.size) from Group g where g.subject.lecturer = l) >= ALL (select(select sum(g1.learningMaterials.size) from Group g1 where g1.subject.lecturer = l2) from Lecturer l2)")
	Collection<Lecturer> lecturersWhoUploadMoreLearningMaterials();

	@Query("select avg(g.learningMaterials.size) from Group g")
	Double avgLearningMaterialsPerGroup();

	@Query("select s from Subject s where (select sum(g.learningMaterials.size) from Group g where g.subject = s) >= ALL (select(select sum(g1.learningMaterials.size) from Group g1 where g1.subject = s2) from Subject s2)")
	Collection<Subject> subjectsWithMoreLearningMaterials();

	@Query("select avg(a.socialIdentities.size) from Actor a")
	Double avgSocialIdentitiesPerActor();

	// Dashboard queries version 2.0 level C

	@Query("select avg(s.syllabi.size) from Subject s")
	Double avgSillabiPerSubject();

	@Query("select avg(s.bibliographies.size) from Syllabus s")
	Double avgBibliographiesPerSyllabus();

	@Query("select s from Subject s where (select sum(sy.bibliographies.size) from Syllabus sy where sy.subject = s) >= ALL (select(select sum(sy1.bibliographies.size) from Syllabus sy1 where sy1.subject = s2) from Subject s2)")
	Collection<Subject> subjectsWithLargestBibliography();

	// Dashboard queries version 2.0 level B

	@Query("select a from Assignment a join a.rubrics r group by a having count(r) < ((select avg(a.rubrics.size) from Assignment a) - (select avg(a.rubrics.size)*0.2 from Assignment a)) OR count(r) > ((select avg(a.rubrics.size) from Assignment a) + (select avg(a.rubrics.size)*0.2 from Assignment a))")
	Collection<Assignment> assignmentsWithMoreOrLessThan20PAvgRubrics();

	@Query("select  DISTINCT l from Lecturer l  join l.subjects s join s.groups g join g.assignments a where (select sum(a.rubrics.size) from Assignment a where a.group.subject.lecturer = l) >= ALL (select(select sum(a2.rubrics.size) from Assignment a2 where a2.group.subject.lecturer = l2) from Lecturer l2)")
	Collection<Lecturer> lecturerWithMoreRubricsPerAssignment();
	
	@Query("select  DISTINCT l from Lecturer l  join l.subjects s join s.groups g join g.assignments a where (select sum(a.rubrics.size) from Assignment a where a.group.subject.lecturer = l) <= ALL (select(select sum(a2.rubrics.size) from Assignment a2 where a2.group.subject.lecturer = l2) from Lecturer l2)")
	Collection<Lecturer> lecturerWithLessRubricsPerAssignment();

}