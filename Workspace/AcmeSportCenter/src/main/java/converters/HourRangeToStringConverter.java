package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.HourRange;

@Component
@Transactional
public class HourRangeToStringConverter implements Converter<HourRange, String> {

	@Override
	public String convert(HourRange hourRange) {
		String result;
		if (hourRange == null) {
			result = null;
		} else {
			result = String.valueOf(hourRange.getId());
		}
		return result;
	}

}
