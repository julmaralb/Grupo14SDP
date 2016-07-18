package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Assignment;

@Component
@Transactional
public class AssignmentToStringConverter implements Converter<Assignment, String> {

	@Override
	public String convert(Assignment assignment) {
		String result;
		if (assignment == null) {
			result = null;
		} else {
			result = String.valueOf(assignment.getId());
		}
		return result;
	}
}