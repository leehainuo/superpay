package com.superpay.sso.service.service;

import com.superpay.sso.model.dto.LoginDTO;
import com.superpay.sso.model.vo.MemberLoginVO;

public interface MemberAuthService {
    void sendCode(String phone);

    MemberLoginVO login(LoginDTO loginDTO);
}
