package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.LanguageExchange;

@Component
@Transactional
public class LanguageExchangeToStringConverter implements
		Converter<LanguageExchange, String> {

	@Override
	public String convert(LanguageExchange languageExchange) {
		String result;
		if (languageExchange == null) {
			result = null;
		} else {
			result = String.valueOf(languageExchange.getId());
		}
		return result;
	}
}
