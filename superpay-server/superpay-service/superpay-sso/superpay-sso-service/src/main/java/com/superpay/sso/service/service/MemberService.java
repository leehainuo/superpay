package com.superpay.sso.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.superpay.member.model.entity.MemberInfo;
import com.superpay.sso.model.entity.Member;


/**
 * <p>
 * 会员表 服务类
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
public interface MemberService extends IService<Member> {


    MemberInfo findByToken();

    String getMemberIdByToken();
}
