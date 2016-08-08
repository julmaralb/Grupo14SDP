package services;

import java.util.Collection;

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
		Assert.isTrue(languageExchangeDescription.getLanguageExchange().getOwner().equals(principal));

		languageExchangeDescriptionRepository.save(languageExchangeDescription);
	}

	public void delete(LanguageExchangeDescription languageExchangeDescription) {
		Assert.notNull(languageExchangeDescription);
		Polyglot principal;
		principal = polyglotService.findByPrincipal();
		Assert.isTrue(languageExchangeDescription.getLanguageExchange().getOwner().equals(principal));

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

		result = languageExchangeDescriptionRepository.findByExchangeIdAndCode(
				languageExchangeId, code);

		return result;
	}
}