package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.KeywordRepository;
import domain.Actor;
import domain.Keyword;

@Service
@Transactional
public class KeywordService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private KeywordRepository keywordRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public KeywordService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Keyword create() {
		Keyword result;

		result = new Keyword();

		return result;
	}

	public Keyword findOne(int keywordId) {
		Assert.notNull(keywordId);

		Keyword result;

		result = keywordRepository.findOne(keywordId);

		return result;
	}

	public Collection<Keyword> findAll() {

		Collection<Keyword> result;

		result = keywordRepository.findAll();

		return result;
	}

	public void save(Keyword keyword) {
		Assert.notNull(keyword);

		keywordRepository.save(keyword);
	}

	public void delete(Keyword keyword) {
		Assert.notNull(keyword);

		keywordRepository.delete(keyword);
	}

	// Other business methods -------------------------------------------------

	public void processKeyword(String keyword) {
		Actor principal;
		Collection<Keyword> keywords;
		Keyword key;

		principal = actorService.findByPrincipal();
		keywords = principal.getKeywords();
		key = keywordRepository.findByKeyword(keyword, principal.getId());

		if (key == null) {
			Keyword newKeyword = create();
			newKeyword.setKeyword(keyword);
			newKeyword.setCount(1);
			newKeyword.setActor(principal);
			save(newKeyword);
			keywords.add(newKeyword);
		} else {
			key.setCount(key.getCount() + 1);
		}
		principal.setKeywords(keywords);
	}

	public Collection<Keyword> findAllByActorId(int actorId) {
		Collection<Keyword> result;

		result = keywordRepository.findAllByActorId(actorId);

		return result;
	}
}