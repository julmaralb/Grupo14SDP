package services;

import java.util.Collection;
import java.util.Random;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import repositories.SportEquipmentRepository;
import domain.SportEquipment;

@Service
@Transactional
public class SportEquipmentService {

	// Managed repository -----------------------------------------------------

	@Autowired
	private SportEquipmentRepository sportEquipmentRepository;

	// Supporting services ----------------------------------------------------

	// Constructors -----------------------------------------------------------

	public SportEquipmentService() {
		super();
	}

	// Simple CRUD methods ----------------------------------------------------

	public SportEquipment create() {
		SportEquipment result;
		String SKU;

		result = new SportEquipment();
		SKU = getAleatorySKU();

		result.setSKU(SKU);

		return result;
	}

	public SportEquipment findOne(int sportEquipmentId) {
		Assert.notNull(sportEquipmentId);

		SportEquipment result;

		result = sportEquipmentRepository.findOne(sportEquipmentId);

		return result;

	}

	public Collection<SportEquipment> findAll() {

		Collection<SportEquipment> result;

		result = sportEquipmentRepository.findAll();

		return result;
	}

	public void save(SportEquipment sportEquipment) {
		Assert.notNull(sportEquipment);

		sportEquipmentRepository.save(sportEquipment);
	}

	public void delete(SportEquipment sportEquipment) {
		Assert.notNull(sportEquipment);

		sportEquipmentRepository.delete(sportEquipment);

	}

	// Other business methods -------------------------------------------------

	public static String getAleatorySKU() {
		String result = "";
		Random random = new Random();
		int letra;
		Boolean f;

		while (result.length() < 7) {
			if (result.length() == 2) {
				result += "-";
			}

			if (Math.random() < 0.51) {
				result += Integer.toString(random.nextInt(10));
			} else {
				f = true;
				while (f) {
					letra = random.nextInt(123);
					if ((letra > 96 && letra < 123)
							|| (letra > 67 && letra < 89)) {
						result += (char) letra;
						f = false;
					}
				}
			}
		}
		return result;
	}
}