/*
 Navicat Premium Data Transfer

 Source Server         : tencent
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : 172.17.0.2:3306
 Source Schema         : aicall

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 14/04/2022 21:15:44
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
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', '0 30 0 * * ? *', 'GMT+08:00');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', '0 0 2 1 * ?', 'GMT+08:00');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', '0/2 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('clusteredScheduler', 'TaskJobOfHour', 'cc.quartz', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('clusteredScheduler', 'TaskJobOfSecond', 'cc.quartz', '0/1 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('DefaultQuartzScheduler', 'TaskJobOfHour', 'cc.quartz', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `CRON_EXPRESSION`, `TIME_ZONE_ID`) VALUES ('DefaultQuartzScheduler', 'TaskJobOfSecond', 'cc.quartz', '0/2 * * * * ?', 'Asia/Shanghai');
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
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay 任务', 'org.zhongweixian.api.quartz.TaskJobOfDay', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 'org.zhongweixian.api.quartz.TaskJobOfHour', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth 任务', 'org.zhongweixian.api.quartz.TaskJobOfMonth', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 'org.zhongweixian.api.quartz.TaskJobOfSecond', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('clusteredScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 'org.zhongweixian.api.quartz.TaskJobOfHour', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('clusteredScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 'org.zhongweixian.api.quartz.TaskJobOfSecond', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('DefaultQuartzScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 'org.zhongweixian.api.quartz.TaskJobOfHour', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `JOB_CLASS_NAME`, `IS_DURABLE`, `IS_NONCONCURRENT`, `IS_UPDATE_DATA`, `REQUESTS_RECOVERY`, `JOB_DATA`) VALUES ('DefaultQuartzScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 'org.zhongweixian.api.quartz.TaskJobOfSecond', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
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
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('clusteredScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('clusteredScheduler', 'TRIGGER_ACCESS');
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('DefaultQuartzScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` (`SCHED_NAME`, `LOCK_NAME`) VALUES ('DefaultQuartzScheduler', 'TRIGGER_ACCESS');
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
INSERT INTO `QRTZ_SCHEDULER_STATE` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES ('cc-quartz', 'VM-0-2-centos1649941431774', 1649942149234, 10000);
INSERT INTO `QRTZ_SCHEDULER_STATE` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES ('clusteredScheduler', 'localhost1630684716234', 1630684907047, 1000);
INSERT INTO `QRTZ_SCHEDULER_STATE` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES ('DefaultQuartzScheduler', 'rlcc141630718230947', 1630727015348, 10000);
INSERT INTO `QRTZ_SCHEDULER_STATE` (`SCHED_NAME`, `INSTANCE_NAME`, `LAST_CHECKIN_TIME`, `CHECKIN_INTERVAL`) VALUES ('DefaultQuartzScheduler', 'VM-0-2-centos1630726941990', 1630727000282, 10000);
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
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay 任务', 1630859400000, -1, 5, 'ERROR', 'CRON', 1630858548000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 1649944800000, 1649941200000, 5, 'WAITING', 'CRON', 1630726916000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth 任务', 1651341600000, 1649067832861, 5, 'WAITING', 'CRON', 1630858548000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 1633751970000, 1633751968000, 5, 'ACQUIRED', 'CRON', 1630726917000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('clusteredScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 1630688400000, 1630684800000, 5, 'WAITING', 'CRON', 1630684251000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('clusteredScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 1630684908000, 1630684907000, 5, 'WAITING', 'CRON', 1630684251000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('DefaultQuartzScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 1630728000000, 1630724400000, 5, 'WAITING', 'CRON', 1630684987000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `JOB_NAME`, `JOB_GROUP`, `DESCRIPTION`, `NEXT_FIRE_TIME`, `PREV_FIRE_TIME`, `PRIORITY`, `TRIGGER_STATE`, `TRIGGER_TYPE`, `START_TIME`, `END_TIME`, `CALENDAR_NAME`, `MISFIRE_INSTR`, `JOB_DATA`) VALUES ('DefaultQuartzScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 1630727016000, 1630727014000, 5, 'WAITING', 'CRON', 1630684987000, 0, NULL, 0, '');
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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cc_admin_menu
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_menu` (`id`, `cts`, `uts`, `menu_id`, `parent_id`, `name`, `path_url`, `path_method`, `menu_level`, `menu_order`, `child_num`, `icon`, `create_default`, `init_default`, `front_site`, `front_icon`, `end_site`, `status`) VALUES (1, 1, 1, '1', '', '系统配置', '', '', 1, 0, 0, '', 0, 0, 1, '', 1, 1);
INSERT INTO `cc_admin_menu` (`id`, `cts`, `uts`, `menu_id`, `parent_id`, `name`, `path_url`, `path_method`, `menu_level`, `menu_order`, `child_num`, `icon`, `create_default`, `init_default`, `front_site`, `front_icon`, `end_site`, `status`) VALUES (2, 1, 1, '2', '1', '账号权限', '', '', 2, 0, 0, '', 0, 0, 1, '', 1, 1);
INSERT INTO `cc_admin_menu` (`id`, `cts`, `uts`, `menu_id`, `parent_id`, `name`, `path_url`, `path_method`, `menu_level`, `menu_order`, `child_num`, `icon`, `create_default`, `init_default`, `front_site`, `front_icon`, `end_site`, `status`) VALUES (3, 1, 1, '3', '2', '菜单列表', '', '', 3, 0, 0, '', 0, 0, 1, '', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_role`;
CREATE TABLE `cc_admin_role` (
  `id` bigint NOT NULL COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '所属企业',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '角色名称',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of cc_admin_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_role_menu`;
CREATE TABLE `cc_admin_role_menu` (
  `id` bigint NOT NULL COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色ID',
  `menu_id` bigint NOT NULL DEFAULT '0' COMMENT '权限ID',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_role_perm` (`role_id`,`menu_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限角色表';

-- ----------------------------
-- Records of cc_admin_role_menu
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (1, 1, 1, 1, 1, 1);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (2, 1, 1, 1, 2, 1);
INSERT INTO `cc_admin_role_menu` (`id`, `cts`, `uts`, `role_id`, `menu_id`, `company_id`) VALUES (3, 1, 1, 1, 3, 1);
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
  `diaplay` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '主叫显号',
  `ring_time` int NOT NULL DEFAULT '10' COMMENT '振铃时长',
  `host` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '登录服务器地址',
  `ext1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展3',
  `state` int NOT NULL DEFAULT '0' COMMENT '坐席状态(1:在线,0:不在线)',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态：1 开通，0关闭',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_agent_key` (`agent_key`,`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3 COMMENT='座席工号表';

-- ----------------------------
-- Records of cc_agent
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (1, 1604503580, 1604503580, 1, '1001', '1001@test', '测试坐席', '1001', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188899998889', 0, 25, 5, '', 10, '172.17.0.2:7200', '', '', '', 1, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (2, 1604560158, 1604560158, 1, '1002', '1002@test', '测试2号', '1002', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188999988887', 0, 25, 5, '', 10, ' ', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (3, 1638195288, 0, 1, 'B001', 'B001@test', 'B001', '', 1, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '', 0, 25, 5, '', 10, '119.97.197.5', '', '', '', 1, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (4, 1638195289, 0, 1, 'B002', 'B002@test', 'B002', '', 1, '$2a$04$aCTZ6qBIaM0Sh5z6S0kYDORhhprvmiTKiTtLFm0C.IQ.gSIH9ckiy', '', 0, 0, 5, '', 10, '', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (5, 1638195289, 0, 1, 'B003', 'B003@test', 'B003', '', 1, '$2a$04$FcACHx2NHDJBEUdai00HuuN5omejMFqfLfqg6UYkD92e0diF/PWlC', '', 0, 0, 5, '', 10, '', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (6, 1638195289, 0, 1, 'B004', 'B004@test', 'B004', '', 1, '$2a$04$XGsb1KDSNkXtMNcK1FvjrePsG6qRxab9w2e63a/4sEBLhok2r7IrC', '', 0, 0, 5, '', 10, '', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (7, 1638195289, 0, 1, 'B005', 'B005@test', 'B005', '', 1, '$2a$04$tBfxsK0lZvjEQhryYwcnZ..uwqhyqbXrkjqhtYWaVEPEWMc3fOCsa', '', 0, 0, 5, '', 10, '', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (8, 1638195289, 0, 1, 'B006', 'B006@test', 'B006', '', 1, '$2a$04$Ck2OGm6qZh0mr9bJ9fLVweuj5j7f4ojxdUjBUXjoEJqNADOkZILvu', '', 0, 0, 5, '', 10, '', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (9, 1638195289, 0, 1, 'B007', 'B007@test', 'B007', '', 1, '$2a$04$LnSoJkbTYreYOkoHlgU57OaZwuPCqmEww.VuonvkKA49K6LQ5is6i', '', 0, 0, 5, '', 10, '', '', '', '', 0, 1);
INSERT INTO `cc_agent` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_name`, `agent_code`, `agent_type`, `passwd`, `sip_phone`, `record`, `group_id`, `after_interval`, `diaplay`, `ring_time`, `host`, `ext1`, `ext2`, `ext3`, `state`, `status`) VALUES (11, 1638195693, 0, 1, 'D001', 'D001@test', 'D001', '', 1, '$2a$04$MDcppCCPp7Hd9ijAnN17c.2ehFhS93DO1llEHfOZHgLJvFktdtwvy', '', 0, 0, 5, '', 10, '', '', '', '', 0, 1);
COMMIT;

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
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席技能组表';

-- ----------------------------
-- Records of cc_agent_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (1, 1604560158, 1604560158, 1, 1, '1001@test', 1, 25, 1);
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (2, 1604560158, 1604560158, 1, 2, '1002@test', 1, 25, 1);
INSERT INTO `cc_agent_group` (`id`, `cts`, `uts`, `company_id`, `agent_id`, `agent_key`, `agent_type`, `group_id`, `status`) VALUES (3, 1, 1, 1, 3, 'B001@test', 1, 25, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sip表';

-- ----------------------------
-- Records of cc_agent_sip
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (1, 1622024375, 1622033134, 1, '870001', 1, '123456', 1);
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (2, 1622072271, 0, 1, '870002', 2, '123456', 1);
INSERT INTO `cc_agent_sip` (`id`, `cts`, `uts`, `company_id`, `sip`, `agent_id`, `sip_pwd`, `status`) VALUES (3, 1, 1, 1, '870003', 3, '123456', 1);
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
  `state_time` bigint NOT NULL DEFAULT '0' COMMENT '当前时间',
  `duration` int NOT NULL DEFAULT '0' COMMENT '持续时间',
  `busy_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '忙碌类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  `ext1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `ext3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_agentstate_agentkey` (`agent_key`) USING BTREE,
  KEY `idx_agentstate_cts` (`state_time`) USING BTREE,
  KEY `idx_agentstate_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8229 DEFAULT CHARSET=utf8mb3 COMMENT='坐席状态历史表';

-- ----------------------------
-- Records of cc_agent_state_log
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8096, 1649396565, 1649396565, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2', '', 'LOGOUT', 0, 'LOGIN', 0, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8097, 1649396593, 1649396593, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2', '', 'LOGOUT', 0, 'LOGIN', 1649396565574, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8098, 1649396610, 1649396610, 1, 25, 2, '1002@test', '测试2号', 0, 1, 1, '172.17.0.2', '', 'LOGOUT', 0, 'LOGIN', 0, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8099, 1649396629, 1649396629, 1, 25, 3, 'B001@test', 'B001', 0, 1, 1, '172.17.0.2', '', 'LOGOUT', 0, 'LOGIN', 0, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8100, 1649396943, 1649396943, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'LOGOUT', 0, 'LOGIN', 1649396943405, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8101, 1649396944, 1649396944, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'LOGIN', 1649396943405, 'READY', 1649396944929, 1524, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8102, 1649396945, 1649396945, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'READY', 1649396944929, 'NOT_READY', 1649396945267, 338, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8103, 1649396946, 1649396946, 1, 25, 1, '1001@test', '测试坐席', 300265803364696064, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'NOT_READY', 1649396945267, 'OUT_CALL', 1649396946245, 978, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8104, 1649396946, 1649396946, 1, 25, 1, '1001@test', '测试坐席', 300265803364696064, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'OUT_CALL', 1649396946245, 'AFTER', 1649396946515, 270, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8105, 1649396960, 1649396960, 1, 25, 1, '1001@test', '测试坐席', 300265864282767360, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'AFTER', 1649396946515, 'OUT_CALL', 1649396960723, 14208, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8106, 1649396961, 1649396961, 1, 25, 1, '1001@test', '测试坐席', 300265864282767360, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'AFTER', 1649396946515, 'OUT_CALL', 1649396960723, 14208, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8107, 1649396964, 1649396964, 1, 25, 1, '1001@test', '测试坐席', 300265864282767360, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'AFTER', 1649396946515, 'OUT_CALL', 1649396960723, 14208, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8108, 1649396966, 1649396966, 1, 25, 1, '1001@test', '测试坐席', 300265864282767360, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'OUT_CALL', 1649396960723, 'TALKING', 1649396966666, 5943, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8109, 1649397018, 1649397018, 1, 25, 1, '1001@test', '测试坐席', 300265864282767360, 1, 1, '172.17.0.2:7200', '119.97.197.5:55154', 'TALKING', 1649396966666, 'AFTER', 1649397018373, 51707, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8226, 1649941007, 1649941007, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'NOT_READY', 1649941002745, 'OUT_CALL', 1649941003779, 1034, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8227, 1649941010, 1649941010, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'OUT_CALL', 1649941003779, 'TALKING', 1649941010731, 6952, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (8228, 1649941062, 1649941062, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'TALKING', 1649941010731, 'AFTER', 1649941062227, 51496, '', 1, '', '', '');
COMMIT;

-- ----------------------------
-- Table structure for cc_agent_state_log_202203
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_state_log_202203`;
CREATE TABLE `cc_agent_state_log_202203` (
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
  `state_time` bigint NOT NULL DEFAULT '0' COMMENT '当前时间',
  `duration` int NOT NULL DEFAULT '0' COMMENT '持续时间',
  `busy_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '忙碌类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  `ext1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `ext3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_agentstate_agentkey` (`agent_key`) USING BTREE,
  KEY `idx_agentstate_cts` (`state_time`) USING BTREE,
  KEY `idx_agentstate_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1691 DEFAULT CHARSET=utf8mb3 COMMENT='坐席状态历史表';

-- ----------------------------
-- Records of cc_agent_state_log_202203
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1, 1646098372, 1646098372, 1, 25, 2, '1002@test', '测试2号', 0, 1, 1, '127.0.0.1', '', 'LOGOUT', 0, 'LOGIN', 0, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1680, 1648642202, 1648642202, 1, 25, 3, 'B001@test', 'B001', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'NOT_READY', 1648642202405, 'READY', 1648642202753, 348, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1681, 1648642203, 1648642203, 1, 25, 3, 'B001@test', 'B001', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'READY', 1648642202753, 'NOT_READY', 1648642203180, 427, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1682, 1648642204, 1648642204, 1, 25, 3, 'B001@test', 'B001', 297100186272923648, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'NOT_READY', 1648642203180, 'OUT_CALL', 1648642204264, 1084, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1683, 1648642204, 1648642204, 1, 25, 3, 'B001@test', 'B001', 297100186272923648, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'NOT_READY', 1648642203180, 'OUT_CALL', 1648642204264, 1084, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1684, 1648642207, 1648642207, 1, 25, 3, 'B001@test', 'B001', 297100186272923648, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'NOT_READY', 1648642203180, 'OUT_CALL', 1648642204264, 1084, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1685, 1648642209, 1648642209, 1, 25, 3, 'B001@test', 'B001', 297100186272923648, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'OUT_CALL', 1648642204264, 'TALKING', 1648642209586, 5322, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1686, 1648642238, 1648642238, 1, 25, 3, 'B001@test', 'B001', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'TALKING', 1648642209586, 'LOGOUT', 1648642238467, 28881, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1687, 1648642239, 1648642239, 1, 25, 2, '1002@test', '测试2号', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:54882', 'TALKING', 1648642196616, 'LOGOUT', 1648642239389, 42773, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1688, 1648642247, 1648642247, 1, 25, 2, '1002@test', '测试2号', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:54882', 'LOGOUT', 1648642239389, 'AFTER', 1648642247580, 8191, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1689, 1648642260, 1648642260, 1, 25, 3, 'B001@test', 'B001', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:54932', 'LOGOUT', 1648642238467, 'AFTER', 1648642260249, 21782, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202203` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1690, 1648643118, 1648643118, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:54681', 'AFTER', 1648642181337, 'LOGOUT', 1648643118417, 937080, '', 1, '', '', '');
COMMIT;

-- ----------------------------
-- Table structure for cc_agent_state_log_202204
-- ----------------------------
DROP TABLE IF EXISTS `cc_agent_state_log_202204`;
CREATE TABLE `cc_agent_state_log_202204` (
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
  `state_time` bigint NOT NULL DEFAULT '0' COMMENT '当前时间',
  `duration` int NOT NULL DEFAULT '0' COMMENT '持续时间',
  `busy_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '忙碌类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  `ext1` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `ext3` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_agentstate_agentkey` (`agent_key`) USING BTREE,
  KEY `idx_agentstate_cts` (`state_time`) USING BTREE,
  KEY `idx_agentstate_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=134 DEFAULT CHARSET=utf8mb3 COMMENT='坐席状态历史表';

-- ----------------------------
-- Records of cc_agent_state_log_202204
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (1, 1649396565, 1649396565, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2', '', 'LOGOUT', 0, 'LOGIN', 0, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (2, 1649396593, 1649396593, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2', '', 'LOGOUT', 0, 'LOGIN', 1649396565574, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (3, 1649396610, 1649396610, 1, 25, 2, '1002@test', '测试2号', 0, 1, 1, '172.17.0.2', '', 'LOGOUT', 0, 'LOGIN', 0, 0, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (128, 1649941002, 1649941002, 1, 25, 1, '1001@test', '测试坐席', 0, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'READY', 1649941002330, 'NOT_READY', 1649941002745, 415, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (129, 1649941003, 1649941003, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'NOT_READY', 1649941002745, 'OUT_CALL', 1649941003779, 1034, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (130, 1649941004, 1649941004, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'NOT_READY', 1649941002745, 'OUT_CALL', 1649941003779, 1034, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (131, 1649941007, 1649941007, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'NOT_READY', 1649941002745, 'OUT_CALL', 1649941003779, 1034, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (132, 1649941010, 1649941010, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'OUT_CALL', 1649941003779, 'TALKING', 1649941010731, 6952, '', 1, '', '', '');
INSERT INTO `cc_agent_state_log_202204` (`id`, `cts`, `uts`, `company_id`, `group_id`, `agent_id`, `agent_key`, `agent_name`, `call_id`, `login_type`, `work_type`, `host`, `remote_address`, `before_state`, `before_time`, `state`, `state_time`, `duration`, `busy_desc`, `status`, `ext1`, `ext2`, `ext3`) VALUES (133, 1649941062, 1649941062, 1, 25, 1, '1001@test', '测试坐席', 302547746248720384, 1, 1, '172.17.0.2:7200', '119.97.197.5:62410', 'TALKING', 1649941010731, 'AFTER', 1649941062227, 51496, '', 1, '', '', '');
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
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通话流程表';

-- ----------------------------
-- Records of cc_call_detail
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_call_detail_202203
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_detail_202203`;
CREATE TABLE `cc_call_detail_202203` (
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
) ENGINE=InnoDB AUTO_INCREMENT=50 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通话流程表';

-- ----------------------------
-- Records of cc_call_detail_202203
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_detail_202203` (`id`, `cts`, `start_time`, `end_time`, `call_id`, `detail_index`, `transfer_type`, `transfer_id`, `reason`, `ext1`, `ext2`, `status`) VALUES (1, 0, 1646500645476, 0, 288117833341075456, 1, 1, 4, '', '', '', 1);
INSERT INTO `cc_call_detail_202203` (`id`, `cts`, `start_time`, `end_time`, `call_id`, `detail_index`, `transfer_type`, `transfer_id`, `reason`, `ext1`, `ext2`, `status`) VALUES (45, 0, 1647482743739, 0, 292237056157941760, 1, 1, 3, '', '', '', 1);
INSERT INTO `cc_call_detail_202203` (`id`, `cts`, `start_time`, `end_time`, `call_id`, `detail_index`, `transfer_type`, `transfer_id`, `reason`, `ext1`, `ext2`, `status`) VALUES (46, 0, 1647482869789, 0, 292237584938041344, 1, 1, 3, '', '', '', 1);
INSERT INTO `cc_call_detail_202203` (`id`, `cts`, `start_time`, `end_time`, `call_id`, `detail_index`, `transfer_type`, `transfer_id`, `reason`, `ext1`, `ext2`, `status`) VALUES (47, 0, 1647488075456, 0, 292259418936967168, 1, 1, 3, '', '', '', 1);
INSERT INTO `cc_call_detail_202203` (`id`, `cts`, `start_time`, `end_time`, `call_id`, `detail_index`, `transfer_type`, `transfer_id`, `reason`, `ext1`, `ext2`, `status`) VALUES (48, 0, 1647488294066, 0, 292260335962816512, 1, 1, 3, '', '', '', 1);
INSERT INTO `cc_call_detail_202203` (`id`, `cts`, `start_time`, `end_time`, `call_id`, `detail_index`, `transfer_type`, `transfer_id`, `reason`, `ext1`, `ext2`, `status`) VALUES (49, 0, 1648652435994, 0, 297143101036691456, 1, 1, 3, '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_call_detail_202204
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_detail_202204`;
CREATE TABLE `cc_call_detail_202204` (
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通话流程表';

-- ----------------------------
-- Records of cc_call_detail_202204
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
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话ID',
  `device_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '设备id',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `device_type` int NOT NULL DEFAULT '1' COMMENT '1:坐席,2:客户,3:外线',
  `cdr_type` int NOT NULL DEFAULT '1' COMMENT '1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插',
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
) ENGINE=InnoDB AUTO_INCREMENT=357 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单明细表';

-- ----------------------------
-- Records of cc_call_device
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (333, 1649396946, 1649396946, 300265803364696064, '1613548369680735', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649396946191, 0, 1649396946361, 0, 0, 1649396946361, 0, 0, 0, '', '', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'UNALLOCATED_NUMBER', '', '404', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (334, 1649396963, 1649397017, 300265864282767360, '4431904561963214', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649396963597, 1649396963641, 1649396965681, 1649396965681, 1649396965681, 1649397017081, 51400, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (335, 1649396960, 1649397017, 300265864282767360, '4234987253808394', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649396960715, 1649396961141, 1649396963542, 1649396963542, 1649396965681, 1649397017181, 53639, 0, 1649396963542, '', '202204/20220408/13/300265864282767360_4234987253808394.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (336, 1649829525, 1649829566, 302080173807763456, '4608447109171071', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649829525814, 1649829526701, 1649829530201, 1649829530201, 1649829532281, 1649829566041, 35840, 0, 1649829530201, '', '202204/20220413/13/302080173807763456_4608447109171071.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (337, 1649829530, 1649829566, 302080173807763456, '4747214879063320', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649829530225, 1649829530261, 1649829532281, 1649829532281, 1649829532281, 1649829566281, 34000, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (338, 1649849977, 1649850031, 302165940118749184, '5298950026833318', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649849977667, 1649849977681, 1649849979721, 1649849979721, 1649849979721, 1649850031141, 51420, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (339, 1649849974, 1649850031, 302165940118749184, '1753392843620835', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649849974096, 1649849974681, 1649849977641, 1649849977641, 1649849979721, 1649850031261, 53620, 0, 1649849977641, '', '202204/20220413/19/302165940118749184_1753392843620835.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (340, 1649852159, 1649852213, 302175093428060160, '2436741286601117', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649852159530, 1649852159583, 1649852161621, 1649852161621, 1649852161621, 1649852213023, 51402, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (341, 1649852156, 1649852213, 302175093428060160, '9806511421904348', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649852156415, 1649852156861, 1649852159442, 1649852159442, 1649852161621, 1649852213161, 53719, 1649852159442, 53719, '', '202204/20220413/20/302175093428060160_9806511421904348.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (342, 1649852721, 1649852774, 302177452824723456, '0690241832547728', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649852721394, 1649852721421, 1649852723441, 1649852723441, 1649852723441, 1649852774861, 51420, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (343, 1649852718, 1649852774, 302177452824723456, '0136800139478210', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649852718939, 1649852719402, 1649852721361, 1649852721361, 1649852723441, 1649852774942, 53581, 1649852721361, 53581, '', '202204/20220413/20/302177452824723456_0136800139478210.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (344, 1649903578, 1649903632, 302390759372357632, '3499669926323612', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649903578663, 1649903578681, 1649903580721, 1649903580721, 1649903580721, 1649903632121, 51400, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (345, 1649903575, 1649903632, 302390759372357632, '1395788937919720', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649903575183, 1649903576001, 1649903578641, 1649903578641, 1649903580721, 1649903632221, 53580, 1649903578641, 53580, '', '202204/20220414/10/302390759372357632_1395788937919720.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (346, 1649927423, 1649927476, 302490766293336064, '7097105325417900', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649927423178, 1649927423202, 1649927425241, 1649927425241, 1649927425241, 1649927476642, 51401, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (347, 1649927418, 1649927476, 302490766293336064, '6195974568233971', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649927418691, 1649927419701, 1649927423101, 1649927423101, 1649927425241, 1649927476761, 53660, 1649927423101, 53660, '', '202204/20220414/17/302490766293336064_6195974568233971.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (348, 1649928084, 1649928138, 302493544201846784, '8529003954665275', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649928084659, 1649928084682, 1649928086721, 1649928086721, 1649928086721, 1649928138121, 51400, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (349, 1649928080, 1649928138, 302493544201846784, '7630850452854589', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649928080996, 1649928081321, 1649928084621, 1649928084621, 1649928086721, 1649928138221, 53600, 1649928084621, 53600, '', '202204/20220414/17/302493544201846784_7630850452854589.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (350, 1649928383, 1649928437, 302494809338150912, '8289412076198476', '1002@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649928383902, 1649928384521, 1649928386544, 1649928386544, 1649928386544, 1649928437961, 51417, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (351, 1649928382, 1649928438, 302494809338150912, '7498254564246402', '1002@test', '测试2号', 1, 2, '1002', '870002', '1002', '', '', 1649928382628, 1649928382642, 1649928383881, 1649928383881, 1649928386544, 1649928438102, 54221, 1649928383881, 54221, '', '202204/20220414/17/302494809338150912_7498254564246402.wav', '', '', 'sofia/external/870002@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (352, 1649928892, 1649928907, 302496946969378816, '2189368012095899', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649928892279, 1649928892842, 1649928895361, 1649928895361, 1649928897462, 1649928907261, 11900, 1649928895361, 11900, '', '202204/20220414/17/302496946969378816_2189368012095899.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (353, 1649928895, 1649928907, 302496946969378816, '6680262582483771', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649928895389, 1649928895421, 1649928897462, 1649928897462, 1649928897462, 1649928907361, 9899, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (354, 1649940976, 1649940976, 302547631253487616, '6578837626125122', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649940976354, 0, 1649940976501, 0, 0, 1649940976501, 0, 0, 0, '', '', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'UNALLOCATED_NUMBER', '', '404', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (355, 1649941007, 1649941060, 302547746248720384, '8055609242886190', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649941007465, 1649941007501, 1649941009542, 1649941009542, 1649941009542, 1649941060961, 51419, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (356, 1649941003, 1649941061, 302547746248720384, '5094632521749595', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649941003771, 1649941004102, 1649941007381, 1649941007381, 1649941009542, 1649941061041, 53660, 1649941007381, 53660, '', '202204/20220414/20/302547746248720384_5094632521749595.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_call_device_202203
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_device_202203`;
CREATE TABLE `cc_call_device_202203` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话ID',
  `device_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '设备id',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `device_type` int NOT NULL DEFAULT '1' COMMENT '1:坐席,2:客户,3:外线',
  `cdr_type` int NOT NULL DEFAULT '1' COMMENT '1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插',
  `caller` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫',
  `called` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫',
  `display` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '显号',
  `called_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `caller_location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫归属地',
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
) ENGINE=InnoDB AUTO_INCREMENT=327 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单明细表';

-- ----------------------------
-- Records of cc_call_device_202203
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_device_202203` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (1, 1646306939, 1646306958, 287305373939924992, '8147068071714765', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1646306939023, 0, 1646306940499, 1646306940499, 1646306942719, 1646306958479, 17980, 0, 1646306940499, '', '/app/clpms/record/20220303/287305373939924992_8147068071714765_1646306940.wav', '', '', 'sofia/external/870001@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202203` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (321, 1648642145, 1648642181, 297099919020261376, '5051905722163107', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1648642145800, 1648642145821, 1648642147861, 1648642147861, 1648642147861, 1648642181344, 33483, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202203` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (322, 1648642193, 1648642247, 297100135463124992, '3149474861538795', '1002@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1648642193423, 1648642193681, 1648642195722, 1648642195722, 1648642195722, 1648642247141, 51419, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202203` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (323, 1648642192, 1648642247, 297100135463124992, '9756903861695654', '1002@test', '测试2号', 1, 2, '1002', '870002', '1002', '', '', 1648642192148, 1648642192161, 1648642193401, 1648642193401, 1648642195722, 1648642247222, 53821, 0, 1648642193401, '', '202203/20220330/20/297100135463124992_9756903861695654.wav', '', '', 'sofia/external/870002@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202203` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (324, 1648642205, 1648642259, 297100186272923648, '5387129939546181', 'B001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1648642205734, 1648642206401, 1648642208441, 1648642208441, 1648642208441, 1648642259841, 51400, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202203` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (325, 1648642204, 1648642259, 297100186272923648, '1277601615658801', 'B001@test', 'B001', 1, 2, 'B001', '870003', 'B001', '', '', 1648642204262, 1648642204281, 1648642205501, 1648642205501, 1648642208441, 1648642259901, 54400, 0, 1648642205501, '', '202203/20220330/20/297100186272923648_1277601615658801.wav', '', '', 'sofia/external/870003@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202203` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (326, 1648652435, 1648652467, 297143101036691456, '21130e33-be1f-45b7-a126-c5add19110f1', '', '', 2, 1, '186****3191', '01011515901', '', '', '', 1648652435940, 0, 1648652435961, 1648652435961, 0, 1648652467201, 31240, 0, 0, 'udp', '', '', '', 'sofia/internal/186****3191@111.204.59.230', 'NORMAL_CLEARING', '', '200', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_call_device_202204
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_device_202204`;
CREATE TABLE `cc_call_device_202204` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话ID',
  `device_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '设备id',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `device_type` int NOT NULL DEFAULT '1' COMMENT '1:坐席,2:客户,3:外线',
  `cdr_type` int NOT NULL DEFAULT '1' COMMENT '1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插',
  `caller` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫',
  `called` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫',
  `display` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '显号',
  `called_location` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `caller_location` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫归属地',
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
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单明细表';

-- ----------------------------
-- Records of cc_call_device_202204
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (1, 1649396946, 1649396946, 300265803364696064, '1613548369680735', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649396946191, 0, 1649396946361, 0, 0, 1649396946361, 0, 0, 0, '', '', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'UNALLOCATED_NUMBER', '', '404', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (2, 1649396963, 1649397017, 300265864282767360, '4431904561963214', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649396963597, 1649396963641, 1649396965681, 1649396965681, 1649396965681, 1649397017081, 51400, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (3, 1649396960, 1649397017, 300265864282767360, '4234987253808394', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649396960715, 1649396961141, 1649396963542, 1649396963542, 1649396965681, 1649397017181, 53639, 0, 1649396963542, '', '202204/20220408/13/300265864282767360_4234987253808394.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (4, 1649829525, 1649829566, 302080173807763456, '4608447109171071', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649829525814, 1649829526701, 1649829530201, 1649829530201, 1649829532281, 1649829566041, 35840, 0, 1649829530201, '', '202204/20220413/13/302080173807763456_4608447109171071.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (5, 1649829530, 1649829566, 302080173807763456, '4747214879063320', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649829530225, 1649829530261, 1649829532281, 1649829532281, 1649829532281, 1649829566281, 34000, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (6, 1649849977, 1649850031, 302165940118749184, '5298950026833318', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649849977667, 1649849977681, 1649849979721, 1649849979721, 1649849979721, 1649850031141, 51420, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (7, 1649849974, 1649850031, 302165940118749184, '1753392843620835', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649849974096, 1649849974681, 1649849977641, 1649849977641, 1649849979721, 1649850031261, 53620, 0, 1649849977641, '', '202204/20220413/19/302165940118749184_1753392843620835.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (8, 1649852159, 1649852213, 302175093428060160, '2436741286601117', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649852159530, 1649852159583, 1649852161621, 1649852161621, 1649852161621, 1649852213023, 51402, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (9, 1649852156, 1649852213, 302175093428060160, '9806511421904348', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649852156415, 1649852156861, 1649852159442, 1649852159442, 1649852161621, 1649852213161, 53719, 1649852159442, 53719, '', '202204/20220413/20/302175093428060160_9806511421904348.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (10, 1649852721, 1649852774, 302177452824723456, '0690241832547728', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649852721394, 1649852721421, 1649852723441, 1649852723441, 1649852723441, 1649852774861, 51420, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (11, 1649852718, 1649852774, 302177452824723456, '0136800139478210', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649852718939, 1649852719402, 1649852721361, 1649852721361, 1649852723441, 1649852774942, 53581, 1649852721361, 53581, '', '202204/20220413/20/302177452824723456_0136800139478210.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (12, 1649903578, 1649903632, 302390759372357632, '3499669926323612', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649903578663, 1649903578681, 1649903580721, 1649903580721, 1649903580721, 1649903632121, 51400, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (13, 1649903575, 1649903632, 302390759372357632, '1395788937919720', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649903575183, 1649903576001, 1649903578641, 1649903578641, 1649903580721, 1649903632221, 53580, 1649903578641, 53580, '', '202204/20220414/10/302390759372357632_1395788937919720.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (14, 1649927423, 1649927476, 302490766293336064, '7097105325417900', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649927423178, 1649927423202, 1649927425241, 1649927425241, 1649927425241, 1649927476642, 51401, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (15, 1649927418, 1649927476, 302490766293336064, '6195974568233971', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649927418691, 1649927419701, 1649927423101, 1649927423101, 1649927425241, 1649927476761, 53660, 1649927423101, 53660, '', '202204/20220414/17/302490766293336064_6195974568233971.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (16, 1649928084, 1649928138, 302493544201846784, '8529003954665275', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649928084659, 1649928084682, 1649928086721, 1649928086721, 1649928086721, 1649928138121, 51400, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (17, 1649928080, 1649928138, 302493544201846784, '7630850452854589', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649928080996, 1649928081321, 1649928084621, 1649928084621, 1649928086721, 1649928138221, 53600, 1649928084621, 53600, '', '202204/20220414/17/302493544201846784_7630850452854589.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (18, 1649928383, 1649928437, 302494809338150912, '8289412076198476', '1002@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649928383902, 1649928384521, 1649928386544, 1649928386544, 1649928386544, 1649928437961, 51417, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (19, 1649928382, 1649928438, 302494809338150912, '7498254564246402', '1002@test', '测试2号', 1, 2, '1002', '870002', '1002', '', '', 1649928382628, 1649928382642, 1649928383881, 1649928383881, 1649928386544, 1649928438102, 54221, 1649928383881, 54221, '', '202204/20220414/17/302494809338150912_7498254564246402.wav', '', '', 'sofia/external/870002@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (20, 1649928892, 1649928907, 302496946969378816, '2189368012095899', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649928892279, 1649928892842, 1649928895361, 1649928895361, 1649928897462, 1649928907261, 11900, 1649928895361, 11900, '', '202204/20220414/17/302496946969378816_2189368012095899.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (21, 1649928895, 1649928907, 302496946969378816, '6680262582483771', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649928895389, 1649928895421, 1649928897462, 1649928897462, 1649928897462, 1649928907361, 9899, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (22, 1649940976, 1649940976, 302547631253487616, '6578837626125122', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649940976354, 0, 1649940976501, 0, 0, 1649940976501, 0, 0, 0, '', '', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'UNALLOCATED_NUMBER', '', '404', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (23, 1649941007, 1649941060, 302547746248720384, '8055609242886190', '1001@test', '', 2, 2, '01088889999', '133****1234', '01088889999', '', '', 1649941007465, 1649941007501, 1649941009542, 1649941009542, 1649941009542, 1649941060961, 51419, 0, 0, '', '', '', '', 'sofia/external/133****1234@172.17.0.2:32460', 'NORMAL_CLEARING', '', '200', '', '', 1);
INSERT INTO `cc_call_device_202204` (`id`, `cts`, `uts`, `call_id`, `device_id`, `agent_key`, `agent_name`, `device_type`, `cdr_type`, `caller`, `called`, `display`, `called_location`, `caller_location`, `call_time`, `ring_start_time`, `ring_end_time`, `answer_time`, `bridge_time`, `end_time`, `talk_time`, `record_start_time`, `record_time`, `sip_protocol`, `record`, `record2`, `record3`, `channel_name`, `hangup_cause`, `ring_cause`, `sip_status`, `ext1`, `ext2`, `status`) VALUES (24, 1649941003, 1649941061, 302547746248720384, '5094632521749595', '1001@test', '测试坐席', 1, 2, '1001', '870001', '1001', '', '', 1649941003771, 1649941004102, 1649941007381, 1649941007381, 1649941009542, 1649941061041, 53660, 1649941007381, 53660, '', '202204/20220414/20/302547746248720384_5094632521749595.wav', '', '', 'sofia/internal/870001@172.17.0.2:6685', 'NORMAL_CLEARING', '', '', '', '', 1);
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
-- Table structure for cc_call_dtmf_202203
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_dtmf_202203`;
CREATE TABLE `cc_call_dtmf_202203` (
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
-- Records of cc_call_dtmf_202203
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_call_dtmf_202204
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_dtmf_202204`;
CREATE TABLE `cc_call_dtmf_202204` (
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
-- Records of cc_call_dtmf_202204
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
  `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '坐席名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=207 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单表';

-- ----------------------------
-- Records of cc_call_log
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (194, 1649396946, 1649396946, 1, 300265803364696064, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649396946191, 0, 1649396946537, 'OUTBOUNT_CALL', 'OUTBOUND', 1, 0, 0, 1, 0, 0, '', '172.17.0.2:7200', '', '', '', '', 1, 0, 0, 0, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
    INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (195, 1649396960715, 1649397017, 1, 300265864282767360, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649396960715, 1649396965781, 1649397017081, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220408/13/300265864282767360_4234987253808394.wav', '', '', 1, 0, 1649396963542, 51300, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (196, 1649829525814, 1649829566, 1, 302080173807763456, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649829525814, 1649829532361, 1649829566041, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/13/302080173807763456_4608447109171071.wav', '', '', 1, 0, 1649829530201, 33680, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (197, 1649849974096, 1649850031, 1, 302165940118749184, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649849974096, 1649849979761, 1649850031141, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/19/302165940118749184_1753392843620835.wav', '', '', 1, 0, 1649849977641, 51380, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (198, 1649852156415, 1649852213, 1, 302175093428060160, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649852156415, 1649852161701, 1649852213023, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/20/302175093428060160_9806511421904348.wav', '', '', 1, 0, 53719, 51322, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (199, 1649852718939, 1649852774, 1, 302177452824723456, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649852718939, 1649852723521, 1649852774861, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/20/302177452824723456_0136800139478210.wav', '', '', 1, 0, 53581, 51340, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (200, 1649903575183, 1649903632, 1, 302390759372357632, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649903575183, 1649903580782, 1649903632121, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/10/302390759372357632_1395788937919720.wav', '', '', 1, 0, 53580, 51339, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (201, 1649927418691, 0, 1, 302490766293336064, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649927418691, 1649927425302, 0, '', 'OUTBOUND', 0, 0, 2, 1, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '', '', '', 1, 0, 0, 0, 0, 0, 0, '', '', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (202, 1649928080996, 1649928138, 1, 302493544201846784, '1001', '870001', '01088889999', '133****1234', '广西-防城港', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649928080996, 1649928086801, 1649928138121, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/17/302493544201846784_7630850452854589.wav', '', '', 1, 0, 53600, 51320, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (203, 1649928382628, 1649928437, 1, 302494809338150912, '1002', '870002', '01088889999', '133****1234', '', '1002@test', '测试2号', 25, 1, 0, 0, 0, 1649928382628, 1649928386601, 1649928437961, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/17/302494809338150912_7498254564246402.wav', '', '', 1, 0, 54221, 51360, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (204, 1649928892279, 1649928907, 1, 302496946969378816, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649928892279, 1649928897502, 1649928907261, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/17/302496946969378816_2189368012095899.wav', '', '', 1, 0, 11900, 9759, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (205, 1649940976, 1649940976, 1, 302547631253487616, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649940976354, 0, 1649940976755, 'OUTBOUNT_CALL', 'OUTBOUND', 1, 0, 0, 1, 0, 0, '', '172.17.0.2:7200', '', '', '', '', 1, 0, 0, 0, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (206, 1649941003771, 1649941060, 1, 302547746248720384, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649941003771, 1649941009601, 1649941060961, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/20/302547746248720384_5094632521749595.wav', '', '', 1, 0, 53660, 51360, 0, 0, 0, '', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_call_log_202203
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_log_202203`;
CREATE TABLE `cc_call_log_202203` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '落单时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '话单id',
  `caller_display` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫显号',
  `caller` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫',
  `called_display` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫显号',
  `called` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫',
  `number_location` varchar(100) NOT NULL DEFAULT '',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT '' COMMENT '坐席名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=175 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单表';

-- ----------------------------
-- Records of cc_call_log_202203
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_log_202203` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (1, 1646306939, 1646306958, 1, 287305373939924992, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1646306939023, 1646306944079, 1646306958479, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 1, 0, '172.17.0.2:7400', 'localhost:7200', '', '/app/clpms/record/20220303/287305373939924992_8147068071714765_1646306940.wav', '', '', 1, 0, 1646306940499, 14400, 0, 0, 0, '_202203', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202203` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (2, 1646307018, 1646307202, 1, 287305709006094336, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1646307018909, 1646307024699, 1646307202519, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', 'localhost:7200', '', '/app/clpms/record/20220303/287305709006094336_2160281383806376_1646307020.wav', '', '', 1, 0, 1646307020200, 177820, 0, 0, 0, '_202203', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202203` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (173, 1648642204, 1648642259, 1, 297100186272923648, 'B001', '870003', '01088889999', '133****1234', '', 'B001@test', 'B001', 25, 1, 0, 0, 0, 1648642204262, 1648642208521, 1648642259841, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202203/20220330/20/297100186272923648_1277601615658801.wav', '', '', 1, 0, 1648642205501, 51320, 0, 0, 0, '_202203', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202203` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (174, 1648652435, 1648652467, 1, 297143101036691456, '01011515901', '', '', '', '', '', NULL, 0, 1, 0, 0, 0, 1648652435940, 0, 1648652467201, 'INBOUND_CALL', 'INBOUND', 0, 0, 1, 1, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '', '', '', 1, 0, 0, 0, 0, 0, 0, '_202203', '{}', '', '', '', '', '', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_call_log_202204
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_log_202204`;
CREATE TABLE `cc_call_log_202204` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '落单时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '话单id',
  `caller_display` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫显号',
  `caller` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '主叫',
  `called_display` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫显号',
  `called` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '被叫',
  `number_location` varchar(100) NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `agent_key` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '坐席',
  `agent_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '坐席名称',
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单表';

-- ----------------------------
-- Records of cc_call_log_202204
-- ----------------------------
BEGIN;
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (1, 1649396946, 1649396946, 1, 300265803364696064, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649396946191, 0, 1649396946537, 'OUTBOUNT_CALL', 'OUTBOUND', 1, 0, 0, 1, 0, 0, '', '172.17.0.2:7200', '', '', '', '', 1, 0, 0, 0, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (2, 1649396960, 1649397017, 1, 300265864282767360, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649396960715, 1649396965781, 1649397017081, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220408/13/300265864282767360_4234987253808394.wav', '', '', 1, 0, 1649396963542, 51300, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (3, 1649829525, 1649829566, 1, 302080173807763456, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649829525814, 1649829532361, 1649829566041, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/13/302080173807763456_4608447109171071.wav', '', '', 1, 0, 1649829530201, 33680, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (4, 1649849974, 1649850031, 1, 302165940118749184, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649849974096, 1649849979761, 1649850031141, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/19/302165940118749184_1753392843620835.wav', '', '', 1, 0, 1649849977641, 51380, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (5, 1649852156, 1649852213, 1, 302175093428060160, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649852156415, 1649852161701, 1649852213023, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/20/302175093428060160_9806511421904348.wav', '', '', 1, 0, 53719, 51322, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (6, 1649852718, 1649852774, 1, 302177452824723456, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649852718939, 1649852723521, 1649852774861, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220413/20/302177452824723456_0136800139478210.wav', '', '', 1, 0, 53581, 51340, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (7, 1649903575, 1649903632, 1, 302390759372357632, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649903575183, 1649903580782, 1649903632121, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/10/302390759372357632_1395788937919720.wav', '', '', 1, 0, 53580, 51339, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (8, 1649928080, 1649928138, 1, 302493544201846784, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649928080996, 1649928086801, 1649928138121, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/17/302493544201846784_7630850452854589.wav', '', '', 1, 0, 53600, 51320, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (9, 1649928382, 1649928437, 1, 302494809338150912, '1002', '870002', '01088889999', '133****1234', '', '1002@test', '测试2号', 25, 1, 0, 0, 0, 1649928382628, 1649928386601, 1649928437961, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/17/302494809338150912_7498254564246402.wav', '', '', 1, 0, 54221, 51360, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (10, 1649928892, 1649928907, 1, 302496946969378816, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649928892279, 1649928897502, 1649928907261, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 1, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/17/302496946969378816_2189368012095899.wav', '', '', 1, 0, 11900, 9759, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (11, 1649940976, 1649940976, 1, 302547631253487616, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649940976354, 0, 1649940976755, 'OUTBOUNT_CALL', 'OUTBOUND', 1, 0, 0, 1, 0, 0, '', '172.17.0.2:7200', '', '', '', '', 1, 0, 0, 0, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
INSERT INTO `cc_call_log_202204` (`id`, `cts`, `uts`, `company_id`, `call_id`, `caller_display`, `caller`, `called_display`, `called`, `number_location`, `agent_key`, `agent_name`, `group_id`, `login_type`, `task_id`, `ivr_id`, `bot_id`, `call_time`, `answer_time`, `end_time`, `call_type`, `direction`, `answer_flag`, `wait_time`, `answer_count`, `hangup_dir`, `sdk_hangup`, `hangup_code`, `media_host`, `cti_host`, `client_host`, `record`, `record2`, `record3`, `record_type`, `record_start_time`, `record_time`, `talk_time`, `frist_queue_time`, `queue_start_time`, `queue_end_time`, `month_time`, `follow_data`, `uuid1`, `uuid2`, `ext1`, `ext2`, `ext3`, `status`) VALUES (12, 1649941003, 1649941060, 1, 302547746248720384, '1001', '870001', '01088889999', '133****1234', '', '1001@test', '测试坐席', 25, 1, 0, 0, 0, 1649941003771, 1649941009601, 1649941060961, 'OUTBOUNT_CALL', 'OUTBOUND', 0, 0, 2, 2, 0, 0, '172.17.0.2:7400', '172.17.0.2:7200', '', '202204/20220414/20/302547746248720384_5094632521749595.wav', '', '', 1, 0, 53660, 51360, 0, 0, 0, '_202204', '{\"autoAnswer\":\"true\"}', '123456', '', '', '', '', 1);
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
  `secret_type` int NOT NULL DEFAULT '1' COMMENT '坐席密码等级',
  `secret_key` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '验证秘钥',
  `ivr_limit` int NOT NULL DEFAULT '50' COMMENT 'IVR通道数',
  `agent_limit` int NOT NULL DEFAULT '50' COMMENT '开通坐席',
  `group_limit` int NOT NULL DEFAULT '10' COMMENT '开通技能组',
  `group_agent_limit` int NOT NULL DEFAULT '1000' COMMENT '单技能组中坐席上限',
  `blacklist` bigint NOT NULL DEFAULT '0' COMMENT '黑名单',
  `notify_url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '话单回调通知',
  `ext1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext4` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext5` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态(0:禁用企业,1:免费企业;2:试用企业,3:付费企业)',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `company_unqidx_name` (`name`) USING BTREE,
  UNIQUE KEY `company_unqidx_code` (`company_code`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业信息表';

-- ----------------------------
-- Records of cc_company
-- ----------------------------
BEGIN;
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `blacklist`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (1, 1604503580, 1604503580, 'test企业', '', 0, 'test', 0, 'test', '18612983191', 1, 0, 1, 1, 1, '0765im6VjutX46iZ', 50, 5000, 20, 1000, 0, 'https://ccdemo.acloudcc.com/push/push/forcePush/pushTest', '', '', '', '', '', 1);
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `blacklist`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (2, 0, 1619796030, '20210430-delLwqXnW', '', 1, 'test2', 0, '曹亮', '', 0, 0, 0, 1, 1, 'djJHDuy34r87du34', 50, 50, 10, 1000, 0, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `blacklist`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (15, 0, 1619797353, 'aaaa-delcSJeGY', '', 0, 'acscwe-delIqjPqn', 0, '', '', 0, 0, 0, 1, 1, 'yfJWvM9jfxAzFUAQ', 50, 50, 10, 1000, 0, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` (`id`, `cts`, `uts`, `name`, `id_path`, `pid`, `company_code`, `gmt`, `contact`, `phone`, `balance`, `bill_type`, `pay_type`, `hidden_customer`, `secret_type`, `secret_key`, `ivr_limit`, `agent_limit`, `group_limit`, `group_agent_limit`, `blacklist`, `notify_url`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (16, 0, 0, 'aaaa', '', 0, 'acscwe', 0, '', '', 0, 0, 0, 1, 1, 'yfJWvM9jfxAzFUAQ', 50, 50, 10, 1000, 0, '', '', '', '', '', '', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组表';

-- ----------------------------
-- Records of cc_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_group` (`id`, `cts`, `uts`, `company_id`, `name`, `after_interval`, `caller_display_id`, `called_display_id`, `record_type`, `level_value`, `tts_engine`, `play_content`, `evaluate`, `queue_play`, `transfer_play`, `group_type`, `notify_position`, `notify_rate`, `notify_content`, `call_memory`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (1, 1604503580, 1638965051, 1, '测试技能组-delDwLywI', 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 0, 1, 10, '0', 1, '', '', '', '', '', 0);
INSERT INTO `cc_group` (`id`, `cts`, `uts`, `company_id`, `name`, `after_interval`, `caller_display_id`, `called_display_id`, `record_type`, `level_value`, `tts_engine`, `play_content`, `evaluate`, `queue_play`, `transfer_play`, `group_type`, `notify_position`, `notify_rate`, `notify_content`, `call_memory`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (2, 1621556151, 1638965054, 1, '测试技能组1-dellqdGSA', 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 1, 1, 10, '0', 1, '', '', '', '', '', 0);
INSERT INTO `cc_group` (`id`, `cts`, `uts`, `company_id`, `name`, `after_interval`, `caller_display_id`, `called_display_id`, `record_type`, `level_value`, `tts_engine`, `play_content`, `evaluate`, `queue_play`, `transfer_play`, `group_type`, `notify_position`, `notify_rate`, `notify_content`, `call_memory`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (25, 1639063337, 0, 1, '测试技能组', 5, 0, 0, 1, 1, 0, '', 0, 0, 0, 1, 1, 10, '0', 1, '', '', '', '', '', 1);
INSERT INTO `cc_group` (`id`, `cts`, `uts`, `company_id`, `name`, `after_interval`, `caller_display_id`, `called_display_id`, `record_type`, `level_value`, `tts_engine`, `play_content`, `evaluate`, `queue_play`, `transfer_play`, `group_type`, `notify_position`, `notify_rate`, `notify_content`, `call_memory`, `ext1`, `ext2`, `ext3`, `ext4`, `ext5`, `status`) VALUES (26, 1639063973, 0, 1, '测试技能组1', 5, 0, 0, 1, 1, 0, '', 0, 0, 0, 1, 1, 10, '0', 1, '', '', '', '', '', 1);
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
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (1, 1, 1, 1, '测试ivr流程', '1000', ' ', '111', '111', '{}', '', 1, 1);
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (2, 2, 2, 1, ' 1001', '1001', ' ', '111', '111', '{}', ' ', 1, 1);
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (3, 3, 3, 1, '9999', '9999', ' ', '1111', '11', '{}', ' ', 1, 1);
INSERT INTO `cc_ivr_workflow` (`id`, `cts`, `uts`, `company_id`, `name`, `oss_id`, `init_params`, `create_user`, `verify_user`, `content`, `voice_item`, `type`, `status`) VALUES (4, 4, 4, 1, '1004', '1004', ' ', '111', '111', '{}', '1', 1, 1);
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
-- Table structure for cc_push_fail_log
-- ----------------------------
DROP TABLE IF EXISTS `cc_push_fail_log`;
CREATE TABLE `cc_push_fail_log` (
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
) ENGINE=InnoDB AUTO_INCREMENT=527 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单推送记录表';



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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字冠路由表';

-- ----------------------------
-- Records of cc_route_call
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (1, 1, 1, 1, 1, '87', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (2, 1, 1, 1, 2, '133', 9, 4, 0, '1 ', 1, '1', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (4, 1, 1, 1, 1, '18899998889', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (7, 1, 1, 1, 4, '18899998887', 9, 4, 0, '1', 0, '1', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (8, 1, 1, 1, 5, '144', 11, 4, 0, '1', 0, '', 1);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (9, 1621915277, 1621923862, 1, 2, '18988889999', 32, 2, 0, '1', 0, '1', 0);
INSERT INTO `cc_route_call` (`id`, `cts`, `uts`, `company_id`, `route_group_id`, `route_num`, `num_max`, `num_min`, `caller_change`, `caller_change_num`, `called_change`, `called_change_num`, `status`) VALUES (10, 1621923239, 0, 1, 3, '870001', 9, 4, 0, '1', 1, '1', 1);
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
INSERT INTO `cc_route_group` (`id`, `cts`, `uts`, `route_group`, `status`) VALUES (4, 1, 1, '18899998887', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组技能表';

-- ----------------------------
-- Records of cc_skill_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (1, 1639063337, 0, 1, 100, 1, 25, 1, 0, 0, 1, 100, 1);
INSERT INTO `cc_skill_group` (`id`, `cts`, `uts`, `company_id`, `level_value`, `skill_id`, `group_id`, `rank_type`, `rank_value_start`, `rank_value`, `match_type`, `share_value`, `status`) VALUES (2, 1639063973, 0, 1, 100, 1, 26, 1, 0, 0, 1, 100, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_stat_hour_agent_work
-- ----------------------------
DROP TABLE IF EXISTS `cc_stat_hour_agent_work`;
CREATE TABLE `cc_stat_hour_agent_work` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT 'cts',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `agent_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席编号',
  `agent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `state_time` bigint NOT NULL DEFAULT '0' COMMENT '统计时间',
  `login_time` bigint NOT NULL DEFAULT '0' COMMENT '登录总时长',
  `ready_time` bigint NOT NULL DEFAULT '0' COMMENT '空闲总时长',
  `not_ready_time` bigint NOT NULL DEFAULT '0' COMMENT '忙碌总时长',
  `busy_time` bigint NOT NULL DEFAULT '0' COMMENT '自定义忙碌总时间',
  `after_time` bigint NOT NULL DEFAULT '0' COMMENT '话后总时长',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话总时长',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_idx_state_agenthourwork_agent` (`state_time`,`agent_key`) USING BTREE,
  FULLTEXT KEY `idx_state_agenthourwork_agent` (`agent_key`)
) ENGINE=InnoDB AUTO_INCREMENT=256 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cc_stat_hour_agent_work
-- ----------------------------
BEGIN;
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (1, 1638288000, 1, '1001@test', '测试坐席', 1638284400, 3398352, 945226, 1625934, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (2, 1638324000, 1, '1001@test', '测试坐席', 1638320400, 8797659, 3094, 1132809, 0, 297605, 200710, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (3, 1638327600, 1, '1001@test', '测试坐席', 1638324000, 825899, 5700, 49064, 0, 810627, 69116, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (4, 1638334800, 1, '1001@test', '测试坐席', 1638331200, -1638320691838, 5539, 24628, 0, 1702903, 108554, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (7, 1638892800, 1, '1001@test', '测试坐席', 1638889200, -1638884401680, 85043, 30388, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (8, 1638896400, 1, '1001@test', '测试坐席', 1638892800, 1478026, 736, 6384, 0, 102394, 31633, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (9, 1638928800, 1, '1001@test', '测试坐席', 1638925200, 2308814, 679, 168445, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (10, 1638932400, 1, '1001@test', '测试坐席', 1638928800, 6170386, 382, 6702, 0, 982338, 31674, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (11, 1638936000, 1, '1001@test', '测试坐席', 1638932400, 3598837, 413, 3882, 0, 4542175, 31206, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (12, 1638939600, 1, '1001@test', '测试坐席', 1638936000, 157760, 0, 0, 0, 1301250, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (13, 1638943200, 1, '1001@test', '测试坐席', 1638939600, 3203016, 1182, 17664, 0, 532605, 80323, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (14, 1639292400, 1, '1001@test', '测试坐席', 1639288800, 7044281, 155236, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (15, 1639296000, 1, '1001@test', '测试坐席', 1639292400, 1039144, 1885, 471985, 0, 162669, 33543, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (16, 1639551600, 1, '1001@test', '测试坐席', 1639548000, -1639540829509, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (17, 1639555200, 1, '1001@test', '测试坐席', 1639551600, -3279093114575, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (18, 1639558800, 1, '1001@test', '测试坐席', 1639555200, 0, 2797087, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (19, 1639562400, 1, '1001@test', '测试坐席', 1639558800, 0, 1083128, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (20, 1639573200, 1, '1001@test', '测试坐席', 1639569600, 0, 37433, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (21, 1639576800, 1, '1001@test', '测试坐席', 1639573200, -8197855314252, 1914, 6481, 0, 378772, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (22, 1639580400, 1, '1001@test', '测试坐席', 1639576800, 3236447, 20114, 146254, 0, 952895, 249563, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (23, 1639584000, 1, '1001@test', '测试坐席', 1639580400, -1639566498266, 6523, 60015, 0, 676436, 139701, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (24, 1639587600, 1, '1001@test', '测试坐席', 1639584000, 1743315, 984, 14622, 0, 80402, 62747, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (25, 1641916800, 1, '1001@test', '测试坐席', 1641913200, -3283819092039, 1170, 305749, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (26, 1641920400, 1, '1001@test', '测试坐席', 1641916800, 2295384, 9452, 381926, 0, 445848, 1077227, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (27, 1641956400, 1, '1001@test', '测试坐席', 1641952800, 8320544, 2198, 732153, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (28, 1641960000, 1, '1001@test', '测试坐席', 1641956400, 407833, 0, 638145, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (29, 1642662000, 1, '1001@test', '测试坐席', 1642658400, 2937248, 1239, 9030, 0, 12542, 60156, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (30, 1642960800, 1, '1001@test', '测试坐席', 1642957200, 1441197, 670, 203283, 0, 343977, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (31, 1643040000, 1, '1001@test', '测试坐席', 1643036400, 3154101, 30490, 231869, 0, 2062241, 107249, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (32, 1643040000, 1, '1002@test', '测试2号', 1643036400, 2914117, 34539, 456664, 0, 2668194, 145460, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (33, 1643360400, 1, '1002@test', '测试2号', 1643356800, 3216945, 1329, 65699, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (34, 1643364000, 1, '1002@test', '测试2号', 1643360400, 2677367, 911, 61388, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (35, 1643367600, 1, '1002@test', '测试2号', 1643364000, 2450012, 1750, 179609, 0, 17536, 31045, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (36, 1644246000, 1, '1001@test', '测试坐席', 1644242400, -21375137609386, 1147, 37704, 0, 227118, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (37, 1644249600, 1, '1001@test', '测试坐席', 1644246000, -11509707318464, 67412, 15763, 0, 1300041, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (38, 1644253200, 1, '1001@test', '测试坐席', 1644249600, 1278985, 1594, 13390, 0, 1125457, 73976, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (39, 1644325200, 1, '1001@test', '测试坐席', 1644321600, 3271334, 23647, 3066, 0, 1210894, 297609, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (40, 1644325200, 1, '1002@test', '测试2号', 1644321600, 1833338, 47991, 3066, 0, 1635225, 297609, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (41, 1644328800, 1, '1001@test', '测试坐席', 1644325200, -50980695, 39888, 14759, 0, 1802249, 492660, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (42, 1644332400, 1, '1001@test', '测试坐席', 1644328800, 1173394, 0, 0, 0, 1671340, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (43, 1644372000, 1, '1001@test', '测试坐席', 1644368400, 7007551, 389, 63558, 0, 0, 164375, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (44, 1644375600, 1, '1001@test', '测试坐席', 1644372000, 1478088, 383, 2772, 0, 1182317, 453661, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (45, 1644390000, 1, '1001@test', '测试坐席', 1644386400, -5736602, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (46, 1644397200, 1, '1001@test', '测试坐席', 1644393600, 3212716, 444, 437286, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (47, 1644404400, 1, '1001@test', '测试坐席', 1644400800, 3366070, 392614, 4333, 0, 1050142, 39195, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (48, 1644422400, 1, '1001@test', '测试坐席', 1644418800, 1388844, 1241, 482614, 0, 70025, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (49, 1644422400, 1, '1002@test', '测试2号', 1644418800, 6383434, 7755, 482614, 0, 77747, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (50, 1644472800, 1, '1001@test', '测试坐席', 1644469200, 5497162, 598, 1410, 0, 1699423, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (51, 1644476400, 1, '1001@test', '测试坐席', 1644472800, 737276, 1021, 1580, 0, 2432710, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (52, 1644490800, 1, '1001@test', '测试坐席', 1644487200, 6431358, 913, 2165, 0, 815883, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (53, 1644494400, 1, '1001@test', '测试坐席', 1644490800, 1637274, 5171, 293499, 0, 1072621, 405715, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (54, 1644494400, 1, '1002@test', '测试2号', 1644490800, 2477283, 6522, 300511, 0, 1783216, 405715, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (55, 1644498000, 1, '1002@test', '测试2号', 1644494400, 7346699, 379, 1758113, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (56, 1644501600, 1, '1001@test', '测试坐席', 1644498000, 3574457, 2369, 2378, 0, 1022907, 370451, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (57, 1644501600, 1, '1002@test', '测试2号', 1644498000, -1644475230506, 8221, 264609, 0, 2058805, 370451, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (58, 1644505200, 1, '1001@test', '测试坐席', 1644501600, 13631168, 53551, 2236, 0, 759760, 207413, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (59, 1644505200, 1, '1002@test', '测试2号', 1644501600, 3350931, 490284, 3576, 0, 3287295, 586282, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (60, 1644508800, 1, '1001@test', '测试坐席', 1644505200, 1280231, 0, 0, 0, 1121833, 181567, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (61, 1644552000, 1, '1001@test', '测试坐席', 1644548400, 3598018, 495, 25401, 0, 153003, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (62, 1644555600, 1, '1001@test', '测试坐席', 1644552000, 524837, 5706, 0, 0, 251306, 179484, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (63, 1644559200, 1, '1001@test', '测试坐席', 1644555600, 2946747, 752, 4351, 0, 83964, 586202, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (64, 1644559200, 1, '1002@test', '测试2号', 1644555600, 3548459, 752, 4351, 0, 83964, 586202, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (65, 1644562800, 1, '1002@test', '测试2号', 1644559200, 3366746, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (66, 1644566400, 1, '1001@test', '测试坐席', 1644562800, 22641966, 4998, 51576, 0, 1630800, 350098, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (67, 1644566400, 1, '1002@test', '测试2号', 1644562800, 3477409, 260936, 229260, 0, 1630800, 465013, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (68, 1644570000, 1, '1001@test', '测试坐席', 1644566400, 2168921, 10445, 138889, 0, 647525, 754983, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (69, 1644570000, 1, '1002@test', '测试2号', 1644566400, 2286762, 12346, 724581, 0, 1566238, 942239, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (70, 1644573600, 1, '1001@test', '测试坐席', 1644570000, 1926770, 800, 705919, 0, 199848, 180285, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (71, 1644573600, 1, '1002@test', '测试2号', 1644570000, 1223999, 3884, 730175, 0, 417822, 344765, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (72, 1644634800, 1, '1001@test', '测试坐席', 1644631200, 1405336, 370, 5481, 0, 813301, 157061, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (73, 1644638400, 1, '1001@test', '测试坐席', 1644634800, 14660983, 13886, 57970, 0, 1609765, 863886, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (74, 1644638400, 1, '1002@test', '测试2号', 1644634800, -1644615596237, 14199, 60985, 0, 1871140, 1042934, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (75, 1644642000, 1, '1001@test', '测试坐席', 1644638400, 963623, 709, 3665, 0, 688033, 358257, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (76, 1644642000, 1, '1002@test', '测试2号', 1644638400, 37529, 709, 3665, 0, 986937, 358257, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (77, 1644649200, 1, '1001@test', '测试坐席', 1644645600, -3453169, 2588, 30704, 0, 1022078, 625086, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (78, 1644649200, 1, '1002@test', '测试2号', 1644645600, 2781819, 3061, 33464, 0, 1169227, 804103, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (79, 1644652800, 1, '1001@test', '测试坐席', 1644649200, 7109892, 2406, 29136, 0, 1584498, 604918, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (80, 1644652800, 1, '1002@test', '测试2号', 1644649200, 15068116, 3714, 58350, 0, 3020456, 1111122, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (81, 1644656400, 1, '1001@test', '测试坐席', 1644652800, 5213696, 1113, 16041, 0, 1078535, 1229690, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (82, 1644656400, 1, '1002@test', '测试2号', 1644652800, 11648708, 1949, 87675, 0, 2363241, 3209937, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (83, 1644660000, 1, '1001@test', '测试坐席', 1644656400, 2956757, 3489, 42888, 0, 0, 1468358, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (84, 1644660000, 1, '1002@test', '测试2号', 1644656400, -670758, 111458, 258030, 0, 0, 1753419, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (85, 1644937860, 1, '1001@test', '测试坐席', 1644934260, 2896971, 356, 2976, 0, 215602, 177625, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (86, 1644937860, 1, '1002@test', '测试2号', 1644934260, 3006513, 718, 7638, 0, 546215, 357541, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (87, 1644940800, 1, '1001@test', '测试坐席', 1644937200, 5643143, 337, 3717, 0, 1194981, 31656, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (88, 1644940800, 1, '1002@test', '测试2号', 1644937200, 3547106, 1073, 179615, 0, 2055077, 211041, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (89, 1644944400, 1, '1001@test', '测试坐席', 1644940800, 619971, 366, 1456, 0, 143120, 12351, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (90, 1644944400, 1, '1002@test', '测试2号', 1644940800, 624069, 714, 6830, 0, 289660, 12351, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (91, 1644980400, 1, '1001@test', '测试坐席', 1644976800, 3175514, 368, 3090, 0, 492895, 56646, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (92, 1644980400, 1, '1002@test', '测试2号', 1644976800, 3535509, 3892, 906416, 0, 639402, 56646, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (93, 1644994800, 1, '1001@test', '测试坐席', 1644991200, 6635149, 584, 7749, 0, 0, 545162, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (94, 1644994800, 1, '1002@test', '测试2号', 1644991200, 13294962, 4475, 7749, 0, 520895, 545162, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (95, 1644998400, 1, '1001@test', '测试坐席', 1644994800, 1514539, 491, 4806, 0, 0, 1984173, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (96, 1644998400, 1, '1002@test', '测试2号', 1644994800, 790398, 975, 9296, 0, 604958, 1984173, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (97, 1645027200, 1, '1001@test', '测试坐席', 1645023600, 1623700, 1214, 26466, 0, 225821, 668188, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (98, 1645027200, 1, '1002@test', '测试2号', 1645023600, 1869095, 1833, 303748, 0, 1095734, 675009, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (99, 1645030800, 1, '1001@test', '测试坐席', 1645027200, -1645011712953, 2434, 36183, 0, 159969, 1900975, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (100, 1645030800, 1, '1002@test', '测试2号', 1645027200, -3290023559004, 4948, 153891, 0, 2083584, 1900975, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (101, 1645034400, 1, '1001@test', '测试坐席', 1645030800, 1533355, 2406, 20123, 0, 823579, 612365, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (102, 1645034400, 1, '1002@test', '测试2号', 1645030800, 1533354, 14502, 68901, 0, 2992187, 614575, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (103, 1645070400, 1, '1001@test', '测试坐席', 1645066800, 2964162, 519, 2586, 0, 0, 353061, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (104, 1645070400, 1, '1002@test', '测试2号', 1645066800, 3022167, 902, 28840, 0, 392047, 353061, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (105, 1645077600, 1, '1001@test', '测试坐席', 1645074000, 6457433, 443, 2526, 0, 552542, 8860, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (106, 1645077600, 1, '1002@test', '测试2号', 1645074000, 12921218, 911, 7998, 0, 1106739, 8860, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (107, 1645081200, 1, '1001@test', '测试坐席', 1645077600, 91184, 0, 0, 0, 643727, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (108, 1645081200, 1, '1002@test', '测试2号', 1645077600, 179518, 0, 0, 0, 2111159, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (109, 1645110000, 1, '1001@test', '测试坐席', 1645106400, 6844870, 285, 3099, 0, 153481, 13872, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (110, 1645110000, 1, '1002@test', '测试2号', 1645106400, 13699545, 626, 22362, 0, 306653, 13872, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (111, 1645113600, 1, '1001@test', '测试坐席', 1645110000, -1645094001794, 11927, 1635294, 0, 886774, 194509, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (112, 1645113600, 1, '1002@test', '测试2号', 1645110000, 3372265, 97192, 1788277, 0, 2373100, 194509, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (113, 1645117200, 1, '1001@test', '测试坐席', 1645113600, 673100, 0, 0, 0, 1337335, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (114, 1645149600, 1, '1001@test', '测试坐席', 1645146000, 11821419, 5796, 497283, 0, 506520, 389477, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (115, 1645149600, 1, '1002@test', '测试2号', 1645146000, 3515800, 9400, 516107, 0, 1569462, 389477, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (116, 1645153200, 1, '1001@test', '测试坐席', 1645149600, 635785, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (117, 1645160400, 1, '1001@test', '测试坐席', 1645156800, 6333970, 1481, 11019, 0, 307745, 525664, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (118, 1645160400, 1, '1002@test', '测试2号', 1645156800, 16052024, 439515, 18096, 0, 333169, 525664, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (119, 1645164000, 1, '1001@test', '测试坐席', 1645160400, 5999097, 101400, 71962, 0, 1861833, 316736, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (120, 1645164000, 1, '1002@test', '测试2号', 1645160400, -1645148637370, 297902, 625083, 0, 1861833, 316736, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (121, 1645167600, 1, '1001@test', '测试坐席', 1645164000, 2675825, 331663, 4020, 0, 2197803, 167198, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (122, 1645167600, 1, '1002@test', '测试2号', 1645164000, 815829, 332175, 9241, 0, 2692806, 167198, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (123, 1645192800, 1, '1001@test', '测试坐席', 1645189200, 7178891, 369, 20183, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (124, 1645192800, 1, '1002@test', '测试2号', 1645189200, 14373330, 810, 24213, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (125, 1645196400, 1, '1001@test', '测试坐席', 1645192800, 1190481, 543, 29544, 0, 890383, 243690, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (126, 1645196400, 1, '1002@test', '测试2号', 1645192800, 2156657, 824, 49116, 0, 1762363, 243690, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (127, 1645200000, 1, '1001@test', '测试坐席', 1645196400, 3282704, 4176, 356, 0, 1148033, 20136, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (128, 1645200000, 1, '1002@test', '测试2号', 1645196400, 266999, 4176, 356, 0, 1616818, 20136, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (129, 1645203600, 1, '1001@test', '测试坐席', 1645200000, 6943483, 1326, 3648, 0, 67862, 179304, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (130, 1645207200, 1, '1001@test', '测试坐席', 1645203600, 956640, 1383, 11625, 0, 947067, 14709, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (131, 1645207200, 1, '1002@test', '测试2号', 1645203600, 952360, 3214, 11625, 0, 1331755, 14709, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (132, 1645243200, 1, '1001@test', '测试坐席', 1645239600, 1485184, 479, 6801, 0, 0, 28245, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (133, 1645243200, 1, '1002@test', '测试2号', 1645239600, 1485178, 3353, 17764, 0, 0, 28245, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (134, 1645286400, 1, '1001@test', '测试坐席', 1645282800, 4646880, 383, 3048, 0, 2363736, 11086, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (135, 1645286400, 1, '1002@test', '测试2号', 1645282800, 2175286, 809, 9166, 0, 3302546, 11086, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (136, 1645293600, 1, '1001@test', '测试坐席', 1645290000, 6282456, 1087, 7815, 0, 486157, 29193, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (137, 1645293600, 1, '1002@test', '测试2号', 1645290000, 12573260, 7723, 7815, 0, 779777, 29193, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (138, 1645297200, 1, '1001@test', '测试坐席', 1645293600, 183040, 0, 0, 0, 402276, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (139, 1645297200, 1, '1002@test', '测试2号', 1645293600, 886, 0, 0, 0, 622265, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (140, 1645686000, 1, '1001@test', '测试坐席', 1645682400, 3414814, 1413, 448161, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (141, 1645686000, 1, '1002@test', '测试2号', 1645682400, 9910679, 1413, 448161, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (142, 1645689600, 1, '1001@test', '测试坐席', 1645686000, 1809826, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (143, 1645696800, 1, '1001@test', '测试坐席', 1645693200, 2166973, 363, 372154, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (144, 1645772400, 1, '1001@test', '测试坐席', 1645768800, 9917997, 370, 1232, 0, 811971, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (145, 1645776000, 1, '1001@test', '测试坐席', 1645772400, 7174068, 3966, 56425, 0, 3389341, 189333, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (146, 1645783200, 1, '1001@test', '测试坐席', 1645779600, 626508, 0, 0, 0, 6320500, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (147, 1645794000, 1, '1001@test', '测试坐席', 1645790400, 538747, 347, 4260, 0, 50440, 63000, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (148, 1645794000, 1, '1002@test', '测试2号', 1645790400, 538747, 347, 4260, 0, 3163354, 63000, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (149, 1645840800, 1, '1001@test', '测试坐席', 1645837200, 10163194, 2235, 7237, 0, 161538, 288702, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (150, 1645840800, 1, '1002@test', '测试2号', 1645837200, 10163194, 2235, 7237, 0, 49995662, 288702, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (151, 1645844400, 1, '1001@test', '测试坐席', 1645840800, 13918866, 40035, 220525, 0, 2194669, 294505, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (152, 1645844400, 1, '1002@test', '测试2号', 1645840800, 2764030, 89851, 222960, 0, 3485771, 294505, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (153, 1645844400, 1, 'B001@test', 'B001', 1645840800, 2764052, 687201, 957573, 0, 3485771, 294505, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (154, 1645848000, 1, '1001@test', '测试坐席', 1645844400, 4023, 0, 0, 0, 1212593, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (155, 1645851600, 1, '1001@test', '测试坐席', 1645848000, 1117731, 7719, 0, 0, 587612, 280875, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (156, 1645851600, 1, '1002@test', '测试2号', 1645848000, 844934, 57231, 0, 0, 902244, 280875, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (157, 1645855200, 1, '1001@test', '测试坐席', 1645851600, 1483788, 4389, 0, 0, 518524, 84850, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (158, 1645855200, 1, '1002@test', '测试2号', 1645851600, 1167865, 25734, 0, 0, 720899, 84850, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (159, 1645858800, 1, '1001@test', '测试坐席', 1645855200, 2286064, 1530, 5352, 0, 1378694, 364395, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (160, 1645858800, 1, '1002@test', '测试2号', 1645855200, 2289048, 11360, 5352, 0, 1949444, 364395, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (161, 1645858800, 1, 'B001@test', 'B001', 1645855200, 2285943, 31343, 5352, 0, 2271491, 364395, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (162, 1645862400, 1, '1001@test', '测试坐席', 1645858800, 539759, 348, 2862, 0, 322425, 93930, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (163, 1645862400, 1, '1002@test', '测试2号', 1645858800, -1645851060261, 348, 2862, 0, 3704864, 93930, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (164, 1645862400, 1, 'B001@test', 'B001', 1645858800, 3229200, 1424, 1176297, 0, 3704864, 93930, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (165, 1645866000, 1, '1001@test', '测试坐席', 1645862400, 1637389, 2904, 923426, 0, 36878, 300262, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (166, 1645866000, 1, '1002@test', '测试2号', 1645862400, 1637399, 2904, 923426, 0, 412132, 300262, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (167, 1645866000, 1, 'B001@test', 'B001', 1645862400, 1637396, 118306, 925156, 0, 448701, 300262, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (168, 1645974000, 1, '1001@test', '测试坐席', 1645970400, 19388289, 355595, 20139, 0, 676811, 101728, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (169, 1645974000, 1, '1002@test', '测试2号', 1645970400, 38786620, 406519, 23740, 0, 1261599, 101728, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (170, 1645974000, 1, 'B001@test', 'B001', 1645970400, 54606626, 505752, 23740, 0, 1732991, 101728, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (171, 1646060400, 1, '1001@test', '测试坐席', 1646056800, 1191638, 84292505, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (172, 1646060400, 1, '1002@test', '测试2号', 1646056800, 1191639, 84292505, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (173, 1646060400, 1, 'B001@test', 'B001', 1646056800, 1191639, 84292505, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (174, 1646064000, 1, '1001@test', '测试坐席', 1646060400, 2804981, 371, 188891, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (175, 1646064000, 1, '1002@test', '测试2号', 1646060400, 6301852, 371, 188891, 0, 812411, 141073, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (176, 1646100000, 1, '1002@test', '测试2号', 1646096400, 3470659, 1068, 51315, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (177, 1646143200, 1, '1001@test', '测试坐席', 1646139600, -3292271662179, 2085, 27268, 0, 947344, 358999, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (178, 1646193600, 1, '1001@test', '测试坐席', 1646190000, 3177913, 305, 5298, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (179, 1646200800, 1, '1001@test', '测试坐席', 1646197200, 7161649, 382, 35520, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (180, 1646200800, 1, '1002@test', '测试2号', 1646197200, 14330913, 968, 39150, 0, 0, 23654, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (181, 1646204400, 1, '1001@test', '测试坐席', 1646200800, 885450, 806, 920173, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (182, 1646204400, 1, '1002@test', '测试2号', 1646200800, 345433, 806, 920173, 0, 188913, 180183, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (183, 1646208000, 1, '1001@test', '测试坐席', 1646204400, -1646197369021, 78735, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (184, 1646208000, 1, '1002@test', '测试2号', 1646204400, -4938598962042, 82730, 4457, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (185, 1646211600, 1, '1001@test', '测试坐席', 1646208000, -3292410941123, 1179478, 391123, 0, 0, 41681, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (186, 1646211600, 1, '1002@test', '测试2号', 1646208000, -4938611963796, 1183497, 399949, 0, 880909, 1120906, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (187, 1646215200, 1, '1001@test', '测试坐席', 1646211600, 1425470, 437, 1379895, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (188, 1646215200, 1, '1002@test', '测试2号', 1646211600, 1305468, 764, 1383594, 0, 394460, 178565, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (189, 1646233200, 1, '1001@test', '测试坐席', 1646229600, 3506104, 1646, 223540, 0, 649061, 266544, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (190, 1646233200, 1, '1002@test', '测试2号', 1646229600, -890353208, 8639, 280212, 0, 1676451, 1394502, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (191, 1646236800, 1, '1001@test', '测试坐席', 1646233200, 2722870, 3099, 133737, 0, 298769, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (192, 1646236800, 1, '1002@test', '测试2号', 1646233200, 2723331, 7593, 141669, 0, 722633, 661203, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (193, 1646272800, 1, '1001@test', '测试坐席', 1646269200, 2395940, 375, 676184, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (194, 1646294400, 1, '1001@test', '测试坐席', 1646290800, -1646285313927, 2793, 0, 0, 460871, 197449, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (195, 1646298000, 1, '1001@test', '测试坐席', 1646294400, 9236827, 1183, 4096, 0, 0, 1543935, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (196, 1646301600, 1, '1001@test', '测试坐席', 1646298000, 1012680, 0, 0, 0, 0, 2096960, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (197, 1646308800, 1, '1001@test', '测试坐席', 1646305200, 2632686, 1507, 5731, 0, 307680, 1126688, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (198, 1646312400, 1, '1001@test', '测试坐席', 1646308800, -1646298390468, 12347, 1691, 0, 1241197, 285674, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (199, 1646316000, 1, '1001@test', '测试坐席', 1646312400, 0, 167095, 0, 0, 3499926, 4135, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (200, 1646316000, 1, '1002@test', '测试2号', 1646312400, 0, 167095, 0, 0, 6826651, 4135, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (201, 1646319600, 1, '1001@test', '测试坐席', 1646316000, 3532676, 2644, 10327, 0, 534396, 179373, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (202, 1646319600, 1, '1002@test', '测试2号', 1646316000, 3532683, 3097, 11488, 0, 809246, 358408, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (203, 1646463600, 1, '1001@test', '测试坐席', 1646460000, 2738458, 1181, 986, 0, 567420, 547532, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (204, 1646463600, 1, '1002@test', '测试2号', 1646460000, 2738459, 4431, 350403, 0, 1029761, 726423, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (205, 1646467200, 1, '1002@test', '测试2号', 1646463600, 889792, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (206, 1646470800, 1, '1001@test', '测试坐席', 1646467200, -1646456055663, 13391, 58817, 0, 951204, 534981, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (207, 1646470800, 1, '1002@test', '测试2号', 1646467200, -1646451093185, 15289, 64089, 0, 2842282, 845138, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (208, 1646470800, 1, 'B001@test', 'B001', 1646467200, 2138478, 15660, 68480, 0, 3130855, 1024268, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (209, 1646474400, 1, '1001@test', '测试坐席', 1646470800, -1646452925717, 3114, 10319, 0, 2709063, 1229814, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (210, 1646478000, 1, '1001@test', '测试坐席', 1646474400, -1646463888871, 1104, 89940, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (211, 1646478000, 1, '1002@test', '测试2号', 1646474400, -3292931390725, 2657, 164409, 0, 129480, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (212, 1646481600, 1, '1001@test', '测试坐席', 1646478000, 2881827, 2750, 0, 0, 992633, 51110, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (213, 1646481600, 1, '1002@test', '测试2号', 1646478000, -4939413065952, 5457, 90582, 0, 3321469, 51110, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (214, 1646496000, 1, '1001@test', '测试坐席', 1646492400, 2734695, 1177, 89929, 0, 867304, 179406, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (215, 1646499600, 1, '1001@test', '测试坐席', 1646496000, -1646499014373, 1058, 70298, 0, 2083208, 156659, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (216, 1646499600, 1, '1002@test', '测试2号', 1646496000, -1646499014373, 1058, 70298, 0, 37953778, 156659, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (217, 1646503200, 1, '1001@test', '测试坐席', 1646499600, 2449934, 0, 0, 0, 6838, 100676, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (218, 1646503200, 1, '1002@test', '测试2号', 1646499600, 2449934, 0, 0, 0, 5462395, 100676, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (219, 1646575200, 1, '1002@test', '测试2号', 1646571600, 3332824, 1280, 1137905, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (220, 1646751600, 1, '1001@test', '测试坐席', 1646748000, 5552670, 5364, 274273, 0, 1328242, 31303, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (221, 1646751600, 1, '1002@test', '测试2号', 1646748000, 11273819, 7327, 394759, 0, 2500076, 209733, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (222, 1646755200, 1, '1001@test', '测试坐席', 1646751600, 6499495, 827, 119846, 0, 1289721, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (223, 1646755200, 1, '1002@test', '测试2号', 1646751600, 12676315, 2093, 129153, 0, 2744507, 553775, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (224, 1646791200, 1, '1001@test', '测试坐席', 1646787600, 31512354, 7681, 110701, 0, 62511, 1952, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (225, 1646791200, 1, '1002@test', '测试2号', 1646787600, 66782325, 16304, 173905, 0, 246531, 1204488, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (226, 1646794800, 1, '1001@test', '测试坐席', 1646791200, -36146861, 1020, 18270, 0, 1570018, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (227, 1646794800, 1, '1002@test', '测试2号', 1646791200, 3158526, 1421, 21225, 0, 2033819, 180458, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (228, 1646798400, 1, '1001@test', '测试坐席', 1646794800, 2431314, 4956, 12679, 0, 882852, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (229, 1646798400, 1, '1002@test', '测试2号', 1646794800, 2011289, 5745, 17878, 0, 1346273, 359127, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (230, 1646841600, 1, '1001@test', '测试坐席', 1646838000, 3112048, 3689, 23434, 0, 499187, 125344, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (231, 1646841600, 1, '1002@test', '测试2号', 1646838000, 3112101, 5100, 29149, 0, 1315117, 373807, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (232, 1646906400, 1, '1002@test', '测试2号', 1646902800, 0, 0, 0, 0, 3546662, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (233, 1646913600, 1, '1001@test', '测试坐席', 1646910000, 6582330, 2947, 4720, 0, 394915, 200247, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (234, 1646913600, 1, '1002@test', '测试2号', 1646910000, 16727570, 4258, 10377, 0, 653017, 410215, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (235, 1646917200, 1, '1001@test', '测试坐席', 1646913600, -1646902678614, 3256, 6519, 0, 1641110, 232913, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (236, 1646917200, 1, '1002@test', '测试2号', 1646913600, -1646897175334, 17913, 15182, 0, 2733213, 1048809, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (237, 1646920800, 1, '1001@test', '测试坐席', 1646917200, 117158, 0, 0, 0, 467742, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (238, 1646920800, 1, '1002@test', '测试2号', 1646917200, 765374, 0, 0, 0, 1494659, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (239, 1647259200, 1, '1001@test', '测试坐席', 1647255600, 11833481, 5554, 475608, 0, 1394625, 477417, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (240, 1647262800, 1, '1001@test', '测试坐席', 1647259200, 2260324, 2198, 10424, 0, 2801965, 534757, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (241, 1647273600, 1, '1001@test', '测试坐席', 1647270000, 3046742, 7410, 454417, 0, 1755892, 126882, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (242, 1647327600, 1, '1001@test', '测试坐席', 1647324000, 3002983, 1221, 6097, 0, 32557, 182638, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (243, 1647475200, 1, '1001@test', '测试坐席', 1647471600, 3420477, 880, 3291, 0, 13689, 9626, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (244, 1647486000, 1, '1002@test', '测试2号', 1647482400, 659533, 454, 16284, 0, 102094, 179152, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (245, 1647867600, 1, '1001@test', '测试坐席', 1647864000, 8488093, 676, 6963, 0, 434676, 135503, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (246, 1647921600, 1, '1001@test', '测试坐席', 1647918000, 6989256, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (247, 1647932400, 1, '1001@test', '测试坐席', 1647928800, 7800479, 7455, 8734, 0, 1159101, 191787, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (248, 1647932400, 1, '1002@test', '测试2号', 1647928800, 2367984, 7800, 12043, 0, 1888348, 369605, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (249, 1647932400, 1, 'B001@test', 'B001', 1647928800, 2427984, 10428, 12043, 0, 2694405, 548624, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (250, 1647936000, 1, '1001@test', '测试坐席', 1647932400, 0, 333, 2574, 0, 3623469, 63889, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (251, 1647954000, 1, '1001@test', '测试坐席', 1647950400, 1588000, 653, 403573, 0, 3192, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (252, 1647954000, 1, '1002@test', '测试2号', 1647950400, 1588024, 1070, 406090, 0, 204657, 178931, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (253, 1647954000, 1, 'B001@test', 'B001', 1647950400, 1588025, 1395, 408529, 0, 415742, 358320, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (254, 1647957600, 1, '1001@test', '测试坐席', 1647954000, 3328020, 2408, 93552, 0, 505034, 230056, 1);
INSERT INTO `cc_stat_hour_agent_work` (`id`, `cts`, `company_id`, `agent_key`, `agent_name`, `state_time`, `login_time`, `ready_time`, `not_ready_time`, `busy_time`, `after_time`, `talk_time`, `status`) VALUES (255, 1647957600, 1, 'B001@test', 'B001', 1647954000, 2848027, 7542, 230097, 0, 915365, 408885, 1);
COMMIT;

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
BEGIN;
INSERT INTO `cc_station` (`id`, `cts`, `uts`, `application_id`, `application_type`, `application_group`, `application_host`, `application_port`, `username`, `pwd`, `status`) VALUES (1, 1, 1, 1000, 1, 'cc-a', '172.17.0.2', 7100, '', '', 1);
INSERT INTO `cc_station` (`id`, `cts`, `uts`, `application_id`, `application_type`, `application_group`, `application_host`, `application_port`, `username`, `pwd`, `status`) VALUES (2, 2, 2, 2000, 2, 'cc-a', '172.17.0.2', 7200, '', '', 1);
INSERT INTO `cc_station` (`id`, `cts`, `uts`, `application_id`, `application_type`, `application_group`, `application_host`, `application_port`, `username`, `pwd`, `status`) VALUES (3, 1, 1, 3000, 3, 'cc-a', '172.17.0.2', 7300, '', '', 1);
INSERT INTO `cc_station` (`id`, `cts`, `uts`, `application_id`, `application_type`, `application_group`, `application_host`, `application_port`, `username`, `pwd`, `status`) VALUES (4, 1, 1, 4000, 4, 'cc-a', '172.17.0.2', 7400, '', 'zhongweixian@gmail.com', 1);
INSERT INTO `cc_station` (`id`, `cts`, `uts`, `application_id`, `application_type`, `application_group`, `application_host`, `application_port`, `username`, `pwd`, `status`) VALUES (6, 0, 0, 4001, 4, 'cc-a', '101.200.44.120', 38021, '', '192.168', 0);
COMMIT;

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
-- Records of cc_vdn_schedule
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_schedule` (`id`, `cts`, `uts`, `company_id`, `name`, `level_value`, `type`, `start_day`, `end_day`, `start_time`, `end_time`, `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`, `status`) VALUES (1, 1, 1, 1, '全天日程', 3, 1, '2021-12-01', '2022-12-30', '', '', 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` (`id`, `cts`, `uts`, `company_id`, `name`, `level_value`, `type`, `start_day`, `end_day`, `start_time`, `end_time`, `mon`, `tue`, `wed`, `thu`, `fri`, `sat`, `sun`, `status`) VALUES (2, 2, 2, 1, '上班时间', 2, 1, '2020-12-01', '2020-12-30', '09:00:00', '18:00:00', 1, 1, 1, 1, 1, 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for location
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `contact_id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `username` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `domain` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `contact` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `received` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `path` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `expires` int unsigned NOT NULL,
  `q` float(10,2) NOT NULL DEFAULT '1.00',
  `callid` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT 'Default-Call-ID',
  `cseq` int NOT NULL DEFAULT '13',
  `last_modified` datetime NOT NULL DEFAULT '1900-01-01 00:00:01',
  `flags` int NOT NULL DEFAULT '0',
  `cflags` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `user_agent` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '',
  `socket` char(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `methods` int DEFAULT NULL,
  `sip_instance` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `kv_store` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `attr` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`contact_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4608403846966292044 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of location
-- ----------------------------
BEGIN;
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264416631309140155, '870002', NULL, 'sip:870002@119.97.197.5:63045;rinstance=8c23168979b45d0d', 'sip:119.97.197.5:63045', NULL, 1647005507, -1.00, '87460ZDQ2MmY2M2RmYzhiYjQ5ODdmOTRhMWNhMzZhOWM5OWI', 28, '2022-03-11 20:31:47', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'udp:172.17.0.2:6685', 5951, NULL, NULL, 'udp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264416631309505658, '870002', NULL, 'sip:870002@192.168.124.2:56033;rinstance=a43dfcdd91062981', 'sip:119.97.197.5:56033', NULL, 1647310913, -1.00, '87460ZGJjNzBlYmJjMzc5NDUzYzFiZTdjNmM1ODI2MjRhYjg', 2, '2022-03-15 09:21:53', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'udp:172.17.0.2:6685', 5951, NULL, NULL, 'udp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264416631309962305, '870002', NULL, 'sip:870002@10.8.0.17:49991;rinstance=7a79073e06f49fd8', 'sip:111.204.179.70:49991', NULL, 1647664329, -1.00, '87460YmU1ZTY2NTc0YTRmMjUzMzNhMTU1Yjc5ODJiOGRlYWY', 2, '2022-03-19 11:32:09', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'udp:172.17.0.2:6685', 5951, NULL, NULL, 'udp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264416631310220529, '870002', NULL, 'sip:870002@111.204.179.70:62923;rinstance=6faf3c496a9a46bd', 'sip:111.204.179.70:62923', NULL, 1647881425, -1.00, '87460OTZlYzJlY2ZhMmQ5YjRlNzA5ZTAxYmFkZjFjZjhlYTc', 8, '2022-03-21 23:50:25', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'udp:172.17.0.2:6685', 5951, NULL, NULL, 'udp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264416631310845030, '870002', NULL, 'sip:870002@192.168.82.30:49417;rinstance=22ee9750a16db492', 'sip:119.97.197.5:49417', NULL, 1648635164, -1.00, '87460M2VhZDBmNTBkZmY2MzhhN2RmZWQ4NGQ5MGZkNzRmMDE', 2, '2022-03-30 17:12:44', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'udp:172.17.0.2:6685', 5951, NULL, NULL, 'udp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264416631311339562, '870002', NULL, 'sip:870002@119.97.197.5:55160;rinstance=feeb1807288e1125', 'sip:119.97.197.5:55160', NULL, 1648795153, -1.00, '87460MTg0ZDEyODMxY2IxYzE0MzUzZDExY2QwNGRkYjJkOTE', 6, '2022-04-01 13:39:13', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'udp:172.17.0.2:6685', 5951, NULL, NULL, 'udp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264627372162407345, '870001', NULL, 'sip:870001@111.204.179.70:54568;transport=UDP;rinstance=533180959bb6d803', 'sip:111.204.179.70:54568', NULL, 1647511019, -1.00, 'Vh0vKcUHRt3zUZZJ66qTrw..', 40, '2022-03-17 17:55:59', 0, 'SIP_PING_FLAG NAT_FLAG', 'Z 5.5.8 v2.10.17.2', 'udp:172.17.0.2:6685', 5951, NULL, NULL, 'udp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264627372163104105, '870001', NULL, 'sip:870001@111.204.179.70:54307;rinstance=29d1fdb2cc5365bb;transport=tcp', 'sip:111.204.179.70:54307;transport=tcp', NULL, 1647881425, -1.00, '87460NjE4YmIyNWQxMTk5ZThlZWZkZTUyN2MzMmMxMzFjNTQ', 8, '2022-03-21 23:50:25', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'tcp:172.17.0.2:6685', 5951, NULL, NULL, 'tcp');
INSERT INTO `location` (`contact_id`, `username`, `domain`, `contact`, `received`, `path`, `expires`, `q`, `callid`, `cseq`, `last_modified`, `flags`, `cflags`, `user_agent`, `socket`, `methods`, `sip_instance`, `kv_store`, `attr`) VALUES (4264627372164205466, '870001', NULL, 'sip:870001@119.97.197.5:51517;rinstance=94eeaaf11890dd89;transport=tcp', 'sip:119.97.197.5:51517;transport=tcp', NULL, 1648795153, -1.00, '87460NjI2ZDgzNmNmMTUwYzQyYzQzZDNkYjY0ZTAzNGE1ZmY', 6, '2022-04-01 13:39:13', 0, 'SIP_PING_FLAG NAT_FLAG', 'Bria 5 release 5.0.2  stamp 87460', 'tcp:172.17.0.2:6685', 5951, NULL, NULL, 'tcp');
COMMIT;

-- ----------------------------
-- Table structure for siu_users
-- ----------------------------
DROP TABLE IF EXISTS `siu_users`;
CREATE TABLE `siu_users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `companyid` int NOT NULL COMMENT '企业ID',
  `sip_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SIP账号',
  `sip_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'SIP密码',
  `subaccound` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '子帐号',
  `subtoken` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '子帐号令牌',
  `bind_phoneid` int DEFAULT NULL COMMENT '绑定的特服号ID',
  `ccsdirectcallflag` tinyint DEFAULT '1' COMMENT 'ccs是否支持硬话机外呼（0：不允许；1：允许)',
  `sip_domain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `type` int DEFAULT NULL COMMENT '类型：1 ivr，2 ccs',
  `stationno` int DEFAULT NULL COMMENT '绑定IVR或CCS站点号',
  `status` int DEFAULT '1' COMMENT '账号是否有效：0：无效；1：有效',
  `ctime` int DEFAULT NULL,
  `utime` int DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `sip_id` (`sip_id`) USING BTREE,
  KEY `sip_companyid` (`companyid`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='SIP用户注册表';

-- ----------------------------
-- Records of siu_users
-- ----------------------------
BEGIN;
INSERT INTO `siu_users` (`id`, `companyid`, `sip_id`, `sip_password`, `subaccound`, `subtoken`, `bind_phoneid`, `ccsdirectcallflag`, `sip_domain`, `type`, `stationno`, `status`, `ctime`, `utime`) VALUES (1, 1, '870001', '123456', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, 1, 0);
INSERT INTO `siu_users` (`id`, `companyid`, `sip_id`, `sip_password`, `subaccound`, `subtoken`, `bind_phoneid`, `ccsdirectcallflag`, `sip_domain`, `type`, `stationno`, `status`, `ctime`, `utime`) VALUES (2, 1, '870002', '123456', NULL, NULL, NULL, 1, NULL, NULL, NULL, 1, NULL, 0);
COMMIT;

-- ----------------------------
-- Table structure for version
-- ----------------------------
DROP TABLE IF EXISTS `version`;
CREATE TABLE `version` (
  `table_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `table_version` int unsigned NOT NULL DEFAULT '0',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  UNIQUE KEY `table_name_idx` (`table_name`) USING BTREE,
  KEY `idx_update_time` (`update_time`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of version
-- ----------------------------
BEGIN;
INSERT INTO `version` (`table_name`, `table_version`, `update_time`) VALUES ('location', 1013, '2022-02-10 07:38:40');
INSERT INTO `version` (`table_name`, `table_version`, `update_time`) VALUES ('siu_users', 7, '2022-02-10 07:38:46');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
