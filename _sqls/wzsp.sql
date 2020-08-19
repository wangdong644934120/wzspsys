/*
Navicat MySQL Data Transfer

Source Server         : guns
Source Server Version : 50556
Source Host           : localhost:3306
Source Database       : wzsp

Target Server Type    : MYSQL
Target Server Version : 50556
File Encoding         : 65001

Date: 2020-08-19 20:12:41
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wzsp_czjl
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_czjl`;
CREATE TABLE `wzsp_czjl` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `czsj` datetime DEFAULT NULL COMMENT '充值时间，2020-08-07 10:00:00',
  `czje` decimal(30,0) DEFAULT NULL COMMENT '100元',
  `czry` bigint(20) DEFAULT NULL COMMENT 'sus_user表中的id，给谁充值',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='充值记录表';

-- ----------------------------
-- Table structure for wzsp_ddsp
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_ddsp`;
CREATE TABLE `wzsp_ddsp` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `ddspid` bigint(20) DEFAULT NULL COMMENT '订单商品id',
  `ddspsl` int(50) DEFAULT NULL COMMENT '订单配送数量',
  `ddspdj` decimal(50,0) DEFAULT NULL COMMENT '订单商品单价',
  `ddid` bigint(20) DEFAULT NULL COMMENT '订单id（订单表中的id）',
  `ddr` bigint(20) DEFAULT NULL COMMENT '订单人（sys_user表中的id）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品表';

-- ----------------------------
-- Table structure for wzsp_ddspwc
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_ddspwc`;
CREATE TABLE `wzsp_ddspwc` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `ddspid` bigint(20) DEFAULT NULL COMMENT '订单商品id',
  `ddspsl` bigint(50) DEFAULT NULL COMMENT '订单商品数量',
  `ddspdj` decimal(50,0) DEFAULT NULL COMMENT '订单商品单价',
  `ddid` bigint(20) DEFAULT NULL COMMENT '订单id（订单表中的id）',
  `ddzj` varchar(50) DEFAULT NULL COMMENT '订单总价(数量*单价)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单商品完成表';

-- ----------------------------
-- Table structure for wzsp_ddwc
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_ddwc`;
CREATE TABLE `wzsp_ddwc` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `ddze` varchar(50) DEFAULT NULL COMMENT '订单总额',
  `fkqkye` varchar(50) DEFAULT NULL COMMENT '付款情况(余额)',
  `fkqkddzf` varchar(50) DEFAULT NULL COMMENT '付款情况(订单支付)',
  `ddsj` datetime DEFAULT NULL COMMENT '订单时间',
  `ddr` bigint(20) DEFAULT NULL COMMENT '订单人(sys_user表中的id)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='订单完成表';

-- ----------------------------
-- Table structure for wzsp_psdz
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_psdz`;
CREATE TABLE `wzsp_psdz` (
  `id` bigint(20) NOT NULL COMMENT '用户id，与sys_user表中的id相同',
  `dz` varchar(100) DEFAULT NULL COMMENT '配送地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='配送地址表';

-- ----------------------------
-- Table structure for wzsp_spgl
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_spgl`;
CREATE TABLE `wzsp_spgl` (
  `id` bigint(20) NOT NULL,
  `spmc` varchar(50) DEFAULT NULL COMMENT '商品名称',
  `spjg` decimal(50,0) DEFAULT NULL COMMENT '商品价格',
  `spkc` int(50) DEFAULT NULL COMMENT '商品库存',
  `spzp` varchar(200) DEFAULT NULL COMMENT '商品照片路径',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商品管理表';

-- ----------------------------
-- Table structure for wzsp_yhdd
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_yhdd`;
CREATE TABLE `wzsp_yhdd` (
  `id` bigint(20) NOT NULL,
  `ddze` varchar(50) DEFAULT NULL COMMENT '订单总额',
  `fkqkye` varchar(50) DEFAULT NULL COMMENT '付款情况(余额)',
  `fkqkddzf` varchar(50) DEFAULT NULL COMMENT '付款情况(到店支付)',
  `ddsj` datetime DEFAULT NULL COMMENT '订单时间',
  `ddr` bigint(20) DEFAULT NULL COMMENT '订单人(sys_user表中的id)',
  `psdz` varchar(100) DEFAULT NULL COMMENT '配送地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户订单表';

-- ----------------------------
-- Table structure for wzsp_yhye
-- ----------------------------
DROP TABLE IF EXISTS `wzsp_yhye`;
CREATE TABLE `wzsp_yhye` (
  `id` bigint(20) NOT NULL COMMENT '用户id，与sys_user表中的id相同',
  `yhye` decimal(20,0) DEFAULT NULL COMMENT '用户余额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户余额表';
