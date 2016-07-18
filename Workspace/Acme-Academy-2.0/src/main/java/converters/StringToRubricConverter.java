package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.RubricRepository;
import domain.Rubric;

@Component
@Transactional
public class StringToRubricConverter implements Converter<String, Rubric> {

	@Autowired
	RubricRepository rubricRepository;

	@Override
	public Rubric convert(String text) {
		Rubric result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = rubricRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}