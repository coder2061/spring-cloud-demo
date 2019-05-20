package com.springcloud.eureka.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 通过@ FeignClient("server_name")，来指定调用哪个服务
 * <p>
 * created by jiangyf on 2019-04-16 20:01
 */
@FeignClient(value = "spring-cloud-eureka-client1",  path = "/user")
public interface UserFeignService {

    @GetMapping("/hi")
    String hi(@RequestParam String name);
}
