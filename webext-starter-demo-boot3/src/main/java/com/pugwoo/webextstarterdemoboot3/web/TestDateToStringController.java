package com.pugwoo.webextstarterdemoboot3.web;

import com.pugwoo.webextstarterdemoboot3.bean.WebJsonBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 输出Date类型时，按0时区处理，输出格式yyyy-MM-dd HH:mm:ss
 * 2018-06-22
 */
@RestController
public class TestDateToStringController {

    @RequestMapping("/testDateToString")
    public WebJsonBean testDateToString(Date time){
        Map<String, Object> result = new HashMap<>();
        if (time == null) {
            result.put("date", "time参数未提供或者格式错误，请确保time参数正确提供，如 ?time=20180808");
        } else {
            result.put("date", time);
        }

        Map<String, Object> now = new HashMap<>();
        result.put("now", now);

        now.put("Date", new Date());
        now.put("LocalDate", LocalDate.now());
        now.put("LocalDateTime", LocalDateTime.now());
        now.put("LocalTime", LocalTime.now());

        return new WebJsonBean(result);
    }

}
