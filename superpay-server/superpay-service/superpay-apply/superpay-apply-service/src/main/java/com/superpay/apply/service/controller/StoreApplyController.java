package com.superpay.apply.service.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.superpay.apply.model.dto.ApplyPageQueryDTO;
import com.superpay.apply.model.dto.StoreApplyDTO;
import com.superpay.apply.model.entity.StoreApply;
import com.superpay.apply.service.service.StoreApplyService;
import com.superpay.common.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 店铺入驻申请表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Tag(name="店铺管理")
@Slf4j
@RestController
@RequestMapping("/storeApply")
public class StoreApplyController {
    @Resource
    private StoreApplyService storeApplyService;

    @PostMapping("demo")
    public String demo(){
        return "hello world";
    }

    @Operation(summary = "店铺入驻申请", description = "店铺入驻申请")
    @PostMapping("apply")
    public Response<Object> apply(@RequestBody StoreApply storeApply){
        storeApplyService.apply(storeApply);
        return Response.success();
    }

    @Operation(summary = "店铺入驻审批", description = "店铺入驻审批")
    @PutMapping("approval")
    public Response<Object> approval(@RequestBody StoreApplyDTO storeApplyDTO){
        storeApplyService.approval(storeApplyDTO);
        return Response.success();
    }

    @Operation(summary = "分页查询店铺入驻申请", description = "分页查询店铺入驻申请")
    @PostMapping("page")
    public Response<Object> getPage(@RequestBody ApplyPageQueryDTO applyPageQueryDTO){
        IPage<StoreApply> page= storeApplyService.getPage(applyPageQueryDTO);
        return Response.success(page);
    }

}
