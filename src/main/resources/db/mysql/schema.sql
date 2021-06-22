CREATE TABLE IF NOT EXISTS `account` (
                                         `accountId` int unsigned NOT NULL AUTO_INCREMENT COMMENT '账户id',
                                         `userId` int DEFAULT NULL COMMENT '用户id',
                                         `updated` timestamp NULL DEFAULT NULL COMMENT '最后一次更新账号时间',
                                         `lastLoginTime` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '上次登录时间',
                                         `created` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建账号时间',
                                         `mobile` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户手机号',
    PRIMARY KEY (`accountId`)
    ) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `user` (
                                      `id` int unsigned NOT NULL AUTO_INCREMENT,
                                      `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE
    ) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;




CREATE TABLE IF NOT EXISTS `repository` (
                                            `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '仓库id',
                                            `address` varchar(60) DEFAULT NULL COMMENT '仓库地址',
    `mobile` varchar(30) DEFAULT NULL COMMENT '仓库电话',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `product_inventory` (
                                                   `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '商品库存id',
                                                   `num` int unsigned NOT NULL COMMENT '剩余库存',
                                                   `repository_id` int unsigned DEFAULT NULL COMMENT '商品所在库存id',
                                                   PRIMARY KEY (`id`),
    KEY `product_fk_repository` (`repository_id`),
    CONSTRAINT `product_fk_repository` FOREIGN KEY (`repository_id`) REFERENCES `repository` (`id`)
    ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `product` (
                                         `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '商品id',
                                         `name` varchar(50) DEFAULT NULL COMMENT '商品名称',
    `img` varchar(255) DEFAULT NULL COMMENT '商品图片',
    `price` double DEFAULT NULL COMMENT '商品价格',
    `created` timestamp NULL DEFAULT NULL COMMENT '商品上架时间',
    `desc` varchar(255) DEFAULT NULL COMMENT '商品描述',
    `flag` tinyint DEFAULT '0' COMMENT '商品状态：0商品已下架，1商品未下架',
    PRIMARY KEY (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE IF NOT EXISTS `order` (
                                       `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '账户id',
                                       `created` timestamp NULL DEFAULT NULL COMMENT '订单创建时间',
                                       `total` double DEFAULT NULL COMMENT '订单总价',
                                       `state` tinyint DEFAULT NULL COMMENT '订单状态：1未付款，2已付款，未发货，3已发货，没收货，4收货，订单结束',
                                       `name` varchar(10) DEFAULT NULL COMMENT '收货人',
    `address` varchar(50) DEFAULT NULL COMMENT '收货地址',
    `mobile` varchar(20) DEFAULT NULL COMMENT '收货人手机号',
    `account_id` int unsigned DEFAULT NULL COMMENT '订单所属账户ID',
    PRIMARY KEY (`id`),
    KEY `order_fk_user` (`account_id`),
    CONSTRAINT `order_fk_user` FOREIGN KEY (`account_id`) REFERENCES `account` (`accountId`) ON DELETE RESTRICT ON UPDATE RESTRICT
    ) ENGINE=InnoDB AUTO_INCREMENT=44 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE IF NOT EXISTS `order_item` (
                                            `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '单项商品ID',
                                            `order_id` int unsigned DEFAULT NULL COMMENT '所属订单id',
                                            `product_id` int unsigned DEFAULT NULL COMMENT '所属商品ID',
                                            `product_num` int unsigned DEFAULT '0' COMMENT '同样商品数量',
                                            `product_price` double DEFAULT '0' COMMENT '购买时商品单价',
                                            PRIMARY KEY (`id`),
    KEY `order_item_fk_product_id` (`product_id`),
    KEY `order_item_fk_order_id` (`order_id`),
    CONSTRAINT `order_item_fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`),
    CONSTRAINT `order_item_fk_product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`)
    ) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
