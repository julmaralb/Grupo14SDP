package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Syllabus;

@Component
@Transactional
public class SyllabusToStringConverter implements Converter<Syllabus, String> {

	@Override
	public String convert(Syllabus syllabus) {
		String result;
		if (syllabus == null) {
			result = null;
		} else {
			result = String.valueOf(syllabus.getId());
		}
		return result;
	}

}
