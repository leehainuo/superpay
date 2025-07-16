package com.superpay.sso.service.config;

import com.superpay.sso.service.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ConcurrentHashMap;

@Configuration
public class MemberServiceConfig {
    public MemberServiceConfig(){
        System.out.println("config...");
    }
    @Bean
    public ConcurrentHashMap<String, MemberService> memberServiceMap(){
        ConcurrentHashMap<String, MemberService> map=new ConcurrentHashMap<>();
        return map;
    }
}
