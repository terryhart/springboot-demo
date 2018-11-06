
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `nev_account`
-- ----------------------------
DROP TABLE IF EXISTS `nev_account`;
CREATE TABLE `nev_account` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `account` varchar(32) NOT NULL COMMENT '账号',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `mobile` varchar(16) NOT NULL COMMENT '手机号',
  `role_id` bigint(11) NOT NULL COMMENT '角色id',
  `city_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '城市id，0表示不限制城市',
  `position` varchar(32) DEFAULT NULL COMMENT '职位',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='账户表';

-- ----------------------------
-- Records of nev_account
-- ----------------------------
INSERT INTO `nev_account` VALUES ('1', 'admin', 'E10ADC3949BA59ABBE56E057F20F883E', '朱继锟', '13888888888', '1', '0', '产品经理', 'NORMAL', '2018-11-02 17:54:50', '2018-11-02 17:54:43');

-- ----------------------------
-- Table structure for `nev_account_role`
-- ----------------------------
DROP TABLE IF EXISTS `nev_account_role`;
CREATE TABLE `nev_account_role` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) NOT NULL COMMENT '角色名称',
  `privilege_code` varchar(256) DEFAULT NULL COMMENT '权限code，多个用逗号分开',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='账户角色';

-- ----------------------------
-- Records of nev_account_role
-- ----------------------------
INSERT INTO `nev_account_role` VALUES ('1', '管理员', '1001,1002,1003,1004,1101,1102,1103,1201,1202,1301,1302,1303,1304', 'NORMAL', '2018-11-01 09:41:09', '2018-10-26 10:23:49');

-- ----------------------------
-- Table structure for `nev_city`
-- ----------------------------
DROP TABLE IF EXISTS `nev_city`;
CREATE TABLE `nev_city` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `province` varchar(32) NOT NULL COMMENT '省份',
  `city` varchar(32) NOT NULL COMMENT '城市',
  `plate_numbers` varchar(32) NOT NULL COMMENT '车牌编号',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='开通城市';

-- ----------------------------
-- Records of nev_city
-- ----------------------------
INSERT INTO `nev_city` VALUES ('1', '广东省', '深圳市', '粤B', 'NORMAL', '2018-11-02 11:30:18', '2018-10-26 15:30:10');

-- ----------------------------
-- Table structure for `nev_customer`
-- ----------------------------
DROP TABLE IF EXISTS `nev_customer`;
CREATE TABLE `nev_customer` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键自增id',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `mobile` varchar(32) NOT NULL COMMENT '手机号',
  `source` varchar(16) NOT NULL COMMENT '来源',
  `city_id` bigint(11) NOT NULL DEFAULT '0' COMMENT '地域id，0表示不限定区域',
  `address` varchar(128) DEFAULT NULL COMMENT '详细地址',
  `message` text COMMENT '留言',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `wechat` varchar(32) DEFAULT NULL COMMENT '微信号',
  `vehicle_type_id` bigint(11) DEFAULT NULL COMMENT '意向车型id',
  `closing_cost` decimal(11,2) DEFAULT NULL COMMENT '车辆成交价',
  `salesman_id` bigint(11) DEFAULT NULL COMMENT '销售员id',
  `remark` text COMMENT '用户备注',
  `keyword` varchar(256) DEFAULT NULL COMMENT '关键字',
  `discard_reason` text COMMENT '放弃原因',
  `discard_time` varchar(16) DEFAULT NULL COMMENT '放弃时间，格式yyyy-MM-dd',
  `discard_status` varchar(16) DEFAULT NULL COMMENT '放弃时的状态',
  `buy_way` varchar(16) DEFAULT NULL COMMENT '购买方式',
  `insure_date` varchar(16) DEFAULT NULL COMMENT '保险日期，格式yyyy-MM-dd',
  `contract_link` text COMMENT '合同链接，多个用逗号分隔',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='客户购车申请表';

-- ----------------------------
-- Records of nev_customer
-- ----------------------------

-- ----------------------------
-- Table structure for `nev_follow_records`
-- ----------------------------
DROP TABLE IF EXISTS `nev_follow_records`;
CREATE TABLE `nev_follow_records` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customer_id` bigint(11) NOT NULL COMMENT '客户id',
  `salesman_id` bigint(11) DEFAULT NULL COMMENT '销售员id',
  `status` varchar(16) NOT NULL COMMENT '跟进状态',
  `keyword` varchar(256) DEFAULT NULL COMMENT '关键字',
  `remark` text COMMENT '跟进备注',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8 COMMENT='跟进记录';

-- ----------------------------
-- Records of nev_follow_records
-- ----------------------------

-- ----------------------------
-- Table structure for `nev_receipt`
-- ----------------------------
DROP TABLE IF EXISTS `nev_receipt`;
CREATE TABLE `nev_receipt` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customer_id` bigint(11) NOT NULL COMMENT '客户id',
  `amount` decimal(11,2) NOT NULL COMMENT '收款金额',
  `type` varchar(16) NOT NULL COMMENT '收款类型',
  `vehicle_id` bigint(11) DEFAULT NULL COMMENT '车辆id',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='收款记录';

-- ----------------------------
-- Records of nev_receipt
-- ----------------------------

-- ----------------------------
-- Table structure for `nev_refund_records`
-- ----------------------------
DROP TABLE IF EXISTS `nev_refund_records`;
CREATE TABLE `nev_refund_records` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `customer_id` bigint(11) NOT NULL COMMENT '客户id',
  `amount` decimal(11,2) NOT NULL COMMENT '退款金额',
  `reason` varchar(16) NOT NULL COMMENT '退款原因',
  `vehicle_id` bigint(11) DEFAULT NULL COMMENT '车辆id',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='退款记录';

-- ----------------------------
-- Records of nev_refund_records
-- ----------------------------

-- ----------------------------
-- Table structure for `nev_vehicle_info`
-- ----------------------------
DROP TABLE IF EXISTS `nev_vehicle_info`;
CREATE TABLE `nev_vehicle_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `vehicle_type_id` bigint(11) NOT NULL COMMENT '车型id',
  `customer_id` bigint(11) DEFAULT NULL COMMENT '客户id',
  `plate_numbers` varchar(32) NOT NULL COMMENT '车牌号',
  `price` decimal(11,2) NOT NULL COMMENT '售价',
  `closing_cost` decimal(11,2) DEFAULT NULL COMMENT '成交价',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `deal_date` timestamp NULL DEFAULT NULL COMMENT '成交日期',
  `return_date` timestamp NULL DEFAULT NULL COMMENT '退回日期',
  `return_reason` text COMMENT '退回原因',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8 COMMENT='车辆信息';

-- ----------------------------
-- Records of nev_vehicle_info
-- ----------------------------

-- ----------------------------
-- Table structure for `nev_vehicle_type`
-- ----------------------------
DROP TABLE IF EXISTS `nev_vehicle_type`;
CREATE TABLE `nev_vehicle_type` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `distributor` varchar(32) NOT NULL COMMENT '分销商',
  `trademark` varchar(32) NOT NULL COMMENT '车辆品牌',
  `status` varchar(16) NOT NULL COMMENT '状态',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='车辆类目';

-- ----------------------------
-- Records of nev_vehicle_type
-- ----------------------------
