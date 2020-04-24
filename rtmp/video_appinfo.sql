/*
 Navicat Premium Data Transfer

 Source Server         : demo.jxrisesun.com_3308
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : demo.jxrisesun.com:3308
 Source Schema         : healthsupervision_pz

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 24/04/2020 15:36:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for video_appinfo
-- ----------------------------
DROP TABLE IF EXISTS `video_appinfo`;
CREATE TABLE `video_appinfo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `appName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用名称',
  `appKey` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用KEY',
  `appSecret` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '应用密钥',
  `accessToken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '认证凭据',
  `tokenExpire` datetime(0) NULL DEFAULT NULL COMMENT '认证有效期',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `status` int(11) NULL DEFAULT 1 COMMENT '状态(1启用0停用)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '视频-监控应用信息' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
