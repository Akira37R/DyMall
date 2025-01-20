CREATE DATABASE IF NOT EXISTS `dymall-cart` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;
USE `dymall-cart`;

DROP TABLE IF EXISTS `cart`;
CREATE TABLE IF NOT EXISTS `cart`
(
    `id`          bigint                                                  NOT NULL AUTO_INCREMENT COMMENT '购物车条目id ',
    `user_id`     bigint                                                  NOT NULL COMMENT '用户id',
    `product_id`     bigint                                                  NOT NULL COMMENT 'sku产品id',
    `num`         int                                                     NOT NULL DEFAULT '1' COMMENT '购买数量',
    `name`        varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '商品标题',
    `spec`        varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci         DEFAULT NULL COMMENT '商品动态属性键值集',
    `price`       int                                                     NOT NULL COMMENT '价格,单位：分',
    `image`       varchar(256) CHARACTER SET utf8 COLLATE utf8_general_ci          DEFAULT '' COMMENT '商品图片',
    `create_time` timestamp                                               NULL     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp                                               NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `key_user_item_id` (`user_id`, `product_id`) USING BTREE
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = COMPACT COMMENT ='订单详情表';

DELETE
FROM `cart`;
INSERT INTO `cart` (`id`, `user_id`, `product_id`, `num`, `name`, `spec`, `price`, `image`, `create_time`, `update_time`)
VALUES (7, 1, 100000006163, 1, '巴布豆(BOBDOG)柔薄悦动婴儿拉拉裤XXL码80片(15kg以上)', '{}', 67100,
        'https://m.360buyimg.com/mobilecms/s720x720_jfs/t23998/350/2363990466/222391/a6e9581d/5b7cba5bN0c18fb4f.jpg!q70.jpg.webp',
        '2023-05-20 13:07:09', '2023-05-20 13:07:09');
