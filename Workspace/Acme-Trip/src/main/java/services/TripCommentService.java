package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.TripCommentRepository;
import domain.TripComment;

@Service
@Transactional
public class TripCommentService {

	// Managed repository -----------------------------------------------------

			@Autowired
			private TripCommentRepository tripCommentRepository;

			// Supporting services ----------------------------------------------------

			// Constructors -----------------------------------------------------------

			public TripCommentService() {
				super();
			}

			// Simple CRUD methods ----------------------------------------------------

			public TripComment create() {
				TripComment result;

				result = new TripComment();

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

				tripCommentRepository.save(tripComment);
			}

			public void delete(TripComment tripComment) {
				Assert.notNull(tripComment);

				tripCommentRepository.delete(tripComment);
			}

			// Other business methods -------------------------------------------------

		
}
