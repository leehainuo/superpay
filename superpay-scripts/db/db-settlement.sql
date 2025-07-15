drop database if exists superpay_settlement;
create database superpay_settlement charset utf8mb4;
use superpay_settlement;

DROP TABLE IF EXISTS `settlement`;

CREATE TABLE `settlement`
(
    `id`              varchar(20)    NOT NULL DEFAULT '' COMMENT '结算总表id',
    `amount`          decimal(10, 2) NOT NULL DEFAULT '0' COMMENT '结算金额',
    `settlement_date` date            NULL DEFAULT NULL COMMENT '结算日期',
    `status`          char(1)        NOT NULL DEFAULT '' COMMENT '0-未提现 1-已提现',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at`      int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='结算总表';

DROP TABLE IF EXISTS `settlement_details`;
CREATE TABLE `settlement_details`
(
    `id`              char(20)       NOT NULL DEFAULT '' COMMENT '结算明细id',
    `member_id`       char(20)       NOT NULL DEFAULT '' COMMENT '会员id',
    `merchant_id`     char(20)       NOT NULL DEFAULT '' COMMENT '商家id',
    `store_id`        char(20)       NOT NULL DEFAULT '' COMMENT '店铺id',
    `price`           decimal(10, 2) NOT NULL DEFAULT '0' COMMENT '小票金额',
    `discount_amount` decimal(10, 2) NOT NULL DEFAULT '0' COMMENT '折扣金额',
    `pay_amount`      decimal(10, 2) NOT NULL DEFAULT '0' COMMENT '实付金额',
    `revenue`         decimal(10, 2) NOT NULL DEFAULT '0' COMMENT '平台收益',
    `principal`       decimal(10, 2) NOT NULL DEFAULT '0' COMMENT '本金',
    `created_at` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at`      int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='结算明细';