package com.superpay.apply.model.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ApplyPageQueryDTO implements Serializable {
    private int current;
    private int pageSize;
}
