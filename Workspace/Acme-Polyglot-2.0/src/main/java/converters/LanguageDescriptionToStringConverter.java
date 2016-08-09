package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.LanguageDescription;

@Component
@Transactional
public class LanguageDescriptionToStringConverter implements
		Converter<LanguageDescription, String> {

	@Override
	public String convert(LanguageDescription languageDescription) {
		String result;
		if (languageDescription == null) {
			result = null;
		} else {
			result = String.valueOf(languageDescription.getId());
		}
		return result;
	}

}
