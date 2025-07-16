package com.superpay.sso.model.bo;

import lombok.Data;

@Data
public class WeChatAccessToken {
    private String access_token;
    private Long expires_in;
}
