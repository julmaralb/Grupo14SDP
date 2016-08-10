package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.SponsorshipDescriptionRepository;
import domain.SponsorshipDescription;

@Component
@Transactional
public class StringToSponsorshipDescriptionConverter implements
		Converter<String, SponsorshipDescription> {

	@Autowired
	SponsorshipDescriptionRepository sponsorshipDescriptionRepository;

	@Override
	public SponsorshipDescription convert(String text) {
		SponsorshipDescription result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = sponsorshipDescriptionRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}

}
