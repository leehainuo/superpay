package com.superpay.apply.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.superpay.apply.model.dto.ApplyPageQueryDTO;
import com.superpay.apply.model.dto.StoreApplyDTO;
import com.superpay.apply.model.entity.StoreApply;

/**
 * <p>
 * 店铺入驻申请表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
public interface StoreApplyService extends IService<StoreApply> {

    void apply(StoreApply storeApply);

    void approval(StoreApplyDTO storeApplyDTO);

    IPage<StoreApply> getPage(ApplyPageQueryDTO applyPageQueryDTO);
}
