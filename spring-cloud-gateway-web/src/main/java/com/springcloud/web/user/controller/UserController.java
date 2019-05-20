package com.springcloud.web.user.controller;

import com.springcloud.web.user.feign.UserFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by jiangyf on 2019-04-16 16:15
 */
@RestController
public class UserController {

    @Autowired
    private UserFeignService userFeignService;

    @GetMapping("/hi")
    public String hi(@RequestParam String name) {
        return userFeignService.hi(name);
    }
}
