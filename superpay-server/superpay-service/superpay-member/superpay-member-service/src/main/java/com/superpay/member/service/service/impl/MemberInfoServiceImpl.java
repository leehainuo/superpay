package com.superpay.member.service.service.impl;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superpay.member.model.entity.MemberInfo;
import com.superpay.member.service.mapper.MemberInfoMapper;
import com.superpay.member.service.service.MemberInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2024-11-05
 */
@Service
public class MemberInfoServiceImpl extends ServiceImpl<MemberInfoMapper, MemberInfo> implements MemberInfoService {

}
