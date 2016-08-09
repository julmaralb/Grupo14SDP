package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LanguageRepository;
import domain.Language;
import domain.LanguageDescription;
import domain.LanguageExchange;

@Service
@Transactional
public class LanguageService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LanguageRepository languageRepository;

	// Supporting services ----------------------------------------------------
	
	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public LanguageService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Language create() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Language result;
		Collection<LanguageDescription> languageDescriptions;

		result = new Language();
		languageDescriptions = new ArrayList<LanguageDescription>();
		
		result.setLanguageDescriptions(languageDescriptions);

		return result;
	}

	public Language findOne(int languageId) {
		Assert.notNull(languageId);

		Language result;

		result = languageRepository.findOne(languageId);

		return result;

	}

	public Collection<Language> findAll() {

		Collection<Language> result;

		result = languageRepository.findAll();

		return result;
	}

	public void save(Language language) {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Assert.notNull(language);

		languageRepository.save(language);
	}

	public void delete(Language language) {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Assert.notNull(language);

		Assert.isTrue(language.getLanguageExchanges().size() == 0);

		languageRepository.delete(language);

	}

	// Other business methods -------------------------------------------------

	public void incrementCounter(LanguageExchange languageExchange) {
		Collection<Language> languages;
		int counter;

		languages = languageExchange.getLanguages();
		for (Language l : languages) {
			counter = l.getLanguageExchanges().size();
			l.setCounter(counter);
		}
	}
}