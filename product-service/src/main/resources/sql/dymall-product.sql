CREATE DATABASE IF NOT EXISTS `dymall-product` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION = 'N' */;
USE `dymall-product`;

CREATE TABLE IF NOT EXISTS `product`
(
    `id`            bigint                                                  NOT NULL AUTO_INCREMENT COMMENT '商品id',
    `name`          varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
    `price`         int                                                     NOT NULL DEFAULT '0' COMMENT '价格',
    `stock`         int UNSIGNED                                            NOT NULL COMMENT '库存数量',
    `picture`         varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci          DEFAULT NULL COMMENT '商品图片',
    `category`      varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci          DEFAULT NULL COMMENT '类目名称',
    `description`   varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci          DEFAULT NULL COMMENT '商品描述',
    `sales`         int                                                              DEFAULT '0' COMMENT '销量',
    `status`        int                                                              DEFAULT '2' COMMENT '商品状态 1-正常，2-下架，3-删除',
    `create_time`   datetime                                                         DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`   datetime                                                         DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    `creator`       bigint                                                           DEFAULT NULL COMMENT '创建人',
    `updater`       bigint                                                           DEFAULT NULL COMMENT '修改人',
    PRIMARY KEY (`id`) USING BTREE,
    KEY `status` (`status`) USING BTREE,
    KEY `updated` (`update_time`) USING BTREE,
    KEY `category` (`category`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 100002672305
  DEFAULT CHARSET = utf8mb3
  ROW_FORMAT = COMPACT COMMENT ='商品表';

# 插入一些示例数据