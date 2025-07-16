package com.superpay.merchant.api;

import com.superpay.merchant.model.entity.MerchantInfo;
import com.superpay.merchant.model.entity.Store;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(
        name = "superpay-merchant-service",
        contextId = "superpay-merchant-service"
)
public interface MerchantClient {
    @PostMapping("merchantInfoApi")
    public boolean save(@RequestBody MerchantInfo merchantInfo);

    @PostMapping("/storeApi/save")
    public boolean addStore(@RequestBody Store store);
}
