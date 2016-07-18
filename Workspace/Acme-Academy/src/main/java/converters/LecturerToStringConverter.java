package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Lecturer;

@Component
@Transactional
public class LecturerToStringConverter implements Converter<Lecturer, String> {

	@Override
	public String convert(Lecturer lecturer) {
		String result;
		if (lecturer == null) {
			result = null;
		} else {
			result = String.valueOf(lecturer.getId());
		}
		return result;
	}

}
