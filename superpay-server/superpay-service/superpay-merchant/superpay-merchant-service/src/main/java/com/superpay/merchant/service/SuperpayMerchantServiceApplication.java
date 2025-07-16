package com.superpay.merchant.service;

import com.superpay.base.api.BaseClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(clients = {BaseClient.class})
@SpringBootApplication
public class SuperpayMerchantServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SuperpayMerchantServiceApplication.class, args);
    }

}
