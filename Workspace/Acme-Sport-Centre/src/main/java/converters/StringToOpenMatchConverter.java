package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.OpenMatchRepository;
import domain.OpenMatch;

@Component
@Transactional
public class StringToOpenMatchConverter implements Converter<String, OpenMatch> {

	@Autowired
	OpenMatchRepository openMatchRepository;

	@Override
	public OpenMatch convert(String text) {
		OpenMatch result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = openMatchRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}