-- 创建数据库
DROP DATABASE IF EXISTS superpay_coupon;
CREATE DATABASE superpay_coupon CHARSET utf8mb4;
USE superpay_coupon;

-- 优惠券表
DROP TABLE IF EXISTS `coupon`;
CREATE TABLE `coupon`
(
    `id`             CHAR(20)    NOT NULL DEFAULT '' COMMENT '优惠券ID',
    `name`           VARCHAR(50) NOT NULL DEFAULT '' COMMENT '优惠券名称',
    `member_id`      VARCHAR(20) NOT NULL DEFAULT '' COMMENT '用户ID',
    `type_id`        CHAR(1)     NOT NULL DEFAULT '0' COMMENT '优惠类型ID',
    `status`         TINYINT     NOT NULL DEFAULT 0 COMMENT '状态 0-未使用 1-已使用 2-已过期',
    `validity_start` DATETIME             DEFAULT CURRENT_TIMESTAMP COMMENT '生效时间',
    `validity_end`   DATETIME             DEFAULT CURRENT_TIMESTAMP COMMENT '结束时间',
    `rule_id`        CHAR(20)    NOT NULL DEFAULT '' COMMENT '关联的规则ID',
    `created_at`     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at`     TINYINT     NOT NULL DEFAULT 0 COMMENT '软删除标志',
    PRIMARY KEY (`id`),
    KEY `idx_status` (`status`),
    KEY `idx_rule_id` (`rule_id`),
    KEY `idx_validity` (`validity_start`, `validity_end`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券表';

-- 优惠券范围表
DROP TABLE IF EXISTS `coupon_scope`;
CREATE TABLE `coupon_scope`
(
    `id`         CHAR(20) NOT NULL DEFAULT '' COMMENT '优惠券范围ID',
    `coupon_id`  CHAR(20) NOT NULL DEFAULT '' COMMENT '优惠券ID',
    `store_id`   CHAR(20) NOT NULL DEFAULT '' COMMENT '适用店铺的ID',
    `created_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at` TINYINT  NOT NULL DEFAULT 0 COMMENT '软删除标志',
    PRIMARY KEY (`id`),
    KEY `idx_coupon_id` (`coupon_id`),
    FOREIGN KEY (`coupon_id`) REFERENCES `coupon` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券范围表';

-- 优惠券类型表
DROP TABLE IF EXISTS `coupon_type`;
CREATE TABLE `coupon_type`
(
    `id`         CHAR(20)    NOT NULL DEFAULT '' COMMENT '优惠券类型ID',
    `type_name`  VARCHAR(50) NOT NULL DEFAULT '' COMMENT '优惠券类型名称 1-折扣 2-满减',
    `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at` TINYINT     NOT NULL DEFAULT 0 COMMENT '软删除标志',
    PRIMARY KEY (`id`),
    UNIQUE KEY `uk_type_name` (`type_name`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券类型表';

-- 优惠券规则主表
DROP TABLE IF EXISTS `coupon_rules`;
CREATE TABLE `coupon_rules`
(
    `id`         CHAR(20)    NOT NULL DEFAULT '' COMMENT '规则ID',
    `rule_type`  VARCHAR(20) NOT NULL DEFAULT '' COMMENT '规则类型，例如 discount, full_reduce',
    `created_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME    NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at` TINYINT     NOT NULL DEFAULT 0 COMMENT '软删除标志',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='优惠券规则主表';

-- 折扣规则表
DROP TABLE IF EXISTS `rule_discount`;
CREATE TABLE `rule_discount`
(
    `rule_id`       CHAR(20)       NOT NULL COMMENT '规则ID',
    `discount`      DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '普通会员折扣比例',
    `discount_plus` DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT 'Plus会员折扣比例',
    PRIMARY KEY (`rule_id`),
    FOREIGN KEY (`rule_id`) REFERENCES `coupon_rules` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='折扣规则表';

-- 满减规则表
DROP TABLE IF EXISTS `rule_full_reduce`;
CREATE TABLE `rule_full_reduce`
(
    `rule_id`     CHAR(20)       NOT NULL COMMENT '规则ID',
    `threshold`   DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '满减门槛',
    `reduce`      DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT '普通会员满减金额',
    `reduce_plus` DECIMAL(10, 2) NOT NULL DEFAULT 0 COMMENT 'Plus会员满减金额',
    PRIMARY KEY (`rule_id`),
    FOREIGN KEY (`rule_id`) REFERENCES `coupon_rules` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='满减规则表';