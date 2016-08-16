package services;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import domain.Actor;
import domain.SocialIdentity;

import utilities.AbstractTest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/datasource.xml",
		"classpath:spring/config/packages.xml" })
@Transactional
@TransactionConfiguration(defaultRollback = true)
public class SocialIdentityServiceTest extends AbstractTest {

	// Service under test -------------------------

	@Autowired
	private SocialIdentityService socialIdentityService;

	// Other services needed -----------------------

	@Autowired
	private ActorService actorService;

	@Autowired
	private GroupService groupService;

	// Tests ---------------------------------------

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Positive Test: Un student crea un social identity;
	 * 
	 */
	@Test
	public void TestCrearSocialIdentity1() {

		authenticate("student1");

		SocialIdentity socialIdentity;
		Actor actor;

		actor = actorService.findByPrincipal();
		socialIdentity = socialIdentityService.create();
		socialIdentity.setActor(actor);
		socialIdentity.setEmail("test@email.com");
		socialIdentity.setHomePage("http://www.homepage.com");
		socialIdentity.setName("Name Test");
		socialIdentity.setNickname("nickname Test");
		socialIdentityService.save(socialIdentity);

		Assert.isTrue(socialIdentityService.findAll().size() == 9,
				"El numero de social identity no es correcto");
		Assert.isTrue(actor.getSocialIdentities().size() == 3,
				"El numero de social identity del actor no es correcto");

		unauthenticate();
	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Negative Test: Un student crea un social identity sin email;
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestCrearSocialIdentity2() {

		authenticate("student1");

		SocialIdentity socialIdentity;
		Actor actor;

		actor = actorService.findByPrincipal();
		socialIdentity = socialIdentityService.create();
		socialIdentity.setActor(actor);
		// socialIdentity.setEmail("test@email.com");
		socialIdentity.setHomePage("http://www.homepage.com");
		socialIdentity.setName("Name Test");
		socialIdentity.setNickname("nickname Test");
		socialIdentityService.save(socialIdentity);

		unauthenticate();
	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Negative Test: Un student crea un social identity null;
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestCrearSocialIdentity3() {

		authenticate("student1");

		socialIdentityService.save(null);

		unauthenticate();
	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Negative Test: Un student crea un social identity sin homepage;
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestCrearSocialIdentity4() {

		authenticate("student1");

		SocialIdentity socialIdentity;
		Actor actor;

		actor = actorService.findByPrincipal();
		socialIdentity = socialIdentityService.create();
		socialIdentity.setActor(actor);
		socialIdentity.setEmail("test@email.com");
		// socialIdentity.setHomePage("http://www.homepage.com");
		socialIdentity.setName("Name Test");
		socialIdentity.setNickname("nickname Test");
		socialIdentityService.save(socialIdentity);

		unauthenticate();
	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Negative Test: Un student crea un social identity sin name;
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestCrearSocialIdentity5() {

		authenticate("student1");

		SocialIdentity socialIdentity;
		Actor actor;

		actor = actorService.findByPrincipal();
		socialIdentity = socialIdentityService.create();
		socialIdentity.setActor(actor);
		socialIdentity.setEmail("test@email.com");
		socialIdentity.setHomePage("http://www.homepage.com");
		// socialIdentity.setName("Name Test");
		socialIdentity.setNickname("nickname Test");
		socialIdentityService.save(socialIdentity);

		unauthenticate();
	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Negative Test: Un student crea un social identity sin nickname;
	 * 
	 */
	@Test(expected = IllegalArgumentException.class)
	public void TestCrearSocialIdentity6() {

		authenticate("student1");

		SocialIdentity socialIdentity;
		Actor actor;

		actor = actorService.findByPrincipal();
		socialIdentity = socialIdentityService.create();
		socialIdentity.setActor(actor);
		socialIdentity.setEmail("test@email.com");
		socialIdentity.setHomePage("http://www.homepage.com");
		socialIdentity.setName("Name Test");
		// socialIdentity.setNickname("nickname Test");
		socialIdentityService.save(socialIdentity);

		unauthenticate();
	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Positive Test: Un student lista sus social identities;
	 * 
	 */
	@Test
	public void TestListarSocialIdentity1() {

		authenticate("student1");

		Collection<SocialIdentity> socialIdentities;

		socialIdentities = socialIdentityService.findByPrincipal();

		Assert.isTrue(socialIdentities.size() == 2,
				"El numero de socialIdentities no concuerda");

		unauthenticate();

	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Positive Test: Un student modifica su social Identity;
	 * 
	 */
	@Test
	public void TestModificarSocialIdentity1() {

		authenticate("student1");

		SocialIdentity socialIdentity;

		socialIdentity = socialIdentityService.findOne(16);

		socialIdentity.setName("Name Test");
		socialIdentityService.save(socialIdentity);

		Assert.isTrue(
				socialIdentityService.findOne(16).getName().equals("Name Test"),
				"No se ha modificado");

		unauthenticate();

	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Negative Test: Un student modifica la social identity de otro student;
	 * 
	 */
	@Test
	public void TestModificarSocialIdentity2() {

		authenticate("student2");

		SocialIdentity socialIdentity;

		socialIdentity = socialIdentityService.findOne(16);

		socialIdentity.setName("Name Test");
		socialIdentityService.save(socialIdentity);

		Assert.isTrue(
				socialIdentityService.findOne(16).getName().equals("Name Test"),
				"No se ha modificado");
		Assert.isTrue(
				socialIdentityService.findOne(16).getActor().getId() == 15,
				"Ha cambiado el actor");

		unauthenticate();

	}

	/**
	 * 1.1 A user who is authenticated must be able to: Manage his or her social
	 * identities, which includes listing, creating, modifying, or deleting
	 * them.
	 * 
	 * Negative Test: Un student elimina la social identity de otro student;
	 * 
	 */
	@Test(expected=IllegalArgumentException.class)
	public void TestEliminarSocialIdentity1() {

		authenticate("student2");

		SocialIdentity socialIdentity;

		socialIdentity = socialIdentityService.findOne(16);

		socialIdentityService.delete(socialIdentity);

		unauthenticate();

	}

}
