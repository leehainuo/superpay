package com.superpay.merchant.service.api;

import com.superpay.merchant.model.entity.Store;
import com.superpay.merchant.service.service.StoreService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/storeApi")
public class StoreApi {
    @Resource
    private StoreService storeService;

    @PostMapping("save")
    public boolean save(@RequestBody Store store){
        this.storeService.saveStore(store);
        return true;
    }
}
