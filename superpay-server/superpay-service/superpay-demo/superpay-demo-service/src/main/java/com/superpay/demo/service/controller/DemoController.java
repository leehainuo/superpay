package com.superpay.demo.service.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihainuo
 */
@RestController
@RequestMapping("/")
public class DemoController {
    @PostMapping
    public String demo() {
        return "demo";
    }
}
