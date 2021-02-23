/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-self
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.105.222.40:3306
 Source Schema         : seata_goods

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 23/02/2021 16:28:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for goods
-- ----------------------------
DROP TABLE IF EXISTS `goods`;
CREATE TABLE `goods`  (
  `goods_id` int NOT NULL AUTO_INCREMENT,
  `info` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品信息',
  `total` int NOT NULL COMMENT '库存',
  `money` int NOT NULL COMMENT '商品金额',
  `freeze` int NOT NULL DEFAULT 0 COMMENT '冻结数量',
  PRIMARY KEY (`goods_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of goods
-- ----------------------------
INSERT INTO `goods` VALUES (1, '苹果', 19, 1000, 0);

SET FOREIGN_KEY_CHECKS = 1;
