package services;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LanguageExchangeDescriptionRepository;
import domain.LanguageExchangeDescription;
import domain.Polyglot;

@Service
@Transactional
public class LanguageExchangeDescriptionService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LanguageExchangeDescriptionRepository languageExchangeDescriptionRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private PolyglotService polyglotService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeDescriptionService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public LanguageExchangeDescription create() {
		LanguageExchangeDescription result;

		result = new LanguageExchangeDescription();

		return result;
	}

	public LanguageExchangeDescription findOne(int languageExchangeDescriptionId) {
		Assert.notNull(languageExchangeDescriptionId);

		LanguageExchangeDescription result;

		result = languageExchangeDescriptionRepository
				.findOne(languageExchangeDescriptionId);

		return result;

	}

	public Collection<LanguageExchangeDescription> findAll() {

		Collection<LanguageExchangeDescription> result;

		result = languageExchangeDescriptionRepository.findAll();

		return result;
	}

	public void save(LanguageExchangeDescription languageExchangeDescription) {
		Assert.notNull(languageExchangeDescription);
		Polyglot principal;
		principal = polyglotService.findByPrincipal();
		Assert.isTrue(languageExchangeDescription.getLanguageExchange()
				.getOwner().equals(principal));

		languageExchangeDescriptionRepository.save(languageExchangeDescription);
	}

	public void delete(LanguageExchangeDescription languageExchangeDescription) {
		Assert.notNull(languageExchangeDescription);
		Polyglot principal;
		principal = polyglotService.findByPrincipal();
		Assert.isTrue(languageExchangeDescription.getLanguageExchange()
				.getOwner().equals(principal));

		languageExchangeDescriptionRepository
				.delete(languageExchangeDescription);

	}

	// Other business methods -------------------------------------------------

	public Collection<LanguageExchangeDescription> findAllByPrincipal() {
		Collection<LanguageExchangeDescription> result;
		Polyglot principal;

		principal = polyglotService.findByPrincipal();
		result = languageExchangeDescriptionRepository
				.findAllByPolyglotId(principal.getId());

		return result;
	}

	public LanguageExchangeDescription findByExchangeIdAndCode(
			int languageExchangeId, String code) {

		LanguageExchangeDescription result;
		ArrayList<LanguageExchangeDescription> allGivenCode;
		ArrayList<LanguageExchangeDescription> allEnglish;
		ArrayList<LanguageExchangeDescription> all;
		Random r = new Random();

		allGivenCode = languageExchangeDescriptionRepository
				.findAllByLanguageExchangeIdAndCode(languageExchangeId, code);
		allEnglish = languageExchangeDescriptionRepository
				.findAllByLanguageExchangeIdAndCode(languageExchangeId, "en");
		all = languageExchangeDescriptionRepository
				.findAllByLanguageExchange(languageExchangeId);

		if (!allGivenCode.isEmpty()) {
			result = allGivenCode.get(r.nextInt(allGivenCode.size()));
		} else if (allGivenCode.isEmpty() && !allEnglish.isEmpty()) {
			result = allEnglish.get(r.nextInt(allEnglish.size()));
		} else if (allEnglish.isEmpty() && !all.isEmpty()) {
			result = all.get(r.nextInt(all.size()));
		}else{
			result = allGivenCode.get(r.nextInt(allGivenCode.size()));
		}
		return result;
	}
}