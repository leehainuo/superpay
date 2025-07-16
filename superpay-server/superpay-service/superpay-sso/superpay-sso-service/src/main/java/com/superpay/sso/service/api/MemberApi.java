package com.superpay.sso.service.api;

import com.superpay.sso.service.service.MemberService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/memberApi")
public class MemberApi {
    @Resource
    private MemberService memberService;

    @GetMapping("getMemberIdByToken")
    public String getMemberIdByToken(){
        return memberService.getMemberIdByToken();
    }
}
