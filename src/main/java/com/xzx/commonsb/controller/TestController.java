package com.xzx.commonsb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/test")
    public String HelloWorld() {
        return "hello,World1";
    }
}
