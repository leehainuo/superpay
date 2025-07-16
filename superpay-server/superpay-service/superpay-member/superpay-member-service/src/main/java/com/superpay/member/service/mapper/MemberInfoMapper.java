package com.superpay.member.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superpay.member.model.entity.MemberInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-05
 */
@Mapper
public interface MemberInfoMapper extends BaseMapper<MemberInfo> {

}
