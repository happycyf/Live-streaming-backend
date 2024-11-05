/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80032
 Source Host           : localhost:3306
 Source Schema         : newzaomengfactory

 Target Server Type    : MySQL
 Target Server Version : 80032
 File Encoding         : 65001

 Date: 03/11/2024 20:22:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for gift
-- ----------------------------
DROP TABLE IF EXISTS `gift`;
CREATE TABLE `gift`  (
  `giftId` int NOT NULL AUTO_INCREMENT COMMENT '礼物编号',
  `giftName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL,
  `giftMoney` double NULL DEFAULT NULL COMMENT '礼物金额',
  PRIMARY KEY (`giftId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of gift
-- ----------------------------
INSERT INTO `gift` VALUES (1, '棒棒糖', 1);
INSERT INTO `gift` VALUES (2, '玫瑰花', 5);
INSERT INTO `gift` VALUES (3, '跑车', 100);
INSERT INTO `gift` VALUES (4, '直升机', 1000);
INSERT INTO `gift` VALUES (5, '礼物盒', 10);

-- ----------------------------
-- Table structure for giftrecord
-- ----------------------------
DROP TABLE IF EXISTS `giftrecord`;
CREATE TABLE `giftrecord`  (
  `giftId` int NOT NULL COMMENT '礼物编号',
  `sendId` int NULL DEFAULT NULL COMMENT '发送用户编号',
  `reId` int NULL DEFAULT NULL COMMENT '接收用户编号',
  `sendTime` datetime NULL DEFAULT NULL COMMENT '送出时间',
  INDEX `sendId`(`sendId` ASC) USING BTREE,
  INDEX `reId`(`reId` ASC) USING BTREE,
  INDEX `giftId`(`giftId` ASC) USING BTREE,
  CONSTRAINT `giftrecord_ibfk_1` FOREIGN KEY (`sendId`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `giftrecord_ibfk_2` FOREIGN KEY (`reId`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `giftrecord_ibfk_3` FOREIGN KEY (`giftId`) REFERENCES `gift` (`giftId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of giftrecord
-- ----------------------------
INSERT INTO `giftrecord` VALUES (1, 2, 3, '2024-10-01 10:43:35');
INSERT INTO `giftrecord` VALUES (2, 2, 4, '2024-10-09 10:43:46');
INSERT INTO `giftrecord` VALUES (2, 4, 2, '2024-10-03 10:44:00');
INSERT INTO `giftrecord` VALUES (3, 1, 2, '2024-10-22 10:44:13');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:41:40');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:43:24');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:43:44');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:45:30');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:45:40');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:45:57');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:55:34');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-23 22:57:50');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 00:52:25');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 04:20:00');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 04:21:07');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 04:21:25');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 04:21:59');
INSERT INTO `giftrecord` VALUES (1, 1, 6, '2024-10-24 04:26:08');
INSERT INTO `giftrecord` VALUES (1, 1, 6, '2024-10-24 04:26:20');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 04:32:13');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 04:33:12');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 04:34:25');
INSERT INTO `giftrecord` VALUES (3, 1, 6, '2024-10-24 04:34:29');
INSERT INTO `giftrecord` VALUES (1, 1, 6, '2024-10-24 04:35:53');
INSERT INTO `giftrecord` VALUES (1, 1, 6, '2024-10-24 04:50:39');
INSERT INTO `giftrecord` VALUES (3, 1, 6, '2024-10-24 04:50:58');
INSERT INTO `giftrecord` VALUES (5, 1, 6, '2024-10-24 05:04:48');
INSERT INTO `giftrecord` VALUES (1, 8, 6, '2024-10-24 06:55:09');
INSERT INTO `giftrecord` VALUES (5, 8, 6, '2024-10-24 06:58:56');
INSERT INTO `giftrecord` VALUES (2, 8, 6, '2024-10-24 07:00:27');
INSERT INTO `giftrecord` VALUES (1, 9, 6, '2024-10-24 14:18:27');

-- ----------------------------
-- Table structure for history
-- ----------------------------
DROP TABLE IF EXISTS `history`;
CREATE TABLE `history`  (
  `hisId` int NOT NULL COMMENT '观看记录编号',
  `uid` int NULL DEFAULT NULL COMMENT '直播编号',
  `liveId` int NULL DEFAULT NULL COMMENT '直播编号',
  `seeTime` datetime NULL DEFAULT NULL COMMENT '观看时间',
  PRIMARY KEY (`hisId`) USING BTREE,
  INDEX `liveId`(`liveId` ASC) USING BTREE,
  CONSTRAINT `history_ibfk_1` FOREIGN KEY (`liveId`) REFERENCES `liverecord` (`liveId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of history
-- ----------------------------
INSERT INTO `history` VALUES (1, 1, NULL, NULL);
INSERT INTO `history` VALUES (2, 2, NULL, NULL);
INSERT INTO `history` VALUES (3, 3, NULL, NULL);

-- ----------------------------
-- Table structure for liverecord
-- ----------------------------
DROP TABLE IF EXISTS `liverecord`;
CREATE TABLE `liverecord`  (
  `liveId` int NOT NULL AUTO_INCREMENT COMMENT '直播编号',
  `uId` int NULL DEFAULT NULL COMMENT '开播主播编号',
  `liveTitle` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '直播标题',
  `startTime` datetime NULL DEFAULT NULL COMMENT '开播时间',
  `overTime` datetime NULL DEFAULT NULL COMMENT '下播时间',
  `seeNumber` int NULL DEFAULT NULL COMMENT '观看人数',
  PRIMARY KEY (`liveId`) USING BTREE,
  INDEX `uId`(`uId` ASC) USING BTREE,
  CONSTRAINT `liverecord_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of liverecord
-- ----------------------------
INSERT INTO `liverecord` VALUES (1, 2, '不上班上班上班', '2024-10-21 10:49:53', '2024-10-21 14:50:00', 3);
INSERT INTO `liverecord` VALUES (2, 1, '数学数学数学数学', '2024-10-22 08:50:31', '2024-10-22 10:50:40', 342);
INSERT INTO `liverecord` VALUES (3, 6, '121', '2024-10-23 22:19:04', '2024-10-23 22:19:11', 1);
INSERT INTO `liverecord` VALUES (4, 6, '3342', '2024-10-23 22:23:35', '2024-10-23 22:24:11', 2);
INSERT INTO `liverecord` VALUES (5, 6, '123', '2024-10-23 23:59:00', '2024-10-23 23:59:06', 1);
INSERT INTO `liverecord` VALUES (6, 6, '2525', '2024-10-24 00:38:54', '2024-10-24 00:38:59', 1);
INSERT INTO `liverecord` VALUES (7, 6, '121', '2024-10-24 01:07:07', '2024-10-24 01:07:29', 1);
INSERT INTO `liverecord` VALUES (8, 6, '2121', '2024-10-24 02:00:09', '2024-10-24 02:00:31', 2);
INSERT INTO `liverecord` VALUES (9, 6, '12121', '2024-10-24 03:07:51', '2024-10-24 03:07:59', 1);
INSERT INTO `liverecord` VALUES (10, 6, '1212', '2024-10-24 03:54:37', '2024-10-24 04:03:54', 1);
INSERT INTO `liverecord` VALUES (11, 6, '1212', '2024-10-24 04:04:02', '2024-10-24 04:12:58', 1);
INSERT INTO `liverecord` VALUES (12, 6, '1212', '2024-10-24 04:14:52', '2024-10-24 04:40:42', 3);
INSERT INTO `liverecord` VALUES (13, 6, '54565', '2024-10-24 13:31:39', '2024-10-24 13:35:15', 1);
INSERT INTO `liverecord` VALUES (14, 6, '54565', '2024-10-24 13:31:39', '2024-10-24 13:35:17', 1);

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order`  (
  `orderId` int NOT NULL AUTO_INCREMENT COMMENT '订单编号',
  `uId` int NULL DEFAULT NULL COMMENT '用户编号',
  `money` double NULL DEFAULT NULL COMMENT '订单金额',
  `orderTime` datetime NULL DEFAULT NULL COMMENT '订单时间',
  PRIMARY KEY (`orderId`) USING BTREE,
  INDEX `uId`(`uId` ASC) USING BTREE,
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`uId`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES (1, 1, 300, '2024-10-22 10:51:04');
INSERT INTO `order` VALUES (2, 3, 500, '2024-10-21 10:51:25');
INSERT INTO `order` VALUES (3, 2, 200, '2024-10-16 10:51:44');
INSERT INTO `order` VALUES (4, 1, 10, '2024-10-24 01:56:00');
INSERT INTO `order` VALUES (5, 8, 999, '2024-10-24 06:44:59');
INSERT INTO `order` VALUES (6, 9, 10, '2024-10-24 14:17:15');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `uId` int NOT NULL AUTO_INCREMENT COMMENT '用户编号',
  `uName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '用户名称',
  `account` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '账号',
  `pwd` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '密码',
  `img` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '头像',
  `attention` int NULL DEFAULT NULL COMMENT '关注数',
  `fan` int NULL DEFAULT NULL COMMENT '粉丝数',
  `blance` double NULL DEFAULT NULL COMMENT '余额',
  `phone` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NOT NULL COMMENT '手机号',
  `sex` char(2) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '性别',
  `adress` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '地址',
  `uTypeId` int NULL DEFAULT NULL COMMENT '用户类型(比如说管理员和普通用户)',
  `tKey` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '主播推流的key',
  PRIMARY KEY (`uId`) USING BTREE,
  UNIQUE INDEX `unique_account`(`account` ASC) USING BTREE,
  UNIQUE INDEX `phone`(`phone` ASC) USING BTREE,
  INDEX `uTypeId`(`uTypeId` ASC) USING BTREE,
  INDEX `account`(`account` ASC) USING BTREE,
  INDEX `attention`(`attention` ASC) USING BTREE,
  CONSTRAINT `user_ibfk_1` FOREIGN KEY (`uTypeId`) REFERENCES `usertype` (`uTypeId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '豆子', '123456', '123456', '/陆小果.jpg', 8, 31, 345, '19576602361', '男', '22', 2, 'lv');
INSERT INTO `user` VALUES (2, '哈哈', '456797', '1486315', '/陆小果.jpg', 543, 234, 5333, '18246375948', '女', '湖南永州', 2, 'hh');
INSERT INTO `user` VALUES (3, '嘻嘻', '791156', '153599', 'touxiang7.png', 54, 34, 7645, '11223344556', '男', '湖南长沙', 2, 'ee');
INSERT INTO `user` VALUES (4, '杀手', '6443163', '4651355', 'touxiang3.png', 432, 53, 523, '23423423432', '女', '湖南湘潭', 1, 'sd');
INSERT INTO `user` VALUES (5, '苏打', '24435313', '645133', 'touxiang9.png', 43, 423, 532, '42342342343', '男', '湖南株洲', 3, 'uf');
INSERT INTO `user` VALUES (6, 'test', 'tesst', '123456', '7d1b06ca-c449-443f-bcaf-a34ed7c4b4e6.png', 0, 30, 291, '123456', '男', '华南F4', 3, '64f1e64c-8ba9-4431-8ddf-e0cb1609d863');
INSERT INTO `user` VALUES (7, '梦16656', '12345', '12345', '/陆小果.jpg', 0, 0, 0, '123132141', '男', NULL, 3, NULL);
INSERT INTO `user` VALUES (8, 'doau', 'test2', '456789', '/陆小果.jpg', -1, 0, 983, '5316131', '女', '221', 2, NULL);
INSERT INTO `user` VALUES (9, '大家哦', '456789', '123456', '/陆小果.jpg', -1, 0, 9, '13207355962', '男', '156', 2, NULL);

-- ----------------------------
-- Table structure for userattention
-- ----------------------------
DROP TABLE IF EXISTS `userattention`;
CREATE TABLE `userattention`  (
  `userUid` int NOT NULL COMMENT '用户账号',
  `FollowUid` int NOT NULL COMMENT '被关注账号',
  `monetary` decimal(10, 2) NOT NULL COMMENT '消费金额',
  INDEX `userattention_ibfk_1`(`userUid` ASC) USING BTREE,
  INDEX `userattention_ibfk_2`(`FollowUid` ASC) USING BTREE,
  CONSTRAINT `userattention_ibfk_1` FOREIGN KEY (`userUid`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userattention_ibfk_2` FOREIGN KEY (`FollowUid`) REFERENCES `user` (`uId`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB CHARACTER SET = utf8mb3 COLLATE = utf8mb3_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userattention
-- ----------------------------
INSERT INTO `userattention` VALUES (3, 6, 100.00);
INSERT INTO `userattention` VALUES (2, 6, 20.00);
INSERT INTO `userattention` VALUES (1, 6, 374.00);

-- ----------------------------
-- Table structure for usertype
-- ----------------------------
DROP TABLE IF EXISTS `usertype`;
CREATE TABLE `usertype`  (
  `uTypeId` int NOT NULL AUTO_INCREMENT COMMENT '用户类型',
  `typeName` varchar(255) CHARACTER SET utf8mb3 COLLATE utf8mb3_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  PRIMARY KEY (`uTypeId`) USING BTREE,
  INDEX `uTypeId`(`uTypeId` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of usertype
-- ----------------------------
INSERT INTO `usertype` VALUES (1, '管理员');
INSERT INTO `usertype` VALUES (2, '用户');
INSERT INTO `usertype` VALUES (3, '超管');

-- ----------------------------
-- Table structure for withdrawal
-- ----------------------------
DROP TABLE IF EXISTS `withdrawal`;
CREATE TABLE `withdrawal`  (
  `Id` int NOT NULL AUTO_INCREMENT COMMENT '提现编号',
  `data` datetime NULL DEFAULT NULL COMMENT '提现时间',
  `money` decimal(10, 2) NULL DEFAULT NULL COMMENT '提现金额',
  `uid` int NULL DEFAULT NULL COMMENT '提现账号id',
  `state` int(1) UNSIGNED ZEROFILL NULL DEFAULT NULL COMMENT '提现状态 0在申请 1成功 2驳回',
  `payAccount` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL COMMENT '支付宝账号',
  PRIMARY KEY (`Id`) USING BTREE,
  INDEX `uid`(`uid` ASC) USING BTREE,
  CONSTRAINT `withdrawal_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `user` (`uId`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of withdrawal
-- ----------------------------
INSERT INTO `withdrawal` VALUES (1, '2024-10-16 10:55:03', 500.00, 2, 1, '234123');
INSERT INTO `withdrawal` VALUES (2, '2024-10-22 10:57:08', 400.00, 2, 1, '675872');
INSERT INTO `withdrawal` VALUES (3, '2024-10-24 05:37:20', 10.00, 6, 1, '123456');
INSERT INTO `withdrawal` VALUES (4, '2024-10-24 14:10:06', 10.00, 6, 1, '123456');

-- ----------------------------
-- View structure for acceptgift
-- ----------------------------
DROP VIEW IF EXISTS `acceptgift`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `acceptgift` AS select (select `gf`.`giftMoney` from `gift` `gf` where (`gf`.`giftId` = `gc`.`giftId`)) AS `giftMoney`,`gc`.`sendId` AS `sendId`,(select `us`.`account` from `user` `us` where (`us`.`uId` = `gc`.`reId`)) AS `account`,`gc`.`sendTime` AS `sendTime` from `giftrecord` `gc`;

-- ----------------------------
-- View structure for userattentionview
-- ----------------------------
DROP VIEW IF EXISTS `userattentionview`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `userattentionview` AS select (select `us`.`uName` from `user` `us` where (`us`.`uId` = `ua`.`userUid`)) AS `uName`,`ua`.`FollowUid` AS `FollowUid`,`ua`.`monetary` AS `monetary` from `userattention` `ua`;

SET FOREIGN_KEY_CHECKS = 1;
