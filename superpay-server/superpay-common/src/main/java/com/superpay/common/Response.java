package com.superpay.common;

import com.superpay.common.exceptions.enums.ResponseEnum;
import lombok.Data;

/**
 * @author lihainuo
 */
@Data
public class Response<T> {
    private T data;
    private int errorCode;
    private String message;
    private boolean success;

    public static <T> Response<T> error(String message) {
        Response<T> response = new Response<>();
        response.setErrorCode(-1);
        response.setMessage(message);
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> error(ResponseEnum resp){
        Response<T> response = new Response<>();
        response.setErrorCode(resp.getCode());
        response.setMessage(resp.getMessage());
        response.setSuccess(false);
        return response;
    }

    public static <T> Response<T> success() {
        return success(null);
    }

    public static <T> Response<T> success(T data) {
        Response<T> response = new Response<>();
        response.setErrorCode(20000);
        response.setData(data);
        response.setSuccess(true);
        response.setMessage("success");
        return response;
    }
}
