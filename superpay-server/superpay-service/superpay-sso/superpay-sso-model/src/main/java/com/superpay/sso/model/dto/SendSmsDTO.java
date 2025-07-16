package com.superpay.sso.model.dto;

import lombok.Data;

/**
 * @author lihainuo
 */
@Data
public class SendSmsDTO {
    private String phone;
    private String type;
}
