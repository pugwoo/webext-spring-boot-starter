package com.pugwoo.bootwebext.converter;

import com.pugwoo.wooutils.lang.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;

public class StringToLocalDateTimeConverter implements Converter<String, LocalDateTime> {

	@Override
	public LocalDateTime convert(String source) {
		return DateUtils.parseLocalDateTime(source);
	}

}
