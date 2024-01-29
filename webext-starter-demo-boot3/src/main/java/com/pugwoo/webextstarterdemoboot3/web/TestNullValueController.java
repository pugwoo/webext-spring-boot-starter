package com.pugwoo.webextstarterdemoboot3.web;

import com.pugwoo.webextstarterdemoboot3.web.form.NullValueDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestNullValueController {

    @GetMapping("/testNullValue")
    public NullValueDTO testNullValue() {
        NullValueDTO dto = new NullValueDTO();
        dto.setName("a");
        dto.setAge(null);

        return dto;
    }

}
