
SET NAMES utf8mb4;
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
  KEY `SCHED_NAME` (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_BLOB_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_CRON_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_CRON_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', '0 30 0 * * ? *', 'GMT+08:00');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', '0 0 2 1 * ?', 'GMT+08:00');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', '0/2 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('clusteredScheduler', 'TaskJobOfHour', 'cc.quartz', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('clusteredScheduler', 'TaskJobOfSecond', 'cc.quartz', '0/1 * * * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('DefaultQuartzScheduler', 'TaskJobOfHour', 'cc.quartz', '0 0 0/1 * * ?', 'Asia/Shanghai');
INSERT INTO `QRTZ_CRON_TRIGGERS` VALUES ('DefaultQuartzScheduler', 'TaskJobOfSecond', 'cc.quartz', '0/2 * * * * ?', 'Asia/Shanghai');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_FIRED_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_FIRED_TRIGGERS` VALUES ('cc-quartz', 'VM-0-2-centos16330809765941633081312876', 'TaskJobOfSecond', 'cc.quartz', 'VM-0-2-centos1633080976594', 1633751966046, 1633751968000, 5, 'ACQUIRED', NULL, NULL, '0', '0');
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_JOB_DETAILS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay 任务', 'org.zhongweixian.api.quartz.TaskJobOfDay', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 'org.zhongweixian.api.quartz.TaskJobOfHour', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth 任务', 'org.zhongweixian.api.quartz.TaskJobOfMonth', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 'org.zhongweixian.api.quartz.TaskJobOfSecond', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('clusteredScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 'org.zhongweixian.api.quartz.TaskJobOfHour', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('clusteredScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 'org.zhongweixian.api.quartz.TaskJobOfSecond', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('DefaultQuartzScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 'org.zhongweixian.api.quartz.TaskJobOfHour', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
INSERT INTO `QRTZ_JOB_DETAILS` VALUES ('DefaultQuartzScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 'org.zhongweixian.api.quartz.TaskJobOfSecond', '1', '0', '0', '1', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787000737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F40000000000010770800000010000000007800);
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_LOCKS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_LOCKS`;
CREATE TABLE `QRTZ_LOCKS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `LOCK_NAME` varchar(40) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`LOCK_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_LOCKS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_LOCKS` VALUES ('cc-quartz', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('cc-quartz', 'TRIGGER_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('clusteredScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('clusteredScheduler', 'TRIGGER_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('DefaultQuartzScheduler', 'STATE_ACCESS');
INSERT INTO `QRTZ_LOCKS` VALUES ('DefaultQuartzScheduler', 'TRIGGER_ACCESS');
COMMIT;

-- ----------------------------
-- Table structure for QRTZ_PAUSED_TRIGGER_GRPS
-- ----------------------------
DROP TABLE IF EXISTS `QRTZ_PAUSED_TRIGGER_GRPS`;
CREATE TABLE `QRTZ_PAUSED_TRIGGER_GRPS` (
  `SCHED_NAME` varchar(120) NOT NULL,
  `TRIGGER_GROUP` varchar(200) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_SCHEDULER_STATE
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('cc-quartz', 'VM-0-2-centos1633080976594', 1633751965484, 10000);
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('clusteredScheduler', 'localhost1630684716234', 1630684907047, 1000);
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('DefaultQuartzScheduler', 'rlcc141630718230947', 1630727015348, 10000);
INSERT INTO `QRTZ_SCHEDULER_STATE` VALUES ('DefaultQuartzScheduler', 'VM-0-2-centos1630726941990', 1630727000282, 10000);
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
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPLE_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
  PRIMARY KEY (`SCHED_NAME`,`TRIGGER_NAME`,`TRIGGER_GROUP`),
  CONSTRAINT `QRTZ_SIMPROP_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `QRTZ_TRIGGERS` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

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
  KEY `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP` (`SCHED_NAME`,`MISFIRE_INSTR`,`NEXT_FIRE_TIME`,`TRIGGER_GROUP`,`TRIGGER_STATE`),
  CONSTRAINT `QRTZ_TRIGGERS_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `QRTZ_JOB_DETAILS` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of QRTZ_TRIGGERS
-- ----------------------------
BEGIN;
INSERT INTO `QRTZ_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay', 'cc.quartz', 'TaskJobOfDay 任务', 1630859400000, -1, 5, 'ERROR', 'CRON', 1630858548000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 1633752000000, 1633748400000, 5, 'WAITING', 'CRON', 1630726916000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth', 'cc.quartz', 'TaskJobOfMonth 任务', 1635703200000, 1633024800000, 5, 'WAITING', 'CRON', 1630858548000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('cc-quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 1633751970000, 1633751968000, 5, 'ACQUIRED', 'CRON', 1630726917000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('clusteredScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 1630688400000, 1630684800000, 5, 'WAITING', 'CRON', 1630684251000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('clusteredScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 1630684908000, 1630684907000, 5, 'WAITING', 'CRON', 1630684251000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('DefaultQuartzScheduler', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour', 'cc.quartz', 'TaskJobOfHour 任务', 1630728000000, 1630724400000, 5, 'WAITING', 'CRON', 1630684987000, 0, NULL, 0, '');
INSERT INTO `QRTZ_TRIGGERS` VALUES ('DefaultQuartzScheduler', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond', 'cc.quartz', 'TaskJobOfSecond 任务', 1630727016000, 1630727014000, 5, 'WAITING', 'CRON', 1630684987000, 0, NULL, 0, '');
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_account
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_account`;
CREATE TABLE `cc_admin_account` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `username` varchar(50) NOT NULL DEFAULT '' COMMENT '用户名',
  `passwd` varchar(50) NOT NULL DEFAULT '' COMMENT '密码',
  `user_type` int NOT NULL DEFAULT '1' COMMENT '类型',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业管理员表';

-- ----------------------------
-- Records of cc_admin_account
-- ----------------------------
BEGIN;
INSERT INTO `cc_admin_account` VALUES (1, 1, 1, 1, 'admin', 'q11111111', 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_account_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_account_role`;
CREATE TABLE `cc_admin_account_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `account_id` bigint NOT NULL DEFAULT '0' COMMENT '账号ID',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色ID',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='账号角色表';

-- ----------------------------
-- Records of cc_admin_account_role
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_permission
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_permission`;
CREATE TABLE `cc_admin_permission` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `permission_name` varchar(255) NOT NULL DEFAULT '' COMMENT '权限名称',
  `permission_url` varchar(255) NOT NULL DEFAULT '' COMMENT '权限URL',
  `parent_id` bigint NOT NULL DEFAULT '0' COMMENT '父权限id',
  `permission_order` int NOT NULL DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) NOT NULL DEFAULT '' COMMENT '图标',
  `is_front` int NOT NULL DEFAULT '1' COMMENT '是否前端权限',
  `is_interface` int NOT NULL DEFAULT '1' COMMENT '是否后端权限',
  `status` int NOT NULL DEFAULT '1',
  `permission_level` int NOT NULL DEFAULT '1' COMMENT '菜单级别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限表';

-- ----------------------------
-- Records of cc_admin_permission
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_admin_permission_role
-- ----------------------------
DROP TABLE IF EXISTS `cc_admin_permission_role`;
CREATE TABLE `cc_admin_permission_role` (
  `id` bigint NOT NULL COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `role_id` bigint NOT NULL DEFAULT '0' COMMENT '角色ID',
  `permission_id` bigint NOT NULL DEFAULT '0' COMMENT '权限ID',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限角色表';

-- ----------------------------
-- Records of cc_admin_permission_role
-- ----------------------------
BEGIN;
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
  `role_name` varchar(255) NOT NULL DEFAULT '' COMMENT '角色名称',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色表';

-- ----------------------------
-- Records of cc_admin_role
-- ----------------------------
BEGIN;
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
  `agent_id` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席工号',
  `agent_key` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席账户',
  `agent_name` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席名称',
  `agent_code` varchar(20) NOT NULL DEFAULT '' COMMENT '坐席分机号',
  `agent_type` int NOT NULL DEFAULT '2' COMMENT '座席类型：2:普通座席；1：班长',
  `passwd` varchar(255) NOT NULL DEFAULT '' COMMENT '座席密码',
  `sip_phone` varchar(255) NOT NULL DEFAULT '' COMMENT '绑定的电话号码',
  `record` int NOT NULL DEFAULT '0' COMMENT '是否录音 0 no 1 yes',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '座席主要技能组  不能为空 必填项',
  `after_interval` int NOT NULL DEFAULT '5' COMMENT '话后自动空闲间隔时长',
  `diaplay` varchar(255) NOT NULL DEFAULT '' COMMENT '主叫显号',
  `ring_time` int NOT NULL DEFAULT '10' COMMENT '振铃时长',
  `host` varchar(255) NOT NULL DEFAULT '' COMMENT '登录服务器地址',
  `ext1` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展3',
  `state` int NOT NULL DEFAULT '0' COMMENT '坐席状态(1:在线,0:不在线)',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态：1 开通，0关闭',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_ccagent_agent_key` (`agent_key`) USING BTREE,
  KEY `idx_ccagent_company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb3 COMMENT='座席工号表';

-- ----------------------------
-- Records of cc_agent
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent` VALUES (1, 1604503580, 1604503580, 1, '1001', '1001@test', '测试坐席', '1001', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188899998889', 0, 1, 5, '', 10, ' ', '', '', '', 0, 1);
INSERT INTO `cc_agent` VALUES (2, 1604560158, 1604560158, 1, '1002', '1002@test', '测试2号', '1002', 2, '$2a$04$VbPGgx5gXs0ayfIHWNx3oOhYKbmMzMLPJHmBZtwIfIfT4HCGSTmpS', '188999988887', 0, 1, 5, '', 10, ' ', '', '', '', 0, 1);
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
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组id',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uniq_idx_agentgroup_agent_group` (`group_id`,`agent_id`) USING BTREE,
  KEY `idx_agentgroup_company_id` (`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席技能组表';

-- ----------------------------
-- Records of cc_agent_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_group` VALUES (1, 1604560158, 1604560158, 1, 1, 1, 1);
INSERT INTO `cc_agent_group` VALUES (2, 1604560158, 1604560158, 1, 2, 1, 1);
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
  `sip` varchar(32) NOT NULL DEFAULT '',
  `agent_id` bigint NOT NULL DEFAULT '0',
  `sip_pwd` varchar(255) DEFAULT '',
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `idx_agentsip_agent_id` (`agent_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='sip表';

-- ----------------------------
-- Records of cc_agent_sip
-- ----------------------------
BEGIN;
INSERT INTO `cc_agent_sip` VALUES (1, 1622024375, 1622033134, 1, '871556590425001', 1, '12345678', 1);
INSERT INTO `cc_agent_sip` VALUES (2, 1622072271, 0, 1, '871556519788001', 2, '12345678', 1);
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
  `agent_key` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席编号',
  `agent_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '' COMMENT '坐席名称',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话唯一标识',
  `login_type` int NOT NULL DEFAULT '1' COMMENT '登录类型',
  `work_type` int NOT NULL DEFAULT '1' COMMENT '工作类型',
  `host` varchar(255) NOT NULL DEFAULT '' COMMENT '服务站点',
  `remote_address` varchar(255) NOT NULL DEFAULT '' COMMENT '远端地址',
  `before_state` varchar(50) NOT NULL DEFAULT '' COMMENT '变更之前状态',
  `before_time` bigint NOT NULL DEFAULT '0' COMMENT '更变之前时间',
  `state` varchar(50) NOT NULL DEFAULT '' COMMENT '变更之后状态',
  `state_time` bigint NOT NULL DEFAULT '0' COMMENT '当前时间',
  `duration` int NOT NULL DEFAULT '0' COMMENT '持续时间',
  `busy_desc` varchar(255) NOT NULL DEFAULT '' COMMENT '忙碌类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  `ext1` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段3',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_agentstate_agentkey` (`agent_key`),
  KEY `idx_agentstate_cts` (`state_time`) USING BTREE,
  KEY `idx_agentstate_group_id` (`group_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=769 DEFAULT CHARSET=utf8mb3 COMMENT='坐席状态历史表';

-- ----------------------------
-- Records of cc_agent_state_log
-- ----------------------------


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
  `transfer_type` int NOT NULL DEFAULT '0' COMMENT '1:进vdn,2:进ivr,3:技能组,4:按键收号,5:外线\n',
  `transfer_id` bigint NOT NULL DEFAULT '0' COMMENT '转接ID',
  `reason` varchar(50) NOT NULL DEFAULT '' COMMENT '出队列原因:排队挂机或者转坐席',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_calldetail_call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='通话流程表';



-- ----------------------------
-- Table structure for cc_call_device
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_device`;
CREATE TABLE `cc_call_device` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话ID',
  `device_id` varchar(50) NOT NULL DEFAULT '' COMMENT '设备id',
  `agent_key` varchar(50) NOT NULL DEFAULT '' COMMENT '坐席',
  `device_type` int NOT NULL DEFAULT '1' COMMENT '1:坐席,2:客户,3:外线',
  `cdr_type` int NOT NULL DEFAULT '1' COMMENT '1:呼入,2:外呼,3:内呼,4:转接,5:咨询,6:监听,7:强插',
  `caller` varchar(50) NOT NULL DEFAULT '' COMMENT '主叫',
  `called` varchar(50) NOT NULL DEFAULT '' COMMENT '被叫',
  `display` varchar(50) NOT NULL DEFAULT '' COMMENT '显号',
  `called_location` varchar(255) NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `caller_location` varchar(50) NOT NULL DEFAULT '' COMMENT '被叫归属地',
  `call_time` bigint NOT NULL DEFAULT '0' COMMENT '呼叫开始时间',
  `ring_start_time` bigint NOT NULL DEFAULT '0' COMMENT '振铃开始时间',
  `ring_end_time` bigint NOT NULL DEFAULT '0' COMMENT '振铃结束时间',
  `answer_time` bigint NOT NULL DEFAULT '0' COMMENT '接通时间',
  `bridge_time` bigint NOT NULL DEFAULT '0' COMMENT '桥接时间',
  `end_time` bigint NOT NULL DEFAULT '0' COMMENT '结束时间',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话时长',
  `sip_protocol` varchar(50) NOT NULL DEFAULT '' COMMENT '信令协议(tcp/udp)',
  `record` varchar(255) NOT NULL DEFAULT '' COMMENT '录音地址',
  `record2` varchar(255) NOT NULL DEFAULT '' COMMENT '备用录音地址',
  `record_time` bigint NOT NULL DEFAULT '0' COMMENT '录音开始时间',
  `channel_name` varchar(50) NOT NULL DEFAULT '' COMMENT '呼叫地址',
  `hangup_cause` varchar(50) NOT NULL COMMENT '挂机原因',
  `ring_cause` varchar(50) NOT NULL DEFAULT '' COMMENT '回铃音识别',
  `sip_status` varchar(50) NOT NULL DEFAULT '' COMMENT 'sip状态',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展字段1',
  `ext2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展字段2',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_calldetail__call_id` (`call_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=82 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单明细表';


-- ----------------------------
-- Table structure for cc_call_dtmf
-- ----------------------------
DROP TABLE IF EXISTS `cc_call_dtmf`;
CREATE TABLE `cc_call_dtmf` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `dtmf_key` varchar(255) NOT NULL DEFAULT '' COMMENT '按键号码',
  `process_id` bigint NOT NULL DEFAULT '0' COMMENT '业务流程id',
  `call_id` bigint NOT NULL DEFAULT '0' COMMENT '通话标识id',
  `dtmf_time` bigint NOT NULL DEFAULT '0' COMMENT '按键时间',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
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
  `caller_display` varchar(255) NOT NULL DEFAULT '' COMMENT '主叫显号',
  `caller` varchar(50) NOT NULL DEFAULT '' COMMENT '主叫',
  `called_display` varchar(255) NOT NULL DEFAULT '' COMMENT '被叫显号',
  `called` varchar(50) NOT NULL DEFAULT '' COMMENT '被叫',
  `agent_key` varchar(20) NOT NULL DEFAULT '' COMMENT '坐席',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组',
  `login_type` int NOT NULL DEFAULT '1' COMMENT '1:sip号,2:webrtc,3:手机',
  `task_id` bigint NOT NULL DEFAULT '0' COMMENT '任务ID',
  `ivr_id` bigint NOT NULL DEFAULT '0' COMMENT 'ivr',
  `bot_id` bigint NOT NULL DEFAULT '0' COMMENT '机器人id',
  `call_time` bigint NOT NULL DEFAULT '0' COMMENT '呼叫开始时间',
  `answer_time` bigint NOT NULL DEFAULT '0' COMMENT '接听时间',
  `end_time` bigint NOT NULL DEFAULT '0' COMMENT '结束时间',
  `call_type` varchar(32) NOT NULL DEFAULT '' COMMENT '呼叫类型',
  `direction` varchar(32) NOT NULL DEFAULT '' COMMENT '呼叫方向',
  `answer_flag` int NOT NULL DEFAULT '0' COMMENT '通话标识(0:接通,1:坐席未接用户未接,2:坐席接通用户未接通,3:用户接通坐席未接通)',
  `wait_time` bigint NOT NULL DEFAULT '0' COMMENT '累计等待时长',
  `answer_count` int NOT NULL DEFAULT '0' COMMENT '应答设备数',
  `hangup_dir` int NOT NULL DEFAULT '1' COMMENT '挂机方向(1:主叫挂机,2:被叫挂机,3:系统挂机)',
  `hangup_code` int NOT NULL DEFAULT '0' COMMENT '挂机原因',
  `media` varchar(255) NOT NULL DEFAULT '' COMMENT '媒体服务器',
  `record` varchar(255) NOT NULL DEFAULT '' COMMENT '录音地址',
  `record2` varchar(255) NOT NULL DEFAULT '' COMMENT '备用录音地址',
  `record_time` bigint NOT NULL DEFAULT '0' COMMENT '录音时间',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话时长(秒)',
  `frist_queue_time` bigint NOT NULL DEFAULT '0' COMMENT '第一次进队列时间',
  `queue_start_time` bigint NOT NULL DEFAULT '0' COMMENT '进队列时间',
  `queue_end_time` bigint NOT NULL DEFAULT '0' COMMENT '出队列时间',
  `follow_data` varchar(4096) NOT NULL DEFAULT '' COMMENT '通话随路数据(2048)',
  `uuid1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展1',
  `uuid2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_calllog_call_id` (`call_id`) USING BTREE,
  KEY `idx_calllog_create_time` (`call_time`) USING BTREE,
  KEY `idx_call_log_agent` (`agent_key`),
  KEY `idx_call_log_group` (`group_id`)
) ENGINE=InnoDB AUTO_INCREMENT=52 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单表';

-- ----------------------------
-- Table structure for cc_company
-- ----------------------------
DROP TABLE IF EXISTS `cc_company`;
CREATE TABLE `cc_company` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `id_path` varchar(255) NOT NULL DEFAULT '' COMMENT '父企业ID',
  `pid` bigint NOT NULL DEFAULT '0' COMMENT '父企业',
  `company_code` varchar(255) NOT NULL DEFAULT '' COMMENT '简称',
  `contact` varchar(255) NOT NULL DEFAULT '' COMMENT '联系人',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '电话',
  `balance` bigint NOT NULL DEFAULT '0' COMMENT '金额',
  `bill_type` int NOT NULL DEFAULT '0' COMMENT '1:呼出计费,2:呼入计费,3:双向计费,0:全免费',
  `pay_type` int NOT NULL DEFAULT '0' COMMENT '0:预付费;1:后付费',
  `hidden_customer` int NOT NULL DEFAULT '0' COMMENT '隐藏客户号码(0:不隐藏;1:隐藏)',
  `secret_type` int NOT NULL DEFAULT '1' COMMENT '坐席密码等级',
  `secret_key` varchar(32) NOT NULL DEFAULT '' COMMENT '验证秘钥',
  `ivr_limit` int NOT NULL DEFAULT '50' COMMENT 'IVR通道数',
  `agent_limit` int NOT NULL DEFAULT '50' COMMENT '开通坐席',
  `group_limit` int NOT NULL DEFAULT '10' COMMENT '开通技能组',
  `group_agent_limit` int NOT NULL DEFAULT '1000' COMMENT '单技能组中坐席上限',
  `blacklist` bigint NOT NULL DEFAULT '0' COMMENT '黑名单',
  `notify_url` varchar(255) NOT NULL DEFAULT '' COMMENT '话单回调通知',
  `ext1` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext4` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext5` varchar(255) NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态(1:启用,0:未启用)',
  PRIMARY KEY (`id`),
  UNIQUE KEY `company_unqidx_name` (`name`),
  UNIQUE KEY `company_unqidx_code` (`company_code`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业信息表';

-- ----------------------------
-- Records of cc_company
-- ----------------------------
BEGIN;
INSERT INTO `cc_company` VALUES (1, 1604503580, 1604503580, 'test企业', '', 0, 'test', 'test', '18612983191', 1, 0, 1, 1, 1, '', 50, 5000, 20, 1000, 0, 'http://115.159.101.178:7100/index', '', '', '', '', '', 1);
INSERT INTO `cc_company` VALUES (2, 0, 1619796030, '20210430-delLwqXnW', '', 1, 'test2', '曹亮', '', 0, 0, 0, 1, 1, 'djJHDuy34r87du34', 50, 50, 10, 1000, 0, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` VALUES (15, 0, 1619797353, 'aaaa-delcSJeGY', '', 0, 'acscwe-delIqjPqn', '', '', 0, 0, 0, 1, 1, '', 50, 50, 10, 1000, 0, '', '', '', '', '', '', 0);
INSERT INTO `cc_company` VALUES (16, 0, 0, 'aaaa', '', 0, 'acscwe', '', '', 0, 0, 0, 1, 1, '', 50, 50, 10, 1000, 0, '', '', '', '', '', '', 1);
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
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '号码池',
  `type` int NOT NULL DEFAULT '0' COMMENT '1:主叫显号,2:被叫显号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `uni_idx_company_display` (`company_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='号码池表';

-- ----------------------------
-- Records of cc_company_display
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_display` VALUES (1, 1604503580, 1604503580, 1, '呼入号码池', 1, 1);
INSERT INTO `cc_company_display` VALUES (2, 1604503580, 1624463394, 1, '主叫外显-deleEwjrg', 2, 0);
INSERT INTO `cc_company_display` VALUES (3, 1604503580, 1624464161, 1, '被叫外显-delUKKKkq', 3, 0);
INSERT INTO `cc_company_display` VALUES (4, 1624443516, 0, 0, '主机号码池', 2, 1);
INSERT INTO `cc_company_display` VALUES (5, 1624443589, 0, 0, '主机号码池1', 2, 1);
INSERT INTO `cc_company_display` VALUES (6, 1624443849, 0, 0, '主机号码池12', 2, 1);
INSERT INTO `cc_company_display` VALUES (7, 1624445273, 0, 0, '主机号码池12222', 2, 1);
INSERT INTO `cc_company_display` VALUES (8, 1624445321, 0, 0, '主机号码池1212', 2, 1);
INSERT INTO `cc_company_display` VALUES (9, 1624445604, 0, 0, '主机号码池1213', 2, 1);
INSERT INTO `cc_company_display` VALUES (10, 1624445968, 0, 0, '主机号码池1214', 2, 1);
INSERT INTO `cc_company_display` VALUES (11, 1624451930, 0, 0, '主机号码池124', 2, 1);
INSERT INTO `cc_company_display` VALUES (12, 1624452041, 0, 0, '主机号码池224', 2, 1);
INSERT INTO `cc_company_display` VALUES (13, 1624452196, 0, 0, '主机号码池324', 2, 1);
INSERT INTO `cc_company_display` VALUES (14, 1624452379, 0, 0, '主机号码池321', 2, 1);
INSERT INTO `cc_company_display` VALUES (17, 1624452723, 0, 0, '主机号码池3211', 2, 1);
INSERT INTO `cc_company_display` VALUES (18, 1624452836, 0, 0, '主机号码池3333', 2, 1);
INSERT INTO `cc_company_display` VALUES (19, 1624453002, 0, 0, '主机号码池33331', 2, 1);
INSERT INTO `cc_company_display` VALUES (20, 1624453280, 0, 0, '主机号码池333331', 2, 1);
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
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '号码',
  `type` int NOT NULL DEFAULT '0' COMMENT '1:呼入号码,2:主叫显号,3:被叫显号',
  `status` int NOT NULL DEFAULT '1' COMMENT '1:未启用,2:启用',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_companyphone_phone` (`company_id`,`phone`,`type`),
  KEY `idx_companyhpone_company_id` (`company_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='企业号码';

-- ----------------------------
-- Records of cc_company_phone
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_phone` VALUES (1, 1604503580, 1622123026, 1, '18800010002', 1, 0);
INSERT INTO `cc_company_phone` VALUES (2, 1604503580, 1604503580, 1, '01012345678', 2, 1);
INSERT INTO `cc_company_phone` VALUES (3, 1604503580, 1604503580, 1, '01088889999', 3, 1);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组号码池';

-- ----------------------------
-- Records of cc_company_phone_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_phone_group` VALUES (1, 1624452836, 0, 0, 0, 1);
INSERT INTO `cc_company_phone_group` VALUES (2, 1624452836, 0, 0, 0, 3);
INSERT INTO `cc_company_phone_group` VALUES (3, 1624452836, 0, 0, 0, 4);
INSERT INTO `cc_company_phone_group` VALUES (4, 1624452836, 0, 0, 0, 5);
INSERT INTO `cc_company_phone_group` VALUES (5, 1624453002, 0, 0, 0, 1);
INSERT INTO `cc_company_phone_group` VALUES (6, 1624453037, 0, 0, 0, 3);
INSERT INTO `cc_company_phone_group` VALUES (7, 1624453037, 0, 0, 0, 4);
INSERT INTO `cc_company_phone_group` VALUES (8, 1624453037, 0, 0, 0, 5);
INSERT INTO `cc_company_phone_group` VALUES (9, 1624453280, 0, 0, 20, 1);
INSERT INTO `cc_company_phone_group` VALUES (10, 1624453304, 0, 0, 20, 3);
INSERT INTO `cc_company_phone_group` VALUES (11, 1624453304, 0, 0, 20, 4);
INSERT INTO `cc_company_phone_group` VALUES (12, 1624453304, 0, 0, 20, 5);
INSERT INTO `cc_company_phone_group` VALUES (25, 1624463566, 0, 0, 2, 1);
INSERT INTO `cc_company_phone_group` VALUES (26, 1624463566, 0, 0, 2, 3);
INSERT INTO `cc_company_phone_group` VALUES (27, 1624463566, 0, 0, 2, 4);
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_com_stat_type` (`company_id`,`type`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cc_company_stat
-- ----------------------------
BEGIN;
INSERT INTO `cc_company_stat` VALUES (1, 2, 1, 1, 1, 1);
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
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '技能组名称',
  `control_flag` int NOT NULL DEFAULT '0' COMMENT '控制开关 1:技能组,2:坐席',
  `after_interval` int NOT NULL DEFAULT '5' COMMENT '话后自动空闲时长',
  `caller_display_id` bigint NOT NULL DEFAULT '0' COMMENT '主叫显号号码池',
  `called_display_id` bigint NOT NULL DEFAULT '0' COMMENT '被叫显号号码池',
  `record_type` int NOT NULL DEFAULT '1' COMMENT '1:振铃录音,2:接通录音',
  `level_value` int NOT NULL DEFAULT '1' COMMENT '技能组优先级',
  `tts_engine` bigint NOT NULL DEFAULT '0' COMMENT 'tts引擎id',
  `play_content` varchar(100) NOT NULL DEFAULT '' COMMENT '转坐席时播放内容',
  `evaluate` bigint NOT NULL DEFAULT '0' COMMENT '转服务评价(0:否,1:是)',
  `queue_play` bigint NOT NULL DEFAULT '0' COMMENT '排队音',
  `transfer_play` bigint NOT NULL DEFAULT '0' COMMENT '转接提示音',
  `group_type` int NOT NULL DEFAULT '0' COMMENT '技能组类型',
  `notify_position` int NOT NULL DEFAULT '0' COMMENT '0:不播放排队位置,1:播放排队位置',
  `notify_rate` int NOT NULL DEFAULT '10' COMMENT '频次',
  `notify_content` varchar(255) NOT NULL DEFAULT '' COMMENT '您前面还有$位用户在等待',
  `call_memory` int NOT NULL DEFAULT '1' COMMENT '主叫记忆(1:开启,0:不开启)',
  `ext1` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展1',
  `ext2` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展2',
  `ext3` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展3',
  `ext4` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展4',
  `ext5` varchar(50) NOT NULL DEFAULT '' COMMENT '扩展5',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_company_name` (`company_id`,`name`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组表';

-- ----------------------------
-- Records of cc_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_group` VALUES (1, 1604503580, 1604503580, 1, '测试技能组', 1, 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 0, 1, 10, '0', 1, '', '', '', '', '', 1);
INSERT INTO `cc_group` VALUES (2, 1621556151, 0, 1, '测试技能组1', 1, 5, 0, 0, 1, 1, 0, '0', 0, 0, 0, 1, 1, 10, '0', 1, '', '', '', '', '', 1);
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
  `custom_expression` varchar(255) NOT NULL DEFAULT '' COMMENT '自定义表达式',
  `status` int NOT NULL DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组中坐席分配策略';

-- ----------------------------
-- Records of cc_group_agent_strategy
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_agent_strategy` VALUES (1, 1, 1, 1, 1, 1, 1, '', 1);
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
  `agent_key` varchar(32) NOT NULL DEFAULT '' COMMENT '坐席',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `phone` varchar(255) NOT NULL DEFAULT '' COMMENT '客户电话',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `unq_idx_group_id` (`group_id`,`phone`,`agent_key`)
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_group` (`group_id`)
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
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_group_overflow` (`group_id`,`overflow_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组排队策略表';

-- ----------------------------
-- Records of cc_group_overflow
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_overflow` VALUES (1, 1, 1, 1, 1, 1, 1);
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
  `strategy_key` varchar(20) NOT NULL DEFAULT '' COMMENT '自定义值',
  `strategy_present` int NOT NULL DEFAULT '1' COMMENT '百分百',
  `strategy_type` int NOT NULL DEFAULT '1' COMMENT '类型',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席自定义策略表';

-- ----------------------------
-- Records of cc_group_strategy_exp
-- ----------------------------
BEGIN;
INSERT INTO `cc_group_strategy_exp` VALUES (1, 1, 1, 1, 1, '1', 1, 1, 1);
INSERT INTO `cc_group_strategy_exp` VALUES (2, 2, 2, 1, 1, '2', 2, 2, 1);
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
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `handle_type` int NOT NULL DEFAULT '1' COMMENT '1:排队,2:溢出,3:挂机',
  `busy_type` int DEFAULT '1' COMMENT '排队方式(1:先进先出,2:vip,3:自定义)',
  `queue_timeout` int DEFAULT '60' COMMENT '排队超时时间',
  `busy_timeout_type` int DEFAULT '1' COMMENT '排队超时(1:溢出,2:挂机)',
  `overflow_type` int DEFAULT '1' COMMENT '溢出(1:group,2:ivr,3:vdn)',
  `overflow_value` int DEFAULT '0' COMMENT '溢出值',
  `lineup_expression` varchar(255) DEFAULT '' COMMENT '自定义排队表达式',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_name` (`company_id`,`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='溢出策略表';

-- ----------------------------
-- Records of cc_overflow_config
-- ----------------------------
BEGIN;
INSERT INTO `cc_overflow_config` VALUES (1, 1, 1, 1, '排队60秒', 1, 1, 60, 2, 1, 0, '');
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
  `exp_key` varchar(30) NOT NULL DEFAULT '' COMMENT '自定义值',
  `rate` int NOT NULL DEFAULT '1' COMMENT '权重',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='溢出策略前置条件';

-- ----------------------------
-- Records of cc_overflow_front
-- ----------------------------
BEGIN;
INSERT INTO `cc_overflow_front` VALUES (1, 1, 1, 1, 1, 1, 0, 0, 0, 1);
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
  `playback` varchar(255) NOT NULL DEFAULT '' COMMENT '放音文件',
  `status` int NOT NULL DEFAULT '1' COMMENT '1:待审核,2:审核通过',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='语音文件表';

-- ----------------------------
-- Records of cc_playback
-- ----------------------------
BEGIN;
INSERT INTO `cc_playback` VALUES (1, 1, 1, 1, '/app/clpms/sounds/222.wav', 2);
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
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='话单推送记录表';

-- ----------------------------
-- Records of cc_push_fail_log
-- ----------------------------
BEGIN;
INSERT INTO `cc_push_fail_log` VALUES (1, 1632884748, 1632938400, 1, 231008627337461760, 'http://127.0.0.1:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231008627337461760,\"callTime\":1632884748690,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"2952433880512393\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632884748683,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"UNALLOCATED_NUMBER\",\"record\":\"\",\"recordTime\":\"0\",\"ringCause\":\"\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipProtocol\":\"\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231008627337461760,\"callTime\":1632884748690,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632884748683,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uuid1\":\"123456\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 4, '', 1);
INSERT INTO `cc_push_fail_log` VALUES (2, 1632884969, 1632938400, 1, 231008691850051584, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632884768703,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632884766503,\"bridgeTime\":1632884768703,\"callId\":231008691850051584,\"callTime\":1632884764071,\"called\":\"871556590425001\",\"calledLocation\":\"\",\"caller\":\"1001\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"1951455940419392\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632884968363,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210929/11/231008691850051584_1951455940419392.wav\",\"recordTime\":1632884766563,\"ringCause\":\"\",\"ringEndTime\":1632884766503,\"ringStartTime\":1632884764363,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":201860},{\"agentKey\":\"1001@test\",\"answerTime\":1632884768623,\"bridgeTime\":1632884768703,\"callId\":231008691850051584,\"callTime\":1632884767072,\"called\":\"14400010002\",\"calledLocation\":\"\",\"caller\":\"01088889999\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"4888137748501043\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632884968163,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"\",\"recordTime\":\"0\",\"ringCause\":\"\",\"ringEndTime\":1632884768623,\"ringStartTime\":1632884766583,\"sipProtocol\":\"\",\"sipStatus\":\"200\",\"talkTime\":199540}],\"callId\":231008691850051584,\"callTime\":1632884764071,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632884968363,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210929/11/231008691850051584_1951455940419392.wav\",\"recordTime\":1632884766563,\"talkTime\":199660,\"taskId\":\"0\",\"uuid1\":\"123456\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 4, '', 1);
INSERT INTO `cc_push_fail_log` VALUES (3, 1632885347, 1632938400, 1, 231011137271889920, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231011137271889920,\"callTime\":1632885347105,\"called\":\"\",\"calledLocation\":\"\",\"caller\":\"\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"1879604369901727\",\"deviceType\":1,\"display\":\"\",\"endTime\":1632885347103,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"UNALLOCATED_NUMBER\",\"record\":\"\",\"recordTime\":\"0\",\"ringCause\":\"\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipProtocol\":\"\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231011137271889920,\"callTime\":1632885347105,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632885347103,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 4, '', 1);
INSERT INTO `cc_push_fail_log` VALUES (4, 1632885407, 1632938400, 1, 231011391752896512, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231011391752896512,\"callTime\":1632885407778,\"called\":\"\",\"calledLocation\":\"\",\"caller\":\"\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"3090332698464802\",\"deviceType\":1,\"display\":\"\",\"endTime\":1632885407783,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"UNALLOCATED_NUMBER\",\"record\":\"\",\"recordTime\":\"0\",\"ringCause\":\"\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipProtocol\":\"\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231011391752896512,\"callTime\":1632885407778,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632885407783,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 4, '', 1);
INSERT INTO `cc_push_fail_log` VALUES (5, 1632885671, 1632938400, 1, 231011629255360512, 'http://192.168.177.183:8709/push/forcePush/pushTest', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632885469503,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632885469443,\"bridgeTime\":1632885469503,\"callId\":231011629255360512,\"callTime\":1632885467909,\"called\":\"14400010002\",\"calledLocation\":\"\",\"caller\":\"01088889999\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"3080960166528524\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632885669484,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"\",\"recordTime\":\"0\",\"ringCause\":\"\",\"ringEndTime\":1632885469443,\"ringStartTime\":1632885467423,\"sipProtocol\":\"\",\"sipStatus\":\"200\",\"talkTime\":200041},{\"agentKey\":\"1001@test\",\"answerTime\":1632885467363,\"bridgeTime\":1632885469503,\"callId\":231011629255360512,\"callTime\":1632885464403,\"called\":\"\",\"calledLocation\":\"\",\"caller\":\"\",\"callerLocation\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"5974430069205817\",\"deviceType\":1,\"display\":\"\",\"endTime\":1632885670183,\"ext1\":\"\",\"ext2\":\"\",\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210929/11/231011629255360512_5974430069205817.wav\",\"recordTime\":1632885467403,\"ringCause\":\"\",\"ringEndTime\":1632885467363,\"ringStartTime\":1632885464663,\"sipProtocol\":\"\",\"sipStatus\":\"\",\"talkTime\":202820}],\"callId\":231011629255360512,\"callTime\":1632885464403,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632885670183,\"ext1\":\"\",\"ext2\":\"\",\"ext3\":\"\",\"followData\":\"\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210929/11/231011629255360512_5974430069205817.wav\",\"recordTime\":1632885467403,\"talkTime\":200680,\"taskId\":\"0\",\"uuid1\":\"\",\"uuid2\":\"\",\"waitTime\":\"0\"}', 4, '', 1);
INSERT INTO `cc_push_fail_log` VALUES (6, 1632896668, 1632896668, 1, 231058621763420160, 'http://127.0.0.1:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231058621763420160,\"callTime\":1632896668290,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"0758937078851367\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632896668303,\"hangupCause\":\"UNALLOCATED_NUMBER\",\"recordTime\":\"0\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231058621763420160,\"callTime\":1632896668290,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632896668303,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"groupId\":1,\"hangupDir\":1,\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"uuid1\":\"123456\"}', 1, '', 2);
INSERT INTO `cc_push_fail_log` VALUES (7, 1632896833, 1632896833, 1, 231059181992411136, 'http://127.0.0.1:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231059181992411136,\"callTime\":1632896801859,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"4245443329779765\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632896833083,\"hangupCause\":\"RECOVERY_ON_TIMER_EXPIRE\",\"recordTime\":\"0\",\"ringEndTime\":\"0\",\"ringStartTime\":1632896802403,\"sipStatus\":\"408\",\"talkTime\":\"0\"}],\"callId\":231059181992411136,\"callTime\":1632896801859,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632896833083,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uuid1\":\"123456\",\"waitTime\":\"0\"}', 1, '', 2);
INSERT INTO `cc_push_fail_log` VALUES (8, 1632896957, 1632896957, 1, 231059835758575616, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231059835758575616,\"callTime\":1632896957729,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"0299072992975607\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632896957743,\"hangupCause\":\"UNALLOCATED_NUMBER\",\"recordTime\":\"0\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231059835758575616,\"callTime\":1632896957729,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632896957743,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uuid1\":\"123456\",\"waitTime\":\"0\"}', 1, '', 2);
INSERT INTO `cc_push_fail_log` VALUES (9, 1632910159, 1632910159, 1, 231115196989440000, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231115196989440000,\"callTime\":1632910156875,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"7283890313100268\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632910156883,\"hangupCause\":\"UNALLOCATED_NUMBER\",\"recordTime\":\"0\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231115196989440000,\"callTime\":1632910156875,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632910156883,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uuid1\":\"123456\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (10, 1632910480, 1632910480, 1, 231115693053968384, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632910280083,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632910277703,\"bridgeTime\":1632910280083,\"callId\":231115693053968384,\"callTime\":1632910275146,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"8315822092400358\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632910479103,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210929/18/231115693053968384_8315822092400358.wav\",\"recordTime\":1632910277943,\"ringEndTime\":1632910277703,\"ringStartTime\":1632910275463,\"talkTime\":201400},{\"agentKey\":\"1001@test\",\"answerTime\":1632910280003,\"bridgeTime\":1632910280083,\"callId\":231115693053968384,\"callTime\":1632910278436,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"9840924108367257\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632910479043,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1632910280003,\"ringStartTime\":1632910278003,\"sipStatus\":\"200\",\"talkTime\":199040}],\"callId\":231115693053968384,\"callTime\":1632910275146,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632910479103,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210929/18/231115693053968384_8315822092400358.wav\",\"recordTime\":1632910277943,\"talkTime\":199020,\"taskId\":\"0\",\"uuid1\":\"123456\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (11, 1632911019, 1632911019, 1, 231118812110389248, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231118812110389248,\"callTime\":1632911018787,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"6078443517464311\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632911018803,\"hangupCause\":\"UNALLOCATED_NUMBER\",\"recordTime\":\"0\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231118812110389248,\"callTime\":1632911018787,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632911018803,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"uuid1\":\"123456\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (12, 1632914010, 1632914010, 1, 231130927470739456, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632913923683,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632913921283,\"bridgeTime\":1632913923683,\"callId\":231130927470739456,\"callTime\":1632913907314,\"called\":\"\",\"caller\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"9148049418066542\",\"deviceType\":1,\"endTime\":1632914008283,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210929/19/231130927470739456_9148049418066542.wav\",\"recordTime\":1632913921563,\"ringEndTime\":1632913921283,\"ringStartTime\":1632913907823,\"talkTime\":87000},{\"agentKey\":\"1001@test\",\"answerTime\":1632913923614,\"bridgeTime\":1632913923683,\"callId\":231130927470739456,\"callTime\":1632913922255,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"6242482573028688\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632914010483,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1632913923614,\"ringStartTime\":1632913921583,\"talkTime\":86869}],\"callId\":231130927470739456,\"callTime\":1632913907314,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632914010483,\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210929/19/231130927470739456_9148049418066542.wav\",\"recordTime\":1632913921563,\"talkTime\":86800,\"taskId\":\"0\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (13, 1632914338, 1632914338, 1, 231131868886466560, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632914137244,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632914135083,\"bridgeTime\":1632914137244,\"callId\":231131868886466560,\"callTime\":1632914131765,\"called\":\"\",\"caller\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"9609868889619851\",\"deviceType\":1,\"endTime\":1632914337263,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210929/19/231131868886466560_9609868889619851.wav\",\"recordTime\":1632914135123,\"ringEndTime\":1632914135083,\"ringStartTime\":1632914132103,\"talkTime\":202180},{\"agentKey\":\"1001@test\",\"answerTime\":1632914137183,\"bridgeTime\":1632914137244,\"callId\":231131868886466560,\"callTime\":1632914135640,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"1226988604888801\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632914337224,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1632914137183,\"ringStartTime\":1632914135143,\"sipStatus\":\"200\",\"talkTime\":200041}],\"callId\":231131868886466560,\"callTime\":1632914131765,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632914337263,\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210929/19/231131868886466560_9609868889619851.wav\",\"recordTime\":1632914135123,\"talkTime\":200019,\"taskId\":\"0\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (14, 1632985520, 1632985520, 1, 231430926293270528, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632985438270,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632985435823,\"bridgeTime\":1632985438270,\"callId\":231430926293270528,\"callTime\":1632985432608,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"6212247736024608\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632985516183,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210930/15/231430926293270528_6212247736024608.wav\",\"recordTime\":1632985436129,\"ringEndTime\":1632985435823,\"ringStartTime\":1632985432983,\"talkTime\":80360},{\"agentKey\":\"1001@test\",\"answerTime\":1632985438183,\"bridgeTime\":1632985438270,\"callId\":231430926293270528,\"callTime\":1632985436601,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"3964383431997053\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632985518445,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1632985438183,\"ringStartTime\":1632985436151,\"talkTime\":80262}],\"callId\":231430926293270528,\"callTime\":1632985432608,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632985518445,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210930/15/231430926293270528_6212247736024608.wav\",\"recordTime\":1632985436129,\"talkTime\":80175,\"taskId\":\"0\",\"uuid1\":\"123456\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (15, 1632985735, 1632985735, 1, 231431330783559680, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632985533683,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632985531523,\"bridgeTime\":1632985533683,\"callId\":231431330783559680,\"callTime\":1632985529045,\"called\":\"871556590425001\",\"caller\":\"1001\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"9599129366585206\",\"deviceType\":1,\"display\":\"1001\",\"endTime\":1632985733203,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210930/15/231431330783559680_9599129366585206.wav\",\"recordTime\":1632985531563,\"ringEndTime\":1632985531523,\"ringStartTime\":1632985529383,\"talkTime\":201680},{\"agentKey\":\"1001@test\",\"answerTime\":1632985533603,\"bridgeTime\":1632985533683,\"callId\":231431330783559680,\"callTime\":1632985532105,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"3465623380199733\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632985733123,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1632985533603,\"ringStartTime\":1632985531583,\"sipStatus\":\"200\",\"talkTime\":199520}],\"callId\":231431330783559680,\"callTime\":1632985529045,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"caller\":\"871556590425001\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632985733203,\"followData\":\"{\\\"autoAnswer\\\":\\\"true\\\"}\",\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210930/15/231431330783559680_9599129366585206.wav\",\"recordTime\":1632985531563,\"talkTime\":199520,\"taskId\":\"0\",\"uuid1\":\"123456\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (16, 1632985974, 1633003200, 1, 231432538554368000, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1632985821943,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1632985821883,\"bridgeTime\":1632985821943,\"callId\":231432538554368000,\"callTime\":1632985820352,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"7660922292631535\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1632985973583,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1632985821883,\"ringStartTime\":1632985819863,\"talkTime\":151700},{\"agentKey\":\"1001@test\",\"answerTime\":1632985819803,\"bridgeTime\":1632985821943,\"callId\":231432538554368000,\"callTime\":1632985817000,\"called\":\"\",\"caller\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"9538211281826186\",\"deviceType\":1,\"endTime\":1632985973443,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20210930/15/231432538554368000_9538211281826186.wav\",\"recordTime\":1632985819843,\"ringEndTime\":1632985819803,\"ringStartTime\":1632985817283,\"talkTime\":153640}],\"callId\":231432538554368000,\"callTime\":1632985817000,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1632985973583,\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20210930/15/231432538554368000_9538211281826186.wav\",\"recordTime\":1632985819843,\"talkTime\":151640,\"taskId\":\"0\",\"waitTime\":\"0\"}', 2, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (17, 1633044577, 1633044577, 1, 231678997967142912, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":0,\"answerFlag\":1,\"answerTime\":\"0\",\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":\"0\",\"bridgeTime\":\"0\",\"callId\":231678997967142912,\"callTime\":1633044577504,\"called\":\"\",\"caller\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"7203660327811826\",\"deviceType\":1,\"endTime\":1633044577524,\"hangupCause\":\"UNALLOCATED_NUMBER\",\"recordTime\":\"0\",\"ringEndTime\":\"0\",\"ringStartTime\":\"0\",\"sipStatus\":\"404\",\"talkTime\":\"0\"}],\"callId\":231678997967142912,\"callTime\":1633044577504,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1633044577524,\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":1,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"recordTime\":\"0\",\"talkTime\":\"0\",\"taskId\":\"0\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (18, 1633044911, 1633044911, 1, 231679517884678144, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1633044709203,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1633044709123,\"bridgeTime\":1633044709203,\"callId\":231679517884678144,\"callTime\":1633044707388,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"7305647598479194\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1633044909163,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1633044709123,\"ringStartTime\":1633044707124,\"sipStatus\":\"200\",\"talkTime\":200040},{\"agentKey\":\"1001@test\",\"answerTime\":1633044706843,\"bridgeTime\":1633044709203,\"callId\":231679517884678144,\"callTime\":1633044701461,\"called\":\"\",\"caller\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"8230236100102885\",\"deviceType\":1,\"endTime\":1633044909243,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20211001/07/231679517884678144_8230236100102885.wav\",\"recordTime\":1633044707083,\"ringEndTime\":1633044706843,\"ringStartTime\":1633044701803,\"talkTime\":202400}],\"callId\":231679517884678144,\"callTime\":1633044701461,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1633044909243,\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20211001/07/231679517884678144_8230236100102885.wav\",\"recordTime\":1633044707083,\"talkTime\":200040,\"taskId\":\"0\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
INSERT INTO `cc_push_fail_log` VALUES (19, 1633045162, 1633045162, 1, 231680569577046016, 'http://115.159.101.178:7100/index', '{\"agentKey\":\"1001@test\",\"answerCount\":2,\"answerFlag\":\"0\",\"answerTime\":1633044960885,\"botId\":\"0\",\"callDeviceList\":[{\"agentKey\":\"1001@test\",\"answerTime\":1633044958683,\"bridgeTime\":1633044960885,\"callId\":231680569577046016,\"callTime\":1633044952204,\"called\":\"\",\"caller\":\"\",\"cdrType\":2,\"channelName\":\"sofia/external/871556590425001@172.17.0.2:6685\",\"deviceId\":\"8372525040413073\",\"deviceType\":1,\"endTime\":1633045160343,\"hangupCause\":\"NORMAL_CLEARING\",\"record\":\"20211001/07/231680569577046016_8372525040413073.wav\",\"recordTime\":1633044958743,\"ringEndTime\":1633044958683,\"ringStartTime\":1633044952703,\"talkTime\":201660},{\"agentKey\":\"1001@test\",\"answerTime\":1633044960783,\"bridgeTime\":1633044960885,\"callId\":231680569577046016,\"callTime\":1633044959262,\"called\":\"14400010002\",\"caller\":\"01088889999\",\"cdrType\":2,\"channelName\":\"sofia/external/14400010002@172.17.0.2:32460\",\"deviceId\":\"6909786047898272\",\"deviceType\":2,\"display\":\"01088889999\",\"endTime\":1633045160283,\"hangupCause\":\"NORMAL_CLEARING\",\"recordTime\":\"0\",\"ringEndTime\":1633044960783,\"ringStartTime\":1633044958763,\"sipStatus\":\"200\",\"talkTime\":199500}],\"callId\":231680569577046016,\"callTime\":1633044952204,\"callType\":\"OUTBOUNT_CALL\",\"called\":\"14400010002\",\"calledDisplay\":\"01088889999\",\"callerDisplay\":\"1001\",\"companyId\":1,\"direction\":\"OUTBOUND\",\"endTime\":1633045160343,\"fristQueueTime\":\"0\",\"groupId\":1,\"hangupDir\":2,\"ivrId\":\"0\",\"loginType\":1,\"media\":\"http:///172.17.0.2:9000\",\"queueEndTime\":\"0\",\"queueStartTime\":\"0\",\"record\":\"20211001/07/231680569577046016_8372525040413073.wav\",\"recordTime\":1633044958743,\"talkTime\":199458,\"taskId\":\"0\",\"waitTime\":\"0\"}', 1, '{\"code\":0,\"message\":\"success\",\"data\":null}', 2);
COMMIT;

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
  `route_num` varchar(32) NOT NULL DEFAULT '' COMMENT '字冠号码',
  `num_max` int NOT NULL DEFAULT '0' COMMENT '最长',
  `num_min` int NOT NULL DEFAULT '0' COMMENT '最短',
  `caller_change` int NOT NULL DEFAULT '0' COMMENT '主叫替换规则',
  `caller_change_num` varchar(32) NOT NULL DEFAULT '' COMMENT '替换号码',
  `called_change` int NOT NULL DEFAULT '0' COMMENT '被叫替换规则',
  `called_change_num` varchar(32) NOT NULL DEFAULT '' COMMENT '替换号码',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `unq_idx_route` (`company_id`,`route_num`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字冠路由表';

-- ----------------------------
-- Records of cc_route_call
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_call` VALUES (1, 1, 1, 1, 1, '87', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` VALUES (2, 1, 1, 1, 2, '133', 9, 4, 0, '1 ', 1, '1', 1);
INSERT INTO `cc_route_call` VALUES (4, 1, 1, 1, 1, '18899998889', 9, 4, 0, '1', 1, '1', 1);
INSERT INTO `cc_route_call` VALUES (7, 1, 1, 1, 4, '18899998887', 9, 4, 0, '1', 0, '1', 1);
INSERT INTO `cc_route_call` VALUES (8, 1, 1, 1, 5, '144', 11, 4, 0, '1', 0, '', 1);
INSERT INTO `cc_route_call` VALUES (9, 1621915277, 1621923862, 1, 2, '18988889999', 32, 2, 0, '1', 0, '1', 0);
INSERT INTO `cc_route_call` VALUES (10, 1621923239, 0, 1, 1, '1889999888999', 9, 4, 0, '1', 1, '1', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_getway
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_getway`;
CREATE TABLE `cc_route_getway` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '号码',
  `media_host` varchar(255) NOT NULL DEFAULT '' COMMENT '媒体地址',
  `media_port` int NOT NULL DEFAULT '0' COMMENT '媒体端口',
  `caller_prefix` varchar(255) NOT NULL DEFAULT '' COMMENT '主叫号码前缀',
  `called_prefix` varchar(255) NOT NULL DEFAULT '' COMMENT '被叫号码前缀',
  `profile` varchar(255) NOT NULL DEFAULT '' COMMENT 'fs的context规则',
  `sip_header1` varchar(255) NOT NULL DEFAULT '' COMMENT 'sip头1',
  `sip_header2` varchar(255) NOT NULL DEFAULT '' COMMENT 'sip头2',
  `sip_header3` varchar(255) NOT NULL DEFAULT '' COMMENT 'sip头3',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uindex_getway_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='媒体网关表';

-- ----------------------------
-- Records of cc_route_getway
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_getway` VALUES (1, 1604503580, 1604503580, '87', '172.17.0.2', 6685, '', '', 'external', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (2, 1604503580, 1604503580, '133', '192.168.180.37', 5080, '', '', 'external', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (3, 1604503580, 1604503580, 'auto87', '192.168.180.226', 38915, '', '', 'external', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (5, 1604503580, 1621856137, '1441', '172.17.0.2', 32460, '', '', 'external', 'P_CALL_ID:A4', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (14, 1621850816, 0, '183clpss', '192.168.177.183', 1111, '', '', 'external', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (16, 1621851435, 1621851612, '183clps1s-delRxNYpt', '192.168.177.183-deljefAWM', 1111, '', '', 'external', '', '', '', 0);
INSERT INTO `cc_route_getway` VALUES (17, 1621856177, 0, '183clps1s', '192.168.177.183', 1111, '', '', 'external', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (21, 1621921932, 0, '183clps1s2', '192.168.177.183', 11111, '', '', 'external', '', '', '', 1);
INSERT INTO `cc_route_getway` VALUES (22, 1627392056, 0, '183clpsa1s2', '192.168.177.183', 11111, '', '', 'external', '', '', '', 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由字冠关联组表';

-- ----------------------------
-- Records of cc_route_getway_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_getway_group` VALUES (1, 1, 1, 1, 1);
INSERT INTO `cc_route_getway_group` VALUES (2, 1, 1, 2, 2);
INSERT INTO `cc_route_getway_group` VALUES (3, 1, 1, 5, 3);
INSERT INTO `cc_route_getway_group` VALUES (4, 1, 1, 7, 4);
INSERT INTO `cc_route_getway_group` VALUES (5, 1, 1, 5, 5);
INSERT INTO `cc_route_getway_group` VALUES (9, 1622004752, 0, 1, 7);
INSERT INTO `cc_route_getway_group` VALUES (10, 1622004752, 0, 2, 7);
INSERT INTO `cc_route_getway_group` VALUES (11, 1622004752, 0, 3, 7);
COMMIT;

-- ----------------------------
-- Table structure for cc_route_group
-- ----------------------------
DROP TABLE IF EXISTS `cc_route_group`;
CREATE TABLE `cc_route_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `route_group` varchar(255) NOT NULL DEFAULT '' COMMENT '网关组',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='网关组';

-- ----------------------------
-- Records of cc_route_group
-- ----------------------------
BEGIN;
INSERT INTO `cc_route_group` VALUES (1, 1, 1, '87路由', 1);
INSERT INTO `cc_route_group` VALUES (2, 1, 1, '133路由', 1);
INSERT INTO `cc_route_group` VALUES (3, 1, 1, '871556590425003', 1);
INSERT INTO `cc_route_group` VALUES (4, 1, 1, '18899998887', 1);
INSERT INTO `cc_route_group` VALUES (5, 1, 1, '144路由', 1);
INSERT INTO `cc_route_group` VALUES (7, 0, 1622004752, '网关组2', 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_schedule_config
-- ----------------------------
DROP TABLE IF EXISTS `cc_schedule_config`;
CREATE TABLE `cc_schedule_config` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `name` varchar(20) NOT NULL DEFAULT '' COMMENT '日程名称',
  `level_value` int NOT NULL DEFAULT '1' COMMENT '优先级',
  `type` int NOT NULL DEFAULT '1' COMMENT '1:指定时间,2:相对时间',
  `start_day` varchar(20) NOT NULL DEFAULT '' COMMENT '开始日期',
  `end_day` varchar(20) NOT NULL DEFAULT '' COMMENT '结束日期',
  `start_time` varchar(20) NOT NULL DEFAULT '' COMMENT '开始时间',
  `end_time` varchar(20) NOT NULL DEFAULT '' COMMENT '结束时间',
  `mon` int NOT NULL DEFAULT '1' COMMENT '周一',
  `tue` int NOT NULL DEFAULT '1' COMMENT '周二',
  `wed` int NOT NULL DEFAULT '1' COMMENT '周三',
  `thu` int NOT NULL DEFAULT '1' COMMENT '周四',
  `fri` int NOT NULL DEFAULT '1' COMMENT '周五',
  `sat` int NOT NULL DEFAULT '1' COMMENT '周六',
  `sun` int NOT NULL DEFAULT '1' COMMENT '周天',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='日程表';

-- ----------------------------
-- Records of cc_schedule_config
-- ----------------------------
BEGIN;
INSERT INTO `cc_schedule_config` VALUES (1, 1, 1, 1, '全天日程', 3, 1, '2020-12-01', '2020-12-30', '', '', 1, 1, 1, 1, 1, 1, 1, 1);
INSERT INTO `cc_schedule_config` VALUES (2, 2, 2, 1, '上班时间', 2, 1, '2020-12-01', '2020-12-30', '09:00:00', '18:00:00', 1, 1, 1, 1, 1, 0, 0, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_skill
-- ----------------------------
DROP TABLE IF EXISTS `cc_skill`;
CREATE TABLE `cc_skill` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `remark` varchar(255) NOT NULL DEFAULT '' COMMENT '备注',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能表';

-- ----------------------------
-- Records of cc_skill
-- ----------------------------
BEGIN;
INSERT INTO `cc_skill` VALUES (1, 1, 1, '1', '1', 1, 1);
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
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='坐席技能表';

-- ----------------------------
-- Records of cc_skill_agent
-- ----------------------------
BEGIN;
INSERT INTO `cc_skill_agent` VALUES (1, 1, 1, 1, 1, 1, 100, 1);
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
  `skill_id` bigint NOT NULL DEFAULT '0' COMMENT '技能ID',
  `group_id` bigint NOT NULL DEFAULT '0' COMMENT '技能组ID',
  `rank_about` int NOT NULL DEFAULT '1' COMMENT '等级类型(1:全部,2:等于,3:>,4:<,5:介于)',
  `rank_value_start` int NOT NULL DEFAULT '0' COMMENT '介于的开始值',
  `rank_value` int NOT NULL DEFAULT '1' COMMENT '等级值',
  `rate` int NOT NULL DEFAULT '100' COMMENT '占用率',
  `status` int NOT NULL DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_group_id` (`group_id`) USING BTREE,
  KEY `id` (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='技能组技能表';

-- ----------------------------
-- Records of cc_skill_group
-- ----------------------------
BEGIN;
COMMIT;

-- ----------------------------
-- Table structure for cc_stat_hour_agent_work
-- ----------------------------
DROP TABLE IF EXISTS `cc_stat_hour_agent_work`;
CREATE TABLE `cc_stat_hour_agent_work` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT 'cts',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业id',
  `agent_key` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席编号',
  `agent_name` varchar(255) NOT NULL DEFAULT '' COMMENT '坐席名称',
  `state_time` bigint NOT NULL DEFAULT '0' COMMENT '统计时间',
  `login_time` bigint NOT NULL DEFAULT '0' COMMENT '登录总时长',
  `ready_time` bigint NOT NULL DEFAULT '0' COMMENT '空闲总时长',
  `not_ready_time` bigint NOT NULL DEFAULT '0' COMMENT '忙碌总时长',
  `busy_time` bigint NOT NULL DEFAULT '0' COMMENT '自定义忙碌总时间',
  `after_time` bigint NOT NULL DEFAULT '0' COMMENT '话后总时长',
  `talk_time` bigint NOT NULL DEFAULT '0' COMMENT '通话总时长',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uniq_idx_state_agenthourwork_agent` (`state_time`,`agent_key`),
  FULLTEXT KEY `idx_state_agenthourwork_agent` (`agent_key`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb3;

-- ----------------------------
-- Records of cc_stat_hour_agent_work
-- ----------------------------
BEGIN;
INSERT INTO `cc_stat_hour_agent_work` VALUES (1, 1633057200040, 1, '1001@test', '', 1633053600040, 2154438, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` VALUES (2, 1633082400043, 1, '1001@test', '', 1633078800043, 0, 0, 0, 0, 1031806, 200888, 1);
INSERT INTO `cc_stat_hour_agent_work` VALUES (3, 1633683600033, 1, '1001@test', '', 1633680000033, 3085366, 0, 0, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` VALUES (4, 1633687200034, 1, '1001@test', '', 1633683600034, 3577995, 9196, 344, 0, 0, 0, 1);
INSERT INTO `cc_stat_hour_agent_work` VALUES (5, 1633690800033, 1, '1001@test', '', 1633687200033, 24009, 0, 0, 0, 0, 0, 1);
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
  `username` varchar(255) NOT NULL DEFAULT '',
  `pwd` varchar(255) NOT NULL DEFAULT '',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='站点类型配置信息';

-- ----------------------------
-- Records of cc_station
-- ----------------------------
BEGIN;
INSERT INTO `cc_station` VALUES (1, 1, 1, 1000, 1, 'A', '115.159.101.178', 7100, '', '', 1);
INSERT INTO `cc_station` VALUES (2, 2, 2, 2000, 2, 'A', '115.159.101.178', 7200, '', '', 1);
INSERT INTO `cc_station` VALUES (3, 1, 1, 3000, 3, 'A', '115.159.101.178', 7300, '', '', 1);
INSERT INTO `cc_station` VALUES (4, 1, 1, 4000, 4, 'A', '115.159.101.178', 7400, '', 'zhongweixian@gmail.com', 1);
INSERT INTO `cc_station` VALUES (5, 1, 1, 2100, 2, 'A', '113.57.114.164', 7300, '', '', 1);
INSERT INTO `cc_station` VALUES (6, 0, 0, 0, 0, '', '', 0, '', '', 1);
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
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT 'vdn名称',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='呼入路由表';

-- ----------------------------
-- Records of cc_vdn_code
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_code` VALUES (3, 3, 3, 1, '01011515901', 1);
INSERT INTO `cc_vdn_code` VALUES (4, 4, 4, 1, '01011515902', 1);
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
  `phone` varchar(20) NOT NULL DEFAULT '' COMMENT '特服号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uni_idx_phone` (`vdn_id`,`company_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由号码表';

-- ----------------------------
-- Records of cc_vdn_phone
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_phone` VALUES (3, 1, 1, 1, 3, '01011515901', 1);
INSERT INTO `cc_vdn_phone` VALUES (4, 1, 1, 1, 4, '01011515902', 1);
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
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '子码日程',
  `vdn_id` bigint NOT NULL DEFAULT '0' COMMENT 'vdn_id',
  `schedule_id` bigint NOT NULL DEFAULT '0' COMMENT '日程id',
  `route_type` int NOT NULL DEFAULT '1' COMMENT '路由类型(1:技能组,2:放音,3:ivr,4:坐席,5:外呼)',
  `route_value` varchar(255) NOT NULL DEFAULT '0' COMMENT '路由类型值',
  `play_type` int NOT NULL DEFAULT '0' COMMENT '放音类型(1:按键导航,2:技能组,3:ivr,4:路由字码,5:挂机)',
  `play_value` bigint NOT NULL DEFAULT '0' COMMENT '放音类型对应值',
  `dtmf_end` varchar(255) NOT NULL DEFAULT '*' COMMENT '结束音',
  `retry` int NOT NULL DEFAULT '1' COMMENT '重复播放次数',
  `dtmf_max` int NOT NULL DEFAULT '1' COMMENT '最大收键长度',
  `dtmf_min` int NOT NULL DEFAULT '1' COMMENT '最小收键长度',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='路由字码表';

-- ----------------------------
-- Records of cc_vdn_schedule
-- ----------------------------
BEGIN;
INSERT INTO `cc_vdn_schedule` VALUES (1, 1, 1, 1, '呼入转技能组', 1, 1, 1, '1', 1, 0, '*', 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` VALUES (2, 2, 2, 1, '上班转技能组', 2, 1, 1, '1', 1, 0, '*', 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` VALUES (3, 3, 3, 1, '按键导航', 2, 2, 2, '1', 1, 1, '*', 1, 1, 1, 1);
INSERT INTO `cc_vdn_schedule` VALUES (4, 4, 4, 1, '按键导航', 3, 1, 2, '1', 1, 1, '*', 2, 3, 3, 1);
INSERT INTO `cc_vdn_schedule` VALUES (5, 5, 5, 1, '进技能组', 4, 1, 1, '1', 1, 1, '*', 1, 1, 1, 1);
COMMIT;

-- ----------------------------
-- Table structure for cc_vdn_schedule_dtmf
-- ----------------------------
DROP TABLE IF EXISTS `cc_vdn_schedule_dtmf`;
CREATE TABLE `cc_vdn_schedule_dtmf` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'PK',
  `cts` bigint NOT NULL DEFAULT '0' COMMENT '创建时间',
  `uts` bigint NOT NULL DEFAULT '0' COMMENT '修改时间',
  `company_id` bigint NOT NULL DEFAULT '0' COMMENT '企业ID',
  `navigate_id` bigint NOT NULL DEFAULT '0' COMMENT '按键导航ID',
  `dtmf` varchar(20) NOT NULL DEFAULT '1' COMMENT '按键',
  `route_type` int NOT NULL DEFAULT '0' COMMENT '路由类型(1:技能组,2:IVR,3:路由字码,4:坐席分机,5:挂机)',
  `route_value` bigint NOT NULL DEFAULT '0' COMMENT '路由值',
  `status` int DEFAULT '1' COMMENT '状态',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='按键导航表';


COMMIT;


