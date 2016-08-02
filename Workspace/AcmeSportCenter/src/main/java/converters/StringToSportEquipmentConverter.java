package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SportEquipmentRepository;
import domain.SportEquipment;

@Component
@Transactional
public class StringToSportEquipmentConverter implements
		Converter<String, SportEquipment> {

	@Autowired
	SportEquipmentRepository sportEquipmentRepository;

	@Override
	public SportEquipment convert(String text) {
		SportEquipment result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = sportEquipmentRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
