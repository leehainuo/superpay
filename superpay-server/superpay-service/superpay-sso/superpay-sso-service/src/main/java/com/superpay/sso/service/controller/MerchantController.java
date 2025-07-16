package com.superpay.sso.service.controller;

import com.superpay.common.Response;
import com.superpay.sso.model.entity.Merchant;
import com.superpay.sso.service.service.MerchantService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商户表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@RestController
@RequestMapping("/merchant")
public class MerchantController {
    @Resource
    private MerchantService merchantService;

    @PostMapping("")
    public Response<Object> save(@RequestBody Merchant merchant){
        this.merchantService.save(merchant);
        return Response.success();
    }
}
