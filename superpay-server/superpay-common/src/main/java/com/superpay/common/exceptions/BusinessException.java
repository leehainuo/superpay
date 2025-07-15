package com.superpay.common.exceptions;


import com.superpay.common.exceptions.enums.ResponseEnum;
import lombok.Data;

@Data
public class BusinessException extends RuntimeException {
  private ResponseEnum response;

  public BusinessException() {
  }

  public BusinessException(String message) {
    super(message);
  }

  public BusinessException(ResponseEnum response){
    super(response.getMessage());
    this.response=response;
  }
}
