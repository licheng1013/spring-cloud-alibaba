/*
 Navicat Premium Data Transfer

 Source Server         : aliyun-self
 Source Server Type    : MySQL
 Source Server Version : 80021
 Source Host           : 47.105.222.40:3306
 Source Schema         : seata_user

 Target Server Type    : MySQL
 Target Server Version : 80021
 File Encoding         : 65001

 Date: 23/02/2021 16:28:59
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `money` int NOT NULL,
  `freeze` int NOT NULL DEFAULT 0 COMMENT '冻结金额',
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, -2000, 0);

SET FOREIGN_KEY_CHECKS = 1;
