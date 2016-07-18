package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ActivityCommentRepository;

import domain.ActivityComment;

@Service
@Transactional
public class ActivityCommentService {
	// Managed repository -----------------------------------------------------

		@Autowired
		private ActivityCommentRepository activityCommentRepository;

		// Supporting services ----------------------------------------------------

		// Constructors -----------------------------------------------------------

		public ActivityCommentService() {
			super();
		}

		// Simple CRUD methods ----------------------------------------------------

		public ActivityComment create() {
			ActivityComment result;

			result = new ActivityComment();

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

			activityCommentRepository.save(activityComment);
		}

		public void delete(ActivityComment activityComment) {
			Assert.notNull(activityComment);

			activityCommentRepository.delete(activityComment);
		}

		// Other business methods -------------------------------------------------
}
