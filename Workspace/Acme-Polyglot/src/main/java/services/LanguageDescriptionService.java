package services;

import java.util.Collection;

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

	// Constructors -----------------------------------------------------------

	public LanguageDescriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public LanguageDescription create() {
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
		Assert.notNull(languageDescription);

		languageDescriptionRepository.save(languageDescription);
	}

	public void delete(LanguageDescription languageDescription) {
		Assert.notNull(languageDescription);

		languageDescriptionRepository.delete(languageDescription);

	}

	// Other business methods -------------------------------------------------

	public LanguageDescription findByLanguageIdAndCode(int languageId,
			String code) {
		LanguageDescription result;

		result = languageDescriptionRepository.findByLanguageIdAndCode(
				languageId, code);

		return result;
	}
}