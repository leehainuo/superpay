package com.superpay.merchant.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superpay.merchant.model.entity.Tag;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 标签表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-10
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {

}
