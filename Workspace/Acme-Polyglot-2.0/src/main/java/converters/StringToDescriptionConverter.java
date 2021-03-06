package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.DescriptionRepository;
import domain.Description;

@Component
@Transactional
public class StringToDescriptionConverter implements
		Converter<String, Description> {

	@Autowired
	DescriptionRepository descriptionRepository;

	@Override
	public Description convert(String text) {
		Description result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = descriptionRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}
