package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Polyglot;

@Component
@Transactional
public class PolyglotToStringConverter implements Converter<Polyglot, String> {

	@Override
	public String convert(Polyglot polyglot) {
		String result;
		if (polyglot == null) {
			result = null;
		} else {
			result = String.valueOf(polyglot.getId());
		}
		return result;
	}

}
