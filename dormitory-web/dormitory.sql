/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : dormitory

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2022-07-18 13:09:59
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for dormitory
-- ----------------------------
DROP TABLE IF EXISTS `dormitory`;
CREATE TABLE `dormitory` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(50) NOT NULL COMMENT '宿舍编号',
  `dormitory_building_id` bigint(20) NOT NULL COMMENT '外键,宿舍所在楼id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dormitory
-- ----------------------------
INSERT INTO `dormitory` VALUES ('1', '11', '3');
INSERT INTO `dormitory` VALUES ('2', '12', '3');
INSERT INTO `dormitory` VALUES ('3', '13', '3');
INSERT INTO `dormitory` VALUES ('4', '14', '3');
INSERT INTO `dormitory` VALUES ('5', '14', '2');

-- ----------------------------
-- Table structure for dormitory_building
-- ----------------------------
DROP TABLE IF EXISTS `dormitory_building`;
CREATE TABLE `dormitory_building` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(50) NOT NULL COMMENT '宿舍楼编号',
  `completed_date` date NOT NULL COMMENT '宿舍楼建成时间',
  `type` char(1) NOT NULL COMMENT '宿舍楼类型:M:男生宿舍,F:女生宿舍',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of dormitory_building
-- ----------------------------
INSERT INTO `dormitory_building` VALUES ('1', '1', '2022-07-16', 'M');
INSERT INTO `dormitory_building` VALUES ('2', '2', '2022-07-07', 'M');
INSERT INTO `dormitory_building` VALUES ('3', '3', '2022-07-14', 'F');

-- ----------------------------
-- Table structure for manager
-- ----------------------------
DROP TABLE IF EXISTS `manager`;
CREATE TABLE `manager` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(50) NOT NULL COMMENT '登录用户名',
  `pwd` varchar(50) NOT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';

-- ----------------------------
-- Records of manager
-- ----------------------------
INSERT INTO `manager` VALUES ('1', 'admin', 'admin');

-- ----------------------------
-- Table structure for student
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `no` varchar(50) NOT NULL COMMENT '学号',
  `name` varchar(50) NOT NULL COMMENT '学生姓名',
  `gender` char(1) NOT NULL COMMENT '性别:M:男,F:女',
  `dept` varchar(100) NOT NULL COMMENT '院系',
  `grade` varchar(100) NOT NULL COMMENT '班级',
  `dormitory_id` bigint(20) NOT NULL COMMENT '外键,宿舍id',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_no` (`no`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('1', '1001', '冰墩墩', 'M', 'jisuanji', '1', '5');
INSERT INTO `student` VALUES ('2', '1003', 'wangmeili', 'F', '化学系', '111', '4');
INSERT INTO `student` VALUES ('3', '1002', '王大锤', 'M', '化学系', '111', '5');
