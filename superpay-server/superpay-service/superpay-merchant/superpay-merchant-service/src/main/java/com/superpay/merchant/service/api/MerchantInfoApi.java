package com.superpay.merchant.service.api;

import com.superpay.merchant.model.entity.MerchantInfo;
import com.superpay.merchant.service.service.MerchantInfoService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lihainuo
 */
@Slf4j
@RestController
@RequestMapping("/merchantInfoApi")
public class MerchantInfoApi {
    @Resource
    private MerchantInfoService merchantInfoService;

    @PostMapping()
    public boolean save(@RequestBody MerchantInfo merchantInfo) {
        this.merchantInfoService.save(merchantInfo);
        return true;
    }
}
