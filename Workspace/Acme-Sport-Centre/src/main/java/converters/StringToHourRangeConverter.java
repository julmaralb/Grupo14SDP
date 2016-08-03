package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.HourRangeRepository;
import domain.HourRange;

@Component
@Transactional
public class StringToHourRangeConverter implements Converter<String, HourRange> {

	@Autowired
	HourRangeRepository hourRangeRepository;

	@Override
	public HourRange convert(String text) {
		HourRange result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = hourRangeRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}