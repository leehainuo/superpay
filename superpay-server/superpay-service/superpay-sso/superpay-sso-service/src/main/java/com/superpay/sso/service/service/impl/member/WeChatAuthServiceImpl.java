package com.superpay.sso.service.service.impl.member;

import com.alibaba.fastjson2.JSON;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.superpay.member.api.MemberInfoClient;
import com.superpay.member.model.entity.MemberInfo;
import com.superpay.sso.model.bo.OpenIdBO;
import com.superpay.sso.service.config.WeChatConfig;
import com.superpay.sso.model.dto.LoginDTO;
import com.superpay.sso.model.entity.Member;
import com.superpay.sso.service.service.MemberAuthService;
import com.superpay.sso.service.service.MemberService;
import com.superpay.sso.service.utils.JwtUtil;
import com.superpay.sso.model.vo.MemberLoginVO;
import com.superpay.sso.service.wechat.WeChatAPI;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

/**
 * @author lihainuo
 */
@Slf4j
@Service("weChatAuthService")
public class WeChatAuthServiceImpl implements MemberAuthService {
    @Resource
    private RestTemplate restTemplate;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private WeChatConfig weChatConfig;

    @Resource
    private MemberService memberService;

    @Resource
    private MemberInfoClient memberInfoClient;

    @Override
    public void sendCode(String phone) {
        throw new RuntimeException("微信登录不需要发送验证码");
    }

    @Override
    public MemberLoginVO login(LoginDTO loginDTO) {
        String url = WeChatAPI.jscode2session;
        url = url.replace("{APPID}", weChatConfig.getAppid());
        url = url.replace("{SECRET}", weChatConfig.getSecret());
        url = url.replace("{CODE}", loginDTO.getCode());

        String str = restTemplate.getForObject(url, String.class);
        OpenIdBO bo = JSON.parseObject(str, OpenIdBO.class);
        log.info("{}", bo);

        LambdaQueryWrapper<Member> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Member::getOpenId, bo.getOpenid());
        Member member = memberService.getOne(wrapper);

        //登录即注册
        if (member == null) {
            //预防空指针
            member = new Member();
            member.setOpenId(bo.getOpenid());
            memberService.save(member);

            //推入队列
            MemberInfo memberInfo=new MemberInfo();
            memberInfo.setId(member.getId());
            memberInfo.setPhone(member.getPhone());
            memberInfoClient.save(memberInfo);
        }

        HashMap<String,Object> map=new HashMap<>();
        map.put("id",member.getId());
        map.put("role","member");
        String token = jwtUtil.createToken(map);

        MemberLoginVO memberLoginVO=new MemberLoginVO();
        memberLoginVO.setId(member.getId());
        memberLoginVO.setToken(token);
        return memberLoginVO;
    }
}
