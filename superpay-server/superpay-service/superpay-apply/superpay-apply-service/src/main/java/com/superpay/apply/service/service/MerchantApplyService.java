package com.superpay.apply.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.superpay.apply.model.dto.ApplyPageQueryDTO;
import com.superpay.apply.model.dto.MerchantApplyDTO;
import com.superpay.apply.model.entity.MerchantApply;

/**
 * <p>
 * 商家入驻申请表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
public interface MerchantApplyService extends IService<MerchantApply> {

    void apply(MerchantApply merchantApply);

    void approval(MerchantApplyDTO merchantApplyDTO);

    IPage<MerchantApply> getPage(ApplyPageQueryDTO applyPageQueryDTO);
}
