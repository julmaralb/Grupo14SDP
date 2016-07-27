package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.AgentRepository;
import security.LoginService;
import security.UserAccount;
import domain.Agent;

@Service
@Transactional
public class AgentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private AgentRepository agentRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public AgentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Agent create() {
		Agent result;

		result = new Agent();

		return result;
	}

	public Agent findOne(int agentId) {
		Assert.notNull(agentId);

		Agent result;

		result = agentRepository.findOne(agentId);

		return result;

	}

	public Collection<Agent> findAll() {

		Collection<Agent> result;

		result = agentRepository.findAll();

		return result;
	}

	public void save(Agent agent) {
		Assert.notNull(agent);

		agentRepository.save(agent);
	}

	public void delete(Agent agent) {
		Assert.notNull(agent);

		agentRepository.delete(agent);

	}

	// Other business methods -------------------------------------------------

	public Agent findByPrincipal() {
		Agent result;
		UserAccount userAccount;

		userAccount = LoginService.getPrincipal();
		result = findByUserAccount(userAccount);

		Assert.notNull(result);

		return result;
	}

	public Agent findByUserAccount(UserAccount userAccount) {
		assert userAccount != null;

		Agent result;

		result = agentRepository.findByUserAccountId(userAccount.getId());
		assert result != null;

		return result;
	}
}