package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.OpenMatchRepository;
import domain.Customer;
import domain.OpenMatch;

@Service
@Transactional
public class OpenMatchService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private OpenMatchRepository openMatchRepository;

	// Supporting services ----------------------------------------------------

	@Autowired
	private CustomerService customerService;

	// Constructors -----------------------------------------------------------

	public OpenMatchService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public OpenMatch create() {
		OpenMatch result;
		Customer principal;

		principal = customerService.findByPrincipal();
		result = new OpenMatch();
		result.setOwner(principal);

		return result;
	}

	public OpenMatch findOne(int openMatchId) {
		Assert.notNull(openMatchId);

		OpenMatch result;

		result = openMatchRepository.findOne(openMatchId);

		return result;
	}

	public Collection<OpenMatch> findAll() {

		Collection<OpenMatch> result;

		result = openMatchRepository.findAll();

		return result;
	}

	public void save(OpenMatch openMatch) {
		Assert.notNull(openMatch);

		Customer principal;
		Collection<OpenMatch> ownOpenMatches;

		principal = customerService.findByPrincipal();
		ownOpenMatches = principal.getOwnOpenMatches();
		ownOpenMatches.add(openMatch);

		openMatch.setOwner(principal);
		principal.setOwnOpenMatches(ownOpenMatches);

		if (openMatch.getNumPlayers() == null) {
			openMatch.setNumPlayers(0);
		}

		openMatchRepository.save(openMatch);
	}

	public void delete(OpenMatch openMatch) {
		Assert.notNull(openMatch);
		Customer principal;

		principal = customerService.findByPrincipal();
		Assert.isTrue(openMatch.getOwner().equals(principal));

		openMatchRepository.delete(openMatch);
	}

	// Other business methods -------------------------------------------------

	public void join(OpenMatch openMatch) {

		Assert.isTrue(openMatch.getNumPlayers() < openMatch.getMaxPlayers());
		Collection<Customer> players;
		Customer principal;
		Integer newNumPlayers;
		Collection<OpenMatch> pOpenMatches;

		players = openMatch.getPlayers();
		principal = customerService.findByPrincipal();
		newNumPlayers = openMatch.getNumPlayers();
		pOpenMatches = principal.getpOpenMatches();

		newNumPlayers = newNumPlayers + 1;
		openMatch.setNumPlayers(newNumPlayers);
		pOpenMatches.add(openMatch);

		Assert.isTrue(!players.contains(principal));
		players.add(principal);
		openMatch.setPlayers(players);
		principal.setpOpenMatches(pOpenMatches);
	}

	public void checkOwner(OpenMatch openMatch) {
		Customer principal;

		principal = customerService.findByPrincipal();

		Assert.isTrue(openMatch.getOwner().equals(principal));
	}

	public Collection<OpenMatch> findByOwnOrJoinedByCustomerId() {
		Collection<OpenMatch> result;
		Customer principal;
		int customerId;

		principal = customerService.findByPrincipal();
		customerId = principal.getId();
		result = openMatchRepository.findOwnOrJoinedByCustomerId(customerId);

		return result;
	}
}