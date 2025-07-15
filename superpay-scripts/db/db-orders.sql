drop
    database if exists superpay_orders;
create
    database superpay_orders charset utf8mb4;
use
    superpay_orders;

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders`
(
    `id`              varchar(20)    NOT NULL DEFAULT '' COMMENT '订单id',
    `open_id`         varchar(50)    NOT NULL DEFAULT '' comment '微信openId 主要用于微信的JsApi',
    `transaction_no`  varchar(35)    NOT NULL DEFAULT '' COMMENT '外部交易号-支付单号',
    `payment`         char(2)        NOT NULL DEFAULT '' COMMENT '支付方式 10=微信jsApi支付 11=微信APP支付 12=微信Native支付 13=微信刷脸支付 14=微信H5支付,20-支付宝扫码,30-银联,40-其他',
    `member_id`       varchar(20)    NOT NULL DEFAULT '' COMMENT '会员id',
    `nickname`        varchar(50)    NOT NULL DEFAULT '' COMMENT '会员昵称',
    `member_grade`    varchar(1)     NOT NULL DEFAULT '' COMMENT '会员等级,是否plus会员 0-游客/注册用户,1-付了一块钱->会员,2-付了198->plus会员',
    `phone`           char(11)       NOT NULL DEFAULT '' COMMENT '会员手机号',
    `price`           decimal(10, 2) NOT NULL DEFAULT 0.0 COMMENT '小票金额（原价）',
    `discount_amount` decimal(10, 2) NOT NULL DEFAULT 0.0 COMMENT '消费者优惠金额',
    `pay_amount`      decimal(10, 2) NOT NULL DEFAULT 0.0 COMMENT '实付金额',
    `perform_revenue` decimal(10, 2) NOT NULL DEFAULT 0.0 COMMENT '平台收益(利润空间)',
    `principal`       decimal(10, 2) NOT NULL DEFAULT 0.0 COMMENT '本金(采购价)',
    `merchant_id`     varchar(20)    NOT NULL DEFAULT '' COMMENT '商家id',
    `merchant_name`   varchar(50)    NOT NULL DEFAULT '' COMMENT '商家名字',
    `store_id`        varchar(20)    NOT NULL DEFAULT '' COMMENT '店铺id',
    `store_name`      char(50)       NOT NULL DEFAULT '' COMMENT '店铺名字',
    `status`          char(1)        NOT NULL DEFAULT '0' COMMENT '状态 0-取消支付/未支付 1-已支付 2-关闭订单',
    `has_coupon`      char(1)        NOT NULL DEFAULT '0' COMMENT '是否使用了优惠券 0-未使用 1-已使用',
    `coupon_id`       varchar(20)    NOT NULL DEFAULT '' COMMENT '优惠券id',
    `beans`           int(10)        NOT NULL DEFAULT '0' COMMENT '使用码豆数量',
    `remark`          varchar(255)   NOT NULL DEFAULT '' COMMENT '备注',
    `created_at`      DATETIME                DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `updated_at`      DATETIME                DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后更新时间',
    `deleted_at`      int(1)         NOT NULL DEFAULT 0 COMMENT '删除时间',
    PRIMARY KEY (`id`),
    KEY `idx_member_id` (`member_id`),
    KEY `idx_merchant_id` (`merchant_id`),
    KEY `idx_store_id` (`store_id`),
    KEY `idx_transaction_no` (`transaction_no`),
    KEY `idx_created_at` (`created_at`),
    KEY `idx_status` (`status`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';