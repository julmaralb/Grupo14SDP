package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.OpenMatch;

@Component
@Transactional
public class OpenMatchToStringConverter implements Converter<OpenMatch, String> {

	@Override
	public String convert(OpenMatch openMatch) {
		String result;
		if (openMatch == null) {
			result = null;
		} else {
			result = String.valueOf(openMatch.getId());
		}
		return result;
	}

}