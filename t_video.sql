/*
Navicat MySQL Data Transfer

Source Server         : local
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : cms

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-04-12 17:31:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_video`
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `pv_id` int(11) NOT NULL AUTO_INCREMENT,
  `pv_auditor` varchar(255) DEFAULT NULL,
  `pv_author` varchar(255) DEFAULT NULL,
  `pv_cname` varchar(255) DEFAULT NULL,
  `pv_create_date` datetime DEFAULT NULL,
  `pv_name` varchar(255) DEFAULT NULL,
  `pv_publish_date` datetime DEFAULT NULL,
  `pv_size` bigint(20) DEFAULT NULL,
  `pv_status` int(11) DEFAULT NULL,
  `pv_title` varchar(255) DEFAULT NULL,
  `pv_view_count` int(11) DEFAULT NULL,
  `pv_channel` int(11) DEFAULT NULL,
  `pv_user` int(11) DEFAULT NULL,
  `pv_picName` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`pv_id`),
  KEY `FK_d4wh0jw89phc6rh33afjdd14w` (`pv_channel`),
  KEY `FK_5t5svnp9u4qkma8odm2wyy9vg` (`pv_user`),
  CONSTRAINT `FK_5t5svnp9u4qkma8odm2wyy9vg` FOREIGN KEY (`pv_user`) REFERENCES `t_user` (`id`),
  CONSTRAINT `FK_d4wh0jw89phc6rh33afjdd14w` FOREIGN KEY (`pv_channel`) REFERENCES `t_channel` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_video
-- ----------------------------
INSERT INTO `t_video` VALUES ('17', '超级管理员', 'wxh', '城院视频', '2016-02-25 23:49:20', '1460356105932.mp4', '2016-02-25 23:51:21', '111062', '1', '测试1测试1测试1测试1测试1', '10', '60', null, '1460356105940.jpg');
INSERT INTO `t_video` VALUES ('18', '超级管理员', 'wxh2', '城院视频', '2016-02-25 23:49:34', '1460356154931.mp4', '2016-02-25 23:49:34', '111062', '1', '测试2', '0', '60', null, '1460356154932.jpg');
INSERT INTO `t_video` VALUES ('19', '超级管理员', '测试1', '城院视频', '2016-02-25 23:49:52', '1460356162893.mp4', '2016-02-25 23:49:52', '111062', '1', '测试3', '1', '60', null, '1460356162896.jpg');
INSERT INTO `t_video` VALUES ('20', '超级管理员', 'wxuaiohao', '城院视频', '2016-02-25 23:50:05', '1460356226291.mp4', '2016-02-25 23:50:05', '111062', '1', 'wuxiahao', '10', '60', null, '1460356226292.jpg');
INSERT INTO `t_video` VALUES ('21', '超级管理员', '测试1', '城院视频', '2016-02-25 23:50:21', '1460356174600.mp4', '2016-02-25 23:50:21', '111062', '1', '尼玛好烦啊啊啊啊', '5', '60', null, '1460356174603.jpg');
INSERT INTO `t_video` VALUES ('22', '超级管理员', '测试1', '城院视频', '2016-02-25 23:50:45', '1460356143986.mp4', '2016-02-25 23:50:45', '111062', '1', '操谁心', '3', '60', null, '1460356143988.jpg');
INSERT INTO `t_video` VALUES ('23', '超级管理员', '吴晓豪', '城院视频', '2016-02-25 23:51:02', '1460356126075.mp4', '2016-02-25 23:51:02', '111062', '1', '尼玛', '5', '60', null, '1460356126077.jpg');
INSERT INTO `t_video` VALUES ('24', '超级管理员', '测试1', '城院视频', '2016-02-25 23:51:15', '1460356117781.mp4', '2016-02-25 23:51:15', '111062', '1', '大帅锅', '35', '60', null, '1460356117783.jpg');
INSERT INTO `t_video` VALUES ('25', null, '纷纷', '城院视频', '2016-02-25 23:51:35', '1456415493200.avi', null, '3974346', '0', '不妨放', '0', '60', '3', '1456415493211.jpg');
