package services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import utilities.AbstractTest;
import domain.Comment;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class CommentServiceTest extends AbstractTest {
	
		// Service under test -------------------------

			@Autowired
			private CommentService commentService;

			// Other services needed -----------------------

			
			// Tests ---------------------------------------
			/**
			 * An actor who is authenticated as an administrator must be able to:
			 * 		-Flag comments and activities as inappropriate.
			 * 
			 *Positive Test: Marcar un commentario como innapropiado 
			 */
			@Test
			public void TestFlagComment1(){
			
				authenticate("admin");
				
				Comment comment;
				Boolean inappropiate;
				comment=commentService.findById(2);
				inappropiate=comment.isInappropriate();
				commentService.flagComment(comment);
				Assert.isTrue(inappropiate!=comment.isInappropriate(),"El mensaje no ha sido marcado como inapropiado");
				
				unauthenticate();
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to:
			 * 		-Flag comments and activities as inappropriate.
			 * 
			 *Negative Test: Un user marca un commentario como inapropiado 
			 */
			@Test(expected = IllegalArgumentException.class)
			public void TestFlagComment2(){
			
				authenticate("user1");
				
				Comment comment;
				comment=commentService.findById(2);
				commentService.flagComment(comment);
			
				unauthenticate();
			}
			
			/**
			 * An actor who is authenticated as an administrator must be able to:
			 * 		-Flag comments and activities as inappropriate.
			 * 
			 *Test: Un manager marca un commentario como inapropiado 
			 */
			@Test(expected = IllegalArgumentException.class)
			public void TestFlagComment3(){
			
				authenticate("manager1");
				
				Comment comment;
				comment=commentService.findById(2);
				commentService.flagComment(comment);
			
				unauthenticate();
			}

}
