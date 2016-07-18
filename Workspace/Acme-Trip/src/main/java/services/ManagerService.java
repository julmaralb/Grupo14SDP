package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.ManagerRepository;
import domain.Manager;

@Service
@Transactional
public class ManagerService {
	// Managed repository -----------------------------------------------------

			@Autowired
			private ManagerRepository managerRepository;

			// Supporting services ----------------------------------------------------

			// Constructors -----------------------------------------------------------

			public ManagerService() {
				super();
			}

			// Simple CRUD methods ----------------------------------------------------

			public Manager create() {
				Manager result;

				result = new Manager();

				return result;
			}

			public Manager findOne(int managerId) {
				Assert.notNull(managerId);

				Manager result;

				result = managerRepository.findOne(managerId);

				return result;
			}

			public Collection<Manager> findAll() {

				Collection<Manager> result;

				result = managerRepository.findAll();

				return result;
			}

			public void save(Manager manager) {
				Assert.notNull(manager);

				managerRepository.save(manager);
			}

			public void delete(Manager manager) {
				Assert.notNull(manager);

				managerRepository.delete(manager);
			}

			// Other business methods -------------------------------------------------

		
	
}
