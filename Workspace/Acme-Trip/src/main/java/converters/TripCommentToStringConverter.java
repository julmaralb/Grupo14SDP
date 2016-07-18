package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.TripComment;

@Component
@Transactional
public class TripCommentToStringConverter implements Converter<TripComment, String> {

	@Override
	public String convert(TripComment tripComment) {
		String result;
		if (tripComment == null) {
			result = null;
		} else {
			result = String.valueOf(tripComment.getId());
		}
		return result;
	}

}
