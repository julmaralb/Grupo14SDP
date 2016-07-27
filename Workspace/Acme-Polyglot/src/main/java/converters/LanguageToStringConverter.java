package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Language;

@Component
@Transactional
public class LanguageToStringConverter implements Converter<Language, String> {

	@Override
	public String convert(Language language) {
		String result;
		if (language == null) {
			result = null;
		} else {
			result = String.valueOf(language.getId());
		}
		return result;
	}
}
