package com.pugwoo.bootwebext.resolver;

import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.exc.InvalidDefinitionException;
import com.pugwoo.bootwebext.JsonParam;
import com.pugwoo.wooutils.json.JSON;
import org.springframework.core.Conventions;
import org.springframework.core.GenericTypeResolver;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Type;

import static com.pugwoo.bootwebext.resolver.AbstractMessageConverterMethodArgumentResolverUtil.*;

/**
 * http://zjumty.iteye.com/blog/1927890
 * 些许改造 2015年8月15日 12:13:01
 * 2018年3月14日 16:52:42 完整支持了泛型，支持1或2个泛型，不支持3个及以上
 * 2022年01月12日 支持泛型，采用springboot的方案，与@RequestBody对json的解析保持一致
 */
public class JsonParamArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		return parameter.getParameterAnnotation(JsonParam.class) != null;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
		parameter = parameter.nestedIfOptional();
		Object arg = parseJsonParam(parameter, webRequest);
		String name = Conventions.getVariableNameForParameter(parameter);
		if (binderFactory != null) {
			WebDataBinder binder = binderFactory.createBinder(webRequest, arg, name);
			if (arg != null) {
				validateIfApplicable(binder, parameter);
				if (binder.getBindingResult().hasErrors() && isBindExceptionRequired(parameter)) {
					throw new MethodArgumentNotValidException(parameter, binder.getBindingResult());
				}
			}
			if (mavContainer != null) {
				mavContainer.addAttribute(BindingResult.MODEL_KEY_PREFIX + name, binder.getBindingResult());
			}
		}
		return adaptArgumentIfNecessary(arg, parameter);
	}
	
	/**
	 * 解析参数对象
	 */
	private Object parseJsonParam(MethodParameter parameter, NativeWebRequest webRequest) throws IOException {
		// 获得@JsonParam注解的value值
		JsonParam jsonParam = parameter.getParameterAnnotation(JsonParam.class);
		String paramName = "";
		if(jsonParam != null) {
			paramName = jsonParam.value();
		}
		
		Object request = webRequest.getNativeRequest();
		if (isHaveInterface(request, "jakarta.servlet.http.HttpServletRequest")) {
			return parse(paramName, parameter, (jakarta.servlet.http.HttpServletRequest) request);
		} else if (isHaveInterface(request, "javax.servlet.http.HttpServletRequest")) {
			 return parse(paramName, parameter, (javax.servlet.http.HttpServletRequest) request);
		 } else {
			throw new UnsupportedOperationException("Unknown request type: " + request.getClass().getName());
		}
	}

	private static boolean isHaveInterface(Object object, String interfaceClass) {
		if (object == null || interfaceClass == null) {
			return false;
		}
		Class<?> currentClass = object.getClass();
		while (currentClass != null) {
			Class<?>[] currentInterfaces = currentClass.getInterfaces();
			for (Class<?> iface : currentInterfaces) {
				if (interfaceClass.equals(iface.getName())) {
					return true;
				}
			}
			currentClass = currentClass.getSuperclass();
		}
		return false;
	}

	private Object parse(String paramName, MethodParameter parameter,
						 jakarta.servlet.http.HttpServletRequest request) throws IOException {
		String paramValue;
		if(paramName.isEmpty()) {
			// 把reqeust的body读取到StringBuilder
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();

			char[] buf = new char[1024];
			int rd;
			while((rd = reader.read(buf)) != -1){
				sb.append(buf, 0, rd);
			}
			paramValue = sb.toString();
		} else {
			paramValue = request.getParameter(paramName);
		}

		if(paramValue == null || paramValue.trim().isEmpty()) {
			return null;
		}

		// jsonString -> obj
		Type type = parameter.getNestedGenericParameterType();
		Class<?> clazz = parameter.getContainingClass();
		JavaType javaType = JSON.getObjectMapper().constructType(GenericTypeResolver.resolveType(type, clazz));

		try {
			return JSON.getObjectMapper().readValue(paramValue, javaType);
		} catch (InvalidDefinitionException ex) {
			throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
		}
	}

	private Object parse(String paramName, MethodParameter parameter,
						 javax.servlet.http.HttpServletRequest request) throws IOException {
		String paramValue;
		if(paramName.isEmpty()) {
			// 把reqeust的body读取到StringBuilder
			BufferedReader reader = request.getReader();
			StringBuilder sb = new StringBuilder();

			char[] buf = new char[1024];
			int rd;
			while((rd = reader.read(buf)) != -1){
				sb.append(buf, 0, rd);
			}
			paramValue = sb.toString();
		} else {
			paramValue = request.getParameter(paramName);
		}

		if(paramValue == null || paramValue.trim().isEmpty()) {
			return null;
		}

		// jsonString -> obj
		Type type = parameter.getNestedGenericParameterType();
		Class<?> clazz = parameter.getContainingClass();
		JavaType javaType = JSON.getObjectMapper().constructType(GenericTypeResolver.resolveType(type, clazz));

		try {
			return JSON.getObjectMapper().readValue(paramValue, javaType);
		} catch (InvalidDefinitionException ex) {
			throw new HttpMessageConversionException("Type definition error: " + ex.getType(), ex);
		}
	}

}
