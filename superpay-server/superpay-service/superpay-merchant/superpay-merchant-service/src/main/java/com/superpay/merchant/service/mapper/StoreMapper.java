package com.superpay.merchant.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superpay.merchant.model.entity.Store;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 店铺表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Mapper
public interface StoreMapper extends BaseMapper<Store> {

}
