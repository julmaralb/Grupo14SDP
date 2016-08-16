package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Assessment;
import domain.Deliverable;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class AssessmentServiceTest extends AbstractTest {
	
	
	// Service under test -------------------------

		@Autowired
		private AssessmentService assessmentService;

		// Other services needed -----------------------

		@Autowired
		private LecturerService lecturerService;

		@Autowired
		private DeliverableService deliverableService;

		@Autowired
		private BibliographyService bibliographyService;

		// Tests ---------------------------------------

		/**
		 * 1.1 An actor who is authenticated as a lecturer must be able to:
		 * Create an assessment regarding a rubric and assign it to a deliverable.
		 * 
		 * Positive Test: Crear un nuevo assessment
		 */

		@Test
		public void testCrearAssessment1() {
		
			authenticate("lecturer1");
			
			Assessment assessment;
			Deliverable deliverable;
			Integer before= assessmentService.findAll().size();
			
			deliverable=deliverableService.findOne(66);
			assessment=assessmentService.create();
			assessment.setExplanation("Explanation test");
			assessment.setNumber(1);
			assessment.setPoints(10);
			assessment.setDeliverable(deliverable);
			assessmentService.save(assessment);
			
			Assert.isTrue(assessmentService.findAll().size()==before+1,"No se ha guardado ningun assessment");
			
			unauthenticate();
			
		}
		
		/**
		 * 1.1 An actor who is authenticated as a lecturer must be able to:
		 * Create an assessment regarding a rubric and assign it to a deliverable.
		 * 
		 * Positive Test: Crear un nuevo assessment
		 */

		@Test(expected=IllegalArgumentException.class)
		public void testCrearAssessment2() {
		
			authenticate("lecturer1");
			
			Assessment assessment;
			Deliverable deliverable;
			
			deliverable=deliverableService.findOne(66);
			assessment=assessmentService.create();
			//assessment.setExplanation("Explanation test");
			assessment.setNumber(1);
			assessment.setPoints(10);
			assessment.setDeliverable(deliverable);
			
			unauthenticate();
			
		}
		
		/**
		 * 1.1 An actor who is authenticated as a lecturer must be able to:
		 * Create an assessment regarding a rubric and assign it to a deliverable.
		 * 
		 * Positive Test: Crear un nuevo assessment
		 */

		@Test(expected=IllegalArgumentException.class)
		public void testCrearAssessment3() {
		
			authenticate("lecturer1");
			
			Assessment assessment;
			Deliverable deliverable;
			
			deliverable=deliverableService.findOne(66);
			assessment=assessmentService.create();
			assessment.setExplanation("Explanation test");
			assessment.setNumber(1);
			assessment.setPoints(10);
			//assessment.setDeliverable(null);
			
			unauthenticate();
			
		}

}
