package com.pugwoo.bootwebext;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnWebApplication
public class CustomObjectMapperConfiguration {

    @Value("${spring.jackson.default-property-inclusion:}")
    private String inclusion;

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {
        if ("non_null".equalsIgnoreCase(inclusion)) {
            objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        }
    }

}
