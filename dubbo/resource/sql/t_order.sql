/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-self
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.105.222.40:3306
 Source Schema         : seata_order

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 16/01/2021 14:44:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order`  (
  `order_id` int NOT NULL AUTO_INCREMENT,
  `money` int NOT NULL COMMENT '订单金额',
  `user_id` int NOT NULL COMMENT '用户id',
  `goods_id` int NOT NULL COMMENT '商品id',
  `amount` int NOT NULL COMMENT '订单数量',
  `description` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单描述',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES (1, 1000, 1, 1, 1, '事务');
INSERT INTO `t_order` VALUES (2, 1000, 1, 1, 1, '事务');

SET FOREIGN_KEY_CHECKS = 1;
