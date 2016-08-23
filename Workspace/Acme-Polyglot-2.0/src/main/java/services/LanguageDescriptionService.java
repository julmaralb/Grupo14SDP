package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LanguageDescriptionRepository;
import domain.LanguageDescription;

@Service
@Transactional
public class LanguageDescriptionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LanguageDescriptionRepository languageDescriptionRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private ActorService actorService;

	// Constructors -----------------------------------------------------------

	public LanguageDescriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public LanguageDescription create() {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		LanguageDescription result;

		result = new LanguageDescription();

		return result;
	}

	public LanguageDescription findOne(int languageDescriptionId) {
		Assert.notNull(languageDescriptionId);

		LanguageDescription result;

		result = languageDescriptionRepository.findOne(languageDescriptionId);

		return result;

	}

	public Collection<LanguageDescription> findAll() {

		Collection<LanguageDescription> result;

		result = languageDescriptionRepository.findAll();

		return result;
	}

	public void save(LanguageDescription languageDescription) {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Assert.notNull(languageDescription);

		languageDescriptionRepository.save(languageDescription);
	}

	public void delete(LanguageDescription languageDescription) {
		Assert.isTrue(actorService.checkAuthority("ADMIN"));
		Assert.notNull(languageDescription);

		languageDescriptionRepository.delete(languageDescription);

	}

	// Other business methods -------------------------------------------------

	public LanguageDescription findByLanguageIdAndCode(int languageId,
			String code) {
		LanguageDescription result;
		ArrayList<LanguageDescription> allGivenCode;
		ArrayList<LanguageDescription> allEnglish;
		ArrayList<LanguageDescription> all;
		Random r = new Random();

		allGivenCode = languageDescriptionRepository
				.findAllByLanguageIdAndCode(languageId, code);
		allEnglish = languageDescriptionRepository.findAllByLanguageIdAndCode(
				languageId, "en");
		all = languageDescriptionRepository.findAllByLanguage(languageId);

		if (!allGivenCode.isEmpty()) {
			result = allGivenCode.get(r.nextInt(allGivenCode.size()));
		} else if (allGivenCode.isEmpty() && !allEnglish.isEmpty()) {
			result = allEnglish.get(r.nextInt(allEnglish.size()));
		} else if (allEnglish.isEmpty() && !all.isEmpty()) {
			result = all.get(r.nextInt(all.size()));
		} else {
			result = allGivenCode.get(r.nextInt(allGivenCode.size()));
		}
		return result;
	}
}