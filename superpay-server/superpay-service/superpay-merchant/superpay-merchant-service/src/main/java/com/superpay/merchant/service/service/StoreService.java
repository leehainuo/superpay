package com.superpay.merchant.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.superpay.merchant.model.dto.StorePageQueryDTO;
import com.superpay.merchant.model.entity.Store;
import com.superpay.merchant.model.vo.StorePageVO;

/**
 * <p>
 * 店铺表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
public interface StoreService extends IService<Store> {

    void saveStore(Store store);

    IPage<Store> getPage(StorePageQueryDTO storePageQueryDTO);

    IPage<StorePageVO> getNearbyPage(StorePageQueryDTO storePageQueryDTO);

    String generateQrCodeByStoreApplyId(String storeApplyId);
}
