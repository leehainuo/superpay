package com.superpay.sso.model.dto;

import lombok.Data;

@Data
public class AdminLoginDTO {
    private String username;
    private String phone;
    private String password;
}
