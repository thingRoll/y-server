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

 Date: 07/09/2018 15:39:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(50) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户密码',
  `token` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份验证',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `tel` bigint(50) NULL DEFAULT NULL COMMENT '手机号码',
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `role` int(5) NOT NULL COMMENT '用户角色，0=管理员，1=普通用户',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  `update_time` datetime(0) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `username`(`username`) USING BTREE COMMENT '用户名'
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', 'a7ea664c576767c54e931d49747828b1', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MSwiZXhwIjoxNTM2MzAwMjM2LCJ1c2VybmFtZSI6ImFkbWluIn0.vBHNLfoBIrcfU6SXz45v8EbAapzQG7nZ3q-qvbovMWY', NULL, NULL, NULL, 0, '2018-09-06 17:06:48', '2018-09-07 14:03:56');
INSERT INTO `user` VALUES (2, 'superuser', 'a7ea664c576767c54e931d49747828b1', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MiwiZXhwIjoxNTM2Mzc4NTkzLCJ1c2VybmFtZSI6InN1cGVydXNlciJ9.q6KZeT76Vcio_X_yGhPpCqegAjpzSGAUgQ6buTSdViM', NULL, 15875006020, 'conghuahuadan@163.com', 1, '2018-09-06 17:09:17', '2018-09-07 11:49:53');
INSERT INTO `user` VALUES (3, 'chhd', 'a7ea664c576767c54e931d49747828b1', 'eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6MywiZXhwIjoxNTM2Mzg2MjgyLCJ1c2VybmFtZSI6ImNoaGQifQ.-pRCeMPEdO-4tz4UIanyp67pX2_Pmrd-b5ySWCMb9BI', NULL, 15875006022, NULL, 1, '2018-09-07 13:51:10', '2018-09-07 13:58:02');
INSERT INTO `user` VALUES (4, 'cwq', 'a7ea664c576767c54e931d49747828b1', NULL, NULL, NULL, 'cwq@163.com', 1, '2018-09-07 14:38:46', '2018-09-07 14:38:46');

SET FOREIGN_KEY_CHECKS = 1;
