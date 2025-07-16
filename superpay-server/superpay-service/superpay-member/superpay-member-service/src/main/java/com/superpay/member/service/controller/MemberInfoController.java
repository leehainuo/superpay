package com.superpay.member.service.controller;

import com.superpay.common.Response;
import com.superpay.member.model.entity.MemberInfo;
import com.superpay.member.service.service.MemberInfoService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 会员表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-05
 */
@Slf4j
@RestController
@RequestMapping("/memberInfo")
public class MemberInfoController {

    @Resource
    private MemberInfoService memberInfoService;

    @Operation(summary = "开发者测试接口")
    @PostMapping("/test")
    public Response<String> test(){
        return Response.success("hello member");
    }

    @PostMapping("")
    public Response<Object> save(@RequestBody MemberInfo memberInfo){
        log.info("memberInfo:{}",memberInfo);
        memberInfoService.save(memberInfo);
        return Response.success();
    }



}
