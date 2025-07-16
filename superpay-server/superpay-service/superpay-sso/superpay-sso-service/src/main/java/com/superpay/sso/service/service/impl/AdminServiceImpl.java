package com.superpay.sso.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.superpay.common.exceptions.BusinessException;
import com.superpay.common.exceptions.enums.ResponseEnum;
import com.superpay.sso.model.dto.AdminLoginDTO;
import com.superpay.sso.model.entity.Admin;
import com.superpay.sso.service.mapper.AdminMapper;
import com.superpay.sso.service.service.AdminService;
import com.superpay.sso.service.utils.JwtUtil;
import com.superpay.sso.model.vo.AdminLoginVO;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private HttpServletRequest request;

    @Override
    public AdminLoginVO login(AdminLoginDTO adminLoginDTO) {
        Admin admin = this.lambdaQuery().eq(Admin::getPhone, adminLoginDTO.getPhone())
                .or().eq(Admin::getPhone,adminLoginDTO.getUsername())
                .one();

        if (admin == null) {
            throw new BusinessException(ResponseEnum.INVALID_ACCOUNT);
        }

        //签发token
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", admin.getId());
        map.put("role","admin");
        String token = jwtUtil.createToken(map);

        AdminLoginVO adminLoginVO = new AdminLoginVO();
        adminLoginVO.setToken(token);
        return adminLoginVO;
    }

    @Override
    public String getAdminIdByToken() {
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
