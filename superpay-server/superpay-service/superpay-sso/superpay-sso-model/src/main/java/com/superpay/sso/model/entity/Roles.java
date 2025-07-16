package com.superpay.sso.model.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * <p>
 * 角色表
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
@Getter
@Setter
public class Roles implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    private String name;
}
