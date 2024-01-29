package com.pugwoo.webextstarterdemoboot3.web;

import com.pugwoo.webextstarterdemoboot3.entity.UserDO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/test")
    public UserDO test() {
        UserDO user = new UserDO();
        user.setName("nick");
        return user;
    }

}
