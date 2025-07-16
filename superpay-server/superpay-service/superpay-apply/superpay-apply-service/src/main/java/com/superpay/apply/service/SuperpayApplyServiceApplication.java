package com.superpay.apply.service;

import com.superpay.merchant.api.MerchantClient;
import com.superpay.sso.api.SSOClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = {SSOClient.class, MerchantClient.class})
@SpringBootApplication
public class SuperpayApplyServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperpayApplyServiceApplication.class, args);
    }

}
