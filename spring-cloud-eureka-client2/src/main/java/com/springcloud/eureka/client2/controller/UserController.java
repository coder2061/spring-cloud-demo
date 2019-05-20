package com.springcloud.eureka.client2.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by jiangyf on 2019-04-16 16:15
 */
@RestController
public class UserController {

    @GetMapping("/test")
    public String test(@RequestParam String name) {
        return "hello," + name;
    }
}
