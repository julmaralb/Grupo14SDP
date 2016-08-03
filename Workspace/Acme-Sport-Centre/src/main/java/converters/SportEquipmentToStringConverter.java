package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SportEquipment;

@Component
@Transactional
public class SportEquipmentToStringConverter implements Converter<SportEquipment, String> {

	@Override
	public String convert(SportEquipment sportEquipment) {
		String result;
		if (sportEquipment == null) {
			result = null;
		} else {
			result = String.valueOf(sportEquipment.getId());
		}
		return result;
	}

}
