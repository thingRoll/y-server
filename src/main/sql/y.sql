/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50721
 Source Host           : localhost:3306
 Source Schema         : y

 Target Server Type    : MySQL
 Target Server Version : 50721
 File Encoding         : 65001

 Date: 13/09/2018 18:44:09
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '文章id',
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `keyword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '关键字，英文逗号“,”分割',
  `summary` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '概述',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `hide_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '隐藏信息',
  `category_id` bigint(50) NULL DEFAULT NULL COMMENT '分类id',
  `visit` int(50) NOT NULL COMMENT '浏览量',
  `link` int(50) NOT NULL COMMENT '点赞量',
  `is_plus` int(1) NOT NULL,
  `disable` int(1) NOT NULL COMMENT '状态，0=开启，1=禁用',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `article_category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` bigint(50) NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '名称',
  `is_plus` int(1) NOT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for article_visit
-- ----------------------------
DROP TABLE IF EXISTS `article_visit`;
CREATE TABLE `article_visit`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(50) NOT NULL COMMENT '文章id',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `user_id` bigint(50) NOT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `os` int(1) NULL DEFAULT NULL COMMENT '浏览平台，0=网页，1=安卓',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网页，记录浏览器名称，安卓，记录设备名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登录账户，用户名，邮箱，手机',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份验证',
  `token_uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份验证id',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `role` int(5) NOT NULL COMMENT '用户角色，0=管理员，1=普通用户，2=会员',
  `tel` bigint(50) NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `email_code` int(10) NULL DEFAULT NULL COMMENT '绑定邮箱的验证码',
  `email_state` int(1) NOT NULL COMMENT '邮箱状态，0=未激活，1=激活',
  `tel_code` int(10) NULL DEFAULT NULL COMMENT '绑定手机的验证码',
  `tel_state` int(1) NOT NULL COMMENT '手机状态，0=未激活，1=激活',
  `disable` int(1) NOT NULL COMMENT '状态，0=开启，1=禁用',
  `os` int(1) NULL DEFAULT NULL COMMENT '浏览平台，0=网页，1=安卓',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网页，记录浏览器名称，安卓，记录设备名称',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名',
  UNIQUE INDEX `email`(`email`) USING BTREE COMMENT '邮箱'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户列表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'admin', 'a7ea664c576767c54e931d49747828b1', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlblVpZCI6ImQwZWMwYjkxLWIyYWUtNDg2Ny1hODhiLWVhODZiMTkzM2U3MSIsImlkIjoxLCJleHAiOjE1MzY4MTA0OTgsInVzZXJuYW1lIjoiYWRtaW4ifQ.xIB5QszDqevbyy6wrERkW-bv-Sf2eV7cLItCmOI8WlI', 'd0ec0b91-b2ae-4867-a88b-ea86b1933e71', NULL, 0, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, NULL, '2018-09-06 17:06:48', '2018-09-12 11:48:55');
INSERT INTO `user` VALUES (2, NULL, 'superuser', 'a7ea664c576767c54e931d49747828b1', NULL, NULL, NULL, 2, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, NULL, '2018-09-06 17:09:17', '2018-09-07 11:49:53');
INSERT INTO `user` VALUES (3, 'chhd', 'chhd', 'a7ea664c576767c54e931d49747828b1', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ0b2tlblVpZCI6IjE1MzY3MjQxMTUyNTIiLCJpZCI6MywiZXhwIjoxNTM2OTE2ODc3LCJ1c2VybmFtZSI6ImNoaGQifQ.YXaDomQ7Lv965EmDzGJ5C1rh194kAmaf__6BeToZyH0', '1536724115252', NULL, 1, NULL, NULL, NULL, 1, NULL, 0, 0, NULL, NULL, '2018-09-07 13:51:10', '2018-09-13 17:21:17');
INSERT INTO `user` VALUES (4, NULL, 'cwq', 'a7ea664c576767c54e931d49747828b1', NULL, NULL, NULL, 1, NULL, NULL, NULL, 0, NULL, 0, 0, NULL, NULL, '2018-09-07 14:38:46', '2018-09-07 14:38:46');

SET FOREIGN_KEY_CHECKS = 1;
