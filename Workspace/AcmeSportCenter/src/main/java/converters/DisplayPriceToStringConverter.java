package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.DisplayPrice;

@Component
@Transactional
public class DisplayPriceToStringConverter implements Converter<DisplayPrice, String> {

	@Override
	public String convert(DisplayPrice displayPrice) {
		String result;
		if (displayPrice == null) {
			result = null;
		} else {
			result = String.valueOf(displayPrice.getId());
		}
		return result;
	}

}
