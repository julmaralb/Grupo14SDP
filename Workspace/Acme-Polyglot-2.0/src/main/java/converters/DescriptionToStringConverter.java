package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Description;

@Component
@Transactional
public class DescriptionToStringConverter implements
		Converter<Description, String> {

	@Override
	public String convert(Description description) {
		String result;
		if (description == null) {
			result = null;
		} else {
			result = String.valueOf(description.getId());
		}
		return result;
	}
}
