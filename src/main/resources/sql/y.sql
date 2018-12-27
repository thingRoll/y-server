/*
 Navicat Premium Data Transfer

 Source Server         : MySQL
 Source Server Type    : MySQL
 Source Server Version : 50641
 Source Host           : localhost:3306
 Source Schema         : y

 Target Server Type    : MySQL
 Target Server Version : 50641
 File Encoding         : 65001

 Date: 27/12/2018 23:24:03
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
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容，html标签',
  `hide_info` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '隐藏信息',
  `category_id` bigint(50) NULL DEFAULT NULL COMMENT '分类id',
  `visit` int(50) NOT NULL DEFAULT 0 COMMENT '浏览量',
  `like` int(50) NOT NULL DEFAULT 0 COMMENT '点赞量',
  `plus` int(1) NOT NULL DEFAULT 0,
  `pan` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网盘下载地址',
  `pan_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网盘提取码',
  `magnet` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '磁力链接',
  `disable` int(1) NOT NULL DEFAULT 0 COMMENT '状态，0=开启，1=禁用',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `category_id`(`category_id`) USING BTREE,
  CONSTRAINT `category_id` FOREIGN KEY (`category_id`) REFERENCES `article_category` (`id`) ON DELETE SET NULL ON UPDATE SET NULL
) ENGINE = InnoDB AUTO_INCREMENT = 28 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '文章列表' ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_category
-- ----------------------------
DROP TABLE IF EXISTS `article_category`;
CREATE TABLE `article_category`  (
  `id` bigint(50) NOT NULL COMMENT '类别id',
  `parent_id` bigint(50) NOT NULL COMMENT '如果父类别id=-1，是根节点，一级类别',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '名称',
  `sort` int(10) NULL DEFAULT NULL COMMENT '排序，根节点优先级最高，越低越前，其次sort，越低越前，其次create_uptime，越新越后',
  `icon` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT 'base64编码的图标',
  `plus` int(1) NOT NULL,
  `disable` int(1) NULL DEFAULT NULL COMMENT '状态，0=开启，1=禁用',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for article_visit
-- ----------------------------
DROP TABLE IF EXISTS `article_visit`;
CREATE TABLE `article_visit`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT,
  `article_id` bigint(50) NOT NULL COMMENT '文章id',
  `article_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文章标题',
  `user_id` bigint(50) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `os` int(1) NULL DEFAULT NULL COMMENT '浏览平台，0=网页，1=安卓',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网页，记录浏览器名称，安卓，记录设备名称，未知填0',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `article_id`(`article_id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `article_id` FOREIGN KEY (`article_id`) REFERENCES `article` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB AUTO_INCREMENT = 89 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Table structure for home_visit
-- ----------------------------
DROP TABLE IF EXISTS `home_visit`;
CREATE TABLE `home_visit`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '网站访问记录id',
  `session_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户id',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `os` int(1) NULL DEFAULT NULL COMMENT '浏览平台，0=网页，1=安卓',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网页，记录浏览器名称，安卓，记录设备名称，未知填0',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  CONSTRAINT `home_visit_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 43 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

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
  `role` int(5) NOT NULL COMMENT '用户角色，\r\n0=超级管理员，\r\n1=管理员，\r\n2=查看管理员，\r\n10=超级用户，\r\n11=用户',
  `tel` bigint(50) NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `email_code` int(10) NULL DEFAULT NULL COMMENT '绑定邮箱的验证码',
  `email_state` int(1) NULL DEFAULT NULL COMMENT '邮箱状态，0=未激活，1=激活',
  `tel_code` int(10) NULL DEFAULT NULL COMMENT '绑定手机的验证码',
  `tel_state` int(1) NULL DEFAULT NULL COMMENT '手机状态，0=未激活，1=激活',
  `disable` int(1) NULL DEFAULT NULL COMMENT '状态，0=禁用，1=禁用',
  `os` int(1) NULL DEFAULT NULL COMMENT '操作平台，0=网页，1=安卓',
  `device` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '网页，记录浏览器名称，安卓，记录设备名称，未知填0',
  `visit_time` datetime(0) NULL DEFAULT NULL COMMENT '最后一次访问时间',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名',
  UNIQUE INDEX `email`(`email`) USING BTREE COMMENT '邮箱'
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户列表' ROW_FORMAT = Compact;

SET FOREIGN_KEY_CHECKS = 1;
