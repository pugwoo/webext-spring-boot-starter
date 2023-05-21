package com.pugwoo.bootwebext.converter;

import com.pugwoo.wooutils.lang.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.time.LocalDate;

public class StringToLocalDateConverter implements Converter<String, LocalDate> {

	@Override
	public LocalDate convert(String source) {
		return DateUtils.parseLocalDate(source);
	}

}
