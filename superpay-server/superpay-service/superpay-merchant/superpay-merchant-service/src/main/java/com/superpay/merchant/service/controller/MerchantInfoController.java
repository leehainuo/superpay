package com.superpay.merchant.service.controller;

import com.superpay.common.Response;
import com.superpay.merchant.model.entity.MerchantInfo;
import com.superpay.merchant.service.service.MerchantInfoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 商户信息表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Slf4j
@Tag(name = "商户信息表")
@RestController
@RequestMapping("/merchantInfo")
public class MerchantInfoController {
    @Resource
    private MerchantInfoService merchantInfoService;

    @Operation(summary = "开发者测试接口")
    @PostMapping("/test")
    public Response<Object> test() {
        return Response.success("hello merchant");
    }

    @Operation(summary = "新增商户信息")
    @PostMapping()
    public Response<Object> save(MerchantInfo merchantInfo) {
        this.merchantInfoService.save(merchantInfo);
        return Response.success();
    }


}
