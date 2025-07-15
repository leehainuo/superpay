drop
database if exists superpay_admin;
create
database superpay_admin charset utf8mb4;
use
superpay_admin;

DROP TABLE IF EXISTS `admin_info`;
CREATE TABLE `admin_info`
(
    `id`             char(20)     NOT NULL DEFAULT '' COMMENT '管理员id',
    `phone`          varchar(18)  NOT NULL DEFAULT '' COMMENT '联系电话',
    `email`          varchar(255) NOT NULL DEFAULT '' COMMENT '邮箱',
    `avatar`         varchar(255) NOT NULL DEFAULT '' COMMENT '头像',
    `status`         char(1)      NOT NULL DEFAULT '0' COMMENT '状态 0-正常 1-禁用',
    `remark`         varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
    `recommendation` varchar(255) NOT NULL DEFAULT '' COMMENT '简介',
    `created_at`     DATETIME              DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`     DATETIME              DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at`     int(1)       NOT NULL DEFAULT 0 COMMENT '删除时间',
    primary key (`id`)
) COMMENT '商户信息表';