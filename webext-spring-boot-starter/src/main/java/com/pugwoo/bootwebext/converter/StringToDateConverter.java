package com.pugwoo.bootwebext.converter;

import com.pugwoo.wooutils.lang.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

/**
 * 将参数转化为Date。
 * 2017年9月14日 10:40:07 只保留最常用的，不考虑外国人习惯。
 */
public class StringToDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		return DateUtils.parse(source);
	}

}
