package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SocialIdentityRepository;
import domain.Actor;
import domain.SocialIdentity;

@Service
@Transactional
public class SocialIdentityService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SocialIdentityRepository socialIdentityRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public SocialIdentityService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SocialIdentity create() {
		SocialIdentity result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = new SocialIdentity();
		result.setActor(principal);

		return result;
	}

	public SocialIdentity findOne(int socialIdentityId) {
		Assert.notNull(socialIdentityId);

		SocialIdentity result;

		result = socialIdentityRepository.findOne(socialIdentityId);

		return result;
	}

	public Collection<SocialIdentity> findAll() {

		Collection<SocialIdentity> result;

		result = socialIdentityRepository.findAll();

		return result;
	}

	public void save(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);
		Actor principal;
		
		if(socialIdentity.getId()>=1){
			Assert.isTrue(socialIdentity.getActor().getId() == actorService
					.findByPrincipal().getId(), "Solo el dueño puede modificar");
		}
		
		Assert.notNull(socialIdentity.getEmail(),"Email no puede ser null");
		Assert.notNull(socialIdentity.getHomePage(),"HomePage no puede ser null");
		Assert.notNull(socialIdentity.getNickname(),"Nickname no puede ser null");
		Assert.notNull(socialIdentity.getName(),"Name no puede ser null");
		
		principal = actorService.findByPrincipal();

		socialIdentity.setActor(principal);
		socialIdentityRepository.save(socialIdentity);
	}

	public void delete(SocialIdentity socialIdentity) {
		Assert.notNull(socialIdentity);

		Assert.isTrue(socialIdentity.getActor().getId() == actorService
				.findByPrincipal().getId(), "Solo el dueño puede eliminar");

		socialIdentityRepository.delete(socialIdentity);
	}

	// Other business methods -------------------------------------------------

	public Collection<SocialIdentity> findByPrincipal() {
		Collection<SocialIdentity> result;
		Actor principal;

		principal = actorService.findByPrincipal();
		result = socialIdentityRepository.findByActorId(principal.getId());

		return result;
	}
}