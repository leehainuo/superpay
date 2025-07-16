package com.superpay.sso.service.api;

import com.superpay.sso.model.entity.Merchant;
import com.superpay.sso.service.service.MerchantService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/merchantApi")
public class MerchantApi {
    @Resource
    private MerchantService merchantService;

    @PostMapping("")
    public Boolean save(@RequestBody Merchant merchant){
        this.merchantService.save(merchant);
        return true;
    }
}
