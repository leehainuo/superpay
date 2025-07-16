package com.superpay.apply.service.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.superpay.apply.model.dto.ApplyPageQueryDTO;
import com.superpay.apply.model.dto.MerchantApplyDTO;
import com.superpay.apply.model.entity.MerchantApply;
import com.superpay.apply.service.service.MerchantApplyService;
import com.superpay.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 商家入驻申请表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Slf4j
@Tag(name = "商家入驻申请表")
@RestController
@RequestMapping("/merchantApply")
public class MerchantApplyController {
    @Resource
    private MerchantApplyService merchantApplyService;

    @Operation(summary = "申请入驻")
    @PostMapping("")
    public Response<Object> apply(@RequestBody MerchantApply merchantApply){
        merchantApplyService.apply(merchantApply);
        return Response.success();
    }

    @Operation(summary = "商家入驻审批")
    @PutMapping
    public Response<Object> approval(@RequestBody MerchantApplyDTO merchantApplyDTO){
        merchantApplyService.approval(merchantApplyDTO);
        return Response.success();
    }

    @Operation(summary = "分页查询商家入驻申请")
    @PostMapping("page")
    public Response<IPage<MerchantApply>> getPage(@RequestBody ApplyPageQueryDTO applyPageQueryDTO){
        IPage<MerchantApply> page= merchantApplyService.getPage(applyPageQueryDTO);
        return Response.success(page);
    }
}
