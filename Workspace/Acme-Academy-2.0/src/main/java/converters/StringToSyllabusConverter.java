package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SyllabusRepository;
import domain.Syllabus;

@Component
@Transactional
public class StringToSyllabusConverter implements Converter<String, Syllabus> {

	@Autowired
	SyllabusRepository syllabusRepository;

	@Override
	public Syllabus convert(String text) {
		Syllabus result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = syllabusRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
