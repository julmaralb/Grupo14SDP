package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.AssignmentRepository;

import domain.Assignment;

@Component
@Transactional
public class StringToAssignmentConverter implements Converter<String, Assignment> {

	@Autowired
	AssignmentRepository assignmentRepository;

	@Override
	public Assignment convert(String text) {
		Assignment result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = assignmentRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}