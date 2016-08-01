package services;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.LanguageExchangeRepository;
import domain.Language;
import domain.LanguageExchange;
import domain.LanguageExchangeDescription;
import domain.Polyglot;

@Service
@Transactional
public class LanguageExchangeService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private LanguageExchangeRepository languageExchangeRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private PolyglotService polyglotService;

	@Autowired
	private FolderService folderService;

	// Constructors -----------------------------------------------------------

	public LanguageExchangeService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public LanguageExchange create() {
		LanguageExchange result;
		Polyglot principal;
		Collection<Polyglot> participants;
		Collection<Language> languages;
		Collection<LanguageExchangeDescription> languageExchangeDescriptions;

		participants = new ArrayList<Polyglot>();
		languages = new ArrayList<Language>();
		languageExchangeDescriptions = new ArrayList<LanguageExchangeDescription>();
		principal = polyglotService.findByPrincipal();

		result = new LanguageExchange();
		result.setOwner(principal);
		result.setParticipants(participants);
		result.setLanguages(languages);
		result.setLanguageExchangeDescriptions(languageExchangeDescriptions);
		result.setCancelled(false);

		return result;
	}

	public LanguageExchange findOne(int languageExchangeId) {
		Assert.notNull(languageExchangeId);

		LanguageExchange result;

		result = languageExchangeRepository.findOne(languageExchangeId);

		return result;

	}

	public Collection<LanguageExchange> findAll() {

		Collection<LanguageExchange> result;

		result = languageExchangeRepository.findAll();

		return result;
	}

	public void save(LanguageExchange languageExchange) {
		Assert.notNull(languageExchange);
		Polyglot principal;

		principal = polyglotService.findByPrincipal();

		languageExchange.setOwner(principal);

		if (languageExchange.getId() == 0) {
			folderService.createExchangeFolder(languageExchange);
		}
		languageExchangeRepository.save(languageExchange);
	}

	public void delete(LanguageExchange languageExchange) {
		Assert.notNull(languageExchange);

		languageExchangeRepository.delete(languageExchange);

	}

	// Other business methods -------------------------------------------------

	public Collection<LanguageExchange> find3MonthsAgo() {
		Collection<LanguageExchange> result;
		Date today;
		Date threeMonthsAgo;
		Calendar cal;

		today = new Date();
		cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, -3);
		threeMonthsAgo = cal.getTime();

		result = languageExchangeRepository.findBetween(threeMonthsAgo, today);

		return result;
	}

	public Collection<LanguageExchange> find3MonthsTime() {
		Collection<LanguageExchange> result;
		Date today;
		Date threeMonthsTime;
		Calendar cal;

		today = new Date();
		cal = Calendar.getInstance();
		cal.setTime(today);
		cal.add(Calendar.MONTH, +3);
		threeMonthsTime = cal.getTime();

		result = languageExchangeRepository.findBetween(today, threeMonthsTime);

		return result;
	}

	public Collection<LanguageExchange> findAllByPrincipal() {
		Collection<LanguageExchange> result;
		Polyglot principal;

		principal = polyglotService.findByPrincipal();
		result = languageExchangeRepository.findAllByOwnerId(principal.getId());

		return result;
	}

	public void joinLanguageExchange(LanguageExchange languageExchange) {
		Polyglot principal;
		Collection<Polyglot> participants;
		Collection<LanguageExchange> languageExchanges;

		principal = polyglotService.findByPrincipal();
		participants = languageExchange.getParticipants();
		languageExchanges = principal.getParticipatedLanguageExchanges();

		participants.add(principal);
		languageExchanges.add(languageExchange);
		languageExchange.setParticipants(participants);
		principal.setParticipatedLanguageExchanges(languageExchanges);

		if (!languageExchange.getOwner().equals(principal)) {
			folderService.createExchangeFolder(languageExchange);
		}
	}

	public void unJoinLanguageExchange(LanguageExchange languageExchange) {
		Polyglot principal;
		Collection<Polyglot> participants;
		Collection<LanguageExchange> languageExchanges;

		principal = polyglotService.findByPrincipal();
		participants = languageExchange.getParticipants();
		Assert.isTrue(participants.contains(principal));
		languageExchanges = principal.getParticipatedLanguageExchanges();

		participants.remove(principal);
		languageExchanges.remove(languageExchange);
		languageExchange.setParticipants(participants);
		principal.setParticipatedLanguageExchanges(languageExchanges);
	}

	public void cancelLanguageExchange(LanguageExchange languageExchange) {
		Polyglot principal;

		principal = polyglotService.findByPrincipal();
		Assert.isTrue(languageExchange.getOwner().equals(principal));

		languageExchange.setCancelled(true);
	}

	public Collection<LanguageExchange> findAllJoinedByPrincipal() {
		Collection<LanguageExchange> result;
		Polyglot principal;

		principal = polyglotService.findByPrincipal();
		result = languageExchangeRepository.findAllJoinedByPolyglotId(principal
				.getId());

		return result;
	}

	public Collection<LanguageExchange> findByKeyword(String keyword) {
		Collection<LanguageExchange> result;

		result = languageExchangeRepository.findByKeyword(keyword);

		return result;
	}

	public Collection<LanguageExchange> findByLanguageId(int languageId) {
		Collection<LanguageExchange> result;

		result = languageExchangeRepository.findByLanguageId(languageId);

		return result;
	}
}