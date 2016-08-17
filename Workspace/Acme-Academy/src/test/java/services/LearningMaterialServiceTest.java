package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.validation.ConstraintViolationException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import repositories.LearningMaterialRepository;
import utilities.AbstractTest;
import domain.Group;
import domain.LearningMaterial;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class LearningMaterialServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private LearningMaterialService learningMaterialService;

	// Other services needed -----------------------

	@Autowired
	private GroupService groupService;

	@Autowired
	private LearningMaterialRepository learningMaterialRepository;

	// Tests ---------------------------------------

	/**
	 * 10.1 An actor who is authenticated as a lecturer must be able to: -
	 * Upload learning materials and associate them with a group that he or
	 * she´s created. Once the learning materials are uploaded they cannot be
	 * modified.
	 * 
	 * Positive Test: Un Lecturer upload learning material
	 */
	@Test
	public void TestCrearLearningMaterial1() {

		authenticate("lecturer1");

		LearningMaterial learningMaterial;
		Group group;
		Collection<String> keywords;
		Collection<LearningMaterial> lm;

		lm = learningMaterialService.findAll();
		group = groupService.findOne(33);
		learningMaterial = learningMaterialService.create();
		learningMaterial.setContents("http://www.contentTest.com");
		learningMaterial.setNotes("Notes Test");
		learningMaterial.setGroup(group);
		learningMaterial.setTitle("Title Test");
		keywords = new ArrayList<String>();
		keywords.add("Test");
		keywords.add("Key");
		learningMaterial.setKeywords(keywords);
		group.getLearningMaterials().add(learningMaterial);
		learningMaterialService.save(learningMaterial);
		groupService.save(group);

		Assert.isTrue(
				lm.size() + 1 == learningMaterialService.findAll().size(),
				"El numero de Learning material no coincide");

		unauthenticate();

	}

	/**
	 * 10.1 An actor who is authenticated as a lecturer must be able to: -
	 * Upload learning materials and associate them with a group that he or
	 * she´s created. Once the learning materials are uploaded they cannot be
	 * modified.
	 * 
	 * Negative Test: Un Lecturer upload learning material sin url en el content
	 */
	@Test(expected = ConstraintViolationException.class)
	public void TestCrearLearningMaterial2() {

		authenticate("lecturer1");

		LearningMaterial learningMaterial2;
		Group group;
		Collection<String> keywords;

		group = groupService.findOne(33);
		learningMaterial2 = learningMaterialService.create();
		learningMaterial2.setContents("Content Test");
		learningMaterial2.setNotes("Notes Test");
		learningMaterial2.setGroup(group);
		learningMaterial2.setTitle("Title Test");
		keywords = new ArrayList<String>();
		keywords.add("Test");
		keywords.add("Key");
		learningMaterial2.setKeywords(keywords);
		group.getLearningMaterials().add(learningMaterial2);
		learningMaterialService.save(learningMaterial2);
		groupService.save(group);

		learningMaterialRepository.flush();

		unauthenticate();

	}

	/**
	 * 10.1 An actor who is authenticated as a lecturer must be able to: -
	 * Upload learning materials and associate them with a group that he or
	 * she´s created. Once the learning materials are uploaded they cannot be
	 * modified.
	 * 
	 * Negative Test: Un Lecturer upload learning material sin keywords
	 */
	@Test(expected = ConstraintViolationException.class)
	public void TestCrearLearningMaterial3() {

		authenticate("lecturer1");

		LearningMaterial learningMaterial3;
		Group group;
		Collection<String> keywords;

		group = groupService.findOne(33);
		learningMaterial3 = learningMaterialService.create();
		learningMaterial3.setContents("Content Test");
		learningMaterial3.setNotes("Notes Test");
		learningMaterial3.setGroup(group);
		learningMaterial3.setTitle("Title Test");
		keywords = new ArrayList<String>();
		keywords.add("Test");
		keywords.add("Key");
		group.getLearningMaterials().add(learningMaterial3);
		learningMaterialService.save(learningMaterial3);
		groupService.save(group);

		learningMaterialRepository.flush();

		unauthenticate();

	}
}
