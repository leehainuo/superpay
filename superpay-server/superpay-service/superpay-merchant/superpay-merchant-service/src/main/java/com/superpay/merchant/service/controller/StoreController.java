package com.superpay.merchant.service.controller;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.superpay.common.Response;
import com.superpay.merchant.model.dto.StorePageQueryDTO;
import com.superpay.merchant.model.entity.Store;
import com.superpay.merchant.service.service.StoreService;
import com.superpay.merchant.model.vo.StorePageVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 店铺表 前端控制器
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Slf4j
@Tag(name = "店铺控制器")
@RestController
@RequestMapping("/store")
public class StoreController {

    @Resource
    private RabbitTemplate rabbitTemplate;
    @Resource
    private StoreService storeService;

    @Operation(summary = "分页查询店铺", description = "根据条件分页查询店铺，主要提供给管理后台")
    @PostMapping("page")
    public Response<IPage<Store>> getPage(@RequestBody StorePageQueryDTO storePageQueryDTO){
        IPage<Store> page= storeService.getPage(storePageQueryDTO);
        return Response.success(page);
    }

    //附近的店铺分页显示
    @Operation(summary = "分页查询附近店铺", description = "根据条件分页查询附近店铺，主要提供给小程序端")
    @PostMapping("nearbyPage")
    public Response<IPage<StorePageVO>> getNearbyPage(@RequestBody StorePageQueryDTO storePageQueryDTO){
        log.info("分页查询附近店铺，参数:{}",storePageQueryDTO);
        IPage<StorePageVO> page= storeService.getNearbyPage(storePageQueryDTO);
        return Response.success(page);
    }

    @PostMapping()
    public void getStoriesByCondition(@RequestBody StorePageQueryDTO storePageQueryDTO){

    }

    @Operation(summary = "根据id查询店铺", description = "根据id查询店铺")
    @GetMapping("{id}")
    public Response<Store> getStoreById(@PathVariable String id){
        Store store = storeService.getById(id);

        Map<String,Object> map=new HashMap<>();
        map.put("userId","1001");
        map.put("storeId",id);
        map.put("action","VIEW");
        map.put("time", LocalDateTime.now());

        rabbitTemplate.convertAndSend("member.behavior.queue", JSON.toJSONString(map));
        return Response.success(store);
    }

    @GetMapping("generateQrCodeByStoreApplyId/{storeApplyId}")
    public Response<String> generateQrCodeByStoreApplyId(@PathVariable String storeApplyId) {
        String base64Image= storeService.generateQrCodeByStoreApplyId(storeApplyId);
        return Response.success(base64Image);
    }
}
