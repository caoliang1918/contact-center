/*
 Source Server         : tencent
 Source Server Type    : MySQL
 Source Server Version : 80025
 Source Host           : 127.0.0.1:3306
 Source Schema         : voice9

 Target Server Type    : MySQL
 Target Server Version : 80025
 File Encoding         : 65001
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for QRTZ_BLOB_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_BLOB_TRIGGERS`;
CREATE TABLE `QRTZ_BLOB_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `BLOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_BLOB_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CALENDARS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CALENDARS`;
CREATE TABLE `QRTZ_CALENDARS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `CALENDAR_NAME` varchar(200) NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`CALENDAR_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_CALENDARS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_CRON_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_CRON_TRIGGERS`;
CREATE TABLE `QRTZ_CRON_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `CRON_EXPRESSION` varchar(120) NOT NULL,
  `TIME_ZONE_ID` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', '0 5 0 * * ? *', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', '0 0 0 1 * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', '0/2 * * * * ?', 'Asia/Shanghai');
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_FIRED_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_FIRED_TRIGGERS`;
CREATE TABLE `QRTZ_FIRED_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `ENTRY_ID` varchar(95) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `FIRED_TIME` bigint NOT NULL,
  `SCHED_TIME` bigint NOT NULL,
  `PRIORITY` int NOT NULL,
  `STATE` varchar(16) NOT NULL,
  `JOB_NAME` varchar(200) DEFAULT NULL,
  `JOB_GROUP` varchar(200) DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`ENTRY_ID`),
  KEY `IDX_QRTZ_FT_TRIG_INST_NAME` (`SCHED_NAME`,`INSTANCE_NAME`),
  KEY `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY` (`SCHED_NAME`,`INSTANCE_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_FT_J_G` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_FT_T_G` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_FT_TG` (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_FIRED_TRIGGERS` (`SCHED_NAME`, `ENTRY_ID`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `INSTANCE_NAME`, `FIRED_TIME`, `SCHED_TIME`, `PRIORITY`, `STATE`, `JOB_NAME`, `JOB_GROUP`, `IS_NONCONCURRENT`, `REQUESTS_RECOVERY`) VALUES ('cc-quartz', 'VM-0-2-centos16527680204271652768024562', 'TaskJobOfSecond', 'cc.quartz', 'VM-0-2-centos1652768020427', 1652776350041, 1652776352000, 5, 'ACQUIRED', NULL, NULL, '0', '0');
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_JOB_DETAILS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_JOB_DETAILS`;
CREATE TABLE `QRTZ_JOB_DETAILS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) NOT NULL,
  `IS_DURABLE` varchar(1) NOT NULL,
  `IS_NONCONCURRENT` varchar(1) NOT NULL,
  `IS_UPDATE_DATA` varchar(1) NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) NOT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_J_REQ_RECOVERY` (`SCHED_NAME`,`REQUESTS_RECOVERY`),
  KEY `IDX_QRTZ_J_GRP` (`SCHED_NAME`,`JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay 任务', 'com.voice9.api.quartz.TaskJobOfDay', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 'com.voice9.api.quartz.TaskJobOfHour', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth 任务', 'com.voice9.api.quartz.TaskJobOfMonth', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 'com.voice9.api.quartz.TaskJobOfSecond', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('cc-quartz', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('cc-quartz', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SCHEDULER_STATE
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SCHEDULER_STATE`;
CREATE TABLE `QRTZ_SCHEDULER_STATE` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `INSTANCE_NAME` varchar(200) NOT NULL,
  `LAST_CHECKIN_TIME` bigint NOT NULL,
  `CHECKIN_INTERVAL` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`INSTANCE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_SCHEDULER_STATE` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES ('cc-quartz', 'VM-0-2-centos1652768020427', 1652776355074, 10000);
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPLE_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPLE_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `REPEAT_COUNT` bigint NOT NULL,
  `REPEAT_INTERVAL` bigint NOT NULL,
  `TIMES_TRIGGERED` bigint NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_SIMPLE_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_SIMPROP_TRIGGERS`;
CREATE TABLE `QRTZ_SIMPROP_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `STR_PROP_1` varchar(512) DEFAULT NULL,
  `STR_PROP_2` varchar(512) DEFAULT NULL,
  `STR_PROP_3` varchar(512) DEFAULT NULL,
  `INT_PROP_1` int DEFAULT NULL,
  `INT_PROP_2` int DEFAULT NULL,
  `LONG_PROP_1` bigint DEFAULT NULL,
  `LONG_PROP_2` bigint DEFAULT NULL,
  `DEC_PROP_1` decimal(13,4) DEFAULT NULL,
  `DEC_PROP_2` decimal(13,4) DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_SIMPROP_TRIGGERS
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_TRIGGERS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_TRIGGERS`;
CREATE TABLE `QRTZ_TRIGGERS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_NAME` varchar(200) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  `JOB_NAME` varchar(200) NOT NULL,
  `JOB_GROUP` varchar(200) NOT NULL,
  `DESCRIPTION` varchar(250) DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint DEFAULT NULL,
  `PREV_FIRE_TIME` bigint DEFAULT NULL,
  `PRIORITY` int DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) NOT NULL,
  `TRIGGER_TYPE` varchar(8) NOT NULL,
  `START_TIME` bigint NOT NULL,
  `END_TIME` bigint DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) DEFAULT NULL,
  `MISFIRE_INSTR` smallint DEFAULT NULL,
  `JOB_DATA` blob,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_J` (`SCHED_NAME`,`JOB_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_JG` (`SCHED_NAME`,`JOB_GROUP`),
  KEY `IDX_QRTZ_T_C` (`SCHED_NAME`,`CALENDAR_NAME`),
  KEY `IDX_QRTZ_T_G` (`SCHED_NAME`,`TRIGGER_GROUP`),
  KEY `IDX_QRTZ_T_STATE` (`SCHED_NAME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_STATE` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_N_G_STATE` (`SCHED_NAME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NEXT_FIRE_TIME` (`SCHED_NAME`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST` (`SCHED_NAME`,`TRIGGER_STATE`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_STATE`),
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay 任务', 1652803500000, 1652717100000, 5, 'WAITING', 'CRON', 1650427288000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 1652778000000, 1652774400000, 5, 'WAITING', 'CRON', 1650427288000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth 任务', 1654012800000, 1651334400000, 5, 'WAITING', 'CRON', 1650427289000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 1652776364000, 1652776362000, 5, 'ACQUIRED', 'CRON', 1650427288000, 0, NULL, 0, '');
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_menu
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_menu`;
CREATE TABLE `cc_admin_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `menu_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单id',
  `parent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '父id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '菜单名称',
  `path_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '访问url',
  `path_method` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '访问方法',
  `menu_level` int NOT NULL DEFAULT '1' COMMENT '菜单等级',
  `menu_order` int NOT NULL DEFAULT '0' COMMENT '排序',
  `child_num` int NOT NULL DEFAULT '0' COMMENT '子节点数',
  `icon` varchar(255) NOT NULL COMMENT '图标',
  `create_default` int NOT NULL DEFAULT '0' COMMENT '创建时添加权限(1:是,0:否)',
  `init_default` int NOT NULL DEFAULT '0' COMMENT '已有账号授权(1:是,0:否)',
  `front_site` int NOT NULL DEFAULT '1' COMMENT '前端',
  `front_icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '图标',
  `end_site` int NOT NULL DEFAULT '1' COMMENT '后端',
  `status` int NOT NULL DEFAULT '1' COMMENT '数据状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_menus_name` (`name`),
  UNIQUE KEY `uniq_menu_id` (`menu_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cc_admin_menu
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_menu` (`id`, `cts`, `uts`, `menu_id`, `parent_id`, `name`, `path_url`, `path_method`, `menu_level`, `menu_order`, `child_num`, `icon`, `create_default`, `init_default`, `front_site`, `front_icon`, `end_site`, `status`) VALUES (7, 0, 0, '37368859509759165676225026973854', '', '一级测试菜单', '', 'POST', 1, 2, 0, 'icon', 0, 0, 1, '', 1, 1);
INSERT INTO `cc_admin_menu` (`id`, `cts`, `uts`, `menu_id`, `parent_id`, `name`, `path_url`, `path_method`, `menu_level`, `menu_order`, `child_num`, `icon`, `create_default`, `init_default`, `front_site`, `front_icon`, `end_site`, `status`) VALUES (8, 0, 0, '13096485404219958249769148186279', '', '一级测试菜单1', '', '', 1, 1, 0, 'icon', 0, 0, 1, '', 1, 1);
INSERT INTO `cc_admin_menu` (`id`, `cts`, `uts`, `menu_id`, `parent_id`, `name`, `path_url`, `path_method`, `menu_level`, `menu_order`, `child_num`, `icon`, `create_default`, `init_default`, `front_site`, `front_icon`, `end_site`, `status`) VALUES (9, 0, 0, '35220572389648550887723993371065', '13096485404219958249769148186279', '二级测试菜单1', '', '', 2, 1, 0, 'icon', 0, 0, 1, '', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_role`;
CREATE TABLE `cc_admin_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '所属企业',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of cc_admin_role
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_role` (`id`, `cts`, `uts`, `company_id`, `role_name`, `status`) VALUES (1, 1652286627, 1652286627, 1, '', 1);
INSERT INTO `cc_admin_role` (`id`, `cts`, `uts`, `company_id`, `role_name`, `status`) VALUES (6, 1652759826, 1652759826, 0, '超级管理员角色', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_role_menu`;
CREATE TABLE `cc_admin_role_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '权限ID',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_role_perm` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限角色表';

-- ----------------------------
-- Records of cc_admin_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (1, 1, 1, 1, '1', 1);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (2, 1, 1, 1, '2', 1);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (3, 1, 1, 1, '3', 1);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (9, 1652763330, 1652763330, 6, '13096485404219958249769148186279', 0);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (10, 1652763330, 1652763330, 6, '37368859509759165676225026973854', 0);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (11, 1652763330, 1652763330, 6, '35220572389648550887723993371065', 0);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (12, 1652763330, 1652763330, 6, '6', 0);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (13, 1652763330, 1652763330, 6, '7', 0);
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_user
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_user`;
CREATE TABLE `cc_admin_user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '密码',
  `avatar` varchar(255) NOT NULL DEFAULT '',
  `user_type` int NOT NULL DEFAULT '1' COMMENT '类型',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业管理员表';

-- ----------------------------
-- Records of cc_admin_user
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_user` (`id`, `cts`, `uts`, `company_id`, `username`, `passwd`, `avatar`, `user_type`, `status`) VALUES (1, 1, 1, 1, 'admin', '$2a$04$FirdLmDLwGWC6/.WnsFICeH1CR6sHib95jKbxqpzG6f0cVFc1.dhC', '', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_user_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_user_role`;
CREATE TABLE `cc_admin_user_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `uid` bigint NOT NULL DEFAULT '0' COMMENT '账号ID',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色ID',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_acmount_id` (`uid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号角色表';

-- ----------------------------
-- Records of cc_admin_user_role
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_user_role` (`id`, `cts`, `uts`, `uid`, `role_id`, `company_id`) VALUES (1, 1, 1, 1, 1, 0);
COMMIT;

-- ----------------------------
-- Table structure for cc_agent
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent`;
CREATE TABLE `cc_agent` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `agent_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席工号',
  `agent_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席账户',
  `agent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `agent_code` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席分机号',
  `agent_type` int NOT NULL DEFAULT '2' COMMENT '座席类型：1:普通座席；2：班长',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '座席密码',
  `sip_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '绑定的电话号码',
  `record` int NOT NULL DEFAULT '0' COMMENT '是否录音 0 no 1 yes',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '座席主要技能组  不能为空 必填项',
  `after_interval` int NOT NULL DEFAULT '5' COMMENT '话后自动空闲间隔时长',
  `display` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '主叫显号',
  `ring_time` int NOT NULL DEFAULT '10' COMMENT '振铃时长',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登录服务器地址',
  `ext1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展3',
  `state` int NOT NULL DEFAULT '0' COMMENT '坐席状态(1:在线,0:不在线)',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态：1 开通，0关闭',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_agent_key` (`agent_key`,`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb3 COMMENT='座席工号表';

-- ----------------------------
-- Records of cc_agent
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `display`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (1, 1604503580, 1604503580, 1, '1001', '1001@test', '测试坐席', '1001', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188899998889', 0, 25, 5, '', 10, ' ', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `display`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (2, 1604560158, 1604560158, 1, '1002', '1002@test', '测试2号', '1002', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188999988887', 0, 25, 5, '', 10, ' ', '', '', '', 0, 1);

-- ----------------------------
-- Table structure for cc_agent_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_group`;
CREATE TABLE `cc_agent_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `agent_id` bigint NOT NULL DEFAULT '0' COMMENT '坐席id',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `agent_type` int NOT NULL DEFAULT '1',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组id',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_agent_group_agent_group` (`group_id`,`agent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席技能组表';

-- ----------------------------
-- Records of cc_agent_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (1, 1604560158, 1604560158, 1, 1, '1001@test', 1, 25, 1);
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (2, 1604560158, 1604560158, 1, 2, '1002@test', 1, 25, 1);
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (3, 1, 1, 1, 3, 'B001@test', 1, 25, 1);
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (4, 1, 1, 1, 4, 'B002@test', 1, 25, 1);
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (5, 0, 0, 0, 0, '', 1, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_agent_sip
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_sip`;
CREATE TABLE `cc_agent_sip` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cts` bigint NOT NULL DEFAULT '0',
  `uts` bigint NOT NULL DEFAULT '0',
  `company_id` bigint DEFAULT '0',
  `sip` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `agent_id` bigint NOT NULL DEFAULT '0',
  `sip_pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '',
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_agentsip_agent` (`agent_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sip表';

-- ----------------------------
-- Records of cc_agent_sip
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (1, 1622024375, 1622033134, 1, '870001', 1, '123456', 1);
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (2, 1622072271, 0, 1, '870002', 2, '123456', 1);
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (3, 1, 1, 1, '870003', 3, '123456', 1);
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (4, 1, 1, 1, '870004', 4, '123456', 1);
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (13, 1, 1, 1, '870005', 5, '123456', 1);
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (14, 1, 1, 1, '870006', 6, '123456', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_agent_state_log
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_state_log`;
CREATE TABLE `cc_agent_state_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '主技能组id',
  `agent_id` bigint NOT NULL DEFAULT '0' COMMENT '坐席id',
  `agent_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席编号',
  `agent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话唯一标识',
  `login_type` int NOT NULL DEFAULT '1' COMMENT '登录类型',
  `work_type` int NOT NULL DEFAULT '1' COMMENT '工作类型',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '服务站点',
  `remote_address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '远端地址',
  `before_state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '变更之前状态',
  `before_time` bigint NOT NULL DEFAULT '0' COMMENT '更变之前时间',
  `state` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '变更之后状态',
  `state_time` bigint NOT NULL DEFAULT '0' COMMENT '当前时间(秒)',
  `duration` int NOT NULL DEFAULT '0' COMMENT '持续时间(秒)',
  `busy_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '忙碌类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  `ext1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `ext3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_agentstate_agentkey` (`agent_key`) USING BTREE,
  KEY `idx_agentstate_cts` (`state_time`) USING BTREE,
  KEY `idx_agentstate_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=565 DEFAULT CHARSET=utf8mb3 COMMENT='坐席状态历史表';

-- ----------------------------
-- Records of cc_agent_state_log
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (564, 1652769142, 1652769142, 1, 25, 2, '1002@test', '测试2号', 0, 2, 1, '10.41.1.210', '', 'LOGOUT', 0, 'LOGIN', 0, 0, '', 1, '', '', '');
COMMIT;

-- ----------------------------
-- Table structure for cc_call_detail
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_detail`;
CREATE TABLE `cc_call_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `start_time` bigint NOT NULL DEFAULT '0' COMMENT '开始时间',
  `end_time` bigint NOT NULL DEFAULT '0' COMMENT '结束时间',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话ID',
  `detail_index` int NOT NULL DEFAULT '1' COMMENT '顺序',
  `transfer_type` int NOT NULL DEFAULT '0' COMMENT '1:进vdn,2:进ivr,3:技能组,4:按键收号,5:外线,6:机器人,10:服务评价',
  `transfer_id` bigint NOT NULL DEFAULT '0' COMMENT '转接ID',
  `reason` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '出队列原因:排队挂机或者转坐席',
  `ext1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_calldetail_call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=75 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通话流程表';

-- ----------------------------
-- Records of cc_call_detail
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_call_device
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_device`;
CREATE TABLE `cc_call_device` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '租户id',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话ID',
  `device_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '设备id',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `device_type` int NOT NULL DEFAULT '1' COMMENT '1:坐席,2:客户,3:外线',
  `cdr_type` int NOT NULL DEFAULT '1' COMMENT '1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插',
  `from_agent` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '转接或咨询发起者',
  `caller` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫',
  `called` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫',
  `display` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '显号',
  `called_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `caller_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `call_time` bigint NOT NULL DEFAULT '0' COMMENT '呼叫开始时间',
  `ring_start_time` bigint NOT NULL DEFAULT '0' COMMENT '振铃开始时间',
  `ring_end_time` bigint NOT NULL DEFAULT '0' COMMENT '振铃结束时间',
  `answer_time` bigint NOT NULL DEFAULT '0' COMMENT '接通时间',
  `bridge_time` bigint NOT NULL DEFAULT '0' COMMENT '桥接时间',
  `end_time` bigint NOT NULL DEFAULT '0' COMMENT '结束时间',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话时长',
  `record_start_time` bigint NOT NULL DEFAULT '0' COMMENT '录音开始时间',
  `record_time` bigint NOT NULL DEFAULT '0' COMMENT '录音时长',
  `sip_protocol` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '信令协议(tcp/udp)',
  `record` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '录音地址',
  `record2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备用录音地址',
  `record3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备用录音地址',
  `channel_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '呼叫地址',
  `hangup_cause` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '挂机原因',
  `ring_cause` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '回铃音识别',
  `sip_status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sip状态',
  `ext1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_calldetail__call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=617 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单明细表';

-- ----------------------------
-- Records of cc_call_device
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_call_dtmf
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_dtmf`;
CREATE TABLE `cc_call_dtmf` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `dtmf_key` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '按键号码',
  `process_id` bigint NOT NULL DEFAULT '0' COMMENT '业务流程id',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话标识id',
  `dtmf_time` bigint NOT NULL DEFAULT '0' COMMENT '按键时间',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='呼叫按键表';

-- ----------------------------
-- Records of cc_call_dtmf
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_call_log
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_log`;
CREATE TABLE `cc_call_log` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '落单时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '话单id',
  `caller_display` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫显号',
  `caller` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫',
  `called_display` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫显号',
  `called` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫',
  `number_location` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户号码归属地',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `agent_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组',
  `login_type` int NOT NULL DEFAULT '1' COMMENT '1:sip号,2:webrtc,3:手机',
  `task_id` bigint NOT NULL DEFAULT '0' COMMENT '任务ID',
  `ivr_id` bigint NOT NULL DEFAULT '0' COMMENT 'ivr',
  `bot_id` bigint NOT NULL DEFAULT '0' COMMENT '机器人id',
  `call_time` bigint NOT NULL DEFAULT '0' COMMENT '呼叫开始时间',
  `answer_time` bigint NOT NULL DEFAULT '0' COMMENT '接听时间',
  `end_time` bigint NOT NULL DEFAULT '0' COMMENT '结束时间',
  `call_type` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '呼叫类型',
  `direction` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '呼叫方向',
  `answer_flag` int NOT NULL DEFAULT '0' COMMENT '通话标识(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)',
  `wait_time` bigint NOT NULL DEFAULT '0' COMMENT '累计等待时长',
  `answer_count` int NOT NULL DEFAULT '0' COMMENT '应答设备数',
  `hangup_dir` int NOT NULL DEFAULT '1' COMMENT '挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)',
  `sdk_hangup` int NOT NULL DEFAULT '0' COMMENT '是否sdk挂机(1:sdk挂机)',
  `hangup_code` int NOT NULL DEFAULT '0' COMMENT '挂机原因',
  `media_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '媒体服务器',
  `cti_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'cti地址',
  `client_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户端地址',
  `record` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '录音地址',
  `record2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备用录音地址',
  `record3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备用录音地址',
  `record_type` int NOT NULL DEFAULT '1' COMMENT '录音状态',
  `record_start_time` bigint NOT NULL DEFAULT '0' COMMENT '录音开始时间',
  `record_time` bigint NOT NULL DEFAULT '0' COMMENT '录音时间',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话时长',
  `frist_queue_time` bigint NOT NULL DEFAULT '0' COMMENT '第一次进队列时间',
  `queue_start_time` bigint NOT NULL DEFAULT '0' COMMENT '进队列时间',
  `queue_end_time` bigint NOT NULL DEFAULT '0' COMMENT '出队列时间',
  `month_time` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '月份',
  `follow_data` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '通话随路数据(2048)',
  `uuid1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展1',
  `uuid2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_calllog_call_id` (`call_id`) USING BTREE,
  KEY `idx_call_log_agent` (`agent_key`) USING BTREE,
  KEY `idx_call_log_group` (`group_id`) USING BTREE,
  KEY `idx_calllog_create_time` (`company_id`,`call_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=332 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单表';

-- ----------------------------
-- Records of cc_call_log
-- ----------------------------
BEGIN;
COMMIT;
-- ----------------------------
-- Table structure for cc_company
-- ----------------------------
DROP TABLE IF EXISTS `cc_company`;
CREATE TABLE `cc_company` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `id_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '父企业ID',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '父企业',
  `company_code` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '简称',
  `gmt` int NOT NULL DEFAULT '0' COMMENT '时区',
  `contact` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '联系人',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '电话',
  `balance` bigint NOT NULL DEFAULT '0' COMMENT '金额',
  `bill_type` int NOT NULL DEFAULT '0' COMMENT '1:呼出计费,2:呼入计费,3:双向计费,0:全免费',
  `pay_type` int NOT NULL DEFAULT '0' COMMENT '0:预付费;1:后付费',
  `hidden_customer` int NOT NULL DEFAULT '0' COMMENT '隐藏客户号码(0:不隐藏;1:隐藏)',
  `secret_type` int NOT NULL DEFAULT '0' COMMENT '坐席密码等级',
  `secret_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '验证秘钥',
  `ivr_limit` int NOT NULL DEFAULT '0' COMMENT 'IVR通道数',
  `agent_limit` int NOT NULL DEFAULT '0' COMMENT '开通坐席',
  `group_limit` int NOT NULL DEFAULT '0' COMMENT '开通技能组',
  `group_agent_limit` int NOT NULL DEFAULT '0' COMMENT '单技能组中坐席上限',
  `record_storage` int NOT NULL DEFAULT '0' COMMENT '录音保留天数',
  `notify_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '话单回调通知',
  `ext1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态(0:禁用企业,1:免费企业;2:试用企业,3:付费企业)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `company_unqidx_name` (`name`) USING BTREE,
  UNIQUE KEY `company_unqidx_code` (`company_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业信息表';

-- ----------------------------
-- Records of cc_company
-- ----------------------------
BEGIN;
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `record_storage`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (1, 1604503580, 1604503580, 'test企业', '', 0, 'test', 0, 'test', '18612983191', 1, 0, 1, 1, 1, '0765im6VjutX46iZ', 50, 5000, 20, 1000, 1, 'https://ccdemo.acloudcc.com/push/push/forcePush/pushTest', '', '', '', '', '', 1);
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `record_storage`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (2, 0, 1619796030, '20210430-delLwqXnW', '', 1, 'test2', 0, '曹亮', '', 0, 0, 0, 1, 1, 'djJHDuy34r87du34', 50, 50, 10, 1000, 1, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `record_storage`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (15, 0, 1619797353, 'aaaa-delcSJeGY', '', 0, 'acscwe-delIqjPqn', 0, '', '', 0, 0, 0, 1, 1, 'yfJWvM9jfxAzFUAQ', 50, 50, 10, 1000, 1, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `record_storage`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (16, 0, 1652767296, 'aaaa-delzHIxUf', '', 0, 'acscwe-delAHkzUP', 0, '', '', 0, 0, 0, 1, 1, 'yfJWvM9jfxAzFUAQ', 50, 50, 10, 1000, 1, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `record_storage`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (17, 0, 0, '曹亮测试企业', '', 0, 'caoliang', 0, '', '', 0, 0, 0, 0, 2, 'LHPhqMTSNHsfQrGE', 100, 100, 10, 1000, 0, '', '', '', '', '', '', 0);
COMMIT;

-- ----------------------------
-- Table structure for cc_company_display
-- ----------------------------
DROP TABLE IF EXISTS `cc_company_display`;
CREATE TABLE `cc_company_display` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '号码池',
  `type` int NOT NULL DEFAULT '0' COMMENT '1:主叫显号,2:被叫显号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `uni_idx_company_display` (`company_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='号码池表';

-- ----------------------------
-- Records of cc_company_display
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (1, 1604503580, 1604503580, 1, '呼入号码池', 1, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (2, 1604503580, 1624463394, 1, '主叫外显-deleEwjrg', 2, 0);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (3, 1604503580, 1624464161, 1, '被叫外显-delUKKKkq', 3, 0);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (4, 1624443516, 0, 0, '主机号码池', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (5, 1624443589, 0, 0, '主机号码池1', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (6, 1624443849, 0, 0, '主机号码池12', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (7, 1624445273, 0, 0, '主机号码池12222', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (8, 1624445321, 0, 0, '主机号码池1212', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (9, 1624445604, 0, 0, '主机号码池1213', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (10, 1624445968, 0, 0, '主机号码池1214', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (11, 1624451930, 0, 0, '主机号码池124', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (12, 1624452041, 0, 0, '主机号码池224', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (13, 1624452196, 0, 0, '主机号码池324', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (14, 1624452379, 0, 0, '主机号码池321', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (17, 1624452723, 0, 0, '主机号码池3211', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (18, 1624452836, 0, 0, '主机号码池3333', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (19, 1624453002, 0, 0, '主机号码池33331', 2, 1);
INSERT INTO `cc_company_display` (`id`, `cts`, `uts`, `company_id`, `name`, `type`, `status`) VALUES (20, 1624453280, 0, 0, '主机号码池333331', 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_company_phone
-- ----------------------------
DROP TABLE IF EXISTS `cc_company_phone`;
CREATE TABLE `cc_company_phone` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '号码',
  `type` int NOT NULL DEFAULT '0' COMMENT '1:呼入号码,2:主叫显号,3:被叫显号',
  `status` int NOT NULL DEFAULT '1' COMMENT '1:未启用,2:启用',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_idx_companyphone_phone` (`company_id`,`phone`,`type`) USING BTREE,
  KEY `idx_companyhpone_company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业号码';

-- ----------------------------
-- Records of cc_company_phone
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_phone` (`id`, `cts`, `uts`, `company_id`, `phone`, `type`, `status`) VALUES (1, 1604503580, 1638932067, 1, '18800010003', 1, 0);
INSERT INTO `cc_company_phone` (`id`, `cts`, `uts`, `company_id`, `phone`, `type`, `status`) VALUES (2, 1604503580, 1604503580, 1, '01012345678', 2, 1);
INSERT INTO `cc_company_phone` (`id`, `cts`, `uts`, `company_id`, `phone`, `type`, `status`) VALUES (3, 1604503580, 1604503580, 1, '01088889999', 3, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_company_phone_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_company_phone_group`;
CREATE TABLE `cc_company_phone_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `display_id` bigint NOT NULL DEFAULT '0' COMMENT '号码池id',
  `phone_id` bigint NOT NULL DEFAULT '0' COMMENT '号码id',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组号码池';

-- ----------------------------
-- Records of cc_company_phone_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (1, 1624452836, 0, 0, 0, 1);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (2, 1624452836, 0, 0, 0, 3);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (3, 1624452836, 0, 0, 0, 4);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (4, 1624452836, 0, 0, 0, 5);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (5, 1624453002, 0, 0, 0, 1);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (6, 1624453037, 0, 0, 0, 3);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (7, 1624453037, 0, 0, 0, 4);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (8, 1624453037, 0, 0, 0, 5);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (9, 1624453280, 0, 0, 20, 1);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (10, 1624453304, 0, 0, 20, 3);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (11, 1624453304, 0, 0, 20, 4);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (12, 1624453304, 0, 0, 20, 5);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (25, 1624463566, 0, 0, 2, 1);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (26, 1624463566, 0, 0, 2, 3);
INSERT INTO `cc_company_phone_group` (`id`, `cts`, `uts`, `company_id`, `display_id`, `phone_id`) VALUES (27, 1624463566, 0, 0, 2, 4);
COMMIT;

-- ----------------------------
-- Table structure for cc_company_stat
-- ----------------------------
DROP TABLE IF EXISTS `cc_company_stat`;
CREATE TABLE `cc_company_stat` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `type` int NOT NULL DEFAULT '1' COMMENT '报表类型(1:坐席报表)',
  `status` int NOT NULL DEFAULT '1' COMMENT '是否企业(1:启用;0:不启用)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_com_stat_type` (`company_id`,`type`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cc_company_stat
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_stat` (`id`, `cts`, `uts`, `company_id`, `type`, `status`) VALUES (1, 2, 1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_group`;
CREATE TABLE `cc_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '新增时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '技能组名称',
  `after_interval` int NOT NULL DEFAULT '5' COMMENT '话后自动空闲时长',
  `caller_display_id` bigint NOT NULL DEFAULT '0' COMMENT '主叫显号号码池',
  `called_display_id` bigint NOT NULL DEFAULT '0' COMMENT '被叫显号号码池',
  `record_type` int NOT NULL DEFAULT '1' COMMENT '1:振铃录音,2:接通录音',
  `level_value` int NOT NULL DEFAULT '1' COMMENT '技能组优先级',
  `tts_engine` bigint NOT NULL DEFAULT '0' COMMENT 'tts引擎id',
  `play_content` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '转坐席时播放内容',
  `evaluate` bigint NOT NULL DEFAULT '0' COMMENT '转服务评价(0:否,1:是)',
  `queue_play` bigint NOT NULL DEFAULT '0' COMMENT '排队音',
  `transfer_play` bigint NOT NULL DEFAULT '0' COMMENT '转接提示音',
  `call_time_out` int NOT NULL DEFAULT '30' COMMENT '外呼呼叫超时时间',
  `group_type` int NOT NULL DEFAULT '0' COMMENT '技能组类型',
  `notify_position` int NOT NULL DEFAULT '0' COMMENT '0:不播放排队位置,1:播放排队位置',
  `notify_rate` int NOT NULL DEFAULT '10' COMMENT '频次',
  `notify_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '您前面还有$位用户在等待',
  `call_memory` int NOT NULL DEFAULT '1' COMMENT '主叫记忆(1:开启,0:不开启)',
  `ext1` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext4` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext5` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_idx_company_name` (`company_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组表';

-- ----------------------------
-- Records of cc_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_group` (`id`, `cts`, `uts`, `company_id`, `name`, `after_interval`, `caller_display_id`, `called_display_id`, `record_type`, `level_value`, `tts_engine`, `play_content`, `evaluate`, `queue_play`, `transfer_play`, `call_time_out`, `group_type`, `notify_position`, `notify_rate`, `notify_content`, `call_memory`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (1, 1604503580, 1638965051, 1, '测试技能组-delDwLywI', 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 20, 0, 1, 10, '0', 1, '', '', '', '', '', 0);
INSERT INTO `cc_group` (`id`, `cts`, `uts`, `company_id`, `name`, `after_interval`, `caller_display_id`, `called_display_id`, `record_type`, `level_value`, `tts_engine`, `play_content`, `evaluate`, `queue_play`, `transfer_play`, `call_time_out`, `group_type`, `notify_position`, `notify_rate`, `notify_content`, `call_memory`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (2, 1621556151, 1638965054, 1, '测试技能组1-dellqdGSA', 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 20, 1, 1, 10, '0', 1, '', '', '', '', '', 0);
INSERT INTO `cc_group` (`id`, `cts`, `uts`, `company_id`, `name`, `after_interval`, `caller_display_id`, `called_display_id`, `record_type`, `level_value`, `tts_engine`, `play_content`, `evaluate`, `queue_play`, `transfer_play`, `call_time_out`, `group_type`, `notify_position`, `notify_rate`, `notify_content`, `call_memory`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (25, 1639063337, 0, 1, '测试技能组', 5, 0, 0, 1, 1, 0, '', 0, 0, 0, 20, 1, 1, 10, '0', 1, '', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_group_agent_strategy
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_agent_strategy`;
CREATE TABLE `cc_group_agent_strategy` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT ' 创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组id',
  `strategy_type` int NOT NULL DEFAULT '1' COMMENT '1:内置策略,2:自定义',
  `strategy_value` int NOT NULL DEFAULT '1' COMMENT '(1最长空闲时间、2最长平均空闲、3最少应答次数、4最少通话时长、5最长话后时长、6轮选、7随机)',
  `custom_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '自定义表达式',
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组中坐席分配策略';

-- ----------------------------
-- Records of cc_group_agent_strategy
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_agent_strategy` (`id`, `cts`, `uts`, `company_id`, `group_id`, `strategy_type`, `strategy_value`, `custom_expression`, `status`) VALUES (1, 1, 1, 1, 25, 1, 1, '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_group_memory
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_memory`;
CREATE TABLE `cc_group_memory` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `agent_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `phone` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '客户电话',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `unq_idx_group_id` (`group_id`,`phone`,`agent_key`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席与客户记忆表';

-- ----------------------------
-- Records of cc_group_memory
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_group_memory_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_memory_config`;
CREATE TABLE `cc_group_memory_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `success_strategy` int NOT NULL DEFAULT '0' COMMENT '匹配成功策略',
  `success_strategy_value` bigint NOT NULL DEFAULT '0' COMMENT '匹配成功策略值',
  `fail_strategy` int NOT NULL DEFAULT '0' COMMENT '匹配失败策略',
  `fail_strategy_value` bigint NOT NULL DEFAULT '0' COMMENT '匹配失败策略值',
  `memory_day` int NOT NULL DEFAULT '30' COMMENT '记忆天数',
  `inbound_cover` int NOT NULL DEFAULT '0' COMMENT '呼入覆盖',
  `outbound_cover` int NOT NULL DEFAULT '0' COMMENT '外呼覆盖',
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_idx_group` (`group_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组坐席记忆配置表';

-- ----------------------------
-- Records of cc_group_memory_config
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_group_overflow
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_overflow`;
CREATE TABLE `cc_group_overflow` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `overflow_id` bigint NOT NULL DEFAULT '0' COMMENT '溢出策略ID',
  `level_value` int NOT NULL DEFAULT '1' COMMENT '优先级',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_idx_group_overflow` (`group_id`,`overflow_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组排队策略表';

-- ----------------------------
-- Records of cc_group_overflow
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_overflow` (`id`, `cts`, `uts`, `group_id`, `overflow_id`, `level_value`, `status`) VALUES (1, 1, 1, 25, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_group_strategy_exp
-- ----------------------------
DROP TABLE IF EXISTS `cc_group_strategy_exp`;
CREATE TABLE `cc_group_strategy_exp` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组id',
  `strategy_key` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '自定义值',
  `strategy_present` int NOT NULL DEFAULT '1' COMMENT '百分百',
  `strategy_type` int NOT NULL DEFAULT '1' COMMENT '类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席自定义策略表';

-- ----------------------------
-- Records of cc_group_strategy_exp
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_strategy_exp` (`id`, `cts`, `uts`, `company_id`, `group_id`, `strategy_key`, `strategy_present`, `strategy_type`, `status`) VALUES (1, 1, 1, 1, 1, '1', 1, 1, 1);
INSERT INTO `cc_group_strategy_exp` (`id`, `cts`, `uts`, `company_id`, `group_id`, `strategy_key`, `strategy_present`, `strategy_type`, `status`) VALUES (2, 2, 2, 1, 1, '2', 2, 2, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_ivr_workflow
-- ----------------------------
DROP TABLE IF EXISTS `cc_ivr_workflow`;
CREATE TABLE `cc_ivr_workflow` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '流程名称',
  `oss_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '流程文件名',
  `init_params` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '用来存贮 ivr 流程启动所需要的参数描述',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '流程发布人',
  `verify_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '流程审核人',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci COMMENT '流程内容(ivr)',
  `voice_item` varchar(10000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '该流程用到的语音文件id，以英文逗号,分隔',
  `type` int NOT NULL DEFAULT '1' COMMENT '1转接，2咨询',
  `status` int NOT NULL COMMENT '流程状态    1：待发布   2：审核中  3：审核未通过  4：审核通过  5：已上线(ivr)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3 COMMENT='ivr流程表';

-- ----------------------------
-- Records of cc_ivr_workflow
-- ----------------------------
BEGIN;
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (1, 1, 1, 1, '测试ivr流程', '1000.xml', ' ', '111', '111', '{}', '', 1, 1);
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (2, 2, 2, 1, ' 1001', '1001.xml', ' ', '111', '111', '{}', ' ', 1, 1);
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (3, 3, 3, 1, '9999', '9999.xml', ' ', '1111', '11', '{}', ' ', 1, 1);
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (4, 4, 4, 1, '1004', '1004.xml', ' ', '111', '111', '{}', '1', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_overflow_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_overflow_config`;
CREATE TABLE `cc_overflow_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint DEFAULT '0' COMMENT '创建时间',
  `uts` bigint DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint DEFAULT '0' COMMENT '企业id',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `handle_type` int NOT NULL DEFAULT '1' COMMENT '1:排队,2:溢出,3:挂机',
  `busy_type` int DEFAULT '1' COMMENT '排队方式(1:先进先出,2:vip,3:自定义)',
  `queue_timeout` int DEFAULT '60' COMMENT '排队超时时间',
  `busy_timeout_type` int DEFAULT '1' COMMENT '排队超时(1:溢出,2:挂机)',
  `overflow_type` int DEFAULT '1' COMMENT '溢出(1:group,2:ivr,3:vdn)',
  `overflow_value` int DEFAULT '0' COMMENT '溢出值',
  `lineup_expression` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '自定义排队表达式',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_idx_name` (`company_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='溢出策略表';

-- ----------------------------
-- Records of cc_overflow_config
-- ----------------------------
BEGIN;
INSERT INTO `cc_overflow_config` (`id`, `cts`, `uts`, `company_id`, `name`, `handle_type`, `busy_type`, `queue_timeout`, `busy_timeout_type`, `overflow_type`, `overflow_value`, `lineup_expression`) VALUES (1, 1, 1, 1, '排队60秒', 1, 1, 60, 2, 1, 0, '');
COMMIT;

-- ----------------------------
-- Table structure for cc_overflow_exp
-- ----------------------------
DROP TABLE IF EXISTS `cc_overflow_exp`;
CREATE TABLE `cc_overflow_exp` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `overflow_id` bigint NOT NULL DEFAULT '0' COMMENT '溢出策略ID',
  `exp_key` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '自定义值',
  `rate` int NOT NULL DEFAULT '1' COMMENT '权重',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='自定义溢出策略优先级';

-- ----------------------------
-- Records of cc_overflow_exp
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_overflow_front
-- ----------------------------
DROP TABLE IF EXISTS `cc_overflow_front`;
CREATE TABLE `cc_overflow_front` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `overflow_id` bigint NOT NULL DEFAULT '0' COMMENT '策略ID',
  `front_type` int NOT NULL DEFAULT '1' COMMENT '1:队列长度; 2:队列等待最大时长; 3:呼损率',
  `compare_condition` int NOT NULL DEFAULT '0' COMMENT '0:全部; 1:小于或等于; 2:等于; 3:大于或等于; 4:大于',
  `rank_value_start` int NOT NULL DEFAULT '0',
  `rank_value` int NOT NULL DEFAULT '0' COMMENT '符号条件值',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='溢出策略前置条件';

-- ----------------------------
-- Records of cc_overflow_front
-- ----------------------------
BEGIN;
INSERT INTO `cc_overflow_front` (`id`, `cts`, `uts`, `company_id`, `overflow_id`, `front_type`, `compare_condition`, `rank_value_start`, `rank_value`, `status`) VALUES (1, 1, 1, 1, 1, 1, 0, 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_platform_license
-- ----------------------------
DROP TABLE IF EXISTS `cc_platform_license`;
CREATE TABLE `cc_platform_license` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `platform_license` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '平台信息',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='LICENSE';

-- ----------------------------
-- Records of cc_platform_license
-- ----------------------------
BEGIN;
INSERT INTO `cc_platform_license` (`id`, `cts`, `uts`, `platform_license`, `status`) VALUES (1, 1652612752, 1652612752, 'cBoRYwDfDPMyGEGuvldRVCc576c9//BXKrJghs+ujkGuwQEQVVI5faS8nt0S3L/DYKa3uW0+mgf/zIfY1qaGaeiuvoDEgF1AzlCOTr5vI5+0DluPb6dmvCVOdW9o36nf3sQzLIOV5bjFqnLGwws7Cn3JiAW6934URCsWPQhPyac=', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_playback
-- ----------------------------
DROP TABLE IF EXISTS `cc_playback`;
CREATE TABLE `cc_playback` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL COMMENT '企业ID',
  `playback` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '放音文件',
  `status` int NOT NULL DEFAULT '1' COMMENT '1:待审核,2:审核通过',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='语音文件表';

-- ----------------------------
-- Records of cc_playback
-- ----------------------------
BEGIN;
INSERT INTO `cc_playback` (`id`, `cts`, `uts`, `company_id`, `playback`, `status`) VALUES (1, 1, 1, 1, '/app/clpms/sounds/222.wav', 2);
COMMIT;

-- ----------------------------
-- Table structure for cc_push_log
-- ----------------------------
DROP TABLE IF EXISTS `cc_push_log`;
CREATE TABLE `cc_push_log` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT 'callid',
  `cdr_notify_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '发送次数',
  `content` varchar(10240) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '推送内容',
  `push_times` int NOT NULL DEFAULT '1' COMMENT '推送次数',
  `push_response` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '推送返回值',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态(1:推送，0:不推送)',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_push_fail_uts` (`uts`)
) ENGINE=InnoDB AUTO_INCREMENT=646 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单推送记录表';


-- ----------------------------
-- Table structure for cc_route_call
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_call`;
CREATE TABLE `cc_route_call` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '所属企业',
  `route_group_id` bigint NOT NULL DEFAULT '0' COMMENT '所属路由组',
  `route_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '字冠号码',
  `num_max` int NOT NULL DEFAULT '0' COMMENT '最长',
  `num_min` int NOT NULL DEFAULT '0' COMMENT '最短',
  `caller_change` int NOT NULL DEFAULT '0' COMMENT '主叫替换规则',
  `caller_change_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '替换号码',
  `called_change` int NOT NULL DEFAULT '0' COMMENT '被叫替换规则',
  `called_change_num` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '替换号码',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `unq_idx_route` (`company_id`,`route_num`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字冠路由表';

-- ----------------------------
-- Records of cc_route_call
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (1, 1, 1, 1, 1, '87', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (2, 1, 1, 1, 2, '133', 9, 4, 0, '1 ', 1, '1', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (8, 1, 1, 1, 5, '144', 11, 4, 0, '1', 0, '', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (10, 1621923239, 0, 1, 3, '87000', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (11, 1, 1, 1, 4, '*', 2, 14, 0, '1', 1, '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_getway
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_getway`;
CREATE TABLE `cc_route_getway` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '号码',
  `media_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '媒体地址',
  `media_port` int NOT NULL DEFAULT '0' COMMENT '媒体端口',
  `caller_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫号码前缀',
  `called_prefix` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫号码前缀',
  `profile` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'fs的context规则',
  `sip_header1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sip头1',
  `sip_header2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sip头2',
  `sip_header3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'sip头3',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uindex_getway_name` (`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='媒体网关表';

-- ----------------------------
-- Records of cc_route_getway
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_getway` (`id`, `cts`, `uts`, `name`, `media_host`, `media_port`, `caller_prefix`, `called_prefix`, `profile`, `sip_header1`, `sip_header2`, `sip_header3`, `status`) VALUES (1, 1604503580, 1604503580, '87', '172.17.0.2', 6685, '', '', 'internal', '', '', '', 1);
INSERT INTO `cc_route_getway` (`id`, `cts`, `uts`, `name`, `media_host`, `media_port`, `caller_prefix`, `called_prefix`, `profile`, `sip_header1`, `sip_header2`, `sip_header3`, `status`) VALUES (2, 1604503580, 1604503580, '133', '172.17.0.2', 32460, '', '', 'external', 'V9-CALL-ID=${callId}', 'V9-DEVICE-ID=${deviceId}', '', 1);
INSERT INTO `cc_route_getway` (`id`, `cts`, `uts`, `name`, `media_host`, `media_port`, `caller_prefix`, `called_prefix`, `profile`, `sip_header1`, `sip_header2`, `sip_header3`, `status`) VALUES (3, 1604503580, 1604503580, 'auto87', '172.17.0.2', 32460, '', '', 'external', 'V9-CALL-ID=${callId}', '', '', 1);
INSERT INTO `cc_route_getway` (`id`, `cts`, `uts`, `name`, `media_host`, `media_port`, `caller_prefix`, `called_prefix`, `profile`, `sip_header1`, `sip_header2`, `sip_header3`, `status`) VALUES (5, 1604503580, 1621856137, '1441', '172.17.0.2', 32460, '', '', 'external', 'V9-CALL-ID=${callId}', 'V9-DEVICE-ID=${deviceId}', '', 1);
INSERT INTO `cc_route_getway` (`id`, `cts`, `uts`, `name`, `media_host`, `media_port`, `caller_prefix`, `called_prefix`, `profile`, `sip_header1`, `sip_header2`, `sip_header3`, `status`) VALUES (7, 1, 1, 'CALL_OUT', '101.200.44.120', 38915, '', '', 'external', 'V9-CALL-ID=${callId}', 'V9-DEVICE-ID=${deviceId}', '', 1);
INSERT INTO `cc_route_getway` (`id`, `cts`, `uts`, `name`, `media_host`, `media_port`, `caller_prefix`, `called_prefix`, `profile`, `sip_header1`, `sip_header2`, `sip_header3`, `status`) VALUES (14, 1621850816, 0, '183clpss', '192.168.177.183', 1111, '', '', 'external', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_getway_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_getway_group`;
CREATE TABLE `cc_route_getway_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `getway_id` bigint NOT NULL DEFAULT '0' COMMENT '媒体网关',
  `route_group_id` bigint NOT NULL DEFAULT '0' COMMENT '网关组',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由字冠关联组表';

-- ----------------------------
-- Records of cc_route_getway_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (1, 1, 1, 3, 1);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (2, 1, 1, 2, 2);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (3, 1, 1, 1, 3);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (4, 1, 1, 7, 4);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (5, 1, 1, 5, 5);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (12, 1638933705, 0, 1, 8);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (13, 1638933705, 0, 2, 8);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (14, 1638933705, 0, 3, 8);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (24, 1638933719, 0, 1, 7);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (25, 1638933719, 0, 2, 7);
INSERT INTO `cc_route_getway_group` (`id`, `cts`, `uts`, `getway_id`, `route_group_id`) VALUES (26, 1638933719, 0, 3, 7);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_group`;
CREATE TABLE `cc_route_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `route_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '网关组',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网关组';

-- ----------------------------
-- Records of cc_route_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (1, 1, 1, '87路由', 1);
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (2, 1, 1, '133路由', 1);
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (3, 1, 1, '871556590425003', 1);
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (4, 1, 1, 'CALL_OUT', 1);
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (5, 1, 1, '144路由', 1);
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (7, 0, 1638933719, '网关组2', 1);
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (8, 0, 0, '网关组1', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_skill
-- ----------------------------
DROP TABLE IF EXISTS `cc_skill`;
CREATE TABLE `cc_skill` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '备注',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_skill_name` (`company_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能表';

-- ----------------------------
-- Records of cc_skill
-- ----------------------------
BEGIN;
INSERT INTO `cc_skill` (`id`, `cts`, `uts`, `name`, `remark`, `company_id`, `status`) VALUES (1, 1, 1639056370, '呼入技能', '呼入技能1', 1, 1);
INSERT INTO `cc_skill` (`id`, `cts`, `uts`, `name`, `remark`, `company_id`, `status`) VALUES (2, 1639032157, 1639032819, '外呼技1212能-delrfyvZp', '手动外呼技能了', 1, 0);
INSERT INTO `cc_skill` (`id`, `cts`, `uts`, `name`, `remark`, `company_id`, `status`) VALUES (3, 1639032213, 1639032213, '手动外呼技能-delnYpkua', '手动外呼技能了', 1, 0);
INSERT INTO `cc_skill` (`id`, `cts`, `uts`, `name`, `remark`, `company_id`, `status`) VALUES (4, 1639051395, 1639051395, '外呼技能', '外呼技能', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_skill_agent
-- ----------------------------
DROP TABLE IF EXISTS `cc_skill_agent`;
CREATE TABLE `cc_skill_agent` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `skill_id` bigint NOT NULL DEFAULT '0' COMMENT '技能id',
  `agent_id` bigint NOT NULL DEFAULT '0' COMMENT '坐席id',
  `rank_value` int NOT NULL DEFAULT '0' COMMENT '范围',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席技能表';

-- ----------------------------
-- Records of cc_skill_agent
-- ----------------------------
BEGIN;
INSERT INTO `cc_skill_agent` (`id`, `cts`, `uts`, `company_id`, `skill_id`, `agent_id`, `rank_value`, `status`) VALUES (12, 1639054899, 1639056313, 1, 1, 1, 111, 1);
INSERT INTO `cc_skill_agent` (`id`, `cts`, `uts`, `company_id`, `skill_id`, `agent_id`, `rank_value`, `status`) VALUES (13, 1639054899, 0, 1, 1, 2, 1, 1);
INSERT INTO `cc_skill_agent` (`id`, `cts`, `uts`, `company_id`, `skill_id`, `agent_id`, `rank_value`, `status`) VALUES (14, 1639054899, 0, 1, 1, 3, 1, 1);
INSERT INTO `cc_skill_agent` (`id`, `cts`, `uts`, `company_id`, `skill_id`, `agent_id`, `rank_value`, `status`) VALUES (15, 1639054899, 0, 1, 1, 4, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_skill_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_skill_group`;
CREATE TABLE `cc_skill_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `level_value` int NOT NULL DEFAULT '1',
  `skill_id` bigint NOT NULL DEFAULT '0' COMMENT '技能ID',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `rank_type` int NOT NULL DEFAULT '1' COMMENT '等级类型(1:全部,2:等于,3:>,4:<,5:介于)',
  `rank_value_start` int NOT NULL DEFAULT '0' COMMENT '介于的开始值',
  `rank_value` int NOT NULL DEFAULT '1' COMMENT '等级值',
  `match_type` int NOT NULL DEFAULT '1' COMMENT '匹配规则(1:低到高,2:高到低)',
  `share_value` int NOT NULL DEFAULT '100' COMMENT '占用率',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_group_id` (`group_id`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组技能表';

-- ----------------------------
-- Records of cc_skill_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (1, 1639063337, 0, 1, 100, 1, 25, 1, 0, 0, 1, 100, 1);
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (2, 1639063973, 0, 1, 100, 1, 26, 1, 0, 0, 1, 100, 1);
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (3, 1652716134, 0, 1, 100, 1, 27, 1, 0, 0, 1, 100, 1);
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (4, 1652716236, 0, 1, 100, 1, 28, 1, 0, 0, 1, 100, 1);
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (5, 1652718585, 0, 1, 100, 1, 29, 1, 0, 0, 1, 100, 1);
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (6, 1652719716, 0, 1, 100, 1, 30, 1, 0, 0, 1, 100, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_stat_day_agent
-- ----------------------------
DROP TABLE IF EXISTS `cc_stat_day_agent`;
CREATE TABLE `cc_stat_day_agent` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT 'cts',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `agent_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席编号',
  `agent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `stat_time` bigint NOT NULL DEFAULT '0' COMMENT '统计时间',
  `callout_cnt` bigint NOT NULL DEFAULT '0' COMMENT '外呼总量',
  `callout_answer_cnt` bigint NOT NULL DEFAULT '0' COMMENT '外呼接通量',
  `callin_cnt` bigint NOT NULL DEFAULT '0' COMMENT '呼入分配量',
  `callin_answer_cnt` bigint NOT NULL DEFAULT '0' COMMENT '呼入接起量',
  `login_cnt` bigint NOT NULL DEFAULT '0' COMMENT '登录次数',
  `ready_cnt` bigint NOT NULL DEFAULT '0' COMMENT '空闲次数',
  `not_ready_cnt` bigint NOT NULL DEFAULT '0' COMMENT '忙碌次数',
  `after_cnt` bigint NOT NULL DEFAULT '0' COMMENT '话后次数',
  `login_time` bigint NOT NULL DEFAULT '0' COMMENT '登录总时长',
  `ready_time` bigint NOT NULL DEFAULT '0' COMMENT '空闲总时长',
  `not_ready_time` bigint NOT NULL DEFAULT '0' COMMENT '忙碌总时长',
  `busy_time` bigint NOT NULL DEFAULT '0' COMMENT '自定义忙碌总时间',
  `after_time` bigint NOT NULL DEFAULT '0' COMMENT '话后总时长',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话总时长',
  `callin_talk_time` bigint NOT NULL DEFAULT '0' COMMENT '呼入通话总时长',
  `callout_talk_time` bigint NOT NULL DEFAULT '0' COMMENT '外呼通话总时长',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_idx_state_agenthourwork_agent` (`stat_time`,`agent_key`) USING BTREE,
  FULLTEXT KEY `idx_state_agenthourwork_agent` (`agent_key`)
) ENGINE=InnoDB AUTO_INCREMENT=300 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cc_stat_day_agent
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_stat_hour_agent
-- ----------------------------
DROP TABLE IF EXISTS `cc_stat_hour_agent`;
CREATE TABLE `cc_stat_hour_agent` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT 'cts',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `agent_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席编号',
  `agent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `stat_time` bigint NOT NULL DEFAULT '0' COMMENT '统计时间',
  `callout_cnt` bigint NOT NULL DEFAULT '0' COMMENT '外呼总量',
  `callout_answer_cnt` bigint NOT NULL DEFAULT '0' COMMENT '外呼接通量',
  `callin_cnt` bigint NOT NULL DEFAULT '0' COMMENT '呼入分配量',
  `callin_answer_cnt` bigint NOT NULL DEFAULT '0' COMMENT '呼入接起量',
  `login_cnt` bigint NOT NULL DEFAULT '0' COMMENT '登录次数',
  `ready_cnt` bigint NOT NULL DEFAULT '0' COMMENT '空闲次数',
  `not_ready_cnt` bigint NOT NULL DEFAULT '0' COMMENT '忙碌次数',
  `after_cnt` bigint NOT NULL DEFAULT '0' COMMENT '话后次数',
  `login_time` bigint NOT NULL DEFAULT '0' COMMENT '登录总时长',
  `ready_time` bigint NOT NULL DEFAULT '0' COMMENT '空闲总时长',
  `not_ready_time` bigint NOT NULL DEFAULT '0' COMMENT '忙碌总时长',
  `busy_time` bigint NOT NULL DEFAULT '0' COMMENT '自定义忙碌总时间',
  `after_time` bigint NOT NULL DEFAULT '0' COMMENT '话后总时长',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话总时长',
  `callin_talk_time` bigint NOT NULL DEFAULT '0' COMMENT '呼入通话总时长',
  `callout_talk_time` bigint NOT NULL DEFAULT '0' COMMENT '外呼通话总时长',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_idx_state_agenthourwork_agent` (`stat_time`,`agent_key`) USING BTREE,
  FULLTEXT KEY `idx_state_agenthourwork_agent` (`agent_key`)
) ENGINE=InnoDB AUTO_INCREMENT=87 DEFAULT CHARSET=utf8mb3;


-- ----------------------------
-- Table structure for cc_station
-- ----------------------------
DROP TABLE IF EXISTS `cc_station`;
CREATE TABLE `cc_station` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `application_id` int NOT NULL DEFAULT '0' COMMENT '应用id',
  `application_type` int NOT NULL DEFAULT '0' COMMENT '服务类型',
  `application_group` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '服务所在的组',
  `application_host` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '服务ip地址',
  `application_port` int NOT NULL DEFAULT '0' COMMENT 'http服务端口',
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `pwd` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='站点类型配置信息';

-- ----------------------------
-- Records of cc_station
-- ----------------------------

-- ----------------------------
-- Table structure for cc_vdn_code
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_code`;
CREATE TABLE `cc_vdn_code` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT 'vdn名称',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='呼入路由表';

-- ----------------------------
-- Records of cc_vdn_code
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_code` (`id`, `cts`, `uts`, `company_id`, `name`, `status`) VALUES (3, 3, 3, 1, '01011515901', 1);
INSERT INTO `cc_vdn_code` (`id`, `cts`, `uts`, `company_id`, `name`, `status`) VALUES (4, 4, 4, 1, '01011515902', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_config`;
CREATE TABLE `cc_vdn_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '子码日程',
  `vdn_id` bigint NOT NULL DEFAULT '0' COMMENT 'vdn_id',
  `schedule_id` bigint NOT NULL DEFAULT '0' COMMENT '日程id',
  `route_type` int NOT NULL DEFAULT '1' COMMENT '路由类型(1:技能组,2:放音,3:ivr,4:坐席,5:外呼)',
  `route_value` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '0' COMMENT '路由类型值',
  `play_type` int NOT NULL DEFAULT '0' COMMENT '放音类型(1:按键导航,2:技能组,3:ivr,4:路由字码,5:挂机)',
  `play_value` bigint NOT NULL DEFAULT '0' COMMENT '放音类型对应值',
  `dtmf_end` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '*' COMMENT '结束音',
  `retry` int NOT NULL DEFAULT '1' COMMENT '重复播放次数',
  `dtmf_max` int NOT NULL DEFAULT '1' COMMENT '最大收键长度',
  `dtmf_min` int NOT NULL DEFAULT '1' COMMENT '最小收键长度',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由字码表';

-- ----------------------------
-- Records of cc_vdn_config
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_config` (`id`, `cts`, `uts`, `company_id`, `name`, `vdn_id`, `schedule_id`, `route_type`, `route_value`, `play_type`, `play_value`, `dtmf_end`, `retry`, `dtmf_max`, `dtmf_min`, `status`) VALUES (4, 4, 4, 1, '转IVR', 3, 1, 3, '1', 1, 1, '*', 2, 3, 3, 1);
INSERT INTO `cc_vdn_config` (`id`, `cts`, `uts`, `company_id`, `name`, `vdn_id`, `schedule_id`, `route_type`, `route_value`, `play_type`, `play_value`, `dtmf_end`, `retry`, `dtmf_max`, `dtmf_min`, `status`) VALUES (5, 5, 5, 1, '进技能组', 4, 1, 1, '25', 1, 1, '*', 1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_dtmf
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_dtmf`;
CREATE TABLE `cc_vdn_dtmf` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `navigate_id` bigint NOT NULL DEFAULT '0' COMMENT '按键导航ID',
  `dtmf` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '1' COMMENT '按键',
  `route_type` int NOT NULL DEFAULT '0' COMMENT '路由类型(1:技能组,2:IVR,3:路由字码,4:坐席分机,5:挂机)',
  `route_value` bigint NOT NULL DEFAULT '0' COMMENT '路由值',
  `status` int DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='按键导航表';

-- ----------------------------
-- Records of cc_vdn_dtmf
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_phone
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_phone`;
CREATE TABLE `cc_vdn_phone` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `vdn_id` bigint NOT NULL DEFAULT '0' COMMENT '路由码',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '特服号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uni_idx_phone` (`vdn_id`,`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由号码表';

-- ----------------------------
-- Records of cc_vdn_phone
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_phone` (`id`, `cts`, `uts`, `company_id`, `vdn_id`, `phone`, `status`) VALUES (3, 1, 1, 1, 3, '01011515901', 1);
INSERT INTO `cc_vdn_phone` (`id`, `cts`, `uts`, `company_id`, `vdn_id`, `phone`, `status`) VALUES (4, 1, 1, 1, 4, '01011515902', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_schedule
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_schedule`;
CREATE TABLE `cc_vdn_schedule` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '日程名称',
  `level_value` int NOT NULL DEFAULT '1' COMMENT '优先级',
  `type` int NOT NULL DEFAULT '1' COMMENT '1:指定时间,2:相对时间',
  `start_day` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '开始日期',
  `end_day` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '结束日期',
  `start_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '开始时间',
  `end_time` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '结束时间',
  `mon` int NOT NULL DEFAULT '1' COMMENT '周一',
  `tue` int NOT NULL DEFAULT '1' COMMENT '周二',
  `wed` int NOT NULL DEFAULT '1' COMMENT '周三',
  `thu` int NOT NULL DEFAULT '1' COMMENT '周四',
  `fri` int NOT NULL DEFAULT '1' COMMENT '周五',
  `sat` int NOT NULL DEFAULT '1' COMMENT '周六',
  `sun` int NOT NULL DEFAULT '1' COMMENT '周天',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日程表';


-- ----------------------------
-- Table structure for cc_sip_gateway
-- ----------------------------
DROP TABLE IF EXISTS `cc_sip_gateway`;
create table cc_sip_gateway
(
    id            bigint auto_increment comment 'PK' primary key,
    cts           bigint       default 0  not null comment '创建时间',
    uts           bigint       default 0  not null comment '修改时间',
    company_id    bigint       default 0  not null comment '企业id',
    company_code  varchar(255) default '' null comment '企业编码',
    company_name  varchar(255) default '' null comment '企业名称',
    username      varchar(255) default '' not null comment '账号',
    passwd        varchar(255) default '' not null comment '密码',
    internal      varchar(255) default '' null comment '网关内网',
    external      varchar(255) default '' null comment '网关外网',
    register_addr varchar(255) default '' null comment '注册地址',
    register_time int          default 0  null comment '注册时间',
    expire        int          default 0  null comment '注册周期(秒)',
    status        int          default 1  null comment '状态(0:删除,1:不在线,2:在线)'
) comment '网关注册账号表';

-- ----------------------------
-- Records of cc_vdn_schedule
-- ----------------------------


SET FOREIGN_KEY_CHECKS = 1;
