package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SupervisorRepository;
import domain.Supervisor;

@Component
@Transactional
public class StringToSupervisorConverter implements
		Converter<String, Supervisor> {

	@Autowired
	SupervisorRepository supervisorRepository;

	@Override
	public Supervisor convert(String text) {
		Supervisor result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = supervisorRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}
}