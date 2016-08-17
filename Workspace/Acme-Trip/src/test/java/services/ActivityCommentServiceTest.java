package services;

import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Activity;
import domain.ActivityComment;
import domain.Actor;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class ActivityCommentServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private ActivityCommentService activityCommentService;

	// Other services needed -----------------------

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private ActorService actorService;
	
	@Autowired
	private ActivityService activityService;

	// Tests ---------------------------------------
	/**
	 * An actor who is authenticated as an administrator must be able to: -Flag
	 * comments and activities as inappropriate.
	 * 
	 * Positive Test: Marcar un commentario como innapropiado
	 */
	@Test
	public void TestFlagComment1() {

		authenticate("admin");

		Activity activity;
		Actor actor;
		
		activity=activityService.findOne(120);
		actor=actorService.findOne(68);
		Boolean inappropiate = false;
		ActivityComment comment;
		comment = activityCommentService.create();
		comment.setInappropriate(false);
		comment.setText("text");
		comment.setTitle("title");
		comment.setActivity(activity);
		comment.setActor(actor);
		comment.setMoment(new Date());
		activityCommentService.save(comment);
		inappropiate = comment.isInappropriate();
		commentService.flagComment(comment);

		Assert.isTrue(!(inappropiate.equals(comment.isInappropriate())),
				"El mensaje no ha sido marcado como inapropiado");

		unauthenticate();
	}

	/**
	 * An actor who is authenticated as an administrator must be able to: -Flag
	 * comments and activities as inappropriate.
	 * 
	 * Negative Test: Un user marca un commentario como inapropiado
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestFlagComment2() {

		authenticate("admin");

		Activity activity;
		Actor actor;
		
		activity=activityService.findOne(95);
		actor=actorService.findOne(62);
		
		authenticate("user1");

		ActivityComment comment;
		comment = activityCommentService.create();
		comment.setInappropriate(false);
		comment.setText("text");
		comment.setTitle("title");
		comment.setActivity(activity);
		comment.setActor(actor);
		comment.setMoment(new Date());
		activityCommentService.save(comment);
		commentService.flagComment(comment);

		unauthenticate();
	}

	/**
	 * An actor who is authenticated as an administrator must be able to: -Flag
	 * comments and activities as inappropriate.
	 * 
	 * Test: Un manager marca un commentario como inapropiado
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestFlagComment3() {

		authenticate("admin");

		Activity activity;
		Actor actor;
		
		activity=activityService.findOne(120);
		actor=actorService.findOne(68);
		
		authenticate("manager1");
		ActivityComment comment;
		comment = activityCommentService.create();
		comment.setInappropriate(false);
		comment.setText("text");
		comment.setTitle("title");
		comment.setActivity(activity);
		comment.setActor(actor);
		comment.setMoment(new Date());
		activityCommentService.save(comment);
		
		commentService.flagComment(comment);

		unauthenticate();
	}
}
