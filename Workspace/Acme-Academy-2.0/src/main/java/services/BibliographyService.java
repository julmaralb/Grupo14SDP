package services;

import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.BibliographyRepository;
import domain.Bibliography;

@Service
@Transactional
public class BibliographyService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private BibliographyRepository bibliographyRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public BibliographyService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public Bibliography create() {
		Bibliography result;

		result = new Bibliography();

		result.setCounter(0);

		return result;
	}

	public Bibliography findOne(int bibliographyId) {
		Assert.notNull(bibliographyId);

		Bibliography result;

		result = bibliographyRepository.findOne(bibliographyId);

		return result;
	}

	public Collection<Bibliography> findAll() {

		Collection<Bibliography> result;

		result = bibliographyRepository.findAll();

		return result;
	}

	public void save(Bibliography bibliography) {
		Assert.notNull(bibliography);

		bibliographyRepository.save(bibliography);
	}

	public void delete(Bibliography bibliography) {
		Assert.notNull(bibliography);

		bibliographyRepository.delete(bibliography);
	}

	// Other business methods -------------------------------------------------

	public Collection<Bibliography> findBySyllabusId(int syllabusId) {
		Collection<Bibliography> result;

		result = bibliographyRepository.findBySyllabusId(syllabusId);

		return result;
	}
}