package com.pugwoo.bootwebext;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.pugwoo.wooutils.json.MultiDateDeserializer;
import com.pugwoo.wooutils.json.MultiLocalDateDeserializer;
import com.pugwoo.wooutils.json.MultiLocalDateTimeDeserializer;
import com.pugwoo.wooutils.json.MultiLocalTimeDeserializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Configuration
@ConditionalOnWebApplication
public class CustomObjectMapperConfiguration {

    @Autowired
    public void setObjectMapper(ObjectMapper objectMapper) {

        // 自定义Date解析器
        {
            MultiDateDeserializer deserializer = new MultiDateDeserializer();
            SimpleModule module = new SimpleModule("DateDeserializerModule",
                    new Version(1, 0, 0, "", "", ""));
            module.addDeserializer(Date.class, deserializer);
            objectMapper.registerModule(module);
        }

        // 自定义LocalDate解析器，这个要放在JavaTimeModule后面，以覆盖JavaTimeModule对LocalDate的默认解析
        {
            MultiLocalDateDeserializer deserializer = new MultiLocalDateDeserializer();
            SimpleModule module = new SimpleModule("LocalDateDeserializerModule",
                    new Version(1, 0, 0, "", "", ""));
            module.addDeserializer(LocalDate.class, deserializer);
            objectMapper.registerModule(module);
        }

        // 自定义LocalDateTime解析器，这个要放在JavaTimeModule后面，以覆盖JavaTimeModule对LocalDateTime的默认解析
        {
            MultiLocalDateTimeDeserializer deserializer = new MultiLocalDateTimeDeserializer();
            SimpleModule module = new SimpleModule("LocalDateTimeDeserializerModule",
                    new Version(1, 0, 0, "", "", ""));
            module.addDeserializer(LocalDateTime.class, deserializer);
            objectMapper.registerModule(module);
        }

        // 自定义LocalTime解析器，这个要放在JavaTimeModule后面，以覆盖JavaTimeModule对LocalTime的默认解析
        {
            MultiLocalTimeDeserializer deserializer = new MultiLocalTimeDeserializer();
            SimpleModule module = new SimpleModule("LocalTimeDeserializerModule",
                    new Version(1, 0, 0, "", "", ""));
            module.addDeserializer(LocalTime.class, deserializer);
            objectMapper.registerModule(module);
        }

    }

}
