package com.superpay.common.exceptions.enums;

import lombok.Getter;

/**
 * @author lihainuo
 */

@Getter
public enum ResponseEnum {
    SUCCESS(20000, "success"),
    ERROR(-1, "失败"),
    INVALID_PARAMETERS(30001, "参数错误"),
    TOO_MANY_REQUESTS(30002, "请求过多"),
    TOKEN_ERROR(40001, "token错误"),
    TOKEN_EXPIRED(40002, "token过期"),
    TOKEN_SIGNATURE_INVALID(40003, "token签名无效"),
    TOKEN_MALFORMED(40004, "token格式错误"),
    TOKEN_MISSING(40005, "token丢失"),
    INVALID_ACCOUNT(40006, "非法的账号信息"),
    INVALID_PHONE(40007, "非法的手机号"),
    LOGIN_TYPE_NOT_SUPPORTED(40008, "不支持的登录类型"),;


    private Integer code;
    private String message;

    private ResponseEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
