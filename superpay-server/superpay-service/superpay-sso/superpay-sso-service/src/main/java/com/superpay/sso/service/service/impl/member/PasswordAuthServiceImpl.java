package com.superpay.sso.service.service.impl.member;

import com.superpay.common.exceptions.BusinessException;
import com.superpay.common.exceptions.enums.ResponseEnum;
import com.superpay.sso.model.dto.LoginDTO;
import com.superpay.sso.model.entity.Member;
import com.superpay.sso.service.service.MemberAuthService;
import com.superpay.sso.service.service.MemberService;
import com.superpay.sso.service.utils.JwtUtil;
import com.superpay.sso.model.vo.MemberLoginVO;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

/**
 * @author lihainuo
 */
@Slf4j
@Service("passwordAuthService")
public class PasswordAuthServiceImpl implements MemberAuthService {
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MemberService memberService;

    @Override
    public void sendCode(String phone) {
        throw new RuntimeException("手机号加密码方式不需要发送验证码");
    }

    @Override
    public MemberLoginVO login(LoginDTO loginDTO) {

        log.info("账号密码登录方式....");
        Member member = memberService.lambdaQuery()
                .eq(Member::getPhone, loginDTO.getPhone())
                .one();
        if (member == null) {
            throw new BusinessException(ResponseEnum.INVALID_ACCOUNT);
        }

        if (!member.getPassword().equals(loginDTO.getPassword())) {
            throw new BusinessException(ResponseEnum.INVALID_ACCOUNT);
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
