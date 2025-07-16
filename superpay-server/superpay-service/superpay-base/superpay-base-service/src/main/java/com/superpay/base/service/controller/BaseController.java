package com.superpay.base.service.controller;

import com.superpay.common.Response;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "开发者测试接口")
@RestController
@RequestMapping("/test")
public class BaseController {

    @PostMapping
    public Response<Object> test() {
        return Response.success("hello base");
    }
}
