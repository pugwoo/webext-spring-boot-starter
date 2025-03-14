package com.pugwoo.bootwebext;

import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.pugwoo.bootwebext.converter.StringToDateConverter;
import com.pugwoo.bootwebext.converter.StringToLocalDateConverter;
import com.pugwoo.bootwebext.converter.StringToLocalDateTimeConverter;
import com.pugwoo.bootwebext.resolver.JsonParamArgumentResolver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@ConditionalOnWebApplication
@Configuration
public class SpringBootWebextAutoConfiguration implements WebMvcConfigurer {

	@Value("${webext.format.date:yyyy-MM-dd HH:mm:ss}")
	private String dateFormat;

	@Value("${webext.format.localDateTime:yyyy-MM-dd HH:mm:ss}")
	private String localDateTimeFormat;

	/**
	 * 设置Date类型的输出格式为yyyy-MM-dd HH:mm:ss
	 */
	@Bean
	public Jackson2ObjectMapperBuilderCustomizer jsonCustomizer() {
	    return builder -> {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(localDateTimeFormat);
			LocalDateTimeSerializer localDateTimeSerializer = new LocalDateTimeSerializer(formatter);

			builder.failOnEmptyBeans(false) // prevent InvalidDefinitionException Error
					.serializerByType(LocalDateTime.class, localDateTimeSerializer)
					.simpleDateFormat(dateFormat);
		};
	}

    /**
     * 支持@JsonParam注解
     */
	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
		resolvers.add(new JsonParamArgumentResolver());
	}

    /**
     * 支持将参数转化为Date<br>
     * 
     *  示例: request: xxx?time=20180621<br>
     *  接口接收参数为 (Date time)<br>
     */
	@Override
	public void addFormatters(FormatterRegistry registry) {
		registry.addConverter(new StringToDateConverter());
		registry.addConverter(new StringToLocalDateConverter());
		registry.addConverter(new StringToLocalDateTimeConverter());
	}

}
