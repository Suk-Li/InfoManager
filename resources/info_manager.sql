/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50540
 Source Host           : localhost:3306
 Source Schema         : info_manager

 Target Server Type    : MySQL
 Target Server Version : 50540
 File Encoding         : 65001

 Date: 26/03/2022 20:53:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `note` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `price` float NULL DEFAULT NULL,
  `amount` int(11) NULL DEFAULT NULL,
  `pic` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`pid`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '橘子', '徐州甜蜜蜜', 4, 2088, '19a770b2-55b7-4b6b-87b0-5421b019a51c.jpeg');
INSERT INTO `product` VALUES (2, '苹果', '山东大苹果', 5, 1900, '');
INSERT INTO `product` VALUES (3, '菠萝', '广东大菠萝', 20, 50, '');
INSERT INTO `product` VALUES (4, '梨子', '北京大鸭梨', 1.5, 150, '');
INSERT INTO `product` VALUES (5, '柿子', '河南红柿子', 2.5, 200, '');
INSERT INTO `product` VALUES (6, '西瓜', '湖南无籽西瓜', 3.9, 200, '');
INSERT INTO `product` VALUES (7, '桃子', '成都水蜜桃', 8, 230, '');
INSERT INTO `product` VALUES (8, '荔枝', '增城荔枝', 10, 100, '');
INSERT INTO `product` VALUES (9, '葡萄', '新疆大葡萄', 12, 300, '');
INSERT INTO `product` VALUES (10, '哈密瓜', '吐鲁番哈密瓜', 20, 180, '');
INSERT INTO `product` VALUES (11, '樱桃', '智利樱桃', 28, 200, '');
INSERT INTO `product` VALUES (12, '香蕉', '菲律宾超甜香蕉', 3, 1200, '');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', '管理员', '123456');

SET FOREIGN_KEY_CHECKS = 1;
