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

 Date: 24/04/2020 15:37:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for video_deviceinfo
-- ----------------------------
DROP TABLE IF EXISTS `video_deviceinfo`;
CREATE TABLE `video_deviceinfo`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `deviceSerial` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '设备序列号,英文字母需为大写',
  `deviceName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备名称',
  `deviceType` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备类型',
  `deviceVersion` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备版本号',
  `validateCode` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '设备验证码,六位大写字母',
  `isEncrypt` int(11) NULL DEFAULT 0 COMMENT '是否加密',
  `encryptPassword` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密密码',
  `channelNo` int(11) NULL DEFAULT 1 COMMENT '通道号',
  `channelName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '通道名称',
  `createStatus` int(255) NULL DEFAULT 0 COMMENT '添加状态(0未添加1已添加2添加失败)',
  `createTime` datetime(0) NULL DEFAULT NULL COMMENT '添加时间',
  `createMsg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '添加状态消息',
  `updateStatus` int(11) NULL DEFAULT 0 COMMENT '更新状态(0未更新1已更新)',
  `updateTime` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `updateMsg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新消息',
  `onlineStatus` int(11) NULL DEFAULT 0 COMMENT '在线状态(0-不在线1在线)',
  `cloudStatus` int(11) NULL DEFAULT NULL COMMENT '云存储状态: -2:设备不支持;-1: 未开通;0: 未激活;1: 激活;2: 过期',
  `appId` bigint(20) NULL DEFAULT 1 COMMENT '应用ID',
  `orgId` bigint(20) NULL DEFAULT NULL COMMENT '机构ID',
  `orgName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '机构名称',
  `videoName` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频名称',
  `viedoType` int(11) NULL DEFAULT NULL COMMENT '视频分类(1饮用水质监控视频，2游泳场所监控，3医疗废弃物监控 4监督平台)',
  `videoUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '视频播放地址',
  `bdLon` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '百度坐标经度',
  `bdLat` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '百度坐标纬度',
  `picUrl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '封面图片',
  `status` int(11) NOT NULL DEFAULT 1 COMMENT '状态(1启用0停用)',
  `isRecommend` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '是否推荐该设备播放（0不推荐 1推荐）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '视频-监控设备表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
