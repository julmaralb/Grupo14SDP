package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.TripCommentRepository;
import domain.TripComment;

@Component
@Transactional
public class StringToTripCommentConverter implements Converter<String, TripComment> {

	@Autowired
	TripCommentRepository tripCommentRepository;

	@Override
	public TripComment convert(String text) {
		TripComment result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = tripCommentRepository.findOne(id);
			}

		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}

		return result;

	}

}
