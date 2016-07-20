package converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import repositories.ChargeRecordRepository;
import domain.ChargeRecord;

@Component
@Transactional
public class StringToChargeRecordConverter implements
		Converter<String, ChargeRecord> {

	@Autowired
	ChargeRecordRepository chargeRecordRepository;

	@Override
	public ChargeRecord convert(String text) {
		ChargeRecord result;
		int id;

		try {
			if (StringUtils.isEmpty(text))
				result = null;
			else {
				id = Integer.valueOf(text);
				result = chargeRecordRepository.findOne(id);
			}
		} catch (Throwable oops) {
			throw new IllegalArgumentException(oops);
		}
		return result;
	}
}