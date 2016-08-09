package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.LanguageDescriptionRepository;
import domain.LanguageDescription;

@Component
@Transactional
public class StringToLanguageDescriptionConverter implements
		Converter<String, LanguageDescription> {

	@Autowired
	LanguageDescriptionRepository languageDescriptionRepository;

	@Override
	public LanguageDescription convert(String text) {
		LanguageDescription result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = languageDescriptionRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
