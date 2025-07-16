package com.superpay.sso.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superpay.sso.model.entity.Merchant;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 商户表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Mapper
public interface MerchantMapper extends BaseMapper<Merchant> {

}
