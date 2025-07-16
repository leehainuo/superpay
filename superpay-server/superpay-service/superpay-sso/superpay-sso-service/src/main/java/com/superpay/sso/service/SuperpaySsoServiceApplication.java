package com.superpay.sso.service;

import com.superpay.member.api.MemberInfoClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = {MemberInfoClient.class})
@SpringBootApplication
public class SuperpaySsoServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperpaySsoServiceApplication.class, args);
    }

}
