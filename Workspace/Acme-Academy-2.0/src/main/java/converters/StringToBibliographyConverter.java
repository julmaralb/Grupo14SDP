package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.BibliographyRepository;
import domain.Bibliography;

@Component
@Transactional
public class StringToBibliographyConverter implements
		Converter<String, Bibliography> {

	@Autowired
	BibliographyRepository bibliographyRepository;

	@Override
	public Bibliography convert(String text) {
		Bibliography result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = bibliographyRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}