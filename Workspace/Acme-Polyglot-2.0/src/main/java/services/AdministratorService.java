package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AdministratorRepository;
import domain.Actor;
import domain.Administrator;
import domain.Language;

@Service
@Transactional
public class AdministratorService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AdministratorRepository administratorRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private LanguageService languageService;

	// Constructors -----------------------------------------------------------

	public AdministratorService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Administrator create() {
		Administrator result;

		result = new Administrator();

		return result;
	}

	public Administrator findOne(int administratorId) {
		Assert.notNull(administratorId);

		Administrator result;

		result = administratorRepository.findOne(administratorId);

		return result;

	}

	public Collection<Administrator> findAll() {

		Collection<Administrator> result;

		result = administratorRepository.findAll();

		return result;
	}

	public void save(Administrator administrator) {
		Assert.notNull(administrator);

		administratorRepository.save(administrator);
	}

	public void delete(Administrator administrator) {
		Assert.notNull(administrator);

		administratorRepository.delete(administrator);

	}

	// Other business methods -------------------------------------------------

	public void disableAccount(Actor actor) {
		actor.getUserAccount().setIsNotBanned(false);
	}

	public void enableAccount(Actor actor) {
		actor.getUserAccount().setIsNotBanned(true);
	}

	public Collection<Object[]> languagesAndCountOfExchangesInvolved() {
		Collection<Object[]> result;

		result = administratorRepository.languagesAndCountOfExchangesInvolved();

		return result;
	}

	public Collection<Object[]> polyglotsAndCountOfExchangesOrganised() {
		Collection<Object[]> result;

		result = administratorRepository
				.polyglotsAndCountOfExchangesOrganised();

		return result;
	}

	public Collection<Object[]> polyglotsAndCountOfExchangesJoined() {
		Collection<Object[]> result;

		result = administratorRepository.polyglotsAndCountOfExchangesJoined();

		return result;
	}

	public Collection<Object[]> languageExchangesAndCountOfSponsorships() {
		Collection<Object[]> result;

		result = administratorRepository
				.languageExchangesAndCountOfSponsorships();

		return result;
	}

	public Collection<Object[]> polyglotsAndCountOfSponsoredExchanges() {
		Collection<Object[]> result;

		result = administratorRepository
				.polyglotsAndCountOfSponsoredExchanges();

		return result;
	}

	public Collection<Object[]> avgMinMaxSponsoredExchangesPerPolyglot() {
		Collection<Object[]> result;

		result = administratorRepository
				.avgMinMaxSponsoredExchangesPerPolyglot();

		return result;
	}

	public Collection<Object[]> meanMinMaxMessagesPerFolder() {
		Collection<Object[]> result;

		result = administratorRepository.meanMinMaxMessagesPerFolder();

		return result;
	}

	public Collection<Object[]> ratioLaguagesMessages() {
		Collection<Object[]> result;
		Collection<Language> languages;

		result = new ArrayList<Object[]>();
		languages = languageService.findAll();
		for (Language l : languages) {
			Object[] obj = new Object[2];
			obj[0] = l.getCode();
			obj[1] = administratorRepository.ratioLaguagesMessages(l.getCode());
			result.add(obj);
		}
		return result;
	}
}