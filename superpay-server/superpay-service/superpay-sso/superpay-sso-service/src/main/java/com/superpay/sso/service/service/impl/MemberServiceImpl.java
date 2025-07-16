package com.superpay.sso.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superpay.common.exceptions.BusinessException;
import com.superpay.common.exceptions.enums.ResponseEnum;
import com.superpay.member.api.MemberInfoClient;
import com.superpay.member.model.entity.MemberInfo;
import com.superpay.sso.model.entity.Member;
import com.superpay.sso.service.mapper.MemberMapper;
import com.superpay.sso.service.service.MemberService;
import com.superpay.sso.service.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
@Slf4j
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

    @Resource
    private MemberMapper memberMapper;

    @Resource
    private HttpServletRequest request;

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private MemberInfoClient memberInfoClient;

    @Override
    public MemberInfo findByToken() {
        String token = request.getHeader("Authorization");
        if (token==null||token.isBlank()) {
            throw new BusinessException(ResponseEnum.TOKEN_MISSING);
        }
        token = token.replace("Bearer ", "");
        String valid = jwtUtil.validateToken(token);
        if("Expired".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_EXPIRED);
        }

        if("Invalid Signature".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_SIGNATURE_INVALID);
        }

        if("Malformed".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_MALFORMED);
        }

        if("Invalid".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_ERROR);
        }
        Claims claims = jwtUtil.parseToken(token);
        String id = claims.get("id", String.class);
        MemberInfo memberInfo = memberInfoClient.getInfoById(id);
        return memberInfo;
    }

    @Override
    public String getMemberIdByToken() {
        String token = request.getHeader("Authorization");
        log.info("token:{}", token);

        if(StringUtils.isBlank(token)){
            throw new BusinessException(ResponseEnum.TOKEN_MISSING);
        }
        token = token.replace("Bearer ", "");
        String valid = jwtUtil.validateToken(token);
        if("Expired".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_EXPIRED);
        }

        if("Invalid Signature".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_SIGNATURE_INVALID);
        }

        if("Malformed".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_MALFORMED);
        }

        if("Invalid".equals(valid)){
            throw new BusinessException(ResponseEnum.TOKEN_ERROR);
        }

        Claims claims = jwtUtil.parseToken(token);
        String id = claims.get("id", String.class);
        log.info("id:{}",id);
        return id;
    }
}
