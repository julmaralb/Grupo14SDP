package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Renting;

@Component
@Transactional
public class RentingToStringConverter implements Converter<Renting, String> {

	@Override
	public String convert(Renting renting) {
		String result;
		if (renting == null) {
			result = null;
		} else {
			result = String.valueOf(renting.getId());
		}
		return result;
	}

}
