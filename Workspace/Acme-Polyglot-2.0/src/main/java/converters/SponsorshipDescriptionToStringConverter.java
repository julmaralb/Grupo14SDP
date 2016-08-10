package converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import domain.SponsorshipDescription;

@Component
@Transactional
public class SponsorshipDescriptionToStringConverter implements Converter<SponsorshipDescription, String>{
	
	@Override
	public String convert(SponsorshipDescription sponsorshipDescription){
		String result;
		if(sponsorshipDescription==null){
			result=null;
		}else{
			result=String.valueOf(sponsorshipDescription.getId());
		}
		return result;
	}

}
