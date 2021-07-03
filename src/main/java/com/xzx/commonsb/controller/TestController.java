package com.xzx.commonsb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class TestController {
    @GetMapping("/test")
    public String HelloWorld() {
        return "hello,World1";
    }

    @PostMapping("/form")
    public String form(@RequestBody Map<String, Object> map) {
        System.out.println(map);
        return "success";
    }
}
