package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.RentingRepository;
import domain.Renting;

@Component
@Transactional
public class StringToRentingConverter implements Converter<String, Renting> {

	@Autowired
	RentingRepository rentingRepository;

	@Override
	public Renting convert(String text) {
		Renting result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = rentingRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;
	}

}
