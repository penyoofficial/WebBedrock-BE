/*
 Navicat Premium Dump SQL

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 80400 (8.4.0)
 Source Host           : localhost:3306
 Source Schema         : webbedrock

 Target Server Type    : MySQL
 Target Server Version : 80400 (8.4.0)
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for nekos
-- ----------------------------
DROP TABLE IF EXISTS `nekos`;
CREATE TABLE `nekos`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `gender` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `age` int NOT NULL,
  `skin_color` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `father_id` int NULL DEFAULT NULL,
  `mother_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`id` DESC) USING BTREE,
  INDEX `fk_father`(`father_id` ASC) USING BTREE,
  INDEX `fk_mother`(`mother_id` ASC) USING BTREE,
  CONSTRAINT `fk_father` FOREIGN KEY (`father_id`) REFERENCES `nekos` (`id`) ON DELETE SET NULL ON UPDATE CASCADE,
  CONSTRAINT `fk_mother` FOREIGN KEY (`mother_id`) REFERENCES `nekos` (`id`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of nekos
-- ----------------------------
INSERT INTO `nekos` VALUES (50, '大桃子', 'FEMALE', 5, 'CALICO', 1, 2);
INSERT INTO `nekos` VALUES (49, '小糖糖', 'MALE', 1, 'GINGER', 3, 4);
INSERT INTO `nekos` VALUES (48, '大奶油', 'FEMALE', 2, 'CREAM', 5, 6);
INSERT INTO `nekos` VALUES (47, '小银银', 'MALE', 3, 'SILVER', 7, 8);
INSERT INTO `nekos` VALUES (46, '大炭炭', 'FEMALE', 4, 'BLACK', 9, 10);
INSERT INTO `nekos` VALUES (45, '小咖啡', 'MALE', 5, 'BROWN', 11, 12);
INSERT INTO `nekos` VALUES (44, '大豆豆', 'FEMALE', 1, 'BROWN', 13, 14);
INSERT INTO `nekos` VALUES (43, '小柠柠', 'MALE', 2, 'YELLOW', 15, 16);
INSERT INTO `nekos` VALUES (42, '大奶茶', 'FEMALE', 3, 'CREAM', 17, 18);
INSERT INTO `nekos` VALUES (41, '小甜甜', 'MALE', 4, 'TABBY', 19, 20);
INSERT INTO `nekos` VALUES (40, '大香香', 'FEMALE', 5, 'GINGER', 21, 22);
INSERT INTO `nekos` VALUES (39, '小蓝蓝', 'MALE', 6, 'BLUE', 23, 24);
INSERT INTO `nekos` VALUES (38, '大橘子', 'FEMALE', 1, 'ORANGE', 25, 26);
INSERT INTO `nekos` VALUES (37, '小灰灰', 'MALE', 2, 'GRAY', 27, 28);
INSERT INTO `nekos` VALUES (36, '大花花', 'FEMALE', 3, 'CALICO', 29, 30);
INSERT INTO `nekos` VALUES (35, '小黑黑', 'MALE', 4, 'BLACK', 31, 32);
INSERT INTO `nekos` VALUES (34, '小白白', 'FEMALE', 5, 'WHITE', 33, 34);
INSERT INTO `nekos` VALUES (33, '大米米', 'MALE', 1, 'WHITE', 35, 36);
INSERT INTO `nekos` VALUES (32, '小糖糖', 'FEMALE', 2, 'GINGER', 37, 38);
INSERT INTO `nekos` VALUES (31, '大青青', 'MALE', 3, 'BLUE', 39, 40);
INSERT INTO `nekos` VALUES (30, '小苏苏', 'FEMALE', 4, 'TABBY', 41, 42);
INSERT INTO `nekos` VALUES (29, '大豆豆', 'MALE', 6, 'BROWN', 43, 44);
INSERT INTO `nekos` VALUES (28, '小梅梅', 'FEMALE', 1, 'GRAY', 45, 46);
INSERT INTO `nekos` VALUES (27, '大猫猫', 'MALE', 2, 'BLACK', 47, 48);
INSERT INTO `nekos` VALUES (26, '小熊熊', 'FEMALE', 3, 'BROWN', 49, 50);
INSERT INTO `nekos` VALUES (25, '小雪球', 'MALE', 4, 'WHITE', NULL, NULL);
INSERT INTO `nekos` VALUES (24, '大米粒', 'FEMALE', 5, 'WHITE', NULL, NULL);
INSERT INTO `nekos` VALUES (23, '小丸子', 'MALE', 1, 'ORANGE', NULL, NULL);
INSERT INTO `nekos` VALUES (22, '小松松', 'FEMALE', 2, 'TABBY', NULL, NULL);
INSERT INTO `nekos` VALUES (21, '大蛋蛋', 'MALE', 3, 'WHITE', NULL, NULL);
INSERT INTO `nekos` VALUES (20, '小香香', 'FEMALE', 4, 'GINGER', NULL, NULL);
INSERT INTO `nekos` VALUES (19, '大蓝猫', 'MALE', 6, 'BLUE', NULL, NULL);
INSERT INTO `nekos` VALUES (18, '咖啡豆', 'FEMALE', 5, 'BROWN', NULL, NULL);
INSERT INTO `nekos` VALUES (17, '豆丸', 'MALE', 2, 'BROWN', NULL, NULL);
INSERT INTO `nekos` VALUES (16, '奶油猫', 'FEMALE', 3, 'CREAM', NULL, NULL);
INSERT INTO `nekos` VALUES (15, '柠檬儿', 'FEMALE', 4, 'YELLOW', NULL, NULL);
INSERT INTO `nekos` VALUES (14, '小灰灰', 'MALE', 6, 'GRAY', NULL, NULL);
INSERT INTO `nekos` VALUES (13, '小橘子', 'FEMALE', 5, 'ORANGE', NULL, NULL);
INSERT INTO `nekos` VALUES (12, '银子', 'MALE', 1, 'SILVER', NULL, NULL);
INSERT INTO `nekos` VALUES (11, '炭仔', 'FEMALE', 2, 'BLACK', NULL, NULL);
INSERT INTO `nekos` VALUES (10, '虎子', 'MALE', 7, 'TABBY', NULL, NULL);
INSERT INTO `nekos` VALUES (9, '奶糖', 'FEMALE', 3, 'CREAM', NULL, NULL);
INSERT INTO `nekos` VALUES (8, '小菲', 'FEMALE', 2, 'YELLOW', NULL, NULL);
INSERT INTO `nekos` VALUES (7, '米琪', 'MALE', 6, 'WHITE', NULL, NULL);
INSERT INTO `nekos` VALUES (6, '灰太', 'MALE', 4, 'GRAY', NULL, NULL);
INSERT INTO `nekos` VALUES (5, '小虹', 'FEMALE', 1, 'CALICO', NULL, NULL);
INSERT INTO `nekos` VALUES (4, '橘仔', 'MALE', 2, 'ORANGE', NULL, NULL);
INSERT INTO `nekos` VALUES (3, '大海', 'MALE', 3, 'BLACK', NULL, NULL);
INSERT INTO `nekos` VALUES (2, '小星', 'FEMALE', 5, 'WHITE', NULL, NULL);
INSERT INTO `nekos` VALUES (1, '小三花', 'MALE', 8, 'YELLOW', NULL, NULL);

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `login_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `user_type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES (1, 'admin', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'ADMIN', '真·管理员');
INSERT INTO `users` VALUES (2, '轩轩', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 'O', NULL);

SET FOREIGN_KEY_CHECKS = 1;
