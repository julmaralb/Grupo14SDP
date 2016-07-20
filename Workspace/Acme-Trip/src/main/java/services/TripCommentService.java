package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TripCommentRepository;
import domain.Actor;
import domain.TripComment;

@Service
@Transactional
public class TripCommentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private TripCommentRepository tripCommentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public TripCommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public TripComment create() {
		TripComment result;
		Date moment;
		Actor principal;

		result = new TripComment();
		principal = actorService.findByPrincipal();
		moment = new Date();

		result.setMoment(moment);
		result.setActor(principal);

		return result;
	}

	public TripComment findOne(int tripCommentId) {
		Assert.notNull(tripCommentId);

		TripComment result;

		result = tripCommentRepository.findOne(tripCommentId);

		return result;
	}

	public Collection<TripComment> findAll() {

		Collection<TripComment> result;

		result = tripCommentRepository.findAll();

		return result;
	}

	public void save(TripComment tripComment) {
		Assert.notNull(tripComment);
		long milliseconds;
		Actor principal;
		
		milliseconds = System.currentTimeMillis() - 100;
		tripComment.setMoment(new Date(milliseconds));
		principal = actorService.findByPrincipal();
		tripComment.setActor(principal);

		tripCommentRepository.save(tripComment);
	}

	public void delete(TripComment tripComment) {
		Assert.notNull(tripComment);

		tripCommentRepository.delete(tripComment);
	}

	// Other business methods -------------------------------------------------

	public Collection<TripComment> findAllAppropriateByTripId(int tripId) {
		Collection<TripComment> result;

		result = tripCommentRepository.findAllByTripId(tripId);

		return result;
	}
}