package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.DeliverableRepository;
import domain.Deliverable;

@Component
@Transactional
public class StringToDeliverableConverter implements Converter<String, Deliverable> {

	@Autowired
	DeliverableRepository deliverableRepository;

	@Override
	public Deliverable convert(String text) {
		Deliverable result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = deliverableRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
