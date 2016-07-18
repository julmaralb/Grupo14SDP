package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Rubric;

@Component
@Transactional
public class RubricToStringConverter implements Converter<Rubric, String> {

	@Override
	public String convert(Rubric rubric) {
		String result;
		if (rubric == null) {
			result = null;
		} else {
			result = String.valueOf(rubric.getId());
		}
		return result;
	}

}
