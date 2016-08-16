package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.CommentRepository;
import domain.Comment;

@Service
@Transactional
public class CommentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private CommentRepository commentRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;
	
	// Constructors -----------------------------------------------------------

	public CommentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Comment create() {
		Comment result;

		result = new Comment();

		return result;
	}

	public Comment findOne(int commentId) {
		Assert.notNull(commentId);

		Comment result;

		result = commentRepository.findOne(commentId);

		return result;
	}

	public Collection<Comment> findAll() {

		Collection<Comment> result;

		result = commentRepository.findAll();

		return result;
	}

	public void save(Comment comment) {
		Assert.notNull(comment);

		commentRepository.save(comment);
	}

	public void delete(Comment comment) {
		Assert.notNull(comment);

		commentRepository.delete(comment);
	}

	// Other business methods -------------------------------------------------

	public Comment findById(int commentId) {
		Comment result;

		result = commentRepository.findById(commentId);

		return result;
	}

	public void flagComment(Comment comment) {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		comment.setInappropriate(true);
	}
}