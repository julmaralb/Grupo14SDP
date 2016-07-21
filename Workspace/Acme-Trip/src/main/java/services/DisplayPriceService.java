package services;

import java.util.ArrayList;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.DisplayPriceRepository;
import domain.DisplayPrice;

@Service
@Transactional
public class DisplayPriceService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private DisplayPriceRepository displayPriceRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public DisplayPriceService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public DisplayPrice create() {
		DisplayPrice result;

		result = new DisplayPrice();

		return result;
	}

	public DisplayPrice findOne(int displayPriceId) {
		Assert.notNull(displayPriceId);

		DisplayPrice result;

		result = displayPriceRepository.findOne(displayPriceId);

		return result;
	}

	public Collection<DisplayPrice> findAll() {

		Collection<DisplayPrice> result;

		result = displayPriceRepository.findAll();

		return result;
	}

	public void save(DisplayPrice displayPrice) {
		Assert.notNull(displayPrice);

		displayPriceRepository.save(displayPrice);
	}

	public void delete(DisplayPrice displayPrice) {
		Assert.notNull(displayPrice);

		displayPriceRepository.delete(displayPrice);
	}

	// Other business methods -------------------------------------------------

	public DisplayPrice findDisplayPrice() {
		Collection<DisplayPrice> displayPriceCollection;
		ArrayList<DisplayPrice> displayPriceList;
		DisplayPrice result;

		displayPriceCollection = displayPriceRepository.findAll();
		displayPriceList = new ArrayList<DisplayPrice>(displayPriceCollection);

		result = displayPriceList.get(0);

		return result;
	}
}