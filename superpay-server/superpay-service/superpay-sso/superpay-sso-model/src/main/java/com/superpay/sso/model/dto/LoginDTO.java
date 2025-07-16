package com.superpay.sso.model.dto;

import lombok.Data;

/**
 * @author lihainuo
 */
@Data
public class LoginDTO {
    private String phone;
    private String password;
    private String code;
    private String type;
}
