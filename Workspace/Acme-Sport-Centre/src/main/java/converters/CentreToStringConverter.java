package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Centre;

@Component
@Transactional
public class CentreToStringConverter implements Converter<Centre, String>{
	
	@Override
	public String convert(Centre centre) {
		String result;
		if (centre == null) {
			result = null;
		} else {
			result = String.valueOf(centre.getId());
		}
		return result;
	}

}
