package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.Deliverable;

@Component
@Transactional
public class DeliverableToStringConverter implements Converter<Deliverable, String> {
	
	@Override
	public String convert(Deliverable deliverable) {
		String result;
		if (deliverable == null) {
			result = null;
		} else {
			result = String.valueOf(deliverable.getId());
		}
		return result;
	}

}
