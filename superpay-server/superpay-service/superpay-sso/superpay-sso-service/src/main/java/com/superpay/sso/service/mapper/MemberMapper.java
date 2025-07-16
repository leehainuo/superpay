package com.superpay.sso.service.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.superpay.sso.model.entity.Member;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 会员表 Mapper 接口
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
@Mapper
public interface MemberMapper extends BaseMapper<Member> {

}
