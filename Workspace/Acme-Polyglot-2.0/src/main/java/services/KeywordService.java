package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

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

	public Collection<Object[]> generalKeywordsStatistics() {
		Collection<Object[]> result;
		HashMap<String, Integer> map;
		Collection<Keyword> all;

		result = new ArrayList<Object[]>();
		map = new HashMap<String, Integer>();
		all = keywordRepository.findAll();

		for (Keyword k : all) {
			if (!map.containsKey(k.getKeyword())) {
				map.put(k.getKeyword(), k.getCount());
			} else {
				map.put(k.getKeyword(), map.get(k.getKeyword()) + 1);
			}
		}
		for(String s: map.keySet()){
			Object[] obj = new Object[2];
			obj[0] = s;
			obj[1] = map.get(s);
			result.add(obj);
		}
		return result;
	}
}