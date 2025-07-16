package com.superpay.sso.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superpay.sso.model.entity.Admin;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
@Mapper
public interface AdminMapper extends BaseMapper<Admin> {

}
