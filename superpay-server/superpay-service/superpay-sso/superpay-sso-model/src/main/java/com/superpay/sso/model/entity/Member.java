package com.superpay.sso.model.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Date;

/**
 * <p>
 * 会员表
 * </p>
 *
 * @author lihainuo
 * @since 2024-10-27
 */
@Getter
@Setter
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 会员id
     */
    private String id;

    /**
     * 电话
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 最后登录ip
     */
    private String lastLoginIp;

    /**
     * 最后登录时间
     */
    private Date lastLoginTime;

    /**
     * 微信open_id
     */
    private String openId;

    /**
     * 微信union_id
     */
    private String unionId;

    /**
     * 状态 0-禁用 1-启用
     */
    private String status;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createdAt;

    /**
     * 最后更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date updatedAt;

    /**
     * 删除时间
     */
    private Integer deletedAt;
}
