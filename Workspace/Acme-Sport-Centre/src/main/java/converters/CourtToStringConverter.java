package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Court;

@Component
@Transactional
public class CourtToStringConverter implements Converter<Court, String> {

	@Override
	public String convert(Court court) {
		String result;
		if (court == null) {
			result = null;
		} else {
			result = String.valueOf(court.getId());
		}
		return result;
	}

}