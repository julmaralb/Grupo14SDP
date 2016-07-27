package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;

import repositories.PolyglotRepository;
import domain.Polyglot;

public class StringToPolyglotConverter implements Converter<String, Polyglot> {

	@Autowired
	PolyglotRepository polyglotRepository;

	@Override
	public Polyglot convert(String text) {
		Polyglot result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = polyglotRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
