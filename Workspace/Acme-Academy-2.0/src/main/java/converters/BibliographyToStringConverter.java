package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Bibliography;

@Component
@Transactional
public class BibliographyToStringConverter implements
		Converter<Bibliography, String> {

	@Override
	public String convert(Bibliography bibliography) {
		String result;
		if (bibliography == null) {
			result = null;
		} else {
			result = String.valueOf(bibliography.getId());
		}
		return result;
	}

}
