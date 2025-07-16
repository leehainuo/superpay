package com.superpay.sso.api;


import com.superpay.sso.model.entity.Merchant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "superpay-sso-service",
        contextId = "superpay-sso-service"
)
public interface SSOClient {

    @GetMapping("memberApi/getMemberIdByToken")
    String getMemberIdByToken();

    @PostMapping("merchantApi")
    Boolean save(@RequestBody Merchant merchant);

    @PostMapping("adminApi/getAdminIdByToken")
    String getAdminIdByToken(String token);
}
