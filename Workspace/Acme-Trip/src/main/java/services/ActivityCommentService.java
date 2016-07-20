package services;

import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActivityCommentRepository;
import domain.ActivityComment;
import domain.Actor;

@Service
@Transactional
public class ActivityCommentService {
	// Managed repository -----------------------------------------------------

	@Autowired
	private ActivityCommentRepository activityCommentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public ActivityCommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public ActivityComment create() {
		ActivityComment result;
		Date moment;
		Actor principal;

		result = new ActivityComment();
		principal = actorService.findByPrincipal();
		moment = new Date();

		result.setMoment(moment);
		result.setActor(principal);

		return result;
	}

	public ActivityComment findOne(int activityCommentId) {
		Assert.notNull(activityCommentId);

		ActivityComment result;

		result = activityCommentRepository.findOne(activityCommentId);

		return result;
	}

	public Collection<ActivityComment> findAll() {

		Collection<ActivityComment> result;

		result = activityCommentRepository.findAll();

		return result;
	}

	public void save(ActivityComment activityComment) {
		Assert.notNull(activityComment);
		long milliseconds;
		Actor principal;

		milliseconds = System.currentTimeMillis() - 100;
		activityComment.setMoment(new Date(milliseconds));
		principal = actorService.findByPrincipal();
		activityComment.setActor(principal);

		activityCommentRepository.save(activityComment);
	}

	public void delete(ActivityComment activityComment) {
		Assert.notNull(activityComment);

		activityCommentRepository.delete(activityComment);
	}

	// Other business methods -------------------------------------------------

	public Collection<ActivityComment> findAllAppropriateByActivityId(int activityId) {
		Collection<ActivityComment> result;

		result = activityCommentRepository.findAllByActivityId(activityId);

		return result;
	}
}